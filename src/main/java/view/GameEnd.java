package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import Contruction.MusicGame;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import model.Music;

public class GameEnd implements Image_Game, Contruction_Game, MusicGame {
    private AnchorPane gameEndPane;
    private Scene gameEndScene;
    private Stage gameEndStage;
    private Stage menuStage;
    private Background background;

    private int timeEnd = 0;
    private AnimationTimer gameEnd;

    private static Music musicEnd;
    private static Music musicMenu;
    public GameEnd() {
        gameEndPane = new AnchorPane();
        gameEndScene = new Scene(gameEndPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        gameEndPane.setLayoutX(0);
        gameEndPane.setLayoutY(0);
        gameEndStage = new Stage();
        gameEndStage.setTitle("Game End");
        gameEndStage.setScene(gameEndScene);
        gameEndStage.show();
    }
    public void gameEnd(Stage menuStage, Music musicMenu, boolean gameOver) {
        this.menuStage = menuStage;
        this.musicMenu = musicMenu;
        //musicMenu.clip.stop();
        gameEnd = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeEnd ++;
                if (timeEnd == 200) {
                    gameEnd.stop();
                    gameEndStage.close();
                    menuStage.show();
                    musicMenu.clip.setMicrosecondPosition(0);
                    musicMenu.musicLoop();
                }
            }
        };
        background = new Background(backgroundImage1);
        gameEndPane.setBackground(background);
        if ( gameOver == false ) {
            musicEnd = new Music(music_Win_url);
            insertImage(SCREEN_WIDTH/2 - (int)image_game_win.getWidth()/2, SCREEN_HEIGHT/2 - (int)image_game_win.getHeight()/2, new ImageView(), image_game_win, gameEndPane);
            musicEnd.musicStart(100);

        }
        else {
            musicEnd = new Music(music_Over_url);
            insertImage(SCREEN_WIDTH/2 - (int)image_game_over.getWidth()/2, SCREEN_HEIGHT/2 - (int)image_game_over.getHeight()/2, new ImageView(), image_game_over, gameEndPane);
            musicEnd.musicStart(150);
        }

        gameEnd.start();
    }
}
