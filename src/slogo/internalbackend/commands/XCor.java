package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Xcor command indicating the x coordinate of the turtle assuming center is (0,0)
 */
public class XCor implements Command {
    /**
     * @param myTurtle
     * @param args
     * @return x coordinate of the turtle
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return myTurtle.getXCor();
    }
}
