package it.unicam.cs.pa.robotswarmsim.library.simulator;


/**
 * This interface represents a simulator for robots, defining a method to simulate robot behavior over time.
 */
public interface Simulator {

    /**
     * Simulates robot behavior over a specified time period using a time step.
     *
     * @param dt   The time step in seconds.
     * @param time The total simulation time in seconds.
     */
    void simulate(double dt, double time);
}
