package slogo.externalbackend;

import slogo.externalbackend.commands.ControlCommand;
import slogo.internalfrontend.Turtle;
import slogo.externalbackend.tokens.*;
import slogo.internalbackend.ModelManager;
import slogo.externalfrontend.VisualExecutor;

import java.util.*;
import java.util.regex.Pattern;
import java.util.Map.Entry;

/**
 * This class is our CommandParser object. An instance of it will be created by our frontend to feed command strings and send to
 * backend. It acts as the bridge between the frontend and backend.
 *
 * @author Jerry Wang
 */

public class CommandParser {
    private static final String languagesPath = "/languages/";
    private static final String SyntaxPath = "slogo.externalbackend.tokens.";
    private static final ResourceBundle CommandResources = ResourceBundle.getBundle("resources/commands/Commands");
    private static final ResourceBundle ControlResources = ResourceBundle.getBundle("resources/commands/ControlCommands");
    private static final ResourceBundle SyntaxResources = ResourceBundle.getBundle(languagesPath + "Syntax");
    private ResourceBundle LanguageResources = ResourceBundle.getBundle(languagesPath + "English"); //Defaults to English

    private final PatternMatcher patternMatcher = new PatternMatcher(SyntaxResources);
    private ModelManager myModelManager = new ModelManager();
    private VisualExecutor myVisualExecutor;

    private Map<Pattern, Token> inputsMap = new HashMap<>();
    private Map<String, String> commandsMap = new HashMap<>();
    private Map<String, Double> variablesMap = new HashMap<>();

    private Stack<CommandNode> argumentsAsNodes;
    private Stack<CommandNode> listAsStack = new Stack<>();

    private ArrayList<CommandNode> TreeRoots = new ArrayList<>();
    private ArrayList<Turtle> allTurtles;

    private ParserManager myParserManager;

    /**
     * CommandParser constructor with instance of a ParserManager
     * @param parserManager
     */
    public CommandParser(ParserManager parserManager) {
        myParserManager = parserManager;
    }

    /**
     *  Calls to create the map of valid commands (reflection) and token types (regex). Creates the instance of the VisualExecutor.
     */
    public void setUpParser() {
        myVisualExecutor = new VisualExecutor(myParserManager);
        createInputsMap();
        createCommandsMap();
    }

    /**
     * This method is responsible for taking the command string from the front end and accomplishing the full parse.
     * It requires helper methods that initially split the command into a stack of CommandNode object, create trees from this
     * stack as necessary, and finally executes these trees recursively.
     * @param command
     * @throws Exception
     */
    public void parseAll(String command) throws Exception {
        allTurtles = (ArrayList) myParserManager.getListOfTurtles();
        TreeRoots.clear();
        command = command.toLowerCase();
        String[] CommandsAsStrings = command.split("\\s+");
        argumentsAsNodes = separateCommands(CommandsAsStrings);
        while (!argumentsAsNodes.isEmpty()) {
            CommandNode firstNode = argumentsAsNodes.peek();
            TreeRoots.add(createTreeStruct(firstNode));
        }
        for (CommandNode root: TreeRoots){
            root.execute(myModelManager, myVisualExecutor, allTurtles);
        }
    }

    /**
     * This method clears the existing language tree than recreates it with the language sent to it from the front end.
     * @param language
     */
    public void selectLanguage(String language){
        LanguageResources = ResourceBundle.getBundle(languagesPath + language);
        commandsMap.clear();
        createCommandsMap();
    }

    private Stack<CommandNode> separateCommands(String[] CommandsAsStrings){
        int commandLength = CommandsAsStrings.length;
        int listID = 0;
        Stack<CommandNode> commandNodes = new Stack<>();
        for (int i = commandLength - 1; i >= 0; i--){
            String argument = CommandsAsStrings[i];
            Token commandAsToken = getTokenFromMap(argument);
            String command = commandsMap.get(argument);
            if (commandAsToken.getTokenType().equals(TokenType.ListStartToken)){
                listID--;
            }
            else if (commandAsToken.getTokenType().equals(TokenType.ListEndToken)){
                listID++;
            }
            CommandNode newNode = new CommandNode(commandAsToken, argument, command, listID);
            if (commandAsToken.getTokenType().equals(TokenType.CommentToken)){

            }
            else {
                commandNodes.add(newNode);
            }
        }
        return commandNodes;
    }

    private int getNumArguments(String command, ResourceBundle resourceFile) throws Exception {
        try {
            String[] path = resourceFile.getString(command).split(",");
            int returned = Integer.parseInt(path[1]);
            return returned;
        } catch (Exception e) {
            return 0;
        }
    }

    private void createInputsMap() {
        List<Entry<String, Pattern>> syntaxPatternMapping = patternMatcher.getPatterns();
        for (Entry<String, Pattern> pattern : syntaxPatternMapping) {
            String type = pattern.getKey();
            Token t = getTokenFromPattern(type);
            inputsMap.put(pattern.getValue(), t);
        }
    }

    private Token getTokenFromPattern(String type){
        try {
            Class<?> clazz = Class.forName(SyntaxPath + type);
            Object o = clazz.getDeclaredConstructor().newInstance();
            return (Token) o;
        } catch (Exception e) {
            //FIXME: Once command error checking is in place we work with these
            return null;
        }
    }

    private Token getTokenFromMap(String key){
        Token returned = null;
        for (Pattern p: inputsMap.keySet()){
            if (patternMatcher.match(key, p)){
                returned = inputsMap.get(p);
            }
        }
        return returned;
    }

