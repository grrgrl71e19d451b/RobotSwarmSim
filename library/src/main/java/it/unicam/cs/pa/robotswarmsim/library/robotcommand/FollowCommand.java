package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * The FollowCommand allows a robot to follow another robot based on a specific label.
 * If it doesn't find a robot with the specified label, it will move randomly within a given distance.
 */
public class FollowCommand implements RobotCommand {
    private final String label;
    private final double distance;
    private final double speed;

    /**
     * Creates a new FollowCommand.
     *
     * @param label    The label to look for in other robots.
     * @param distance The maximum distance within which to search for other robots.
     * @param speed    The speed at which to move towards the target robot.
     */
    public FollowCommand(String label, double distance, double speed) {
        this.label = label;
        this.distance = distance;
        this.speed = speed;
    }

    /**
     * Executes the "follow" command, adjusting the robot's heading and speed based on nearby signaling robots.
     *
     * @param robot The robot executing the command.
     * @param dt    Time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {
        // Retrieve nearby robots signaling the specified label.
        List<Robot> signalingRobots = robot.getRobotsWithinRange(distance)
                .stream()
                .filter(r -> r.isSignaling(label))
                .collect(Collectors.toList());

        // Determine the target position for the robot.
        Point targetPosition = getTargetPosition(robot, signalingRobots);

        // Calculate the new heading and adjust the robot's position.
        double newHeading = calculateHeading(robot.getPosition(), targetPosition);

        robot.setHeading(newHeading);
        robot.setSpeed(speed);
        moveRobot(robot, dt, newHeading);
        printSignaledRobots(signalingRobots, robot);
    }

    /**
     * Calculates the target position based on the signaling robots or chooses a random position if none are found.
     *
     * @param robot           The robot executing the command.
     * @param signalingRobots List of robots signaling the specified label.
     * @return The target position for the robot to move towards.
     */
    private Point getTargetPosition(Robot robot, List<Robot> signalingRobots) {
        if (!signalingRobots.isEmpty()) {
            return calculateAveragePosition(signalingRobots);
        } else {
            return getRandomTargetPosition(robot, distance);
        }
    }

    /**
     * Moves the robot based on its speed, heading, and the specified time step.
     *
     * @param robot      The robot to move.
     * @param dt         Time step in seconds since the last execution.
     * @param newHeading The new heading for the robot.
     */
    private void moveRobot(Robot robot, double dt, double newHeading) {
        // Calculate the distance and direction of movement based on the robot's speed and the elapsed time (dt).
        double distanceToMove = speed * dt;
        double deltaX = distanceToMove * Math.cos(Math.toRadians(newHeading));
        double deltaY = distanceToMove * Math.sin(Math.toRadians(newHeading));

        // Update the robot's position using the calculated changes in X and Y coordinates.
        Point newPosition = new Point(robot.getPosition().x() + deltaX, robot.getPosition().y() + deltaY);
        robot.setPosition(newPosition);
    }

    /**
     * Calculates and returns the average position among a list of robots.
     *
     * @param robots The list of robots for which to calculate the average position.
     * @return The calculated average position as a Point object.
     */
    private Point calculateAveragePosition(List<Robot> robots) {
        double avgX = robots.stream().mapToDouble(r -> r.getPosition().x()).average().orElse(0);
        double avgY = robots.stream().mapToDouble(r -> r.getPosition().y()).average().orElse(0);
        return new Point(avgX, avgY);
    }

    /**
     * Prints the IDs of robots emitting a specific signal.
     * This method is used to identify and display robots that have been detected as signal emitters.
     *
     * @param signalingRobots The list of robots emitting the signal.
     * @param executingRobot  The robot on which the command is executed.
     */
    private void printSignaledRobots(List<Robot> signalingRobots, Robot executingRobot) {
        System.out.println("Robot on which command was executed: Robot id: " + Integer.toHexString(System.identityHashCode(executingRobot)));
        System.out.println("Robots emitting the signal: ");
        for (Robot robot : signalingRobots) {
            System.out.println("Robot id: " + Integer.toHexString(System.identityHashCode(robot)));
        }
    }

    /**
     * Calculates the heading from a starting point to a target point.
     *
     * @param currentPosition The current position.
     * @param targetPosition  The target position.
     * @return The heading (in degrees) toward the target point.
     */
    private double calculateHeading(Point currentPosition, Point targetPosition) {
        double dx = targetPosition.x() - currentPosition.x();
        double dy = targetPosition.y() - currentPosition.y();
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    /**
     * Generates a random target position based on the robot's current position and distance limit.
     *
     * @param robot The robot executing the command.
     * @param bound The boundary for random movement.
     * @return A new Point object representing the random target position.
     */
    private Point getRandomTargetPosition(Robot robot, double bound) {
        Random random = new Random();
        double dx = (random.nextDouble() * 2 - 1) * bound; // Random value between -bound and +bound
        double dy = (random.nextDouble() * 2 - 1) * bound; // Random value between -bound and +bound
        return new Point(robot.getPosition().x() + dx, robot.getPosition().y() + dy);
    }
}
