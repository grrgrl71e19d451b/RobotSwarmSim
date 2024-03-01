package it.unicam.cs.pa.robotswarmsim.library.simulator;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import it.unicam.cs.pa.robotswarmsim.library.robotcommand.*;
import java.util.List;

/**
 * This class represents a simulator for robots that can execute a list of commands on multiple robots.
 */
public class RobotSimulator implements Simulator {

    private final List<Robot> robots;
    private int stepNumber = 0;

    /**
     * Constructs a new RobotSimulator with a list of robots.
     *
     * @param robots The list of robots to simulate.
     */
    public RobotSimulator(List<Robot> robots) {

        this.robots = robots;
    }

    /**
     * Simulates the behavior of all robots over a specified time period using a given time step.
     *
     * @param dt   The time step in seconds.
     * @param time The total simulation time in seconds.
     */
    @Override
    public void simulate(double dt, double time) {
        printInitialRobotInfo();

        // Iterate through the simulation time in discrete time steps
        for (double t = 0; t < time; t += dt) {
            try {
                // Pause the simulation for the specified time step
                Thread.sleep((long) (dt * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Interrupted while waiting: " + e.getMessage());
                return;
            }

            // Iterate through each robot to execute commands and print information
            for (Robot robot : robots) {
                // Check if all robots have completed their commands
                if (haveAllRobotsCompletedCommands()) {
                    System.out.println("All robots have completed their commands.");
                }

                // Execute commands for the current robot and print its information
                executeRobotCommands(robot, dt);
                printRobotInfo(robot, stepNumber);
            }

            stepNumber++;
        }
    }

    /**
     * Getter for stepNumber.
     *
     * @return The current step number.
     */
    public int getStepNumber() {

        return this.stepNumber;
    }
    /**
     * Checks if all robots have completed their commands.
     *
     * @return True if all robots have completed their commands, false otherwise.
     */
    private boolean haveAllRobotsCompletedCommands() {
        return robots.stream()
                .allMatch(robot -> robot.getCurrentCommandIndex() >= robot.getCommands().size());
    }

    /**
     * Executes the commands for a given robot based on the time step.
     *
     * @param robot The robot to execute commands on.
     * @param dt    The time step in seconds.
     */
    private void executeRobotCommands(Robot robot, double dt) {
        if (robot.getCurrentCommandIndex() < robot.getCommands().size()) {
            RobotCommand command = robot.getCommands().get(robot.getCurrentCommandIndex());

            // Execute different types of commands
            if (command instanceof UntilCommand untilCommand) {
                executeUntilCommand(robot, untilCommand, dt);
            } else if (command instanceof RepeatCommand repeatCommand) {
                executeRepeatCommand(robot, repeatCommand, dt);
            } else if (command instanceof ForeverCommand) {
                executeForeverCommand(robot, command, dt);
            } else if (command instanceof TimedCommand timedCommand) {
                executeTimedCommand(robot, timedCommand, dt);
            } else {
                executeRegularCommand(robot, command, dt);
            }
        }
    }

    /**
     * Executes the UntilCommand for the robot.
     *
     * @param robot        The robot to execute commands on.
     * @param untilCommand The UntilCommand to execute.
     * @param dt           The time step in seconds.
     */
    private void executeUntilCommand(Robot robot, UntilCommand untilCommand, double dt) {
        System.out.println("Executing command: UntilCommand");
        untilCommand.execute(robot, dt);

        // Check if UntilCommand is completed
        if (untilCommand.isCompleted()) {
            System.out.println("UntilCommand completed.");
            robot.incrementCommandIndex();
        }
    }

    /**
     * Executes the RepeatCommand for the robot.
     *
     * @param robot         The robot to execute commands on.
     * @param repeatCommand The RepeatCommand to execute.
     * @param dt            The time step in seconds.
     */
    private void executeRepeatCommand(Robot robot, RepeatCommand repeatCommand, double dt) {
        System.out.println("Executing command: RepeatCommand");
        repeatCommand.execute(robot, dt);

        // Check if RepeatCommand is completed
        if (repeatCommand.isCompleted()) {
            System.out.println("RepeatCommand completed.");
            robot.incrementCommandIndex();
        }
    }

    /**
     * Executes the ForeverCommand for the robot.
     *
     * @param robot   The robot to execute commands on.
     * @param command The ForeverCommand to execute.
     * @param dt      The time step in seconds.
     */
    private void executeForeverCommand(Robot robot, RobotCommand command, double dt) {
        System.out.println("Executing command: ForeverCommand");
        command.execute(robot, dt);
        // ForeverCommand repeats indefinitely, so it does not increment the command index
    }

    /**
     * Executes the TimedCommand for the robot.
     *
     * @param robot        The robot to execute commands on.
     * @param timedCommand The TimedCommand to execute.
     * @param dt           The time step in seconds.
     */
    private void executeTimedCommand(Robot robot, TimedCommand timedCommand, double dt) {
        System.out.println("Executing command: " + timedCommand.getClass().getSimpleName());
        timedCommand.execute(robot, dt);

        // Update the elapsed time after command execution
        double elapsedTime = robot.getTimedCommandTime();

        // Check if the command is completed after execution
        if (timedCommand.isCompleted(elapsedTime)) {
            System.out.println("TimedCommand completed.");
            robot.incrementCommandIndex();
            robot.resetTimedCommandTime();
        }
    }

    /**
     * Executes a regular command for the robot.
     *
     * @param robot   The robot to execute commands on.
     * @param command The regular command to execute.
     * @param dt      The time step in seconds.
     */
    private void executeRegularCommand(Robot robot, RobotCommand command, double dt) {
        System.out.println("Executing command: " + command.getClass().getSimpleName());
        command.execute(robot, dt);
        robot.incrementCommandIndex();
        System.out.println("Command " + command.getClass().getSimpleName() + " completed.");
    }

    /**
     * Prints information about the robot's state in the simulation.
     *
     * @param robot       The robot for which to print the information.
     * @param stepNumber  The current step number in the simulation.
     */
    private void printRobotInfo(Robot robot, int stepNumber) {
        System.out.println("--------------------------------------------------");
        System.out.println("Step " + (stepNumber + 1) + ": Current state of the robot:"); // Increment stepNumber by 1
        System.out.println(robot); // Use the toString() method of the Robot class
        System.out.println();
        System.out.println("--------------------------------------------------");
    }


    /**
     * Prints initial information about the robots' states.
     * This method displays the initial state of each robot randomly placed in the simulation.
     */
    private void printInitialRobotInfo() {
        System.out.println("--------------------------------------------------");
        for (Robot robot : robots) {
            System.out.println("Initial state of the robot randomly placed");
            System.out.println("--------------------------------------------------");
            System.out.println("Step 0: Initial state of the robot:");
            System.out.println(robot); // Use the toString() method of the Robot class for each robot
            System.out.println();
            System.out.println("--------------------------------------------------");
        }
    }

}