    private void createCommandsMap() {
        Enumeration<String> keys = LanguageResources.getKeys();
        while (keys.hasMoreElements()){
            String command = keys.nextElement();
            String allTypes = LanguageResources.getString(command);
            ArrayList<String> possible = new ArrayList<>(Arrays.asList(allTypes.split(Pattern.quote("|"))));
            for(String s: possible){
                commandsMap.put(s, command);
            }
        }
    }

    private ControlCommand makeControlCommand (String keyCommand, ResourceBundle resourceBundle) {
        try {
            String[] path = resourceBundle.getString(keyCommand).split(",");
            Class<?> clazz = Class.forName(path[0]);
            Object o = clazz.getDeclaredConstructor().newInstance();
            return (ControlCommand) o;
        } catch (Exception e) {
            return null;
        }
    }

    private CommandNode createTreeStruct(CommandNode NodeCalledFrom) throws Exception {
        CommandNode returnedNode = NodeCalledFrom;
        CommandNode currentNode = argumentsAsNodes.pop();
        Token currentNodeToken = currentNode.getToken();
        TokenType argumentTokenType = currentNodeToken.getTokenType();
        if (argumentTokenType.equals(TokenType.ConstantToken)) {
        }
        else if (argumentTokenType.equals(TokenType.CommandToken)) {
            if (ControlResources.containsKey(currentNode.getCommand())){
                returnedNode = handleControlCommand(currentNode);
            }
            else {
                handleCommand(currentNode);
            }
        }
        else if (argumentTokenType.equals(TokenType.ListStartToken)) {
            returnedNode = handleList(currentNode);
        }
        else if (argumentTokenType.equals(TokenType.VariableToken)){
            returnedNode = handleVariable(currentNode);
        }
        else{

        }
        //FIXME: Error Checking for all Impossible Cases
        return returnedNode;
    }

    private CommandNode handleVariable(CommandNode currentNode) {
        variablesMap = myParserManager.getUserVariables();
        String variableName = currentNode.getString();
        variableName = variableName.substring(1);
        Double finalValue = variablesMap.get(variableName);
        return new CommandNode(new ConstantToken(), Double.toString(finalValue), Double.toString(finalValue), currentNode.getListID());
    }

    private void handleCommand(CommandNode currentNode) throws Exception {
        String thisCommand = currentNode.getCommand();
        int numParameters = getNumArguments(thisCommand, CommandResources);
        for (int i = 0; i < numParameters; i++){
            CommandNode argument = createTreeStruct(argumentsAsNodes.peek());
            currentNode.addChild(argument);
        }
    }

    private CommandNode handleControlCommand(CommandNode currentNode) throws Exception {
        String currentNodeCommand = currentNode.getCommand();
        ControlCommand controlCommand = makeControlCommand(currentNodeCommand, ControlResources);
        ArrayList<String> passedArguments = new ArrayList<>();
        int controlArguments = getNumArguments(currentNodeCommand, ControlResources);
        if (controlArguments == -1){
            passedArguments = createOneListArguments(currentNode);
        }
        else {
            for (int i = 0; i < controlArguments; i++) {
                passedArguments.add(argumentsAsNodes.pop().getString());
            }
        }
        double finalValue = controlCommand.execute(myParserManager, passedArguments);
        return new CommandNode(new ConstantToken(), Double.toString(finalValue), Double.toString(finalValue), currentNode.getListID());
    }

    private ArrayList<String> createOneListArguments(CommandNode currentNode){
        ArrayList<String> passedArguments = new ArrayList<>();
        if (argumentsAsNodes.pop().getToken().getTokenType().equals(TokenType.ListStartToken)){
            currentNode = argumentsAsNodes.pop();
            while (!currentNode.getToken().getTokenType().equals(TokenType.ListEndToken)){
                passedArguments.add(currentNode.getString());
                currentNode = argumentsAsNodes.pop();
            }
        }
        else{
            //FIXME: Error
        }
        return passedArguments;
    }

    private CommandNode handleList(CommandNode currentNode) throws Exception {
        ArrayList<CommandNode> listAsList = new ArrayList<>();
        int currentID = currentNode.getListID() + 1;
        while (!(currentNode.getToken().getTokenType().equals(TokenType.ListEndToken))){
            listAsList.add(currentNode);
            currentNode = argumentsAsNodes.pop();
        }
        for (int i = listAsList.size() - 1; i > 0; i--){ //Get rid of ListStartToken
            listAsStack.add(listAsList.get(i));
        }
        ArrayList<CommandNode> ListRoots = new ArrayList<>();
        Stack<CommandNode> holdStack = argumentsAsNodes;
        argumentsAsNodes = listAsStack;
        while (!argumentsAsNodes.isEmpty()) {
            CommandNode firstNode = argumentsAsNodes.peek();
            ListRoots.add(createTreeStruct(firstNode));
        }
        argumentsAsNodes = holdStack;
        double finalValue = 0;
        for (CommandNode root: ListRoots){
            finalValue = root.execute(myModelManager, myVisualExecutor, allTurtles);
        }
        listAsStack.clear();
        return new CommandNode(new ConstantToken(), Double.toString(finalValue), Double.toString(finalValue), currentID);
    }

    /**
     * To be called from ParserManager as a result of the Repeat Command. Not functional.
     * @param numTimes
     * @return
     * @throws Exception
     */
    public double repeatCommand(int numTimes) throws Exception {
        CommandNode currentNode = argumentsAsNodes.pop();
        double returned = 0;
        Stack<CommandNode> stackAsIs = argumentsAsNodes;
        for (int i = 0; i < numTimes; i++){
            argumentsAsNodes = stackAsIs;
            returned = handleList(currentNode).getValue();
        }
        return returned;
    }

    /**
     * Returns the CommandParser's instance of the VisualExecutor.
     * @return myVisualExecutor
     */
    public VisualExecutor getVisualExecutor(){
        return myVisualExecutor;
    }

}
