package it.unicam.cs.pa.robotswarmsim.library.entity;

import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.area.Area;
import it.unicam.cs.pa.robotswarmsim.library.robotcommand.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a robot in the simulation environment.
 */
public class Robot implements Entity {
    private Point position;
    private double heading; // Direction in degrees
    private double speed; // Speed in meters per second
    private final Environment environment; // Environment in which the robot moves
    private final Set<String> signals;
    private List<RobotCommand> commands;
    private final Set<String> currentAreaLabels; // Set to track area labels

    private double timedCommandElapsedTime = 0; // Elapsed time for the current TimedCommand
    private int currentCommandIndex = 0;

    /**
     * Increment the current command index.
     */
    public void incrementCommandIndex() {
        currentCommandIndex++;
    }

    /**
     * Get the current command index.
     *
     * @return The current command index.
     */
    public int getCurrentCommandIndex() {
        return currentCommandIndex;
    }

    /**
     * Increment the elapsed time for the current TimedCommand.
     *
     * @param dt The time in seconds to add to the elapsed time.
     */
    public void incrementTimedCommandTime(double dt) {
        this.timedCommandElapsedTime += dt;
    }

    /**
     * Get the elapsed time for the current TimedCommand.
     *
     * @return The elapsed time for the current TimedCommand.
     */
    public double getTimedCommandTime() {
        return this.timedCommandElapsedTime;
    }

    /**
     * Reset the elapsed time for the current TimedCommand.
     */
    public void resetTimedCommandTime() {
        this.timedCommandElapsedTime = 0;
    }

    /**
     * Create a new robot with the specified position and environment.
     *
     * @param position    The initial position of the robot.
     * @param environment The environment in which the robot moves.
     */
    public Robot(Point position, Environment environment) {
        this.position = position;
        this.heading = 0;
        this.speed = 0;
        this.environment = environment;
        this.signals = new HashSet<>(); // Initialize the set of signals
        this.currentAreaLabels = new HashSet<>(); // Labels of the visited areas
    }

    /**
     * Add a label to the set of active signals of the robot.
     *
     * @param label The label to add.
     */
    public void signalLabel(String label) {
        signals.add(label);
    }

    /**
     * Remove a label from the set of active signals of the robot.
     *
     * @param label The label to remove.
     */
    public void removeLabel(String label) {
        signals.remove(label);
    }

    /**
     * Check if the robot is emitting a signal with the specified label.
     *
     * @param label The label to check.
     * @return true if the robot is emitting the signal, otherwise false.
     */
    public boolean isSignaling(String label) {
        return signals.contains(label);
    }

    /**
     * Set the direction of the robot.
     *
     * @param heading The new direction in degrees.
     */
    @Override
    public void setHeading(double heading) {
        this.heading = heading;
    }

    /**
     * Set the speed of the robot.
     *
     * @param speed The new speed in meters per second.
     */
    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Get the current position of the robot.
     *
     * @return The current position of the robot.
     */
    @Override
    public Point getPosition() {
        return position;
    }

    /**
     * Get the current direction of the robot in degrees.
     *
     * @return The current direction of the robot in degrees.
     */
    @Override
    public double getHeading() {
        return heading;
    }

    /**
     * Get the current speed of the robot in meters per second.
     *
     * @return The current speed of the robot in meters per second.
     */
    @Override
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the robot's position and updates area labels.
     *
     * @param position The new position of the robot, not null.
     */
    @Override
    public void setPosition(Point position) {
        this.position = position;
        updateCurrentAreaLabels();
    }

    /**
     * Refreshes area labels based on the robot's current position.
     */
    private void updateCurrentAreaLabels() {
        // Update the set of labels based on the new position
        this.currentAreaLabels.clear();
        for (Area area : environment.getAreas()) {
            if (area.contains(this.position)) {
                this.currentAreaLabels.add(area.getLabel());
            }
        }
    }

    /**
     * Get the current area labels the robot is in.
     *
     * @return The set of current area labels.
     */
    public Set<String> getCurrentAreaLabels() {
        return currentAreaLabels;
    }

    /**
     * Get all robots in the environment.
     *
     * @return A list of all robots in the environment.
     */
    public List<Robot> getAllRobotsInEnvironment() {
        return this.environment.getRobots();
    }

    /**
     * Get robots within the specified range around this robot.
     *
     * @param range The range to search for other robots.
     * @return A list of robots within the specified range.
     */
    public List<Robot> getRobotsWithinRange(double range) {
        return getAllRobotsInEnvironment().stream()
                .filter(r -> !r.equals(this) && this.getPosition().distanceTo(r.getPosition()) <= range)
                .collect(Collectors.toList());
    }

    /**
     * Get a string representation of the robot's information.
     *
     * @return A string with the robot's information.
     */
    @Override
    public String toString() {

        String areaDescription = currentAreaLabels.isEmpty()
                ? "No areas"
                : "Areas: " + String.join(", ", currentAreaLabels);


        String signalDescription = signals.isEmpty()
                ? "No signals"
                : "Active signals: " + String.join(", ", signals);

        return "Robot id: " + Integer.toHexString(System.identityHashCode(this)) + "\n" +
                areaDescription + "\n" +
                "Position X: " + position.x() + "\n" +
                "Position Y: " + position.y() + "\n" +
                "Direction: " + heading + " degrees\n" +
                "Speed: " + speed + " m/s\n" +
                signalDescription + "\n";
    }

    /**
     * Set the commands for the robot.
     *
     * @param commands The list of commands to set.
     */
    public void setCommands(List<RobotCommand> commands) {
        this.commands = commands;
    }

    /**
     * Get the commands assigned to the robot.
     *
     * @return The list of commands assigned to the robot.
     */
    public List<RobotCommand> getCommands() {
        return this.commands;
    }
}
