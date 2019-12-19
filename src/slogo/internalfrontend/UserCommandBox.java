package slogo.internalfrontend;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a UserCommandBox to show user defined commands
 * Dependencies: WindowComponent
 */
public class UserCommandBox implements WindowComponent{
    private TextArea userCommandBoxTextArea = new TextArea();
    private Label userCommandBoxLabel;

    /**
     * This method sets up the TextArea and label for the UserCommandBox
     * Assumptions: magic numbers used to set some parameters of the text area
     * @param resources- the resources bundle to get the text for the label and the border width
     */
    public void setUpWindowComponent(ResourceBundle resources) {
        BorderWidths borderWidth = new BorderWidths(Integer.parseInt(resources.getString("BORDER_WIDTH")));
        userCommandBoxTextArea.setEditable(false);
        userCommandBoxTextArea.setStyle("-fx-opacity: 1;");
        userCommandBoxTextArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, borderWidth)));

        userCommandBoxLabel = new Label(resources.getString("USER_COMMAND_BOX_LABEL"));
        userCommandBoxLabel.setLabelFor(userCommandBoxLabel);
    }

    /**
     * This method returns the TextArea associated with the UserCommandBox WindowComponent
     * Assumptions:
     * @return- the TextArea
     */
    public TextArea getComponentNode() {
        return userCommandBoxTextArea;
    }

    /**
     * This method returns the Label associated with the UserCommandBox WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return userCommandBoxLabel;
    }
}
