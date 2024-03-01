package it.unicam.cs.pa.robotswarmsim.library;

import it.unicam.cs.pa.robotswarmsim.library.entity.Entity;

/**
 * This interface represents a command that can be executed by any entity in the simulation.
 * @param <T> The type of entity on which the command can be executed.
 */
public interface EntityCommand<T extends Entity> {
    /**
     * Executes the command on the specified entity.
     *
     * @param entity The entity on which the command is executed.
     * @param dt     The time step in seconds since the last execution.
     */
    default void execute(T entity, double dt) {
    }
}
