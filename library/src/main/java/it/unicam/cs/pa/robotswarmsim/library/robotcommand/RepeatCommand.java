package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

import java.util.List;

/**
 * The RepeatCommand allows a set of robot commands to be repeated a specified number of times.
 */
public class RepeatCommand implements RobotCommand {
    private final int times; // The number of times to repeat the commands
    private final List<RobotCommand> commands; // The list of commands to repeat
    private int currentIteration = 0; // The current iteration count
    private int currentCommandIndex = 0; // The current index of the command to execute

    /**
     * Creates a new RepeatCommand with the specified number of times and a list of commands to repeat.
     *
     * @param times    The number of times to repeat the commands.
     * @param commands The list of commands to repeat.
     */
    public RepeatCommand(int times, List<RobotCommand> commands) {
        this.times = times;
        this.commands = commands;
    }

    /**
     * Executes the repeat command, repeating the specified list of commands a given number of times.
     *
     * @param robot The robot executing the command.
     * @param dt    The time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {
        // Check if the current iteration is within the specified limit.
        if (currentIteration < times) {
            // Check if there are commands to execute in the list.
            if (currentCommandIndex < commands.size()) {
                // Retrieve the current command from the list, print its name, and execute it on the robot.
                RobotCommand command = commands.get(currentCommandIndex);
                System.out.println("Executing internal command: " + command.getClass().getSimpleName());

                command.execute(robot, dt);
                currentCommandIndex++;

                // Check if all commands in the list have been executed for the current iteration.
                if (currentCommandIndex >= commands.size()) {
                    currentCommandIndex = 0; // Reset the command index for the next iteration.
                    currentIteration++; // Move to the next iteration.
                }
            }
        } else {
            System.out.println("RepeatCommand completed");
        }
    }


    /**
     * Checks if the RepeatCommand has completed all iterations.
     *
     * @return true if all iterations are completed, false otherwise.
     */
    public boolean isCompleted() {

        return currentIteration >= times;
    }
}
