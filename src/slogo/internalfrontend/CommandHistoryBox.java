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
 * This class implements WindowComponent to serve as a CommandHistoryBox for executed user commands
 * Dependencies: WindowComponent
 */
public class CommandHistoryBox implements WindowComponent{
    private TextArea commandHistoryTextArea = new TextArea();
    private Label commandHistoryLabel;

    /**
     * This method sets up the borderwidth, commandHistoryTextArea, and label for the CommandHistoryBox
     * Assumptions:
     * @param resources- the resources bundle to get correct default borderWidth and text for the label
     */
    public void setUpWindowComponent(ResourceBundle resources) {
        BorderWidths borderWidth = new BorderWidths(Integer.parseInt(resources.getString("BORDER_WIDTH")));
        commandHistoryTextArea.setEditable(false);
        commandHistoryTextArea.setStyle("-fx-opacity: 1;");
        commandHistoryTextArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, borderWidth)));

        commandHistoryLabel = new Label(resources.getString("COMMAND_HISTORY_BOX_LABEL"));
        commandHistoryLabel.setLabelFor(commandHistoryTextArea);
    }

    /**
     * This method returns the TextArea associated with the CommandHistoryBox WindowComponent
     * Assumptions:
     * @return- the TextArea
     */
    public TextArea getComponentNode() {
        return commandHistoryTextArea;
    }

    /**
     * This method returns the Label associated with the CommandHistoryBox WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return commandHistoryLabel;
    }
}
