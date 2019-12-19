package slogo.internalfrontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;
import slogo.externalbackend.CommandParser;
import slogo.externalbackend.ParserManager;
import java.util.*;

/**
 * @author Matt Harris
 * This class handles all the WindowComponents in the scene as well as implements the functionality of some visual commands
 * Dependencies: WindowComponent, CommandParser, etc, etc
 * This class never really got where I intended it to. It still is trying to do way too much and I intend to clean it up as part
 * of my masterpice.
 */
public class WindowManager {
    private static ResourceBundle windowParametersResources = ResourceBundle.getBundle("resources/visual/WindowParameters");
    private ResourceBundle interpreterLanguageResources = ResourceBundle.getBundle("resources/parsing/LanguageOptions");
    private ResourceBundle windowComponentsResources = ResourceBundle.getBundle("resources/visual/IncludedWindowComponents");
    private static ResourceBundle colorIndices = ResourceBundle.getBundle("resources/visual/ColorIndices");
    private final Paint backgroundColor = Color.color(Double.parseDouble(windowParametersResources.getString("DEFAULT_BACKGROUND_R")),
                                                        Double.parseDouble(windowParametersResources.getString("DEFAULT_BACKGROUND_G")),
                                                        Double.parseDouble(windowParametersResources.getString("DEFAULT_BACKGROUND_B")));
    private static PlayingField playingField;
    private static Group displayGroup;
    private static Scene displayScene;
    private static Stage displayStage;
    private static PathGroup pathGroup = new PathGroup(windowParametersResources);
    private static Color pathColor = Color.color(Double.parseDouble(windowParametersResources.getString("DEFAULT_PEN_R")),
                                                    Double.parseDouble(windowParametersResources.getString("DEFAULT_PEN_G")),
                                                    Double.parseDouble(windowParametersResources.getString("DEFAULT_PEN_B")));
    private static CommandParser commandParser;
    private static Map<String, WindowComponent> windowComponentsMap;
    private static Popup popup = new Popup();
    private static TextArea errorContent = new TextArea();
    private static TurtleImageSelector myTurtleImageSelector;
    private static BorderPane root = new BorderPane();
    private static ParserManager myParserManager;

    public WindowManager(ParserManager parserManager) {
        myParserManager = parserManager;
        commandParser = myParserManager.getParser();
    }

    /**
     * This method creates a scene with all the proper visual assets, dimensions and colors
     * @param myStage- the stage to be displayed
     * @return- the created and populated Scene
     */
    public Scene setUpWindow(Stage myStage) {
        int windowWidth = Integer.parseInt(windowParametersResources.getString("WINDOW_WIDTH"));
        int windowHeight = Integer.parseInt(windowParametersResources.getString("WINDOW_HEIGHT"));
        displayStage = myStage;
        displayScene = new Scene(createDisplayGroup(), windowWidth, windowHeight, backgroundColor);
        return displayScene;
    }

