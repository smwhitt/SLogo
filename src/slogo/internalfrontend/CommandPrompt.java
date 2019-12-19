package slogo.internalfrontend;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a CommandPrompt for input from user
 * Dependencies: WindowComponent
 */
public class CommandPrompt implements WindowComponent {
    private TextField commandPromptTextArea = new TextField();
    private Label commandPromptLabel;

    /**
     * This method sets up the borderwidth, commandPromptTextArea, and label for the CommandPrompt
     * Assumptions:
     * @param resources- the resources bundle to get correct default borderWidth and text for the label
     */
    public void setUpWindowComponent(ResourceBundle resources){
        BorderWidths borderWidth = new BorderWidths(Integer.parseInt(resources.getString("BORDER_WIDTH")));
        commandPromptTextArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, borderWidth)));
        commandPromptLabel = new Label(resources.getString("COMMAND_PROMPT_LABEL"));
        commandPromptLabel.setLabelFor(commandPromptTextArea);
    }

    /**
     * This method returns the TextField associated with the CommandPrompt WindowComponent
     * Assumptions:
     * @return- the TextField
     */
    public TextField getComponentNode() {
        return commandPromptTextArea;
    }

    /**
     * This method returns the Label associated with the CommandPrompt WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return commandPromptLabel;
    }
}

