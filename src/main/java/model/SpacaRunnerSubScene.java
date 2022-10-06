package model;

import Contruction.Image_Game;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.File;

public class SpacaRunnerSubScene extends SubScene implements Image_Game {

    private final static String FONT_PATH = "src/main/resources/Resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = new File("src/main/resources/Resources/yellow_panel.png").toURI().toString();

    private boolean isHidden;
    public SpacaRunnerSubScene() {
        super(new AnchorPane() , 600, 450);
        prefWidth(600);
        prefHeight(450);
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 450, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        isHidden = true;
        setLayoutX(1024);
        setLayoutY(100);

    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if(isHidden) {
            transition.setToX(-650);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();

    }
}
