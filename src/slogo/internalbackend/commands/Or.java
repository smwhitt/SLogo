package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * Or command returns whether or not one or both of the arguments are non-zero
 */
public class Or implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return 1 if true, 0 if false
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null && args.get(1) != null) {
            return (args.get(0) != 0 || args.get(1) != 0) ? 1 : 0;
        }
        return 0;
    }

}
