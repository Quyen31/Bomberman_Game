package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.SpacaRunnerSubScene;
import model.SpaceRunnerButton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 600;

    private static final int MENU_BUTTON_X = 100;
    private static final int MENU_BUTTON_Y = 100;

    private static SpacaRunnerSubScene credistsSubScene = new SpacaRunnerSubScene();
    private static SpacaRunnerSubScene helpSubScene = new SpacaRunnerSubScene();
    private static SpacaRunnerSubScene scoreSubScene = new SpacaRunnerSubScene();

    private static SpacaRunnerSubScene sceneToHide;
    List<SpaceRunnerButton> MenuButton ;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;


    public ViewManager() throws IOException {
        MenuButton = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setTitle("Bomberman");
        mainStage.setScene(mainScene);
        creteBackground();
        createButtons();
        textBackground();
        createSubScenes();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(SpaceRunnerButton button) {
        button.setLayoutX(MENU_BUTTON_X);
        button.setLayoutY(MENU_BUTTON_Y + MenuButton.size() * 100);
        MenuButton.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createHelpButton();
        createScoreButton();
        createCredistsButton();
        createExitButton();
    }

    private void createSubScenes() {
        mainPane.getChildren().add(credistsSubScene);
        mainPane.getChildren().add(helpSubScene);
        mainPane.getChildren().add(scoreSubScene);

    }

    private void showSubScene(SpacaRunnerSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createStartButton() {
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);

    }

    private void createScoreButton() {
        SpaceRunnerButton scoretButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoretButton);
        scoretButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubScene);
            }
        });
    }

    private void createHelpButton() {
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubScene);
            }
        });
    }

    private void createCredistsButton() {
        SpaceRunnerButton credistsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(credistsButton);

        credistsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(credistsSubScene);
            }
        });
    }

    private void createExitButton() {
        SpaceRunnerButton ExitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(ExitButton);

        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }

    private void creteBackground() {

        Image image1 = new Image(new File("src/main/resources/Resources/blue.png").toURI().toString(),256, 256, false, true);
        BackgroundImage backgroundImage1 = new BackgroundImage(image1,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(backgroundImage1));
    }

    private void textBackground() {
         ImageView image2 = new ImageView(new File("src/main/resources/Resources/text_Bomberman.png").toURI().toString());
         image2.setLayoutX(500);
         image2.setLayoutY(30);
         mainPane.getChildren().add(image2);
    }
}
