package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;
import java.util.List;

/**
 * @author Megan Lemcke
 * Shape command returns the turtle's current shape index
 */
public class Shape implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return shape index
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return myTurtle.getShape();
    }

}