    private Group createDisplayGroup() {
        displayGroup = new Group();
        displayGroup.getChildren().add(createRootBorderPane());
        playingField = new PlayingField();
        playingField.setUpWindowComponent(windowParametersResources);
        displayGroup.getChildren().add(playingField.getComponentNode());
        Button errorCloseButton = new Button("Close Popup");
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        popup.hide();
                    }
                };
        errorCloseButton.setOnAction(event);
        popup.getContent().addAll(errorContent, errorCloseButton);
        return displayGroup;
    }

    private BorderPane createRootBorderPane() {
        createWindowComponents();

        int windowWidth = Integer.parseInt(windowParametersResources.getString("WINDOW_WIDTH"));
        int windowHeight = Integer.parseInt(windowParametersResources.getString("WINDOW_HEIGHT"));

        WindowComponentSelector windowComponentSelector = (WindowComponentSelector) windowComponentsMap.get("WindowComponentSelector");
        windowComponentSelector.setWindowComponents(windowComponentsResources);
        windowComponentSelector.setSelectorAction(root, windowComponentsMap);

        BorderPane top = setUpBorderPane(windowComponentsMap.get("WindowComponentSelector").getComponentLabel(), windowComponentsMap.get("WindowComponentSelector").getComponentNode(), null, null, null);
        BorderPane bottom = setUpBorderPane(null, windowComponentsMap.get("CommandPrompt").getComponentNode(), null, null, windowComponentsMap.get("EnterCommandButton").getComponentNode());

        root.setTop(top);
        root.setCenter(windowComponentsMap.get("CommandHistoryBox").getComponentNode());
        root.setBottom(bottom);
        root.setLeft(windowComponentsMap.get("CommandHistoryBox").getComponentLabel());
        root.setLayoutX(windowWidth * 3 / 5);
        root.setPrefSize(windowWidth * 2 / 5, windowHeight);
        root.setStyle("-fx-padding: 10;");
        return root;
    }

    private void createWindowComponents() {
        windowComponentsMap = new TreeMap<>();
        Enumeration<String> windowComponentKeys = windowComponentsResources.getKeys();
        while (windowComponentKeys.hasMoreElements()) {
            String classNameKey = windowComponentKeys.nextElement();
            String classPathValue = windowComponentsResources.getString(classNameKey);
            WindowComponent windowComponent = (WindowComponent) makeObject(classPathValue);
            windowComponent.setUpWindowComponent(windowParametersResources);
            windowComponentsMap.put(classNameKey, windowComponent);
        }
        setWindowComponentActions();
    }

    private void setWindowComponentActions(){
        EnterCommandButton enterCommandButton = (EnterCommandButton) windowComponentsMap.get("EnterCommandButton");
        CommandHistoryBox commandHistoryBox = (CommandHistoryBox) windowComponentsMap.get("CommandHistoryBox");
        CommandPrompt commandPrompt = (CommandPrompt) windowComponentsMap.get("CommandPrompt");
        BackgroundColorSelector backgroundColorSelector = (BackgroundColorSelector) windowComponentsMap.get("BackgroundColorSelector");
        myTurtleImageSelector = (TurtleImageSelector) windowComponentsMap.get("TurtleImageSelector");
        LanguageSelector languageSelector = (LanguageSelector) windowComponentsMap.get("LanguageSelector");
        PenColorSelector penColorSelector = (PenColorSelector) windowComponentsMap.get("PenColorSelector");
        enterCommandButton.setButtonAction(commandHistoryBox, commandPrompt, commandParser);
        backgroundColorSelector.setSelectorAction(playingField);
        languageSelector.setLanguages(interpreterLanguageResources);
        languageSelector.setSelectorAction(commandParser);
        penColorSelector.setSelectorAction(pathGroup);
    }

    /**
     * This method serves as the connection point for the setpensize command to set the stroke width of our paths
     * @param strokeWidth- new strokewidth to set
     */
    public void setStrokeWidth(Double strokeWidth){
        pathGroup.setStrokeWidth(strokeWidth);
    }

    /**
     * This method creates an error popup window that is displayed in the center of the screen
     */
    public void showErrorPopup() {
        errorContent.setText("\n\n\nError found for your entered command.\nPlease consult the help pages to see valid commands.");
        popup.show(displayStage);
    }

    /**
     * This method serves as the connection point for the tell command to create new turtles
     * @param id- the new turtle's id
     * @return- the newly created turtle
     */
    public Turtle createTurtle(int id) {
        Turtle turtle = new Turtle();
        turtle.setUpTurtle(id, playingField.getHomeX(), playingField.getHomeY(), windowParametersResources);
        if (playingField != null && pathGroup != null) {
            Path penLine = new Path();
            pathGroup.add(penLine);
            penLine.setUpWindowComponent(windowParametersResources);
            penLine.setInitialPosition(playingField.getHomeX() + turtle.getComponentNode().getFitWidth() / 2, playingField.getHomeY() + turtle.getComponentNode().getFitHeight() / 2);
            penLine.getComponentNode().setStroke(pathColor);

            myTurtleImageSelector.setButtonAction(displayStage, turtle);

            displayGroup.getChildren().add(turtle.getComponentNode());
        }
        return turtle;
    }

    /**
     * adds user variable and expression to visual list
     * @param variable
     * @param expression
     */
    public void createUserVariable(String variable, Double expression) {
        TextArea variableTextArea = (TextArea) windowComponentsMap.get("VariableBox").getComponentNode();
        variableTextArea.appendText(variable +": " + expression + "\n");
    }

    /**
     * This method draws a new pen line from the position of the turtle to given coordinates based on penstatus
     * @param xCor- the new xCor used to calculate the new lineEndX
     * @param yCor- the new yCor used to calculate the new lineEndY
     * @param turtle- the turtle to draw the penLine for
     */
    public static void drawPenLine(double xCor, double yCor, Turtle turtle) {
        double lineStartX = turtle.getComponentNode().getX() + turtle.getComponentNode().getFitWidth() / 2;
        double lineStartY = turtle.getComponentNode().getY() + turtle.getComponentNode().getFitHeight() / 2;
        double lineEndX = xCor + turtle.getComponentNode().getFitWidth() / 2;
        double lineEndY = yCor + turtle.getComponentNode().getFitHeight() / 2;
        pathGroup.createNewPath(lineStartX, lineStartY, lineEndX, lineEndY, windowParametersResources);

        if (turtle.getPenStatus() && !(displayGroup.getChildren().contains(pathGroup.getLatestPath().getComponentNode()))) {
            displayGroup.getChildren().add(pathGroup.getLatestPath().getComponentNode());
        }
    }

    /**
     * This method clears all penLines in the PathGroup and refreshes the displayStage
     */
    public static void clearPaths() {
        for (Path path : pathGroup.getPathList()) {
            if (displayGroup.getChildren().contains(path.getComponentNode())) {
                displayGroup.getChildren().remove(path.getComponentNode());
            }
        }
        pathGroup.clear();
        displayStage.hide();
        displayStage.show();
    }

    private BorderPane setUpBorderPane(Node top, Node center, Node bottom, Node left, Node right) {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(top);
        borderPane.setCenter(center);
        borderPane.setBottom(bottom);
        borderPane.setLeft(left);
        borderPane.setRight(right);
        return borderPane;
    }

    private Object makeObject (String keyCommand) {
        try {
            Class<?> clazz = Class.forName(keyCommand);
            Object o;
            try {
                o = clazz.getDeclaredConstructor(Object.class).newInstance(myParserManager);
            } catch (Exception e) {
                o = clazz.getDeclaredConstructor().newInstance();
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setPathColor(ArrayList<Double> arg, Turtle turtle) {
        turtle.setPenColor(arg.get(0));
        String[] rgb = colorIndices.getString(Double.toString(turtle.getPenColor())).split(",");
        double r = Double.parseDouble(rgb[0])/255;
        double g = Double.parseDouble(rgb[1])/255;
        double b = Double.parseDouble(rgb[2])/255;
        pathGroup.setPathColor(Color.color(r,g,b));
    }

    public static void setBackgroundColor(ArrayList<Double> arg, Turtle turtle) {
        String[] rgb = colorIndices.getString(Double.toString(arg.get(0))).split(",");
        double r = Double.parseDouble(rgb[0])/255;
        double g = Double.parseDouble(rgb[1])/255;
        double b = Double.parseDouble(rgb[2])/255;
        playingField.getComponentNode().setFill(Color.color(r,g,b));
    }


    public void setStrokeWidth(ArrayList<Double> arg, Turtle turtle){
        pathGroup.setStrokeWidth(arg.get(0));
    }

}
