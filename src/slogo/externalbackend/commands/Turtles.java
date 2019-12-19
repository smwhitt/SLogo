package slogo.externalbackend.commands;

import slogo.externalbackend.ParserManager;

import java.util.ArrayList;

/**
 * @author Samantha Whitt
 * Turtles command gets how many turtles have been created this far
 */
public class Turtles implements ControlCommand {

    /**
     * @param myManager
     * @param arguments
     * @return (double) size of the turtles list
     */
    @Override
    public double execute(ParserManager myManager, ArrayList<String> arguments) {
        return myManager.getListOfTurtles().size();
    }
}
