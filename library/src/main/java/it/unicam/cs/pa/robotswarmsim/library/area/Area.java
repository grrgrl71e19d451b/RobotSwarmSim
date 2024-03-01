package it.unicam.cs.pa.robotswarmsim.library.area;

import it.unicam.cs.pa.robotswarmsim.library.Point;

/**
 * This interface represents an area in a swarm robot simulation.
 */
public interface Area {
    /**
     * Checks if the given point is inside the area.
     *
     * @param point The point to check.
     * @return true if the point is inside the area, otherwise false.
     */
    boolean contains(Point point);

    /**
     * Returns the label of the area.
     *
     * @return The label of the area.
     */
    String getLabel();
}
