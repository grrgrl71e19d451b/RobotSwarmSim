package it.unicam.cs.pa.robotswarmsim.library.area;

import it.unicam.cs.pa.robotswarmsim.library.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Rectangle}.
 * This class provides unit tests for the Rectangle class,
 * ensuring that its public behaviors function correctly.
 */
public class RectangleTest {

    private Rectangle rectangle;
    private String label;

    /**
     * Sets up the testing environment before each test.
     * Initializes a Rectangle object with a predefined top-left point, width, height, and label.
     */
    @BeforeEach
    void setUp() {
        Point topLeft = new Point(0, 0); // Top-left corner of the rectangle
        double width = 10.0; // Width of the rectangle
        double height = 5.0; // Height of the rectangle
        label = "TestRectangle"; // Label for the rectangle
        rectangle = new Rectangle(topLeft, width, height, label);
    }

    /**
     * Tests if the {@link Rectangle#contains(Point)} method returns true for a point inside the rectangle.
     */
    @Test
    void testContainsTrue() {
        Point insidePoint = new Point(5, 2); // Point inside the rectangle
        assertTrue(rectangle.contains(insidePoint), "Rectangle should contain point (5,2)");
    }

    /**
     * Tests if the {@link Rectangle#contains(Point)} method returns false for a point outside the rectangle.
     */
    @Test
    void testContainsFalse() {
        Point outsidePoint = new Point(15, 10); // Point outside the rectangle
        assertFalse(rectangle.contains(outsidePoint), "Rectangle should not contain point (15,10)");
    }

    /**
     * Tests if the {@link Rectangle#getLabel()} method returns the correct label of the rectangle.
     */
    @Test
    void testGetLabel() {
        assertEquals(label, rectangle.getLabel(), "The label should match the one set in setUp");
    }

    /**
     * Tests if the {@link Rectangle#toString()} method returns the correct string representation of the rectangle.
     */
    @Test
    void testToString() {
        String expected = "Rectangle{topLeftX=0.0, topLeftY=0.0, width=10.0, height=5.0, label='TestRectangle'}";
        assertEquals(expected, rectangle.toString(), "The string representation should match the expected format");
    }
}
