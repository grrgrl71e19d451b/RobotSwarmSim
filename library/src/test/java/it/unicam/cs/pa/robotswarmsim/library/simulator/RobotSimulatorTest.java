package it.unicam.cs.pa.robotswarmsim.library.simulator;

import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;
import it.unicam.cs.pa.robotswarmsim.library.Environment;
import it.unicam.cs.pa.robotswarmsim.library.Point;
import it.unicam.cs.pa.robotswarmsim.library.robotcommand.RobotCommand;
import it.unicam.cs.pa.robotswarmsim.library.robotcommand.MoveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RobotSimulatorTest {

    private RobotSimulator simulator;
    private Environment environment;
    private List<Robot> robots;

    @BeforeEach
    public void setUp() {
        environment = new Environment(); // Assuming that Environment has a parameterless constructor
        robots = new ArrayList<>();
        simulator = new RobotSimulator(robots); // Initializing the simulator with the list of robots
    }

    @Test
    public void testSimulateWithMoveCommand() {
        // Creation and setup of robots
        Robot robot1 = new Robot(new Point(0, 0), environment);
        Robot robot2 = new Robot(new Point(10, 10), environment);
        robots.add(robot1);
        robots.add(robot2);

        // Assignment of Move commands
        MoveCommand moveCommand1 = new MoveCommand(1, 0, 1); // Move right at speed 1
        MoveCommand moveCommand2 = new MoveCommand(0, 1, 1); // Move up at speed 1

        List<RobotCommand> commands1 = new ArrayList<>();
        commands1.add(moveCommand1);
        commands1.add(moveCommand1);
        commands1.add(moveCommand1);
        commands1.add(moveCommand1);
        commands1.add(moveCommand1);
        robot1.setCommands(commands1);

        List<RobotCommand> commands2 = new ArrayList<>();
        commands2.add(moveCommand2);
        commands2.add(moveCommand2);
        commands2.add(moveCommand2);
        commands2.add(moveCommand2);
        commands2.add(moveCommand2);
        robot2.setCommands(commands2);

        // Definition of simulation parameters
        double dt = 1.0; // Time step of 1 second
        double time = 5.0; // Total simulation time of 5 seconds

        // Storing initial positions
        Point initialPosition1 = robot1.getPosition();
        Point initialPosition2 = robot2.getPosition();

        // Execution of the simulation
        simulator.simulate(dt, time);

        // Verify that the positions of the robots have changed
        Point finalPosition1 = robot1.getPosition();
        Point finalPosition2 = robot2.getPosition();

        assertNotEquals(initialPosition1, finalPosition1, "The position of robot1 should change after simulation");
        assertNotEquals(initialPosition2, finalPosition2, "The position of robot2 should change after simulation");

        // Specific verification of new positions (optional, depending on the exact logic of the movement command)
        assertEquals(new Point(5, 0), finalPosition1, "The final position of robot1 is incorrect");
        assertEquals(new Point(10, 15), finalPosition2, "The final position of robot2 is incorrect");
    }

    /**
     * Test construction of RobotSimulator.
     */
    @Test
    public void testConstructor() {
        assertNotNull(simulator, "Simulator should not be null after construction.");
    }

    /**
     * Test incrementation of stepNumber in simulate method.
     */
    @Test
    public void testStepNumberIncrement() {
        double dt = 1.0;
        double time = 5.0;
        simulator.simulate(dt, time);

        assertEquals(5, simulator.getStepNumber(), "Step number should be equal to the number of iterations.");
    }

    /**
     * Verifies that the simulation runs without unhandled exceptions.
     */
    @Test
    public void testSimulationRunsSmoothly() {
        double dt = 1.0;
        double time = 5.0;
        assertDoesNotThrow(() -> simulator.simulate(dt, time));
    }

}
