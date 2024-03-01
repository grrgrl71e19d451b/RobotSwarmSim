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
 * Test class for RepeatCommand.
 */
public class RepeatCommandTest {

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
     * Test the constructor of RepeatCommand.
     */
    @Test
    public void testConstructor() {
        RepeatCommand repeatCommand = new RepeatCommand(2, commandList); // Create RepeatCommand with 2 iterations
        assertNotNull(repeatCommand, "RepeatCommand should be successfully instantiated.");
    }

    /**
     * Test the execute method of RepeatCommand.
     */
    @Test
    public void testExecute() {
        int iterations = 2; // Number of iterations to test
        List<RobotCommand> commandList = Arrays.asList(command1, command2);
        RepeatCommand repeatCommand = new RepeatCommand(iterations, commandList);

        double dt = 1.0; // Define a timestep

        // Total number of execute calls = iterations * number of commands
        int totalExecutions = iterations * commandList.size();
        for (int i = 0; i < totalExecutions; i++) {
            repeatCommand.execute(robot, dt);
        }

        // Assertions
        assertEquals(iterations, command1.getExecutionCount(),
                "Command1 should be executed " + iterations + " times.");
        assertEquals(iterations, command2.getExecutionCount(),
                "Command2 should be executed " + iterations + " times.");
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
