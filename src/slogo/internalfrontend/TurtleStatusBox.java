package slogo.internalfrontend;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import java.util.ResourceBundle;

public class TurtleStatusBox implements WindowComponent{
    private TextArea turtleStatusBoxTextArea = new TextArea();
    private Label turtleStatusBoxLabel;

    public void setUpWindowComponent(ResourceBundle resources) {
        BorderWidths borderWidth = new BorderWidths(Integer.parseInt(resources.getString("BORDER_WIDTH")));
        turtleStatusBoxTextArea.setEditable(false);
        turtleStatusBoxTextArea.setStyle("-fx-opacity: 1;");
        turtleStatusBoxTextArea.setMaxHeight(turtleStatusBoxTextArea.getHeight()/2);
        turtleStatusBoxTextArea.setText("turtle status goes here");
        turtleStatusBoxTextArea.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, borderWidth)));
        turtleStatusBoxLabel = new Label(resources.getString("TURTLE_STATUS_BOX_LABEL"));
        turtleStatusBoxLabel.setLabelFor(turtleStatusBoxTextArea);
    }

    public TextArea getComponentNode() {
        return turtleStatusBoxTextArea;
    }

    public Label getComponentLabel() {
        return turtleStatusBoxLabel;
    }
}
