package slogo.internalfrontend;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a color selector for the PlayingField color
 * Dependencies: WindowComponent, PlayingField
 */
public class BackgroundColorSelector implements WindowComponent{
    private ColorPicker backgroundColorPicker = new ColorPicker();
    private Label backgroundColorPickerLabel;

    /**
     * This method sets up the label for the BackGroundColorSelector
     * Assumptions:
     * @param resources- the resources bundle to get the text for the label
     */
    public void setUpWindowComponent(ResourceBundle resources) {
        backgroundColorPickerLabel = new Label(resources.getString("BACKGROUND_COLOR_SELECTOR_LABEL"));
        backgroundColorPickerLabel.setLabelFor(backgroundColorPicker);
    }

    /**
     * This method returns the ColorPicker associated with the BackgroundColorSelector WindowComponent
     * Assumptions:
     * @return- the ColorPicker
     */
    public ColorPicker getComponentNode() {
        return backgroundColorPicker;
    }

    /**
     * This method returns the Label associated with the BackgroundColorSelector WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return backgroundColorPickerLabel;
    }

    /**
     * This method sets the action for the backgroundColorPicker to set the fill of the playingField to the color chosen
     * Assumptions:
     * @param playingField- The playingfield object we want the ColorPicker to control
     */
    public void setSelectorAction(PlayingField playingField){
        backgroundColorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                playingField.getComponentNode().setFill(backgroundColorPicker.getValue());
            }
        });
    }
}
