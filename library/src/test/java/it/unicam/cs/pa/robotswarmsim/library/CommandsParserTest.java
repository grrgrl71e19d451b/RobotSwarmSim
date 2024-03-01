package it.unicam.cs.pa.robotswarmsim.library;

import it.unicam.cs.pa.robotswarmsim.library.robotcommand.MoveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CommandsParser.
 * It tests the functionalities of parsing and processing robot commands and environment data.
 */
public class CommandsParserTest {

    private CommandsParser parser;

    /**
     * Sets up the test environment before each test.
     * It initializes the CommandsParser object.
     */
    @BeforeEach
    public void setUp() {
        parser = new CommandsParser();
    }

    /**
     * Test to verify that the command stack is correctly initialized.
     */
    @Test
    public void testCommandStackInitialization() {
        assertNotNull(parser.getRobotCommands(), "Command stack should be initialized but is null.");
    }

    /**
     * Test to verify the processing of a simple movement command.
     */
    @Test
    public void testProcessMoveCommand() {
        // Use a command that moves the robot 1 meter per second in a single axis
        String commandString = "MOVE 1 0 1";
        parser.processCommandString(commandString, false);

        // Ensure that the command list is not empty after processing a move command
        assertFalse(parser.getRobotCommands().isEmpty(), "Command list should not be empty after processing a move command.");

        // Verify that the first command in the list is an instance of MoveCommand
        assertTrue(parser.getRobotCommands().get(0) instanceof MoveCommand, "First command should be an instance of MoveCommand.");

        // Get the first command and verify its parameters
        MoveCommand moveCommand = (MoveCommand) parser.getRobotCommands().get(0);
        assertEquals(1, moveCommand.getXDirection(), "X direction value should be 1");
        assertEquals(0, moveCommand.getYDirection(), "Y direction value should be 0");
        assertEquals(1, moveCommand.getSpeed(), "Speed value should be 1");
    }

}
