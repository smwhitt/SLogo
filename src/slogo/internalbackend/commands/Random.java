package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * Random command returns random non-negative number strictly less than the input argument
 */
public class Random implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return random non-negative number less than max
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            java.util.Random random = new java.util.Random();
            return random.nextInt(args.get(0).intValue());
        }
        return 0;
    }
}
