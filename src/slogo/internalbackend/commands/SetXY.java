package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * SetXY command indicates the coordinate where the turtle should be located
 */
public class SetXY implements Command {

    /**
     * Calculates distance the turtle would have turned to travel to be at the given x,y
     * @param location
     * @return distance between (0,0) and given location coordinate
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> location) {
        if (location.get(0) != null && location.get(1) != null) {
            double turt_x = myTurtle.getXCor();
            double turt_y = myTurtle.getYCor();
            double new_x = location.get(0);
            double new_y = location.get(1);
            return Math.sqrt(Math.pow(new_x - turt_x, 2) + Math.pow(new_y - turt_y, 2));
        }
        return 0;
    }
}
