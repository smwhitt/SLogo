package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Ycor command indicating the y coordinate of the turtle assuming center is (0,0)
 */
public class YCor implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return y coordinate of the turtle
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return -myTurtle.getYCor();
    }
}
