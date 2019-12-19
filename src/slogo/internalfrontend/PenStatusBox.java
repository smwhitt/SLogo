package slogo.internalfrontend;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import java.util.ResourceBundle;

public class PenStatusBox implements WindowComponent{
    private TextArea penStatusBoxTextArea = new TextArea();
    private Label penStatusBoxLabel;

    public void setUpWindowComponent(ResourceBundle resources) {
        BorderWidths borderWidth = new BorderWidths(Integer.parseInt(resources.getString("BORDER_WIDTH")));
        penStatusBoxTextArea.setEditable(false);
        penStatusBoxTextArea.setStyle("-fx-opacity: 1;");
        penStatusBoxTextArea.setMaxHeight(penStatusBoxTextArea.getHeight()/2);
        penStatusBoxTextArea.setText("pen status goes here");
        penStatusBoxTextArea.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, borderWidth)));
        penStatusBoxLabel = new Label(resources.getString("PEN_STATUS_BOX_LABEL"));
        penStatusBoxLabel.setLabelFor(penStatusBoxTextArea);
    }

    public TextArea getComponentNode() {
        return penStatusBoxTextArea;
    }

    public Label getComponentLabel() {
        return penStatusBoxLabel;
    }
}
