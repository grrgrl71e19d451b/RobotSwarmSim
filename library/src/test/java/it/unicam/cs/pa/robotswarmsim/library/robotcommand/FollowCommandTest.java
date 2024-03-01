package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for FollowCommand.
 */
class FollowCommandTest {
    private Robot robot;
    private FollowCommand followCommand;

    // Definition of the speed variable

    @BeforeEach
    void setUp() {
        // Create a simple test environment
        Environment testEnvironment = new Environment();

        // Initialize the robot with the test environment and set the speed
        robot = new Robot(new Point(0, 0), testEnvironment);
        double speed = 1.0;
        robot.setSpeed(speed);

        // Initialize the FollowCommand
        String label = "testLabel";
        double distance = 10.0;
        followCommand = new FollowCommand(label, distance, speed);
    }

    @Test
    void testFollowCommandWithNoRobotsInRange() {
        // Assuming no robots are within range, the robot should move randomly.
        // Here, we can only check if the robot's position changes, but we can't predict the exact movement.
        Point initialPosition = robot.getPosition();
        followCommand.execute(robot, 1); // Execute for 1 second

        Point newPosition = robot.getPosition();
        assertNotEquals(initialPosition, newPosition, "Robot should have moved from its initial position");
    }

    @Test
    void testFollowCommandWithRobotsInRange() {
        // Setup the test environment and robots
        TestEnvironment testEnvironment = new TestEnvironment();
        Robot targetRobot = new Robot(new Point(10, 10), testEnvironment);
        targetRobot.signalLabel("testLabel");
        testEnvironment.addRobot(targetRobot);

        Robot robot = new Robot(new Point(0, 0), testEnvironment);
        robot.setSpeed(1.0); // Set a speed

        // Execute the FollowCommand with a sufficiently large distance
        FollowCommand followCommand = new FollowCommand("testLabel", 50, 1.0);
        followCommand.execute(robot, 5); // Execute for 5 seconds

        // Verify that the robot's position has changed
        assertNotEquals(robot.getPosition(), new
                Point(0, 0), "The robot should have changed its position after executing the command.");
    }


    /**
     * Test environment class to control the robots present.
     */
    static class TestEnvironment extends Environment {
        public void addRobot(Robot robot) {
            // Implement adding the robot to the environment
        }
    }
}
