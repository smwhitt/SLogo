package slogo.internalfrontend;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.util.ResourceBundle;

public class TurtleShapeHelp implements WindowComponent{

    /**
     * @author Megan Lemcke
     * This class is a WindowComponent that displays the shape/image options for the turtle and their corresponding
     * indices.
     */
    private BorderPane root;
    private Label shapeHelpLabel;
    private static ResourceBundle turtleShapes = ResourceBundle.getBundle("resources/visual/TurtleShapes");

    /**
     * This method sets up the WindowComponent as a root Border Pane, with top, center, bottom, right, and left attributes.
     * The turtle shapes and indices are pulled from the TurtleShapes resource bundle.
     */
    public void setUpWindowComponent(ResourceBundle resources) {
        root = new BorderPane();
        BorderPane top = new BorderPane();
        BorderPane center = new BorderPane();
        BorderPane bottom = new BorderPane();
        ImageView turtle1 = new ImageView(turtleShapes.getString("1.0"));
        turtle1.setFitWidth(Integer.parseInt(resources.getString("WINDOW_WIDTH"))*1/10);
        turtle1.setFitHeight(Integer.parseInt(resources.getString("WINDOW_WIDTH"))*1/10);
        top.setRight(turtle1);

        ImageView turtle2 = new ImageView(turtleShapes.getString("2.0"));
        turtle2.setFitWidth(Integer.parseInt(resources.getString("WINDOW_WIDTH"))*1/10);
        turtle2.setFitHeight(Integer.parseInt(resources.getString("WINDOW_WIDTH"))*1/10);
        center.setRight(turtle2);

        ImageView turtle3 = new ImageView(turtleShapes.getString("3.0"));
        turtle3.setFitWidth(Integer.parseInt(resources.getString("WINDOW_WIDTH"))*1/10);
        turtle3.setFitHeight(Integer.parseInt(resources.getString("WINDOW_WIDTH"))*1/10);
        bottom.setRight(turtle3);

        top.setCenter(new Label("1.0"));
        center.setCenter(new Label("2.0"));
        bottom.setCenter(new Label("3.0"));

        root.setTop(top);
        root.setCenter(center);
        root.setBottom(bottom);

        shapeHelpLabel = new Label(resources.getString("TURTLE_SHAPE_HELP_LABEL"));
        shapeHelpLabel.setLabelFor(root);
    }

    /**
     * This method returns the Component Node of the Window Component, which in this case is the root BorderPane.
     */
    public BorderPane getComponentNode() {
        return root;
    }

    /**
     * This method returns the label of the Window Component.
     */
    public Label getComponentLabel() {
        return shapeHelpLabel;
    }
}