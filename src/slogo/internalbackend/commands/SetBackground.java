package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * SetBackground command sets background color of screen to the input argument index and returns that index
 */
public class SetBackground implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return color index
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return args.get(0);
        }
        return 0;
    }

}
