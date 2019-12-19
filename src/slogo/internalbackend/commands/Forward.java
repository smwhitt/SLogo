package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Forward command indicating number of pixels to be moved forwards
 */
public class Forward implements Command {

    /**
     * Calculates how much the turtle will have to move
     * @param pixels
     * @return number of pixels the turtle should move in the frontend
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> pixels) {
        if (pixels.get(0) != null) {
            return pixels.get(0);
        }
        return 0;
    }
}
