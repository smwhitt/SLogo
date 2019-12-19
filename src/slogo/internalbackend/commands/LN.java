package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * LN command returns the natural logarithm of the argument
 */
public class LN implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return natural log of argument
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return Math.log(args.get(0));
        }
        return 0;
    }

}
