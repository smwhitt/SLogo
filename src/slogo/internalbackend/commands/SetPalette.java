package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * SetPalette command sets color corresponding at given index to given r g b color values and returns that index, where
 * r, g, and b are nonnegative integers less than 256 specifying an amount of red, green, and blue
 */
public class SetPalette implements Command {

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
