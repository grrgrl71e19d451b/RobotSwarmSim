package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import it.unicam.cs.pa.robotswarmsim.library.Point;

/**
 * The MoveCommand allows a robot to move in a specified direction and speed.
 */
public class MoveCommand implements RobotCommand {
    private final double xDirection;
    private final double yDirection;
    private final double speed;

    /**
     * Creates a new MoveCommand with the specified direction and speed.
     *
     * @param xDirection The x-component of the direction (between -1 and 1).
     * @param yDirection The y-component of the direction (between -1 and 1).
     * @param speed      The speed of the movement in meters per second.
     * @throws IllegalArgumentException if the direction values are invalid.
     */
    public MoveCommand(double xDirection, double yDirection, double speed) {
        if ((xDirection == 0 && yDirection == 0) ||
                (xDirection < -1 || xDirection > 1) ||
                (yDirection < -1 || yDirection > 1)) {
            throw new IllegalArgumentException("Invalid direction values. x and y must be between -1 and 1, and at most one of them should be non-zero.");
        }
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.speed = speed;
    }

    /**
     * Executes the move command, moving the robot in the specified direction and speed.
     *
     * @param robot The robot executing the command.
     * @param dt    The time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {
        // Calculate the heading angle based on x and y components.
        double heading = Math.toDegrees(Math.atan2(yDirection, xDirection));

        // Calculate the distance the robot should travel in this dt.
        double distance = speed * dt;

        // Calculate the new displacement in x and y directions.
        double deltaX = distance * Math.cos(Math.toRadians(heading));
        double deltaY = distance * Math.sin(Math.toRadians(heading));

        // Calculate the new position for the robot.
        Point currentPosition = robot.getPosition();
        Point newPosition = new Point(currentPosition.x() + deltaX, currentPosition.y() + deltaY);

        // Update the robot's position, heading, and speed.
        robot.setPosition(newPosition);
        robot.setHeading(heading);
        robot.setSpeed(speed);

    }
    /**
     * Gets the x-direction component of this move command.
     * @return The x-direction value.
     */
    public double getXDirection() {

        return xDirection;
    }

    /**
     * Gets the y-direction component of this move command.
     * @return The y-direction value.
     */
    public double getYDirection() {

        return yDirection;
    }

    /**
     * Gets the speed of this move command.
     * @return The speed.
     */
    public double getSpeed() {

        return speed;
    }
}
