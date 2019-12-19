package slogo.internalbackend;

import slogo.internalfrontend.Turtle;
import slogo.internalbackend.commands.Command;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Megan Lemcke, Samantha Whitt
 * ModelManager is part of the internal API for the backend
 * Once commands have been parsed/created by CommandParser, they are passed to ModelManager, which are then
 * executed using reflection to create the appropriate command class
 */
public class ModelManager {
    private static final ResourceBundle myResources = ResourceBundle.getBundle("resources/commands/Commands");

    /**
     * @param keyCommand
     * @param myTurtle
     * @param args
     * @return value obtained from the command (specific value on the class)
     */
    public double executeCommand(String keyCommand, Turtle myTurtle, List<Double> args) {
        Command c = (Command) makeClass(keyCommand);
        if (c==null){
            return 0;
        }
        else{
            return c.calculate(myTurtle, args);
        }
    }

    private Object makeClass (String keyCommand) {
        try {
            String[] path = myResources.getString(keyCommand).split(",");
            Class<?> clazz = Class.forName(path[0]);
            Object o = clazz.getDeclaredConstructor().newInstance();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
