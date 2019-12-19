package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * SetPenColor command sets the pen path to the corresponding color at the input index, and returns that index
 */
public class SetPenColor implements Command {

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
