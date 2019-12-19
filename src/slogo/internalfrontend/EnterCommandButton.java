package slogo.internalfrontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import slogo.externalbackend.CommandParser;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a EnterCommandButton for input from user
 * Dependencies: WindowComponent, CommandHistoryBox, CommandPrompt, CommandParser
 */
public class EnterCommandButton implements WindowComponent {
    private Button enterCommandButton;
    private Label enterCommandButtonLabel;

    /**
     * This method creates the button and label for the EnterCommandButton
     * Assumptions:
     * @param resources- the resources bundle to get correct text for the button and text for the label
     */
    public void setUpWindowComponent(ResourceBundle resources){
        enterCommandButton = new Button(resources.getString("ENTER_COMMAND_BUTTON_LABEL"));
        enterCommandButtonLabel = new Label(resources.getString("ENTER_COMMAND_BUTTON_LABEL"));
    }

    /**
     * This method returns the Button associated with the EnterCommandButton WindowComponent
     * Assumptions:
     * @return- the Button
     */
    public Button getComponentNode(){
        return enterCommandButton;
    }

    /**
     * This method returns the Label associated with the EnterCommandButton WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return enterCommandButtonLabel;
    }

    /**
     * This method sets the button action of the Button associated with the EnterCommandButton WindowComponent
     * Assumptions:
     * @param commandHistoryBox- the commandHistoryBox to write the entered command to
     * @param commandPrompt- the commandPrompt to write the entered command to
     * @param parser- the commandParser to pass the user entered string
     */
    public void setButtonAction(CommandHistoryBox commandHistoryBox, CommandPrompt commandPrompt, CommandParser parser){
        enterCommandButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event) {
                writeOutput(commandHistoryBox.getComponentNode(), commandPrompt.getComponentNode().getText());
                try {
                    parser.parseAll(commandPrompt.getComponentNode().getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void writeOutput(TextArea outputArea, String msg)
    {
        outputArea.appendText(msg + "\n");
    }
}
