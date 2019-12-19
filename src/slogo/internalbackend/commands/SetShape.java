package slogo.internalbackend.commands;

import slogo.internalfrontend.Turtle;

import java.util.List;

/**
 * @author Megan Lemcke
 * SetShape command  sets shape of turtle to that represented by index, and returns that index
 */
public class SetShape implements Command {

    /**
     * @param myTurtle
     * @param args
     * @return given index
     */
    @Override
    public double calculate(Turtle myTurtle, List<Double> args) {
        if (args.get(0) != null) {
            return args.get(0);
        }
        return myTurtle.getID();
    }

}
