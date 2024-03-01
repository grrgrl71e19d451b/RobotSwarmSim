package it.unicam.cs.pa.robotswarmsim.library.entity;

import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Robot}.
 * Provides unit tests for the Robot class, focusing on its public methods and behaviors.
 */
public class RobotTest {

    private Robot robot;

    /**
     * Sets up the testing environment before each test.
     * Initializes a Robot object with a simple stub Environment.
     */
    @BeforeEach
    void setUp() {
        // Creating a simple stub environment
        // Other necessary overridden methods of Environment
        Environment stubEnvironment = new Environment() {
            private final List<Robot> robots = new ArrayList<>();

            @Override
            public List<Robot> getRobots() {
                return robots;
            }

            // Other necessary overridden methods of Environment
        };

        Point initialPosition = new Point(0, 0);
        robot = new Robot(initialPosition, stubEnvironment);
        // Assuming Environment needs to keep track of the robot
        stubEnvironment.getRobots().add(robot);
    }

    @Test
    void testPositionSettingAndGet() {
        Point newPosition = new Point(1, 1);
        robot.setPosition(newPosition);
        assertEquals(newPosition, robot.getPosition(), "Robot position should be updated to the new position");
    }

    @Test
    void testHeadingSettingAndGet() {
        double newHeading = 90.0;
        robot.setHeading(newHeading);
        assertEquals(newHeading, robot.getHeading(), "Robot heading should be updated to the new heading");
    }

    @Test
    void testSpeedSettingAndGet() {
        double newSpeed = 5.0;
        robot.setSpeed(newSpeed);
        assertEquals(newSpeed, robot.getSpeed(), "Robot speed should be updated to the new speed");
    }

    @Test
    void testSignalLabelAndIsSignaling() {
        String testLabel = "testSignal";
        robot.signalLabel(testLabel);
        assertTrue(robot.isSignaling(testLabel), "Robot should be signaling the added label");
    }

    @Test
    void testRemoveLabel() {
        String testLabel = "testSignal";
        robot.signalLabel(testLabel);
        robot.removeLabel(testLabel);
        assertFalse(robot.isSignaling(testLabel), "Robot should no longer be signaling the removed label");
    }

}
