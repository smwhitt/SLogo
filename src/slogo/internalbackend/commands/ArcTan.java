package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * ArcTan command returns the arctangent of the argument in degrees
 */
public class ArcTan implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return arctan of the argument
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return Math.atan(Math.toRadians(args.get(0)));
        }
        return 0;
    }

}
