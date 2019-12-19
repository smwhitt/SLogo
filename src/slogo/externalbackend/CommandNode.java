package slogo.externalbackend;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import slogo.internalfrontend.Turtle;
import slogo.externalbackend.tokens.Token;
import slogo.internalbackend.ModelManager;
import slogo.externalfrontend.VisualExecutor;

/**
 * This class is our CommandNode. It is the structure that the command trees are built from. This should contain all necessary
 * information about each argument from the command string, including what sort of token it is, its associated value if it is a
 * constant, its associated command string if it is a command, its children nodes, and its original string as input to the parser.
 *
 * @author Jerry Wang
 */

public class CommandNode {

        private Token token;
        private double value;
        private ArrayList<CommandNode> children = new ArrayList<>();
        private String argumentAsString;
        private String associatedCommand;
        private int listID = 0;

        /**
         * The CommandNode constructor. If it is a constant, then we assign the value.
         * @param token
         * @param argumentAsString
         * @param associatedCommand
         * @param listID
         */
        CommandNode(Token token, String argumentAsString, String associatedCommand, int listID) {
                this.token = token;
                this.argumentAsString = argumentAsString;
                this.listID = listID;
                this.associatedCommand = associatedCommand;
                if (token.getTokenType().equals(TokenType.ConstantToken)){
                        value = Double.parseDouble(argumentAsString);
                }
        }

        /**
         * Recursive function to execute all commands starting at a root in a tree structure. Requires a ModelManager (backend)
         * and a VisualExecutor (frontend) to send the commands to. It also requires a list of turtles so that these groups know
         * the objects to apply the commands to.
         * @param modelManager
         * @param visualExecutor
         * @param myTurtles
         * @return a double value associated with the command(s).
         * @throws NoSuchMethodException
         * @throws IllegalAccessException
         * @throws InvocationTargetException
         */
        public double execute(ModelManager modelManager, VisualExecutor visualExecutor, ArrayList<Turtle> myTurtles) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
                if (token.getTokenType().equals(TokenType.CommandToken)){
                        ArrayList<Double> args = new ArrayList<>();
                        for (CommandNode child: children){
                                double d = child.execute(modelManager, visualExecutor, myTurtles);
                                args.add(d);
                        }
                        for (Turtle t: myTurtles) {
                                if (t.getActiveStatus()) {
                                        value = modelManager.executeCommand(associatedCommand, t, args);
                                        ArrayList<Double> visualArgs = new ArrayList<Double>();
                                        visualArgs.add(new Double(value));
                                        visualArgs.addAll(args);
                                        visualExecutor.executeVisualCommandString(associatedCommand, visualArgs, t);
                                }
                        }
                }
                return value;
        }

        /**
         * Adds a child node to the list of children.
         * @param newChild
         */
        public void addChild(CommandNode newChild) {
                children.add(newChild);
        }

        /**
         * @return the associated value of the CommandNode
         */
        public double getValue() {
                return value;
        }

        /**
         * @return the associated string of the CommandNode
         */
        public String getString() {
                return argumentAsString;
        }

        /**
         * @return the associated command string of the CommandNode
         */
        public String getCommand(){
                return associatedCommand;
        }

        /**
         * @return the associated token of the CommandNode
         */
        public Token getToken(){
                return token;
        }

        /**
         * @return the listID of the CommandNode (relic, not used in current implementation but for better design)
         */
        public int getListID(){
                return listID;
        }

}
