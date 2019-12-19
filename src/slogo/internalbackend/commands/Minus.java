package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Minus commands returns the negative of the argument
 */
public class Minus implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return negative of the input argument
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return -args.get(0);
        }
        return 0;
    }
}
