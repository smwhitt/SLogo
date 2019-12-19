package slogo.externalbackend.commands;

import slogo.externalbackend.ParserManager;
import slogo.internalfrontend.Turtle;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;

public class Tell implements ControlCommand {
    @Override
    public double execute(ParserManager myManager, ArrayList<String> arguments) {
        ArrayList<Turtle> turtles = (ArrayList) myManager.getListOfTurtles();
        ArrayList<Integer> IDs = new ArrayList<>();
        ArrayList<Integer> argumentIDs = new ArrayList<>();
        for (Turtle turtle: turtles) {
            IDs.add(turtle.getID());
        }
        int turtle_id = 0;
        for (String s: arguments){
            turtle_id = Integer.parseInt(s);
            argumentIDs.add(turtle_id);
            if (! IDs.contains(turtle_id)) {
                myManager.addTurtle(turtle_id);
            }
        }
        for (int i: IDs){
            if (!argumentIDs.contains(i)){
                myManager.getTurtleFromID(i).setActiveStatus(false);
            }
        }
        return turtle_id;
    }
}
