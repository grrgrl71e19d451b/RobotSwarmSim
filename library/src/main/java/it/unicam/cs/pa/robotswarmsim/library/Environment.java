package it.unicam.cs.pa.robotswarmsim.library;

import it.unicam.cs.pa.robotswarmsim.library.area.Area;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the environment in which robots operate.
 */
public class Environment {
    private final List<Area> areas; // List of areas in the environment
    private final List<Robot> robots; // List of robots in the environment

    /**
     * Constructs a new Environment object.
     */
    public Environment() {
        this.areas = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    /**
     * Adds an area to the environment.
     *
     * @param area The area to add.
     */
    public void addArea(Area area) {

        areas.add(area);
    }

    /**
     * Adds a robot to the environment.
     *
     * @param robot The robot to add.
     */
    public void addRobot(Robot robot) {

        robots.add(robot);
    }

    /**
     * Gets a list of robots in the environment.
     *
     * @return A list of robots in the environment.
     */
    public List<Robot> getRobots() {

        return new ArrayList<>(robots);
    }

    /**
     * Gets a list of areas in the environment.
     *
     * @return A list of areas in the environment.
     */
    public List<Area> getAreas() {

        return new ArrayList<>(areas);
    }

}
