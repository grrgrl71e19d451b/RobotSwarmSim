package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MoveCommand class.
 */
public class MoveCommandTest {

    Robot mockRobot;

    @BeforeEach
    void setup() {
        // Create a starting point and a mock environment for the robot.
        Point startPosition = new Point(0, 0);
        Environment mockEnvironment = new Environment() {
            // Implement any necessary methods of the Environment here, if needed.
        };

        // Instantiate the Robot with the required parameters.
        mockRobot = new Robot(startPosition, mockEnvironment) {
            private Point position = startPosition;

            @Override
            public void setPosition(Point newPosition) {
                this.position = newPosition;
            }

            @Override
            public Point getPosition() {
                return this.position;
            }

            // Implement other necessary methods, such as setHeading and setSpeed.
        };
    }

    @Test
    void testValidMoveCommand() {
        // Test creating a valid MoveCommand and executing it.
        double xDirection = 0.5;
        double yDirection = 0.5;
        double speed = 1;
        double dt = 1; // 1 second

        // Calculate heading angle
        double heading = Math.toDegrees(Math.atan2(yDirection, xDirection));

        // Calculate expected position
        double expectedX = speed * dt * Math.cos(Math.toRadians(heading));
        double expectedY = speed * dt * Math.sin(Math.toRadians(heading));

        MoveCommand command = new MoveCommand(xDirection, yDirection, speed);
        command.execute(mockRobot, dt);

        Point expectedPosition = new Point(expectedX, expectedY);
        assertEquals(expectedPosition, mockRobot.getPosition(), "Robot did not move as expected.");
    }

    @Test
    void testInvalidDirection() {
        // Test creating a MoveCommand with an invalid direction.
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(2, 0, 1), "MoveCommand should throw IllegalArgumentException for an invalid direction.");
    }
}
