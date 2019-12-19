package slogo.internalfrontend;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Matt Harris
 * This class serves to maintain and affect the group of paths being displayed in the window.
 * Dependencies: Path
 */
public class PathGroup {
    private List<Path> pathList = new ArrayList<Path>();;
    private Color currentPathColor;
    private Double currentStrokeWidth;
    private static ResourceBundle windowParametersResources;

    public PathGroup(ResourceBundle resources){
        windowParametersResources = resources;
        int defaultRVal = Integer.parseInt(windowParametersResources.getString("DEFAULT_PEN_R"));
        int defaultGVal = Integer.parseInt(windowParametersResources.getString("DEFAULT_PEN_G"));
        int defaultBVal = Integer.parseInt(windowParametersResources.getString("DEFAULT_PEN_B"));
        currentPathColor = Color.rgb(defaultRVal, defaultGVal, defaultBVal);
        currentStrokeWidth = Double.parseDouble(windowParametersResources.getString("DEFAULT_PEN_WIDTH"));
    }

    /**
     * Adds a given path to the pathlist
     * Assumptions:
     * @param path- the new path to be added
     */
    public void add(Path path){
        pathList.add(path);
    }

    /**
     * clears the pathlist
     * Assumptions:
     */
    public void clear(){
        pathList.clear();
    }

    /**
     * Returns the pathList maintained by the PathGroup
     * Assumptions:
     * @return- the PathList
     */
    public List<Path> getPathList(){
        return pathList;
    }

    /**
     * Sets the color of paths that have yet to be created
     * Assumptions:
     * @param color- the new color
     */
    public void setPathColor(Color color){
        currentPathColor = color;
    }

    /**
     * Creates a new path at the given positions and adds it to the PathGroup
     * Assumptions:
     * @param startX- start x cor of line
     * @param startY- start y cor of line
     * @param endX- end x cor of line
     * @param endY- endY cor of line
     * @param windowParametersResources- resources to set up Path WindowComponent
     */
    public void createNewPath(double startX, double startY, double endX, double endY, ResourceBundle windowParametersResources){
        Path penLine = new Path();
        penLine.setUpWindowComponent(windowParametersResources);
        penLine.setInitialPosition(startX, startY);
        penLine.setPosition(endX, endY);
        penLine.getComponentNode().setStroke(currentPathColor);
        penLine.getComponentNode().setStrokeWidth(currentStrokeWidth);
        add(penLine);
    }
    /**
     * Returns the latest path added to the group
     * Assumptions:
     * @return- the latest path added to the group
     */
    public Path getLatestPath(){
        return pathList.get(pathList.size()-1);
    }

    /**
     * Sets the width of paths that have yet to be created
     * Assumptions:
     * @param strokeWidth- the new strokeWidth
     */
    public void setStrokeWidth(Double strokeWidth){
        currentStrokeWidth = strokeWidth;
    }
}
