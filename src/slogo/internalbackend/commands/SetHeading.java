package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Set Heading command indicates the degree that the turtle should face
 */
public class SetHeading implements Command {

    /**
     * Calculates number of degrees the turtle would have turned to get to the given heading
     * @param degrees
     * @return difference in current heading and the set heading
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> degrees) {
        if (degrees.get(0) != null) {
            double turt_angle = myTurtle.getHeading();
            return turt_angle - degrees.get(0);
        }
        return 0;
    }
}
