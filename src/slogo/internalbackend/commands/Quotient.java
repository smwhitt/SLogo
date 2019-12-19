package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Quotient command calculates the total of the two input arguments via division
 * not including/disregarding the remainder
 * Example: 10/3 = 3
 */
public class Quotient implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return quotient of the first argument by the second argument
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null && args.get(1) != null) {
            return args.get(0)/args.get(1);
        }
        return 0;
    }
}
