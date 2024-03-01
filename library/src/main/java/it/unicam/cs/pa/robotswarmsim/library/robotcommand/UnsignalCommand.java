package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

/**
 * This class represents a command to remove a label from a robot.
 * It implements the RobotCommand interface.
 */
public class UnsignalCommand implements RobotCommand {
    private final String signal; // The label to remove

    /**
     * Creates a new UnsignalCommand object with the specified label.
     *
     * @param signal The label to remove from the robot.
     */
    public UnsignalCommand(String signal) {

        this.signal = signal;
    }

    /**
     * Executes the command to remove the specified label from the robot.
     *
     * @param robot The robot from which to remove the label.
     * @param dt    The time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {

        robot.removeLabel(signal);
    }
}
