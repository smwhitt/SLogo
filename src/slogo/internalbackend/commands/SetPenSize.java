package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * SetPenSize command sets size of the pen to be input pixels thickness, and returns this pixel value
 */
public class SetPenSize implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return given pixels
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return args.get(0);
        }
        return 0;
    }

}
