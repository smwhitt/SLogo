package slogo.internalfrontend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.util.*;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a WindowComponentSelector for the displayed WindowComponent
 * Dependencies: WindowComponent
 */
public class WindowComponentSelector implements WindowComponent {
    private ComboBox windowComponentSelectorBox;
    private Label windowComponentSelectorLabel;

    /**
     * This method sets up the ComboBox and label for the WindowComponentSelector
     * Assumptions:
     * @param resources- the resources bundle to get the text for the label
     */
    public void setUpWindowComponent(ResourceBundle resources){
        windowComponentSelectorBox = new ComboBox();
        windowComponentSelectorLabel = new Label(resources.getString("WINDOW_COMPONENT_SELECTOR_LABEL"));
    }

    /**
     * This method sets the options of the ComboBox based on the WindowComponents in IncludedWindowComponents.properties
     * excluding those mentioned in the SelectorExcludedWindowComponents.properties
     * Assumptions: the path for the SelectorExcludedWindowComponents.properties is hardcoded
     * @param allWindowComponentsResources- the resources bundle to get all windowComponents to be included in the display
     */
    public void setWindowComponents(ResourceBundle allWindowComponentsResources){
        ResourceBundle excludedWindowComponentsResources = ResourceBundle.getBundle("resources/visual/SelectorExcludedWindowComponents");
        Enumeration<String> enumeration = allWindowComponentsResources.getKeys();
        ArrayList<String> excludedWindowComponentChoices = new ArrayList<String>();
        excludedWindowComponentChoices.addAll(Collections.list(excludedWindowComponentsResources.getKeys()));
        boolean excludedFlag = false;
        while (enumeration.hasMoreElements()) {
            String nextElement = enumeration.nextElement();
            for (String excluded : excludedWindowComponentChoices){
                if (nextElement.equals(excluded)){
                    excludedFlag = true;
                }
            }
            if (!excludedFlag){
                windowComponentSelectorBox.getItems().add(nextElement);
            }
            excludedFlag = false;
        }
    }

    /**
     * This method returns the ComboBox associated with the WindowComponentSelector WindowComponent
     * Assumptions:
     * @return- the ComboBox
     */
    public ComboBox getComponentNode(){
        return windowComponentSelectorBox;
    }

    /**
     * This method returns the Label associated with the WindowComponentSelector WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return windowComponentSelectorLabel;
    }

    /**
     * This method sets the action of the selector to display the chosen windowComponent and its label on the right side of the screen
     * Assumptions:
     * @param root- the borderpane we are using to control what is being displayed
     * @param windowComponentMap- the map of all windowComponents used to get the new component to be displayed
     */
    public void setSelectorAction(BorderPane root, Map<String, WindowComponent> windowComponentMap) {
        windowComponentSelectorBox.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        for (WindowComponent wc : windowComponentMap.values()){
                            if (wc.getClass().toString().equals("class slogo.internalfrontend."+newValue)){
                                root.setLeft(wc.getComponentLabel());
                                root.setCenter(wc.getComponentNode());
                            }
                        }

                    }
                }
        );
    }
}
