package slogo.externalbackend;

import slogo.internalfrontend.Turtle;

import javax.swing.text.html.parser.Parser;
import java.util.*;

/**
 * @author Samantha Whitt
 * ParserManager acts as the control for parser
 * Contains an instance of CommandParser, WindowManager contains an instance of ParserManager in return
 * Passed down to CommandParser, VisualExectuor, and WindowManager to facilitate conversation
 * Also contains the list of turtles in the backend and user defined variables
 * Edits how the parser runs correspondingly by getting passed into specific parser commands
 */
public class ParserManager {
    private CommandParser myParser;
    private Map<Integer, Turtle> myTurtles;
    private Map<String, Double> userVariables;
    private int repeat = 1;

    /**
     * Constructor for ParserManager
     * Initiates the CommandParser, turtles hashmap relating ID to turtle, and userVariables hashmap
     * relating variable to value
     */
    public ParserManager() {
        myParser = new CommandParser(this);
        myTurtles = new HashMap<>();
        userVariables = new HashMap<>();
    }

    /**
     * Called by Make command and upon first creation of the VisualExecutor/WindowManager
     * Adds the new turtle with new id to the hashmap and puts it on the screen visually
     */
    public void addTurtle(int turtle_id) {
        Turtle new_turtle = myParser.getVisualExecutor().getWindowManager().createTurtle(turtle_id);
        myTurtles.put(turtle_id, new_turtle);
    }

    /**
     *
     * @param variable
     * @param value
     */
    public void addUserVariable(String variable, Double value) {
        userVariables.put(variable, value);
        myParser.getVisualExecutor().getWindowManager().createUserVariable(variable, value);
    }

    /**
     * @return list of turtles (not their IDs)
     */
    public List<Turtle> getListOfTurtles() {
        return new ArrayList(myTurtles.values());
    }

    public Turtle getTurtleFromID(int ID) {
        return myTurtles.get(ID);
    }

    /**
     * @return list of user-defined variables
     */
    public Map<String, Double> getUserVariables() {
        return userVariables;
    }

    /**
     * @return the specific instance of the CommandParser used
     */
    public CommandParser getParser() {
        return myParser;
    }

    public double setRepeat(int newRepeat) throws Exception {
        repeat = newRepeat;
        return myParser.repeatCommand(newRepeat);
    }

    public int getRepeat() {
        return repeat;
    }

}
