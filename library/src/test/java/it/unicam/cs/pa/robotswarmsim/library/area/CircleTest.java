package it.unicam.cs.pa.robotswarmsim.library.area;

import it.unicam.cs.pa.robotswarmsim.library.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Circle}.
 * This class provides a series of unit tests for the Circle class,
 * ensuring that all its public behaviors work as expected.
 */
public class CircleTest {

    private Circle circle;
    private String label;

    /**
     * Sets up the testing environment before each test.
     * Initializes a Circle object with a predefined center, radius, and label.
     */
    @BeforeEach
    void setUp() {
        Point center = new Point(0, 0); // Center at (0,0)
        double radius = 5.0; // A sample radius
        label = "TestCircle"; // A sample label for the circle
        circle = new Circle(center, radius, label);
    }

    /**
     * Tests if the {@link Circle#contains(Point)} method returns true for a point inside the circle.
     */
    @Test
    void testContainsTrue() {
        Point insidePoint = new Point(1, 1); // Point inside the circle
        assertTrue(circle.contains(insidePoint), "Circle should contain point (1,1)");
    }

    /**
     * Tests if the {@link Circle#contains(Point)} method returns false for a point outside the circle.
     */
    @Test
    void testContainsFalse() {
        Point outsidePoint = new Point(10, 10); // Point outside the circle
        assertFalse(circle.contains(outsidePoint), "Circle should not contain point (10,10)");
    }

    /**
     * Tests if the {@link Circle#getLabel()} method returns the correct label of the circle.
     */
    @Test
    void testGetLabel() {
        assertEquals(label, circle.getLabel(), "The label should match the one set in setUp");
    }

    /**
     * Tests if the {@link Circle#toString()} method returns the correct string representation of the circle.
     */
    @Test
    void testToString() {
        String expected = "Circle{centerX=0.0, centerY=0.0, radius=5.0, label='TestCircle'}";
        assertEquals(expected, circle.toString(), "The string representation should match the expected format");
    }
}
