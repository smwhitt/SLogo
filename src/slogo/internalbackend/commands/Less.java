package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Samantha Whitt
 * Less command returns whether or not the first arugment is less than the second argument in value
 */
public class Less implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return 1 if true, 0 if false
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null && args.get(1) != null) {
            if (args.get(0) < args.get(1)) {
                return 1;
            }
        }
        return 0;
    }
}
