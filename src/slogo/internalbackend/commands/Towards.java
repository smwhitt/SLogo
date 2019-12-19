package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Towards command indicates the coordinate that the turtle should face if (0,0) is the center
 */
public class Towards implements Command {
    private double full_circle = 360;
    private double half_circle = 180;

    /**
     * Calculates number of degrees the turtle would have turned to face the given x,y
     * @param location
     * @return inverse tangent/degree between the center and the x,y coordinate
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> location) {
        if (location.get(0) != null && location.get(1) != null) {
            double new_x = location.get(0);
            double new_y = location.get(1);
            double new_angle = Math.toDegrees(Math.atan(new_y/new_x));
            double turt_angle = myTurtle.getHeading();

            // get new angle in terms of counterclockwise degrees
            if (new_x < 0 && new_y > 0) {
                new_angle = half_circle - new_angle;
            } else if (new_x < 0 && new_y < 0) {
                new_angle = half_circle + new_angle;
            } else if (new_x > 0 && new_y < 0 ) {
                new_angle = full_circle - new_angle;
            }

            return turt_angle - new_angle;
        }
        return 0;
    }
}
