package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * Pi command returns the number pi
 */
public class Pi implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return pi
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return Math.PI;
    }

}
