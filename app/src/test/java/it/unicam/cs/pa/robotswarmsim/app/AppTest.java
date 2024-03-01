package it.unicam.cs.pa.robotswarmsim.app;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the App.
 * It tests basic behaviors of the application.
 */
public class AppTest {

    @Test
    public void testMainWithInsufficientArguments() {
        String[] args = new String[] {}; // No arguments provided

        // Assert that IllegalArgumentException is thrown due to insufficient arguments
        assertThrows(IllegalArgumentException.class, () -> App.main(args),
                "Should throw IllegalArgumentException when not enough arguments are provided.");
    }

    @Test
    public void testMainWithInvalidArguments() {
        String[] args = {"invalid", "args", "for", "testing"};
        assertThrows(IllegalArgumentException.class, () -> App.main(args),
                "Should throw IllegalArgumentException for invalid arguments.");
    }

    @Test
    public void testMainWithNonExistentFilePaths() {
        String[] args = {"1", "5", "file", "non_existent_path/env", "file", "non_existent_path/prog", "5"};
        assertThrows(NoSuchFileException.class, () -> App.main(args),
                "Should throw NoSuchFileException for non-existent file paths.");
    }

    @Test
    public void testMainWithNegativeRobotNumber() {
        // Use correct input data but with a negative number of robots
        String[] args = {
                "2",
                "20",
                "string",
                "Z2 CIRCLE 5 6 50\nZ1 RECTANGLE 1 1 80 80", // Environment data
                "string",
                "SIGNAL Z1\nDO FOREVER\nMOVE 1 0 1\nSTOP\nDONE", // Robot commands
                "-1" // Negative number of robots
        };

        // Verify that IllegalArgumentException is thrown for the negative number of robots
        assertThrows(IllegalArgumentException.class, () -> App.main(args),
                "Should throw IllegalArgumentException for negative number of robots.");
    }

}
