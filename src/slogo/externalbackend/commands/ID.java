package slogo.externalbackend.commands;

import slogo.externalbackend.ParserManager;
import slogo.internalfrontend.Turtle;

import java.util.ArrayList;

/**
 * @author Samantha Whitt
 * ID class gets the active turtle's ID assuming that only one turtle is active at a time
 */
public class ID implements ControlCommand {

    /**
     * @param myManager
     * @param arguments
     * @return (double) ID of the active turtle
     */
    @Override
    public double execute(ParserManager myManager, ArrayList<String> arguments) {
        for (Turtle myTurtle: myManager.getListOfTurtles()) {
            if (myTurtle.getActiveStatus()) return myTurtle.getID();
        }
        return 0;
    }
}
