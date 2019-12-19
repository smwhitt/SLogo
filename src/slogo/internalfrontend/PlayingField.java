package slogo.internalfrontend;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as the playingField of the turtles
 * Dependencies: WindowComponent
 */
public class PlayingField implements WindowComponent {
    private Rectangle rectanglePlayingField;
    private double homeX;
    private double homeY;

    /**
     * This method sets up the positioning and parameters of the Rectangle associated with PlayingField
     * Assumptions: magic numbers used here to size the playing field
     * @param resources- the resources bundle to get the windowParameters from
     */
    public void setUpWindowComponent(ResourceBundle resources){
        int borderWidth = Integer.parseInt(resources.getString("BORDER_WIDTH"));
        int windowWidth = Integer.parseInt(resources.getString("WINDOW_WIDTH"));
        int windowHeight = Integer.parseInt(resources.getString("WINDOW_HEIGHT"));
        int playingFieldWidth = windowWidth*1/2;
        int playingFieldHeight = windowHeight*3/4;
        int playingFieldXPos = windowWidth/32;
        int playingFieldYPos = windowHeight/16;
        rectanglePlayingField = new Rectangle(playingFieldXPos, playingFieldYPos, playingFieldWidth, playingFieldHeight);
        rectanglePlayingField.setStroke(Color.BLACK);
        rectanglePlayingField.setFill(Color.WHITE);
        rectanglePlayingField.setStrokeWidth(borderWidth);
        homeX = playingFieldXPos + rectanglePlayingField.getWidth()/2;
        homeY = playingFieldYPos + rectanglePlayingField.getHeight()/2;
    }

    /**
     * Returns the x coordinate of the home position
     * @return- XCor of home position
     */
    public double getHomeX(){
        return homeX;
    }

    /**
     * Returns the y coordinate of the home position
     * @return- YCor of home position
     */
    public double getHomeY(){
        return homeY;
    }

    /**
     * Returns the Rectangle associated with the PlayingField
     * @return- the Rectangle
     */
    public Rectangle getComponentNode() {
        return rectanglePlayingField;
    }

    /**
     * Returns the Label associated with the PlayingField
     * @return- the Label
     */
    public Label getComponentLabel() {
        return null;
    }
}
