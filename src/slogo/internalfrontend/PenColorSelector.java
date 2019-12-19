package slogo.internalfrontend;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a color selector for the Pen color
 * Dependencies: WindowComponent, PlayingField
 */
public class PenColorSelector implements WindowComponent{
    private ColorPicker penColorPicker = new ColorPicker();
    private Label penColorPickerLabel;

    /**
     * This method sets up the label for the PenColorSelector
     * Assumptions:
     * @param resources- the resources bundle to get the text for the label
     */
    public void setUpWindowComponent(ResourceBundle resources) {
        penColorPickerLabel = new Label(resources.getString("PEN_COLOR_SELECTOR_LABEL"));
        penColorPickerLabel.setLabelFor(penColorPicker);
    }

    /**
     * This method returns the ColorPicker associated with the PenColorSelector WindowComponent
     * Assumptions:
     * @return- the ColorPicker
     */
    public ColorPicker getComponentNode() {
        return penColorPicker;
    }

    /**
     * This method returns the Label associated with the PenColorSelector WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return penColorPickerLabel;
    }

    /**
     * This method sets the action for the PenColorPicker to set the color of pathGroup
     * Assumptions:
     * @param pathGroup- The PathGroup object we want the ColorPicker to control
     */
    public void setSelectorAction(PathGroup pathGroup){
        penColorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                pathGroup.setPathColor(penColorPicker.getValue());
            }
        });
    }
}
