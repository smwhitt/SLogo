package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * PenColor command returns the turtle's current color index
 */
public class PenColor implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return index of turtle's penColor
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return myTurtle.getPenColor();
    }

}
