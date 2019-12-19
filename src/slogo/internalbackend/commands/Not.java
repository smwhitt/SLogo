package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * Not command returns whether the argument is zero or non-zero; logical "not" gate
 */
public class Not implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return 1 if argument is zero, 0 if argument is non-zero
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return (args.get(0) == 0) ? 1 : 0;
        }
        return 0;
    }

}