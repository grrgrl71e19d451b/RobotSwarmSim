package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

import java.util.List;

/**
 * The ForeverCommand allows a robot to execute a list of commands repeatedly in a loop.
 */
public class ForeverCommand implements RobotCommand {
    private final List<RobotCommand> commands;
    private int currentCommandIndex = 0;

    /**
     * Creates a new ForeverCommand with a list of commands to execute repeatedly.
     *
     * @param commands The list of commands to execute in a loop.
     */
    public ForeverCommand(List<RobotCommand> commands) {
        this.commands = commands;
    }

    /**
     * Repeatedly executes a list of commands in an endless loop.
     *
     * @param robot The robot executing the commands.
     * @param dt    Time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {
        if (currentCommandIndex < commands.size()) {
            RobotCommand command = commands.get(currentCommandIndex);
            System.out.println("Executing internal command: " + command.getClass().getSimpleName());
            command.execute(robot, dt);

            // Increment the index and check if it has exceeded the size of the list
            currentCommandIndex++;
            if (currentCommandIndex >= commands.size()) {
                currentCommandIndex = 0;
            }
        }
    }

}
