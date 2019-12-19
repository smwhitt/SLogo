package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Remainder command calculates the remainder when dividing two arguments
 * Example: 10%3 = 1
 */
public class Remainder implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return remainder of the first argument divided by the second argument
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null && args.get(1) != null) {
            return args.get(0)%args.get(1);
        }
        return 0;
    }
}
