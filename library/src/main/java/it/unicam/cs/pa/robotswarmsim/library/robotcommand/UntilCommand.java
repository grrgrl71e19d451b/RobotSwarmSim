package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import java.util.List;

/**
 * This class represents a command that executes a list of commands until the robot enters a specific target area.
 * It implements the RobotCommand interface.
 */
public class UntilCommand implements RobotCommand {
    private final String targetLabel; // Target label for the Until command
    private final List<RobotCommand> commands;
    private int currentCommandIndex = 0;
    private boolean completed = false; // Manages the completion status of the command

    /**
     * Constructs a new UntilCommand with the specified target label and list of commands.
     *
     * @param targetLabel The target label the robot needs to enter for the command to complete.
     * @param commands    The list of commands to execute until the target is reached.
     */
    public UntilCommand(String targetLabel, List<RobotCommand> commands) {
        this.targetLabel = targetLabel;
        this.commands = commands;
    }

    /**
     * Executes the command by iterating through the list of commands until the target area is reached.
     *
     * @param robot The robot to execute the commands on.
     * @param dt    The time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {
        // Continue executing commands until completed
        if (!completed) {
            RobotCommand command = commands.get(currentCommandIndex);
            System.out.println("Executing internal command: " + command.getClass().getSimpleName());
            command.execute(robot, dt);
            currentCommandIndex++;

            // Reset index if it reaches the end of the commands list
            if (currentCommandIndex >= commands.size()) {
                currentCommandIndex = 0;
            }

            // Check if the robot has reached the target area
            if (robot.getCurrentAreaLabels().contains(targetLabel)) {
                System.out.println("Robot has reached the target area: " + targetLabel);
                completed = true;
            }
        } else {
            System.out.println("UntilCommand completed.");
        }
    }

    /**
     * Checks if this command is completed.
     *
     * @return true if completed, false otherwise.
     */
    public boolean isCompleted() {

        return completed;
    }


}
