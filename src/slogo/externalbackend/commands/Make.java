package slogo.externalbackend.commands;

import slogo.externalbackend.ParserManager;

import java.util.ArrayList;

/**
 * @author Samantha Whitt
 * Make command adds a variable with its expression as user-defined
 */
public class Make implements ControlCommand {

    /**
     * Adds the variable and its expression to the map of user defined commands
     * The variable is the key and the expression is its value
     * @param myManager
     * @param arguments
     * @return (double) value of the expression
     */
    @Override
    public double execute(ParserManager myManager, ArrayList<String> arguments) {
        String variable = arguments.get(0);
        Double value = Double.parseDouble(arguments.get(1));
        myManager.addUserVariable(variable, value);
        return value;
    }
}
