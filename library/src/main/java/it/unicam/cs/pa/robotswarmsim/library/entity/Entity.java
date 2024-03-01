package it.unicam.cs.pa.robotswarmsim.library.entity;

import it.unicam.cs.pa.robotswarmsim.library.Point;

/**
 * This interface represents an entity in a swarm robot simulation.
 */
public interface Entity {

    /**
     * Sets the orientation of the entity.
     *
     * @param heading The orientation in degrees.
     */
    void setHeading(double heading);

    /**
     * Sets the speed of the entity.
     *
     * @param speed The speed in meters per second.
     */
    void setSpeed(double speed);

    /**
     * Sets the position of the entity.
     *
     * @param position The position of the entity.
     */
    void setPosition(Point position);

    /**
     * Returns the current position of the entity.
     *
     * @return The position of the entity.
     */
    Point getPosition();

    /**
     * Returns the current orientation of the entity.
     *
     * @return The orientation in degrees of the entity.
     */
    double getHeading();

    /**
     * Returns the current speed of the entity.
     *
     * @return The speed in meters per second of the entity.
     */
    double getSpeed();
}
