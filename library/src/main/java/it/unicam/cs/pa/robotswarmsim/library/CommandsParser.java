package it.unicam.cs.pa.robotswarmsim.library;

import it.unicam.cs.pa.robotswarmsim.utilities.FollowMeParser;
import it.unicam.cs.pa.robotswarmsim.utilities.FollowMeParserException;
import it.unicam.cs.pa.robotswarmsim.utilities.FollowMeParserHandler;
import it.unicam.cs.pa.robotswarmsim.library.robotcommand.*;
import it.unicam.cs.pa.robotswarmsim.library.area.Circle;
import it.unicam.cs.pa.robotswarmsim.library.area.Rectangle;
import it.unicam.cs.pa.robotswarmsim.utilities.ShapeData;

import java.util.*;

/**
 * This class parses and processes robot commands and environment data from a string.
 */
public class CommandsParser implements FollowMeParserHandler {

    private final Stack<List<RobotCommand>> commandStack = new Stack<>();
    private List<RobotCommand> currentCommands = new ArrayList<>();

    private final List<RobotCommand> commands = new ArrayList<>();

    private Environment environment = new Environment();
    private final FollowMeParser parser;

    /**
     * Constructs a new CommandsParser.
     */
    public CommandsParser() {

        this.parser = new FollowMeParser(this);
    }

    /**
     * Gets the list of parsed robot commands.
     *
     * @return The list of robot commands.
     */
    public List<RobotCommand> getRobotCommands() {

        return commands;
    }

    /**
     * Gets the parsed environment.
     *
     * @return The parsed environment.
     */
    public Environment getEnvironment() {

        return environment;
    }

    /**
     * Processes the input command string, distinguishing between environment and robot commands.
     * This method parses and handles commands based on the specified isEnvironment parameter.
     *
     * @param commandString  The input command string.
     * @param isEnvironment  Indicates whether the command is related to the environment.
     */
    public void processCommandString(String commandString, boolean isEnvironment) {
        try {
            String formattedCommandString = commandString.replace("\\n", "\n");

            // Parse and handle environment commands
            if (isEnvironment) {
                List<ShapeData> shapeDataList = parser.parseEnvironment(formattedCommandString);
                for (ShapeData shapeData : shapeDataList) {
                    createAreaFromShapeData(shapeData);
                }
            } else {
                // Parse and handle robot program commands
                parser.parseRobotProgram(formattedCommandString);
            }
        } catch (FollowMeParserException e) {
            System.err.println("Error during parsing: " + e.getMessage());
        }
    }

    /**
     * Creates an area (Circle or Rectangle) in the environment based on the provided ShapeData.
     *
     * @param shapeData The ShapeData object containing information about the shape to be created.
     */
    private void createAreaFromShapeData(ShapeData shapeData) {
        String shapeType = shapeData.shape();
        String label = shapeData.label();
        double[] args = shapeData.args();

        // Create a Circle if the shape type is "CIRCLE"
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            Circle circle = new Circle(new Point(args[0], args[1]), args[2], label);
            environment.addArea(circle);
        }
        // Create a Rectangle if the shape type is "RECTANGLE"
        else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            Rectangle rectangle = new Rectangle(new Point(args[0], args[1]), args[2], args[3], label);
            environment.addArea(rectangle);
        }
    }

    /**
     * Called when the parsing process starts. Initializes data structures and sets up for parsing.
     * Clears existing commands, creates a new environment, clears the command stack, and sets the currentCommands.
     */
    @Override
    public void parsingStarted() {
        commands.clear();
        environment = new Environment();
        commandStack.clear();
        currentCommands = commands;
    }

    /**
     * Called when the parsing process is completed. Performs any necessary actions after parsing.
     */
    @Override
    public void parsingDone() {
    }

    /**
     * Adds a MoveCommand to the currentCommands list based on the provided arguments.
     *
     * @param args An array containing the arguments for the MoveCommand (x, y, speed).
     */
    @Override
    public void moveCommand(double[] args) {
        MoveCommand command = new MoveCommand(args[0], args[1], args[2]);
        currentCommands.add(command);
    }

    /**
     * Adds a MoveRandomCommand to the currentCommands list based on the provided arguments.
     *
     * @param args An array containing the arguments for the MoveRandomCommand (minX, maxX, minY, maxY, speed).
     */
    @Override
    public void moveRandomCommand(double[] args) {
        MoveRandomCommand command = new MoveRandomCommand(args[0], args[1], args[2], args[3], args[4]);
        currentCommands.add(command);
    }

    /**
     * Adds a SignalCommand to the currentCommands list with the specified label.
     *
     * @param label The label for the SignalCommand.
     */
    @Override
    public void signalCommand(String label) {
        SignalCommand command = new SignalCommand(label);
        currentCommands.add(command);
    }

    /**
     * Adds an UnsignalCommand to the currentCommands list with the specified label.
     *
     * @param label The label for the UnsignalCommand.
     */
    @Override
    public void unsignalCommand(String label) {
        UnsignalCommand command = new UnsignalCommand(label);
        currentCommands.add(command);
    }

    /**
     * Adds a FollowCommand to the list of current commands.
     *
     * @param label The label to follow.
     * @param args  Additional arguments for the follow command.
     */
    @Override
    public void followCommand(String label, double[] args) {
        FollowCommand command = new FollowCommand(label, args[0], args[1]);
        currentCommands.add(command);
    }

    /**
     * Adds a StopCommand to the list of current commands.
     */
    @Override
    public void stopCommand() {
        StopCommand command = new StopCommand();
        currentCommands.add(command);
    }

    /**
     * Adds a ContinueCommand to the list of current commands.
     *
     * @param seconds The duration to continue the command.
     */
    @Override
    public void continueCommand(int seconds) {
        ContinueCommand command = new ContinueCommand(seconds);
        currentCommands.add(command);
    }

    /**
     * Starts a repeat command block, specifying the number of repetitions.
     *
     * @param n The number of repetitions for the repeat command block.
     */
    @Override
    public void repeatCommandStart(int n) {
        List<RobotCommand> repeatCommands = new ArrayList<>();
        currentCommands.add(new RepeatCommand(n, repeatCommands));
        commandStack.push(currentCommands);
        currentCommands = repeatCommands;
    }

    /**
     * Starts an until command block, specifying the label to check.
     *
     * @param label The label to check for in the until command block.
     */
    @Override
    public void untilCommandStart(String label) {
        List<RobotCommand> untilCommands = new ArrayList<>();
        currentCommands.add(new UntilCommand(label, untilCommands));
        commandStack.push(currentCommands);
        currentCommands = untilCommands;
    }

    /**
     * Starts a forever command block.
     */
    @Override
    public void doForeverStart() {
        List<RobotCommand> foreverCommands = new ArrayList<>();
        currentCommands.add(new ForeverCommand(foreverCommands));
        commandStack.push(currentCommands);
        currentCommands = foreverCommands;
    }

    /**
     * Ends the current command block, returning to the previous level of commands.
     * If there are no command blocks on the stack, this method has no effect.
     */
    @Override
    public void doneCommand() {
        if (!commandStack.isEmpty()) {
            currentCommands = commandStack.pop();
        }
    }

}
