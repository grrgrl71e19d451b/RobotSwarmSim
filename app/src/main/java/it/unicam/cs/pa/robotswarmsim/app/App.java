package it.unicam.cs.pa.robotswarmsim.app;

import it.unicam.cs.pa.robotswarmsim.library.CommandsParser;
import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import it.unicam.cs.pa.robotswarmsim.library.robotcommand.RobotCommand;
import it.unicam.cs.pa.robotswarmsim.library.simulator.RobotSimulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The App class is the main class that starts the robot swarm simulation.
 * It reads command line parameters, loads or parses the environment and robot commands,
 * creates robots, and initiates the simulation.
 */
public class App {

    /**
     * The main method that starts the robot swarm simulation.
     *
     * @param args Command line arguments: <timePerInstruction> <simulationTime> <environmentType>
     *             <environmentPath> <programType> <programPath> <numberOfRobots>
     * @throws IOException              If an input/output error occurs while reading files.
     * @throws IllegalArgumentException If the provided arguments are insufficient or invalid.
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 7) {
            throw new IllegalArgumentException(
                    "Not enough arguments. Usage: <timePerInstruction> <simulationTime> <environmentType> <environmentPath> <programType> <programPath> <numberOfRobots>");
        }

        long timePerInstruction = Long.parseLong(args[0]);
        if (timePerInstruction <= 0) {
            throw new IllegalArgumentException("Time per instruction must be a positive number.");
        }

        long simulationTime = Long.parseLong(args[1]);
        if (simulationTime <= 0) {
            throw new IllegalArgumentException("Simulation time must be a positive number.");
        }
        String environmentType = args[2];
        if (!environmentType.equals("file") && !environmentType.equals("string")) {
            throw new IllegalArgumentException("Environment type must be 'file' or 'string'.");
        }

        String environmentPath = args[3];
        if (environmentType.equals("file") && isInvalidPath(environmentPath)) {
            throw new IllegalArgumentException("Environment path is not a valid file path.");
        }

        String programType = args[4];
        if (!programType.equals("file") && !programType.equals("string")) {
            throw new IllegalArgumentException("Program type must be 'file' or 'string'.");
        }

        String programPath = args[5];
        if (programType.equals("file") && isInvalidPath(programPath)) {
            throw new IllegalArgumentException("Program path is not a valid file path.");
        }

        int numberOfRobots = Integer.parseInt(args[6]);
        if (numberOfRobots < 0) {
            throw new IllegalArgumentException("Number of robots cannot be negative.");
        }

        // Load or parse environment and robot commands
        Environment environment = loadEnvironment(environmentType, environmentPath);
        List<RobotCommand> robotCommands = loadRobotCommands(programType, programPath);

        // Create and add robots to the environment
        List<Robot> robots = createRobots(numberOfRobots, environment, robotCommands);

        // Create a simulator and run the simulation
        RobotSimulator simulator = new RobotSimulator(robots);
        simulator.simulate(timePerInstruction, simulationTime);
    }

    /**
     * Loads or parses the environment based on the specified type and path.
     *
     * @param envType The type of environment data, either "file" or "string".
     * @param envPath The path to the environment data (file path if envType is "file", or raw data if envType is "string").
     * @return The Environment object representing the loaded or parsed environment.
     * @throws IOException If an input/output error occurs while reading files.
     */
    private static Environment loadEnvironment(String envType, String envPath) throws IOException {
        if (envType.equals("file")) {
            return loadEnvironmentFromFile(envPath);
        } else {
            return parseEnvironmentFromString(envPath);
        }
    }

    /**
     * Checks if the given path is a valid file path.
     *
     * @param path The path to be checked for validity.
     * @return True if the path is invalid, false if it is valid.
     */
    private static boolean isInvalidPath(String path) {
        try {
            Paths.get(path);
            return false; // Return false if the path is valid.
        } catch (InvalidPathException e) {
            return true; // Return true if the path is invalid.
        }
    }

    /**
     * Loads the environment from a file specified by the given file path.
     *
     * @param filePath The path to the file containing environment data.
     * @return The Environment object representing the loaded environment.
     * @throws IOException If an input/output error occurs while reading the file.
     */
    private static Environment loadEnvironmentFromFile(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));
        return parseEnvironmentFromString(content);
    }

    /**
     * Parses environment data from a string and returns the corresponding Environment object.
     *
     * @param environmentData The string containing environment data.
     * @return The Environment object representing the parsed environment.
     */
    private static Environment parseEnvironmentFromString(String environmentData) {
        CommandsParser handler = new CommandsParser();
        handler.processCommandString(environmentData, true);
        return handler.getEnvironment();
    }

    /**
     * Loads robot commands based on the specified program type and path.
     *
     * @param progType The type of the robot program, either "file" or "string".
     * @param progPath The path to the robot program file or the actual program string.
     * @return A list of RobotCommand objects representing the loaded robot program.
     * @throws IOException If an input/output error occurs while reading the file.
     */
    private static List<RobotCommand> loadRobotCommands(String progType, String progPath) throws IOException {
        if (progType.equals("file")) {
            return loadRobotProgramFromFile(progPath);
        } else {
            return parseRobotProgramFromString(progPath);
        }
    }

    /**
     * Loads robot commands from a file specified by the given file path.
     *
     * @param filePath The path to the file containing the robot program.
     * @return A list of RobotCommand objects representing the loaded robot program.
     * @throws IOException If an input/output error occurs while reading the file.
     */
    private static List<RobotCommand> loadRobotProgramFromFile(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));
        return parseRobotProgramFromString(content);
    }

    /**
     * Parses a robot program from the given string representation.
     *
     * @param programData The string representation of the robot program.
     * @return A list of RobotCommand objects parsed from the program data.
     */
    private static List<RobotCommand> parseRobotProgramFromString(String programData) {
        CommandsParser handler = new CommandsParser();
        handler.processCommandString(programData, false);
        return handler.getRobotCommands();
    }

    /**
     * Creates a specified number of robots with random initial positions and assigns them the given commands.
     *
     * @param numRobots   The number of robots to create.
     * @param environment The environment in which the robots will operate.
     * @param commands    The list of commands to assign to each robot.
     * @return A list of Robot objects representing the created robots.
     */
    private static List<Robot> createRobots(int numRobots, Environment environment, List<RobotCommand> commands) {
        List<Robot> robots = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numRobots; i++) {
            int x = random.nextInt(51); // Range from 0 to 50
            int y = random.nextInt(51); // Range from 0 to 50

            Robot robot = new Robot(new Point(x, y), environment);
            robot.setCommands(new ArrayList<>(commands)); // Each robot gets a copy of the commands
            robots.add(robot);
            environment.addRobot(robot);
        }

        return robots;
    }


}
