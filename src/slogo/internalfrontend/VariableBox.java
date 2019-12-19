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
 * This class implements WindowComponent to serve as a VariableBox to show user defined variables
 * Dependencies: WindowComponent
 */
public class VariableBox implements WindowComponent{
    private TextArea variableBoxTextArea = new TextArea();
    private Label variableBoxLabel;

    /**
     * This method sets up the TextArea and label for the VariableBox
     * Assumptions: magic numbers used to set some parameters of the text area
     * @param resources- the resources bundle to get the text for the label and the border width
     */
    public void setUpWindowComponent(ResourceBundle resources) {
        BorderWidths borderWidth = new BorderWidths(Integer.parseInt(resources.getString("BORDER_WIDTH")));
        variableBoxTextArea.setEditable(false);
        variableBoxTextArea.setStyle("-fx-opacity: 1;");
        variableBoxTextArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, borderWidth)));

        variableBoxLabel = new Label(resources.getString("VARIABLE_BOX_LABEL"));
        variableBoxLabel.setLabelFor(variableBoxTextArea);
    }

    /**
     * This method returns the TextArea associated with the VariableBox WindowComponent
     * Assumptions:
     * @return- the TextArea
     */
    public TextArea getComponentNode() {
        return variableBoxTextArea;
    }

    /**
     * This method returns the Label associated with the VariableBox WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return variableBoxLabel;
    }
}
