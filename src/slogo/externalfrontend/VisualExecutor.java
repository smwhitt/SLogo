package slogo.externalfrontend;

import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.externalbackend.ParserManager;
import slogo.internalfrontend.Turtle;
import slogo.internalfrontend.TurtleManager;
import slogo.internalfrontend.WindowManager;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Matt Harris
 * This class receives all communication from parser to execute commands that update the visual assets of the app
 * and to display error messages.
 * Dependencies: WindowManager, Parser, Turtle, TurtleManager,
 */

public class VisualExecutor {
    /**
     * This method will carry out commands corresponding to the visualization of the program,
     * such as moving the imageview of the turtle, rotating the turtle, putting the pen up or down, etc.
     * This method will be called by the controller.
     */
    private WindowManager windowManager;
    private ResourceBundle commandResources;

    public VisualExecutor(ParserManager parserManager){
        windowManager = new WindowManager(parserManager);
        commandResources = ResourceBundle.getBundle("resources/commands/Commands");
    }

    /**
     * This method creates the Scene by calling WindowManager to setUpWindow
     * Assumptions:
     * @param displayStage- the stage being displayed in the app
     * @return- the created scene with all visual assets added
     */
    public Scene createScene(Stage displayStage){
        return windowManager.setUpWindow(displayStage);
    }

    /**
     * This method executes visual commands parsed and sent by the parser as well as displays errors from parser.
     * Assumptions:
     * @param keyCommand- the stage being displayed in the app
     * @param args- the arguments of the visual command to be used. The first in the list will always be the result
     *            returned from the backend execution of the command and the subsequent list items will be the actual arguments
     *            given to the backend command
     * @param turtle- the turtle to execute the visual command on
     */
    public void executeVisualCommandString(String keyCommand, List<Double> args, Turtle turtle) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            String[] path = commandResources.getString(keyCommand).split(",");
            WindowManager.class.getMethod(path[2], args.getClass(), turtle.getClass()).invoke(windowManager, args, turtle);
        }
        catch(Exception e) {
            try {
                    String[] path = commandResources.getString(keyCommand).split(",");
                    TurtleManager.class.getMethod(path[2], args.getClass(), turtle.getClass()).invoke(null, args, turtle);
                }
            catch(Exception f){
                //handleError();
            }
        }
    }

    /**
     * This method handles an error thrown by the parser
     * Assumptions: only one error will ever be thrown instructing the user to consult the help pages
     */
    public void handleError(){
        windowManager.showErrorPopup();
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }
}
