package slogo.internalfrontend;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a TurtleImageSelector
 * Dependencies: Turtle
 */
public class TurtleImageSelector implements WindowComponent {
    private Button turtleImageButton;
    private Label turtleImageSelectorLabel;
    private FileChooser fileChooser;

    /**
     * This method sets up the FileChooser, Button, and label for the LanguageSelector
     * Assumptions:
     * @param resources- the resources bundle to get the text for the button and label
     */
    public void setUpWindowComponent(ResourceBundle resources){
        fileChooser = new FileChooser();
        turtleImageButton = new Button(resources.getString("TURTLE_IMAGE_SELECTOR_TEXT"));
        turtleImageSelectorLabel = new Label(resources.getString("TURTLE_IMAGE_SELECTOR_LABEL"));
    }

    /**
     * This method returns the Button associated with the TurtleImageSelector WindowComponent
     * Assumptions:
     * @return- the Button
     */
    public Button getComponentNode(){
        return turtleImageButton;
    }

    /**
     * This method returns the Label associated with the TurtleImageSelector WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return turtleImageSelectorLabel;
    }

    /**
     * Sets the action of the TurtleImageSelector to set the Turtle Image
     * Assumptions:
     * @param displayStage- the stage to open the file chooser in
     * @param turtle- the turtle to set image
     */
    public void setButtonAction(Stage displayStage, Turtle turtle){
        turtleImageButton.setOnAction(new EventHandler() {
            public void handle(Event t) {
                File file = fileChooser.showOpenDialog(displayStage);
                turtle.getComponentNode().setImage(new Image(file.getName()));
            }
        });
    }
}
