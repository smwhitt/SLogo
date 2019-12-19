package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Heading command indicates the angle the turtle is rotated at currently
 * assuming that counterclockwise the angle goes from 0 to 360 degrees
 */
public class Heading implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return turtle's angle of rotation
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
         return myTurtle.getHeading();
    }
}
