package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * Sine command returns the sine of the argument in degrees
 */
public class Sine implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return sine of the argument
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return Math.sin(Math.toRadians(args.get(0)));
        }
        return 0;
    }

}
