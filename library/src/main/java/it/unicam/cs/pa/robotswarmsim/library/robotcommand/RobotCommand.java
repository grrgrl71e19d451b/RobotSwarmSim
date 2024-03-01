package it.unicam.cs.pa.robotswarmsim.library.robotcommand;

import it.unicam.cs.pa.robotswarmsim.library.EntityCommand;
import it.unicam.cs.pa.robotswarmsim.library.entity.Robot;

/**
 * This interface represents a command that can be executed by a robot in the simulation.
 */
public interface RobotCommand extends EntityCommand<Robot> {
    /**
     * Executes the command on the specified robot.
     *
     * @param robot The robot on which the command is executed.
     * @param dt    The time step in seconds since the last execution.
     */
    @Override
    default void execute(Robot robot, double dt) {
    }
}
