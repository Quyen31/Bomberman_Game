package view;

import Contruction.Image_Game;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import model.Music;

public class GameEnd extends ShowView {
    private Stage menuStage;
    private Background background;

    private int timeEnd = 0;
    private AnimationTimer gameEnd;

    private static Music musicEnd;
    private static Music musicMenu;
    public GameEnd() {
        super("Game End");
        Stage.show();
    }
    public void gameEnd(Stage menuStage, Music musicMenu, boolean gameOver) {
        this.menuStage = menuStage;
        this.musicMenu = musicMenu;
        gameEnd = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeEnd ++;
                if (timeEnd == 200) {
                    gameEnd.stop();
                    Stage.close();
                    menuStage.show();
                    musicMenu.clip.setMicrosecondPosition(0);
                    musicMenu.musicLoop();
                }
            }
        };
        background = new Background(backgroundImage1);
        Pane.setBackground(background);
        if ( gameOver == false ) {
            musicEnd = new Music(music_Win_url);
            Image_Game.insertImage(SCREEN_WIDTH/2 - (int)image_game_win.getWidth()/2, SCREEN_HEIGHT/2 - (int)image_game_win.getHeight()/2, new ImageView(), image_game_win, Pane);
            musicEnd.musicStart(100);

        }
        else {
            musicEnd = new Music(music_Over_url);
            Image_Game.insertImage(SCREEN_WIDTH/2 - (int)image_game_over.getWidth()/2, SCREEN_HEIGHT/2 - (int)image_game_over.getHeight()/2, new ImageView(), image_game_over, Pane);
            musicEnd.musicStart(150);
        }

        gameEnd.start();
    }
}
