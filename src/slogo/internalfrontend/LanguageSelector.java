package slogo.internalfrontend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import slogo.externalbackend.CommandParser;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a LanguageSelector for the interpreter language
 * Dependencies: WindowComponent, CommandParser
 */
public class LanguageSelector implements WindowComponent {
    private ComboBox languageSelectorBox;
    private Label languageSelectorLabel;

    /**
     * This method sets up the ComboBox and label for the LanguageSelector
     * Assumptions:
     * @param resources- the resources bundle to get the text for the label and the URL path
     */
    public void setUpWindowComponent(ResourceBundle resources){
        languageSelectorBox = new ComboBox();
        languageSelectorLabel = new Label(resources.getString("LANGUAGE_SELECTOR_LABEL"));
    }

    /**
     * This method sets up the content of the ComboBox with the language options
     * Assumptions:
     * @param resources- the resources bundle to get the language options from
     */
    public void setLanguages(ResourceBundle resources){
        Enumeration<String> enumeration = resources.getKeys();
        while (enumeration.hasMoreElements()) {
            languageSelectorBox.getItems().add(enumeration.nextElement());
        }
    }

    /**
     * This method returns the ComboBox associated with the LanguageSelector WindowComponent
     * Assumptions:
     * @return- the ComboBox
     */
    public ComboBox getComponentNode(){
        return languageSelectorBox;
    }

    /**
     * This method returns the Label associated with the LanguageSelector WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return languageSelectorLabel;
    }

    /**
     * Sets the action of the language selector to set the interpreter language on a new choice
     * @param parser- the CommandParser to affect
     * Assumptions:
     */
    public void setSelectorAction(CommandParser parser) {
        languageSelectorBox.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        parser.selectLanguage(newValue.toString());
                    }
                }
        );
    }
}
