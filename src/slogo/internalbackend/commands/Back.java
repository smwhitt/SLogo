package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Back command indicating number of pixels to be moved backwards
 */
public class Back implements Command {

    /**
     * Calculates how much the turtle will have to move
     * @param pixels
     * @return how many pixels the turtle should move in the frontend
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> pixels) {
        if (pixels.get(0) != null) {
            return pixels.get(0);
        }
        return 0;
    }
}
