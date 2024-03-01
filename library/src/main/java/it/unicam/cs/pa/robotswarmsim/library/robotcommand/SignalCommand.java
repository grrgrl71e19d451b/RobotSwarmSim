package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

/**
 * The SignalCommand allows a robot to emit a specific signal.
 * This signal can be used for communication with other robots or to indicate a specific state.
 */
public class SignalCommand implements RobotCommand {
    private final String signal; // The signal to emit

    /**
     * Constructs a new SignalCommand with the specified signal.
     *
     * @param signal The signal that the robot should emit.
     */
    public SignalCommand(String signal) {

        this.signal = signal;
    }

    /**
     * Executes the signal emission command on the specified robot.
     * The robot will emit the signal specified during the creation of the command.
     *
     * @param robot The robot on which to execute the command.
     * @param dt    The time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {
        // Emit the specified signal through the robot
        robot.signalLabel(signal);
    }
}
