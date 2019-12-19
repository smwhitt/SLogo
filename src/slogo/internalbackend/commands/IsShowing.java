package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * IsShowing command returns whether or not the turtle is visible or not
 */
public class IsShowing implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return 1 if true, 0 if false
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return (myTurtle.getVisible()) ? 1 : 0;
    }

}
