package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ContinueCommand using JUnit 5.
 */
class ContinueCommandTest {

    private Robot testRobot;
    private ContinueCommand continueCommand;
    private double testDuration;

    /**
     * Sets up the testing environment.
     */
    @BeforeEach
    void setUp() {
        // Create a minimal environment instance
        Environment environment = new Environment();

        // Initialize the robot with the environment
        testRobot = new Robot(new Point(0, 0), environment);
        testRobot.setSpeed(1.0); // Set speed to 1 meter/second
        testRobot.setHeading(90); // Set heading to 90 degrees (east)

        testDuration = 5.0; // Set duration to 5 seconds
        continueCommand = new ContinueCommand(testDuration);
        testRobot.setHeading(0); // Direction towards the positive X-axis
    }

    /**
     * Test if the robot's position is updated correctly.
     */
    @Test
    void testPerformCommand() {
        continueCommand.execute(testRobot, testDuration);
        double expectedX = 5.0; // Expected new X position
        double actualX = testRobot.getPosition().x();
        double delta = 0.0001; // A small delta for comparing floating-point numbers

        assertEquals(expectedX, actualX, delta, "The robot's X position should be updated correctly.");
    }

    /**
     * Test if the robot's heading remains unchanged.
     */
    @Test
    void testHeadingUnchanged() {
        double initialHeading = testRobot.getHeading();
        continueCommand.execute(testRobot, testDuration);
        assertEquals(initialHeading, testRobot.getHeading(), "Robot's heading should remain unchanged.");
    }

    /**
     * Test if the robot's speed remains unchanged.
     */
    @Test
    void testSpeedUnchanged() {
        double initialSpeed = testRobot.getSpeed();
        continueCommand.execute(testRobot, testDuration);
        assertEquals(initialSpeed, testRobot.getSpeed(), "Robot's speed should remain unchanged.");
    }

}
