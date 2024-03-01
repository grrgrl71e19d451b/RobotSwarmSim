package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ForeverCommand.
 */
public class ForeverCommandTest {

    private Robot robot;
    private TestCommand command1;
    private TestCommand command2;
    private List<RobotCommand> commandList;

    /**
     * Setup the testing environment.
     */
    @BeforeEach
    public void setUp() {
        Point point = new Point(0, 0); // Assuming Point can be instantiated with x, y coordinates
        Environment environment = new Environment(); // Assuming Environment can be instantiated directly
        robot = new Robot(point, environment); // Create a Robot instance with required parameters

        command1 = new TestCommand();
        command2 = new TestCommand();
        commandList = Arrays.asList(command1, command2);
    }
    /**
     * Test the constructor of ForeverCommand.
     */
    @Test
    public void testConstructor() {
        ForeverCommand foreverCommand = new ForeverCommand(commandList);
        assertNotNull(foreverCommand, "ForeverCommand should be successfully instantiated.");
    }

    /**
     * Test the execute method of ForeverCommand.
     */
    @Test
    public void testExecute() {
        ForeverCommand foreverCommand = new ForeverCommand(commandList);
        double dt = 1.0; // Define a timestep

        // Execute the foreverCommand multiple times to test the loop
        int numberOfExecutions = 4;
        for (int i = 0; i < numberOfExecutions; i++) {
            foreverCommand.execute(robot, dt);
        }

        // Check if each command was executed the expected number of times
        assertEquals(2, command1.getExecutionCount(), "Command1 should be executed twice.");
        assertEquals(2, command2.getExecutionCount(), "Command2 should be executed twice.");
    }

    /**
     * A simple implementation of RobotCommand for testing purposes.
     */
    private static class TestCommand implements RobotCommand {
        private int executionCount = 0;

        @Override
        public void execute(Robot robot, double dt) {
            executionCount++;
        }

        public int getExecutionCount() {
            return executionCount;
        }
    }
}
