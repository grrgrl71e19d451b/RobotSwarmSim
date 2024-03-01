package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import java.util.Random;

/**
 * The MoveRandomCommand allows a robot to move to a random position within a specified area at a given speed.
 */
public class MoveRandomCommand implements RobotCommand {
    private final double x1, x2, y1, y2; // Defines the rectangular area boundaries
    private final double speed; // The speed of the movement in meters per second
    private final Random random = new Random(); // Random number generator

    /**
     * Creates a new MoveRandomCommand with the specified area boundaries and speed.
     *
     * @param x1     The minimum x-coordinate of the area.
     * @param x2     The maximum x-coordinate of the area.
     * @param y1     The minimum y-coordinate of the area.
     * @param y2     The maximum y-coordinate of the area.
     * @param speed  The speed of the movement in meters per second.
     */
    public MoveRandomCommand(double x1, double x2, double y1, double y2, double speed) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.speed = speed;
    }

    /**
     * Executes the move command, moving the robot to a random position within the specified area at the given speed.
     *
     * @param robot The robot executing the command.
     * @param dt    The time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {
        // Choose a random target position within the defined area
        double xTarget = x1 + random.nextDouble() * (x2 - x1);
        double yTarget = y1 + random.nextDouble() * (y2 - y1);

        // Calculate the direction towards the target position
        double dx = xTarget - robot.getPosition().x();
        double dy = yTarget - robot.getPosition().y();
        double heading = Math.toDegrees(Math.atan2(dy, dx));

        // Calculate the distance the robot can travel in dt
        double distance = speed * dt;

        // Calculate the new displacement in x and y directions
        double deltaX = distance * Math.cos(Math.toRadians(heading));
        double deltaY = distance * Math.sin(Math.toRadians(heading));

        // Calculate the new position
        Point newPosition = new Point(robot.getPosition().x() + deltaX, robot.getPosition().y() + deltaY);

        // Update the position, heading, and speed of the robot.
        robot.setPosition(newPosition);
        robot.setHeading(heading);
        robot.setSpeed(speed);

    }
}
