package slogo.internalfrontend;

import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a HelpPage for the basic commmands
 * Dependencies: WindowComponent
 */
public class HelpPageBasic implements WindowComponent{
    private Label helpBrowserLabel;
    private WebView helpWebView;
    private WebEngine webEngine;

    /**
     * This method sets up the WebView, WebEngine, and label for the HelpPageBasic
     * Assumptions:
     * @param resources- the resources bundle to get the text for the label and the URL path
     */
    public void setUpWindowComponent(ResourceBundle resources) {
        helpWebView = new WebView();
        webEngine = helpWebView.getEngine();
        helpBrowserLabel = new Label(resources.getString("HELP_PAGE_BASIC_LABEL"));
        webEngine.load(resources.getString("HELP_PAGE_BASIC_URL"));
    }

    /**
     * This method returns the WebView associated with the HelpPageBasic WindowComponent
     * Assumptions:
     * @return- the WebView
     */
    public WebView getComponentNode(){
        return helpWebView;
    }

    /**
     * This method returns the Label associated with the HelpPageBasic WindowComponent
     * Assumptions:
     * @return- the Label
     */
    public Label getComponentLabel() {
        return helpBrowserLabel;
    }
}
