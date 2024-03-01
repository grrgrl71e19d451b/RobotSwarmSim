package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MoveRandomCommand class.
 */
public class MoveRandomCommandTest {

    Robot mockRobot;
    MoveRandomCommand command;

    @BeforeEach
    void setup() {
        // Create a starting point and a fictitious environment for the robot.
        Point startPosition = new Point(0, 0);
        Environment mockEnvironment = new Environment() {
            // Implement the necessary methods of the Environment here, if needed.
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

            // Implement other necessary methods, such as setHeading, etc.
        };

        // Initialize MoveRandomCommand with a specific area and speed.
        command = new MoveRandomCommand(0, 10, 0, 10, 1);
    }

    @Test
    void testRobotMovesWithinBounds() {
        // Execute the command for a specific time step (1 second time step).
        command.execute(mockRobot, 1);

        // Check if the new position of the robot is within the specified bounds.
        Point newPosition = mockRobot.getPosition();
        assertTrue(newPosition.x() >= 0 && newPosition.x() <= 10 &&
                        newPosition.y() >= 0 && newPosition.y() <= 10,
                "Robot should move within the specified bounds.");
    }
}
