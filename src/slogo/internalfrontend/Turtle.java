package slogo.internalfrontend;

import javafx.scene.image.ImageView;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class represents a single turtle to be shown in the display and manipulated by commands
 * Dependencies:
 */
public class Turtle {
    private ImageView turtleImage;
    private double homeXCor;
    private double homeYCor;
    private boolean penStatus;
    private int turtleID;
    private double penColor;
    private double shape;
    private boolean activeStatus;
    private String imageFilePath;
    private ResourceBundle turtleShapes = ResourceBundle.getBundle("resources/visual/TurtleShapes");

    /**
     * This method sets up the id, turtle image, width, height, home coordinates, pen status, pen color, and active status for a turtle
     * and places the turtle at home.
     * Assumptions: magic numbers used here to size the turtle
     * @param resources- the resources bundle to get the windowParameters from
     */
    public void setUpTurtle(int id, double homeX, double homeY, ResourceBundle resources){
        turtleID = id;
        imageFilePath = resources.getString("TURTLE_IMAGE_PATH");
        turtleImage = new ImageView(imageFilePath);
        int turtleWidth = Integer.parseInt(resources.getString("WINDOW_WIDTH"))*1/10;
        int turtleHeight = Integer.parseInt(resources.getString("WINDOW_WIDTH"))*1/10;
        turtleImage.setFitWidth(turtleWidth);
        turtleImage.setFitHeight(turtleHeight);

        homeXCor = homeX - turtleWidth/2;
        homeYCor = homeY - turtleHeight/2;

        penStatus = true;
        penColor = 1;

        activeStatus = true;
        moveHome();
    }

    /**
     * Returns the active status of the turtle
     * Assumptions:
     * @return- boolean active status of turtle
     */
    public boolean getActiveStatus() {
        return activeStatus;
    }

    /**
     * Sets the active status of the turtle
     * Assumptions:
     * @param set - boolean to set activeStatus to
     */
    public void setActiveStatus(boolean set) {
        activeStatus = set;
    }

    /**
     * Sets the position of the turtle
     * Assumptions:
     * @param xCor- new x cor to set turtle to
     * @param yCor- new y cor to set turtle to
     */
    public void setPosition(double xCor, double yCor){
        turtleImage.setX(xCor);
        turtleImage.setY(yCor);
    }

    /**
     * Sets the visibleStatus of the turtle
     * Assumptions:
     * @param visibleStatus- new visualStatus of turtle
     */
    public void setVisible(boolean visibleStatus){
        turtleImage.setVisible(visibleStatus);
    }

    /**
     * Gets the visibleStatus of the turtle
     * Assumptions:
     * @return - visualStatus of turtle
     */
    public boolean getVisible(){
        return turtleImage.isVisible();
    }

    /**
     * Gets the ImageView associated with the turtle
     * Assumptions:
     * @return - the ImageView
     */
    public ImageView getComponentNode() {
        return turtleImage;
    }

    /**
     * Gets the xcor of the turtle
     * Assumptions:
     * @return - xcor of turtle
     */
    public double getXCor(){
        return turtleImage.getX() - homeXCor;
    }

    /**
     * Gets the ycor of the turtle
     * Assumptions:
     * @return - ycor of turtle
     */
    public double getYCor(){
        return turtleImage.getY() - homeYCor;
    }

    /**
     * Gets the penColor of the turtle
     * Assumptions:
     * @return - penColor of turtle
     */
    public double getPenColor() {
        return penColor;
    }

    /**
     * Gets the index of the turtle shape
     * Assumptions:
     * @return - index of the shape of turtle
     */
    public double getShape() { return shape; }

    /**
     * Sets the penColor of the turtle
     * Assumptions:
     * @param newPenColor- new penColor of turtle
     */
    public void setPenColor(double newPenColor) {
        penColor = newPenColor;
    }

    /**
     * Sets the shape of the turtle based off an index
     * Assumptions:
     * @param newShape- new shape index of turtle
     */
    public void setShape(double newShape) {
        shape = newShape;
        imageFilePath = turtleShapes.getString(Double.toString(shape));
    }

    /**
     * Gets the penStatus of the turtle
     * Assumptions:
     * @return - penStatus of turtle
     */
    public boolean getPenStatus() { return penStatus; }

    /**
     * Sets the penStatus of the turtle
     * Assumptions:
     * @param newStatus- new penStatus of turtle
     */
    public void setPenStatus(boolean newStatus){
        penStatus = newStatus;
    }

    /**
     * Sets the position of the turtle to hits home coordinates
     * Assumptions:
     */
    public void moveHome(){
        setPosition(homeXCor, homeYCor);
    }

    /**
     * Gets the heading of the turtle
     * Assumptions:
     * @return- the heading of the turtle represented as 0->360 degrees
     */
    public double getHeading(){
        double heading = turtleImage.getRotate()%360;
        if (heading<0){
            return Math.abs(heading);
        }
        return 360 - heading;
    }

    /**
     * Sets the heading of the turtle
     * Assumptions:
     * @param degrees - the new heading of the turtle represented as 0->360 degrees
     */
    public void setHeading(double degrees){
        turtleImage.setRotate(degrees);
    }

    /**
     * Gets the ID of the turtle
     * Assumptions:
     * @return- the ID of the turtle
     */
    public int getID() {
        return turtleID;
    }

    /**
     *
     * @return
     */
    public String getImageFilePath() {
        return imageFilePath;
    }

    /**
     *
     * @param newImagePath
     */
    public void setImageFilePath(String newImagePath) {
        imageFilePath = newImagePath;
    }
}
