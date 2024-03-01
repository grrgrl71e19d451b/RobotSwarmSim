package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for UntilCommandTest.
 */
public class UntilCommandTest {

    private Robot robot;
    private TestCommand testCommand1;
    private TestCommand testCommand2;
    private UntilCommand untilCommand;
    private final String targetLabel = "targetArea";

    @BeforeEach
    public void setUp() {
        // Initialize a Robot instance with necessary parameters
        Point point = new Point(0, 0);
        Environment environment = new Environment();
        robot = new Robot(point, environment);

        // Create test command instances
        testCommand1 = new TestCommand();
        testCommand2 = new TestCommand();
        List<RobotCommand> commandList = Arrays.asList(testCommand1, testCommand2);

        // Create an UntilCommand with a target label and command list
        untilCommand = new UntilCommand(targetLabel, commandList);
    }

    @Test
    public void testExecuteUntilTargetReached() {
        // Simulate executing commands until the robot "reaches" the target area
        for (int i = 0; i < 5; i++) {
            untilCommand.execute(robot, 1.0);
        }

        // Simulate reaching the target area by adding the label
        robot.getCurrentAreaLabels().add(targetLabel);
        untilCommand.execute(robot, 1.0);

        // Verify that commands are no longer executed after reaching the target area
        int executionCountAfterTarget = testCommand1.getExecutionCount() + testCommand2.getExecutionCount();
        untilCommand.execute(robot, 1.0);
        int finalExecutionCount = testCommand1.getExecutionCount() + testCommand2.getExecutionCount();

        assertEquals(executionCountAfterTarget, finalExecutionCount, "No additional commands should be executed after reaching the target area.");
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
