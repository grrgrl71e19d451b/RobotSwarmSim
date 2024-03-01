package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

/**
 * The StopCommand allows a robot to stop its movement by setting its speed to 0.
 */
public class StopCommand implements RobotCommand {

    /**
     * Executes the stop command on the specified robot.
     * It sets the robot's speed to 0 to stop its movement.
     *
     * @param robot The robot on which to execute the command.
     * @param dt    The time step in seconds since the last execution (not used in this command).
     */
    @Override
    public void execute(Robot robot, double dt) {
        // Set the robot's speed to 0 to stop its movement.
        robot.setSpeed(0);

        // Since the robot stops immediately, its future position after dt
        // is the same as its current position.
    }
}
