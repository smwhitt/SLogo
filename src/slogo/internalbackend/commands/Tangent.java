package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * Tangent command returns the tangent of the argument in degrees
 */
public class Tangent implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return tangent of the argument
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return Math.tan(Math.toRadians(args.get(0)));
        }
        return 0;
    }

}
