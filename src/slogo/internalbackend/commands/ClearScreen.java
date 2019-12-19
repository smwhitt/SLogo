package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * ClearScreen command returns the distance the turtle traveled in pixels from its previous position to the home position;
 * turtle's trail is also erased in the frontend
 */
public class ClearScreen implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return distance from turtle's current position to home position, (0,0)
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        double XPos = myTurtle.getXCor();
        double YPos = myTurtle.getYCor();

        return Math.sqrt(Math.pow(XPos, 2) + Math.pow(YPos, 2));
    }

}