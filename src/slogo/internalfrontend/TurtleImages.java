package slogo.internalfrontend;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.exceptions.FileException;
import slogo.externalbackend.ParserManager;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This view is an option in the combobox that allows the user to on-demand click on any of the turtles' images and
 * instantaneously change it to whatever .png image on the computer
 * @author Samantha Whitt
 */
public class TurtleImages implements WindowComponent {
    private final String SCROLL_WIDTH = "image_selector_width", SCROLL_HEIGHT = "image_selector_height", PNG = ".png",
            PNG_ERROR = "Please select a file with a .png extension", FILE_ERROR = "Please select a file from your computer";
    private final int PNG_END = 4;

    private ParserManager myParserManager;
    private ScrollPane myPane;
    private ResourceBundle myResourceBundle;

    /**
     * Creates an instance of the view to be added in the combobox
     * @param parserManager
     * @throws FileException
     */
    public TurtleImages(Object parserManager) throws FileException {
        myParserManager = (ParserManager) parserManager;
        myPane = new ScrollPane();
    }

    /**
     * Sets up the a scrollpane to show all of the current turtles' images whether active or not
     * @param resources
     */
    @Override
    public void setUpWindowComponent(ResourceBundle resources) {
        myResourceBundle = resources;
        myPane.setMaxWidth(Double.parseDouble(myResourceBundle.getString(SCROLL_WIDTH)));
        myPane.setMaxHeight(Double.parseDouble(myResourceBundle.getString(SCROLL_HEIGHT)));
        myPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        myPane.setFitToWidth(true);
    }

    /**
     * Called each time the view is chosen from the combobox to "fill in" the scrollpane with all of the
     * turtles' images
     * @return
     */
    @Override
    public Node getComponentNode() {
        BorderPane pane = new BorderPane();
        Pane turtleImages = getTurtleImages();
        myPane.setContent(turtleImages);
        pane.setCenter(myPane);
        return pane;
    }

    /**
     * @return null
     */
    @Override
    public Label getComponentLabel() {
        return null;
    }

    private Pane getTurtleImages() {
        VBox vbox = new VBox();
        List<Turtle> turtles = myParserManager.getListOfTurtles();
        for (Turtle turtle: turtles) {
            ImageView copyTurtleImage = new ImageView(new Image(turtle.getImageFilePath()));
            copyTurtleImage.setFitWidth(Double.parseDouble(myResourceBundle.getString(SCROLL_WIDTH)));
            copyTurtleImage.setFitHeight(Double.parseDouble(myResourceBundle.getString(SCROLL_HEIGHT)));
            vbox.getChildren().add(createClickableImage(copyTurtleImage, turtle.getID()));
        }
        return vbox;
    }

    private Node createClickableImage(Node imageView, int id) {
        imageView.setOnMouseClicked(handler -> selectImage((ImageView) imageView, id));
        return imageView;
    }

    private void selectImage(ImageView imageView, int id) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                if (!selectedFile.getName().substring(selectedFile.getName().length()-PNG_END).equals(PNG)) {
                    throw new FileException(PNG_ERROR);
                }
                Image fileInput = new Image(new FileInputStream(selectedFile));
                imageView.setImage(fileInput);
                myParserManager.getTurtleFromID(id).setImageFilePath(selectedFile.toString());
                myParserManager.getTurtleFromID(id).getComponentNode().setImage(fileInput);
            } catch (Exception e) {
                throw new FileException(FILE_ERROR);
            }
        }
    }
}
