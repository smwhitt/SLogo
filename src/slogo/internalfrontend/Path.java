package slogo.internalfrontend;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class implements WindowComponent to serve as a Path to track turtle movement
 * Dependencies: WindowComponent
 */
public class Path implements WindowComponent {
    private Line penLine;
    private double previousX;
    private double previousY;

    /**
     * This method sets up the Line for the Path
     * Assumptions:
     */
    public void setUpWindowComponent(ResourceBundle resources) {
        penLine = new Line();
    }

    /**
     * This method returns the Line associated with the Path WindowComponent
     * Assumptions:
     * @return- the Line
     */
    public Line getComponentNode() {
        return penLine;
    }

    public Label getComponentLabel() { return null; }

    /**
     * This method sets the start and endpts of the line
     * Assumptions:
     * @param xCor- the new endpt Xcor of the line
     * @param yCor- the new endpt Ycor of the line
     */
    public void setPosition(double xCor, double yCor) {
        penLine.setEndX(previousX);
        penLine.setEndY(previousY);
        penLine.setStartX(xCor);
        penLine.setStartY(yCor);
        previousX = xCor;
        previousY = yCor;
    }

    /**
     * This method sets the variable for start pt of a line to be used in setPosition when a turtle moves
     * Assumptions:
     * @param xCor- the new startpt Xcor of the line
     * @param yCor- the new startpt Ycor of the line
     */
    public void setInitialPosition(double xCor, double yCor){
        previousX = xCor;
        previousY = yCor;
    }
}
