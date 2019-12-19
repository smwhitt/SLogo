package slogo.internalfrontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This is an interface that will be implemented by all WindowComponents to be displayed
 */
interface WindowComponent {

    /**
     * This method will be implemented by all visual components to set their attributes and ultimately add themselves
     * to the window.
     */
    void setUpWindowComponent(ResourceBundle resources);

    /**
     * This method will be implemented by all visual components to return their JavaFX node object for displaying
     * @return the Node to be displayed
     */
    Node getComponentNode();

    /**
     * This method will be implemented by all visual components to return their label for displaying
     * @return- the Label to be displayed
     */
    Label getComponentLabel();
}
