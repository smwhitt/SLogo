package slogo.internalfrontend;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class TurtleManager {

    /**
     * @author Megan Lemcke
     * This class serves to break up the functionality of WindowManager by extracting the methods related to the visual
     * commands for the turtle.
     */
    private static ResourceBundle turtleShapes = ResourceBundle.getBundle("resources/visual/TurtleShapes");

    /**
     * This method will be used to visually move the turtle FORWARD in response to information from the slogo.externalfrontend.
     */
    public static void moveTurtleForward(ArrayList<Double> arg, Turtle turtle) {
        double xPosition = turtle.getComponentNode().getX();
        double yPosition = turtle.getComponentNode().getY();
        double rotate = Math.toRadians(turtle.getComponentNode().getRotate());

        double deltaX = Math.cos(rotate) * arg.get(0);
        double deltaY = Math.sin(rotate) * arg.get(0);

        double newXCor = xPosition + deltaX;
        double newYCor = yPosition + deltaY;
        WindowManager.drawPenLine(newXCor, newYCor, turtle);
        turtle.setPosition(newXCor, newYCor);
    }

    /**
     * This method will be used to visually move the turtle BACKWARD in response to information from the slogo.externalfrontend.
     */
    public static void moveTurtleBackward(ArrayList<Double> arg, Turtle turtle) {
        arg.set(0, arg.get(0)*-1);
        moveTurtleForward(arg, turtle);
    }

    /**
     * This method will be used to ROTATE the turtle the given amount of degrees in response to information
     * from the slogo.externalfrontend.
     */
    public static void rotateTurtle(ArrayList<Double> arg, Turtle turtle) {
        turtle.setHeading(turtle.getComponentNode().getRotate() + arg.get(0));
    }

    /**
     * This method will be used to ROTATE the turtle towards a given pixel position in response to information
     * from the slogo.externalfrontend.
     * ex) setTurtleTowards 50 50 will turn the heading of the turtle to 45 degrees.
     */
    public static void setTurtleTowards(ArrayList<Double> arg, Turtle turtle) {
        double rads = Math.atan((arg.get(2) - turtle.getYCor()) / (arg.get(1) - turtle.getXCor()));
        turtle.setHeading(Math.toDegrees(rads));
    }

    /**
     * This method will be used to move the turtle's location to the x-coordinate and y-coordinate in response
     * to information from the slogo.externalfrontend.
     */
    public static void setTurtlePosition(ArrayList<Double> arg, Turtle turtle, PlayingField playingField) {
        turtle.setPosition(arg.get(1) + playingField.getHomeX(), arg.get(2) + playingField.getHomeY());
    }

    /**
     * This method will be used to ROTATE the turtle to the provided heading in degrees, in response to information
     * from the slogo.externalfrontend.
     */
    public static void setTurtleRotate(ArrayList<Double> arg, Turtle turtle){
        turtle.setHeading(arg.get(0));
    }

    /**
     * This method will be used to trace the turtle's movement and display pen movements in response to information
     * from the slogo.externalfrontend.
     */
    public static void setPenDown(ArrayList<Double> arg, Turtle turtle) {
        turtle.setPenStatus(true);
    }

    /**
     * This method will be used to turn OFF the tracing of the turtle's movement and NOT display pen movements in
     * response to information from the slogo.externalfrontend.
     */
    public static void setPenUp(ArrayList<Double> arg, Turtle turtle) {
        turtle.setPenStatus(false);
    }

    /**
     * This method will be used to make the turtle visible in response to information
     * from the slogo.externalfrontend.
     */
    public static void showTurtle(ArrayList<Double> arg, Turtle turtle) {
        turtle.setVisible(true);
    }

    /**
     * This method will be used to make the turtle invisible in response to information
     * from the slogo.externalfrontend.
     */
    public static void hideTurtle(ArrayList<Double> arg, Turtle turtle) {
        turtle.setVisible(false);
    }

    /**
     * This method will be used to retrieve the current X position of the turtle with respect to the home position/
     * center of the playing field.
     */
    public static void getX(ArrayList<Double> arg, Turtle turtle) { turtle.getXCor(); }

    /**
     * This method will be used to retrieve the current Y position of the turtle with respect to the home position/
     * center of the playing field.
     */
    public static void getY(ArrayList<Double> arg, Turtle turtle) { turtle.getYCor(); }

    /**
     * This method will be used to retrieve the current orientation of the turtle with respect to the home orientation of
     * due East.  The heading is calculated like the unit circle, positively increasing towards 2pi or 360 degrees as
     * the turtle rotates counterclockwise.
     */
    public static void getAngle(ArrayList<Double> arg, Turtle turtle) { turtle.getHeading(); }

    /**
     * This method will be used to retrieve the current pen status of the turtle, whether it is up or down.
     */
    public static void getPenStatus(ArrayList<Double> arg, Turtle turtle) { turtle.getPenStatus(); }

    /**
     * This method will be used to retrieve the current visibility status of the turtle, whether it is visible or hidden.
     */
    public static void getTurtleStatus(ArrayList<Double> arg, Turtle turtle) { turtle.getVisible(); }

    /**
     * This method will be used to retrieve the index of the current pen color of the turtle's path.
     */
    public static void getPenColor(ArrayList<Double> arg, Turtle turtle) { turtle.getPenColor(); }

    /**
     * This method will be used to retrieve the index of the current shape of the turtle.
     */
    public static void getShape(ArrayList<Double> arg, Turtle turtle) { turtle.getShape(); }

    /**
     * This method will be used to send the turtle to the home position (0,0) and erase all pen lines currently displayed
     * on the screen.
     */
    public static void clearScreen(ArrayList<Double> arg, Turtle turtle) {
        turtle.moveHome();
        WindowManager.clearPaths();
    }

    /**
     * This method will be used to send the turtle to the home position (0,0), but leaves all pen lines currently being
     * displayed.
     */
    public static void moveTurtleHome(ArrayList<Double> arg, Turtle turtle) {
        turtle.moveHome();
    }

    /**
     * This method will be used to set the turtle image/shape to one of the files provided in the data package.
     */
    public static void setTurtleImage(ArrayList<Double> arg, Turtle turtle) {
        turtle.setShape(arg.get(0));
        turtle.getComponentNode().setImage(new Image(turtleShapes.getString(Double.toString(turtle.getShape()))));
    }

}
