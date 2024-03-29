package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import model.Music;
import model.SpacaRunnerSubScene;
import model.SpaceRunnerButton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewManager extends ShowView {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 600;

    private static final int MENU_BUTTON_X = 100;
    private static final int MENU_BUTTON_Y = 100;

    private static SpacaRunnerSubScene credistsSubScene = new SpacaRunnerSubScene();
    private static SpacaRunnerSubScene helpSubScene = new SpacaRunnerSubScene();
    private static SpacaRunnerSubScene scoreSubScene = new SpacaRunnerSubScene();

    private static SpacaRunnerSubScene sceneToHide;
    List<SpaceRunnerButton> MenuButton ;
//    private AnchorPane Pane;
//    private Scene Scene;
//    private Stage Stage;

    private static Music musicMenu;


    public ViewManager() throws IOException {
        super("Menu Bomberman");
        MenuButton = new ArrayList<>();
        creteBackground();
        createButtons();
        textBackground();
        createSubScenes();
        musicMenu = new Music(music_menu_url);
        musicMenu.musicLoop();
    }

    public Stage getMainStage() {
        return Stage;
    }

    private void addMenuButton(SpaceRunnerButton button) {
        button.setLayoutX(MENU_BUTTON_X);
        button.setLayoutY(MENU_BUTTON_Y + MenuButton.size() * 100);
        MenuButton.add(button);
        Pane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createHelpButton();
        createScoreButton();
        createCredistsButton();
        createExitButton();
    }

    private void createSubScenes() {
        Pane.getChildren().add(credistsSubScene);
        Pane.getChildren().add(helpSubScene);
        Pane.getChildren().add(scoreSubScene);

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

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                musicMenu.clip.stop();
                MENU_LEVEL menu_level = new MENU_LEVEL();
                menu_level.createNewGame(Stage, 0);
                MENU_LEVEL.musicMenu = musicMenu;
            }
        });
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
                Stage.close();
            }
        });
    }

    private void creteBackground() {
        Pane.setBackground(new Background(backgroundImage1));
    }

    private void textBackground() {
         ImageView image2 = new ImageView(new File("src/main/resources/Resources/text_Bomberman.png").toURI().toString());
         image2.setLayoutX(500);
         image2.setLayoutY(30);
         Pane.getChildren().add(image2);
    }
}
