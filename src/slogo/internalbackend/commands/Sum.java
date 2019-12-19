package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Sum command calculates the total of the two input arguments via addition
 */
public class Sum implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return sum of the two arguments
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null && args.get(1) != null) {
            return args.get(0)+args.get(1);
        }
        return 0;
    }
}
