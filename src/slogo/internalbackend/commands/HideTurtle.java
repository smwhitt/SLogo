package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * HideTurtle command makes the turtle invisible and returns 0
 */
public class HideTurtle implements Command{

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
