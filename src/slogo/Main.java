package slogo;

import slogo.externalbackend.CommandParser;
import slogo.externalbackend.ParserManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.externalfrontend.VisualExecutor;

/**
 * @author Matthew Harris, Megan Lemcke, Jerry Wang, and Samantha Whitt
 * Initiates the timeline animation, creates ParserManager and sets up all the components
 * including but not limited to the CommandParser, VisualExecutor, and initial turtle
 */
public class Main extends Application {

    /**
     * Start of the program.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage){
        Timeline myAnimation = new Timeline();
        ParserManager myParserManager = new ParserManager();
        CommandParser myParser = myParserManager.getParser();
        myParser.setUpParser();
        VisualExecutor visualExecutor = myParserManager.getParser().getVisualExecutor();
        primaryStage.setScene(visualExecutor.createScene(primaryStage));
        primaryStage.show();
        myParserManager.addTurtle(1);
        var frame = new KeyFrame(Duration.millis(1000/60), e -> step());
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private void step() {
    }

    /**
     * Main -- runs the entire program
     */
    public static void main (String[] args) {
        launch(args);
    }
}
