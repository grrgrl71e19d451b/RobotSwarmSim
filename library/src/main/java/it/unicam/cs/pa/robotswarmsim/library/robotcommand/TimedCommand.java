package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

/**
 * The TimedCommand is an abstract class for commands with a specific execution time.
 */
public abstract class TimedCommand implements RobotCommand {
    private final double executionTime;

    /**
     * Constructs a new TimedCommand with the specified execution time.
     *
     * @param executionTime The time in seconds for which the command should execute.
     */
    public TimedCommand(double executionTime) {

        this.executionTime = executionTime;
    }

    /**
     * Executes the timed command on the specified robot, controlling completion with the robot's elapsed time.
     * It performs the command for the remaining time or the specified time step, whichever is smaller.
     *
     * @param robot The robot on which to execute the command.
     * @param dt    The time step in seconds since the last execution.
     */
    @Override
    public void execute(Robot robot, double dt) {
        // Check if the command is not completed based on the robot's elapsed time
        if (!isCompleted(robot.getTimedCommandTime())) {
            double timeLeft = executionTime - robot.getTimedCommandTime();
            double timeToExecute = Math.min(dt, timeLeft);

            // Perform the timed command for the calculated time
            performCommand(robot, timeToExecute);

            // Update the robot's elapsed time for the timed command
            robot.incrementTimedCommandTime(timeToExecute);

            System.out.println("Elapsed time in TimedCommand: " + robot.getTimedCommandTime() + " seconds.");
        }
    }


    /**
     * Performs the specific command logic for the given robot and time step.
     *
     * @param robot The robot on which to perform the command.
     * @param dt    The time step in seconds for the command execution.
     */
    protected abstract void performCommand(Robot robot, double dt);

    /**
     * Checks if the command is completed based on the elapsed time.
     *
     * @param elapsedTime The time elapsed since the command started.
     * @return true if the command is completed, false otherwise.
     */
    public boolean isCompleted(double elapsedTime) {

        return elapsedTime >= executionTime;
    }
}
