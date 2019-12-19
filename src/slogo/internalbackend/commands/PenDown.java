package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * PenDown command puts the pen down and returns 1
 */
public class PenDown implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return 1
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return 1;
    }

}
