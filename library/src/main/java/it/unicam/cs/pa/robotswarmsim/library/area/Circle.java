package it.unicam.cs.pa.robotswarmsim.library.area;

import it.unicam.cs.pa.robotswarmsim.library.Point;

/**
 * This class represents a circular area in a swarm robot simulation.
 */
public class Circle implements Area {
    private final Point center; // Center of the circle
    private final double radius; // Radius of the circle
    private final String label; // Label for the circular area

    /**
     * Constructor for the Circle class.
     *
     * @param center Center of the circle.
     * @param radius Radius of the circle.
     * @param label Label for the circular area.
     */
    public Circle(Point center, double radius, String label) {
        this.center = center;
        this.radius = radius;
        this.label = label;
    }

    /**
     * Checks if the given point is inside the circular area.
     *
     * @param point The point to check.
     * @return true if the point is inside the circular area, otherwise false.
     */
    @Override
    public boolean contains(Point point) {
        double distanceSquared = Math.pow(point.x() - center.x(), 2) + Math.pow(point.y() - center.y(), 2);
        return distanceSquared <= Math.pow(radius, 2);
    }

    /**
     * Returns the label of the circular area.
     *
     * @return The label of the circular area.
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Returns a string representation of the Circle object with additional information.
     *
     * @return A string representing the Circle object with additional information.
     */
    @Override
    public String toString() {
        return "Circle{" +
                "centerX=" + center.x() + // X-coordinate of the center
                ", centerY=" + center.y() + // Y-coordinate of the center
                ", radius=" + radius + // Radius of the circle
                ", label='" + label + '\'' + // Label
                '}';
    }
}
