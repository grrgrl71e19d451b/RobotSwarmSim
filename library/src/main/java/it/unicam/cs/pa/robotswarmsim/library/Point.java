package it.unicam.cs.pa.robotswarmsim.library;

/**
 * This class represents a 2D point with x and y coordinates.
 */
public record Point(double x, double y) {

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other The other point to calculate the distance to.
     * @return The Euclidean distance between this point and the other point.
     */
    public double distanceTo(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
