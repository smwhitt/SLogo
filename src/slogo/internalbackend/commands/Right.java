package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Right command indicating number of degrees the turtle should turn clockwise
 */
public class Right implements Command {

    /**
     * Calculates how much the turtle will have to rotate clockwise
     * @param degrees
     * @return number of degrees the turtle should rotate in the frontend
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> degrees) {
        if (degrees.get(0) != null) {
            return degrees.get(0);
        }
        return 0;
    }
}
