package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * PenUp command puts the pen up and returns 0
 */
public class PenUp implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return 0
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return 0;
    }

}
