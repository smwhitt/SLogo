package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * Power command returns the base raised to the power of the exponent, where the base is the first argument and the
 * exponent is the second argument
 */
public class Power implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return argument one to the power of argument two
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null && args.get(1) != null) {
            return Math.pow(args.get(0), args.get(1));
        }
        return 0;
    }

}
