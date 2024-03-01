package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

/**
 * This command represents a continuous movement command for a robot for a specified duration.
 */
public class ContinueCommand extends TimedCommand {

    /**
     * Creates a new instance of the ContinueCommand with the specified duration.
     *
     * @param duration The duration of the command in seconds.
     */
    public ContinueCommand(double duration) {

        super(duration);
    }

    /**
     * Performs the continuous movement command on the specified robot for the given time.
     *
     * @param robot          The robot on which the command is executed.
     * @param timeToExecute  The time in seconds for which the command is executed.
     */
    @Override
    protected void performCommand(Robot robot, double timeToExecute) {
        System.out.println("Updating position for " + timeToExecute + " seconds.");

        // Calculate the distance traveled based on the current speed and time.
        double distance = robot.getSpeed() * timeToExecute;

        // Update the robot's position using the calculated distance and current heading.
        double deltaX = distance * Math.cos(Math.toRadians(robot.getHeading()));
        double deltaY = distance * Math.sin(Math.toRadians(robot.getHeading()));
        Point newPosition = new Point(robot.getPosition().x() + deltaX, robot.getPosition().y() + deltaY);

        robot.setPosition(newPosition);
    }

}
