package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * Cosine command returns the cosine of the argument in degrees
 */
public class Cosine implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return cosine of the argument
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return Math.cos(Math.toRadians(args.get(0)));
        }
        return 0;
    }

}
