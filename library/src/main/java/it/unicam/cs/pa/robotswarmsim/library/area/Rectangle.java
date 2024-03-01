package it.unicam.cs.pa.robotswarmsim.library.area;

import it.unicam.cs.pa.robotswarmsim.library.Point;

/**
 * This class represents a rectangular area in a swarm robot simulation.
 */
public class Rectangle implements Area {
    private final Point topLeft; // Top-left point of the rectangle
    private final double width; // Width of the rectangle
    private final double height; // Height of the rectangle
    private final String label; // Label for the rectangular area

    /**
     * Constructor for the Rectangle class.
     *
     * @param topLeft Top-left point of the rectangle.
     * @param width   Width of the rectangle.
     * @param height  Height of the rectangle.
     * @param label   Label for the rectangular area.
     */
    public Rectangle(Point topLeft, double width, double height, String label) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.label = label;
    }

    /**
     * Checks if the given point is inside the rectangular area.
     * Uses the graphical Cartesian system with the origin at the top left.
     *
     * @param point The point to check, based on graphical Cartesian coordinates.
     * @return true if the point is inside the rectangular area, otherwise false.
     */
    @Override
    public boolean contains(Point point) {
        return point.x() >= topLeft.x() && point.x() <= topLeft.x() + width &&
                point.y() >= topLeft.y() && point.y() <= topLeft.y() + height;
    }

    /**
     * Returns the label of the rectangular area.
     *
     * @return The label of the rectangular area.
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Returns a string representation of the Rectangle object with additional information.
     *
     * @return A string representing the Rectangle object with additional information.
     */
    @Override
    public String toString() {
        return "Rectangle{" +
                "topLeftX=" + topLeft.x() + // X-coordinate of the top-left point
                ", topLeftY=" + topLeft.y() + // Y-coordinate of the top-left point
                ", width=" + width + // Width of the rectangle
                ", height=" + height + // Height of the rectangle
                ", label='" + label + '\'' + // Label
                '}';
    }
}
