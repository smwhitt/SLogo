package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * IsPenDown command returns whether or not the penStatus is down (1) or up (0)
 */
public class IsPenDown implements Command{

    /**
     * @param myTurtle
     * @param args
     * @return 1 if true, 0 if false
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        return (myTurtle.getPenStatus()) ? 1 : 0;
    }

}