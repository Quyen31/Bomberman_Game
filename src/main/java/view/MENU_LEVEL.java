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
import model.Doc_File;
import model.Music;

public class MENU_LEVEL implements Image_Game, Contruction_Game, MusicGame {
    private static AnchorPane gameLevelPane;
    private static Scene gameLevelScene;
    private static Stage menuLevelStage;
    private static Stage gameStage;
    private static Stage menuStage;

    private static ImageView imageView;
    private static ImageView imageView1;

    private static Background background;
    public static Doc_File a;
    public static int index = 0;

    public static int GAME_WIDTH ;// Chiều rộng Game

    public static int GAME_HEIGHT;// Chiều dài Game

    public static int LEVEL;// LEVEL hiện tại

    public static char[][] LEVEL_MAP; // Map LEVEL hiện tại

    private static int timeDelay = 0;

    static AnimationTimer delay;
    private static Music music ;

    public static Music musicMenu;



    public MENU_LEVEL() {
        gameLevelPane = new AnchorPane();
        gameLevelScene = new Scene(gameLevelPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        gameLevelPane.setLayoutX(0);
        gameLevelPane.setLayoutY(0);
        menuLevelStage = new Stage();
        menuLevelStage.setTitle("Start Bomberman");
        menuLevelStage.setScene(gameLevelScene);
        imageView = new ImageView();
        imageView1 = new ImageView();
    }

    private void delayBackground () {
        delay = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeDelay ++;
                if (timeDelay == 200) {
                    timeDelay = 0;
                    delay.stop();
                    inGame();
                }
            }
        };
        background = new Background(backgroundImage1);
        gameLevelPane.setBackground(background);
        insertImage(SCREEN_WIDTH/2 - (int)image_text_level[index].getWidth()/2, SCREEN_HEIGHT/2 - (int)image_text_level[index].getHeight()/2, imageView1, image_text_level[index], gameLevelPane);
        delay.start();
    }

    private void DocFile() {
        a = new Doc_File(ARRAY_URL_LEVEL[index]);
        GAME_WIDTH = a.Arr[2];
        GAME_HEIGHT = a.Arr[1];
        LEVEL = a.Arr[0];
        LEVEL_MAP = new char[GAME_HEIGHT][GAME_WIDTH];
        a.ReaderFile(LEVEL_MAP);

    }

    private void inGame() {
        GameViewManager gameViewManager = new GameViewManager(GAME_WIDTH, GAME_HEIGHT, LEVEL_MAP);
        gameViewManager.createNewGame(menuLevelStage, menuStage);
        music.clip.stop();
    }

    public void createNewGame(Stage stage,int a) {
        if (a == 0) {
            this.menuStage = stage;
            this.menuStage.hide();
        }
        if ( a == 1) {
            this.gameStage = stage;
            this.gameStage.close();
        }
        music = new Music(music_start_url);
        music.clip.start();
        DocFile();
        delayBackground();
        menuLevelStage.show();
    }

}
