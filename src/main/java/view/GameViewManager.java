package view;

import Contruction.Contruction_Game;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Contruction.ViewImage;
import Contruction.Image_Game;

public class GameViewManager implements Image_Game, Contruction_Game {
    private static AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;
    private static final double GAME_WIDTH = 1056;
    private static final double GAME_HEIGHT = 624;
    private static Bomberman_run bombermanRun = new Bomberman_run();;

    private static char level1[][] = new char[100][100];
    private static boolean isLeftKeyPressed;
    private static boolean isRightPressed;
    private static boolean isUpKeyPressed;
    private static boolean isDownKeyPressed;
    private AnimationTimer gameTimer;

    public GameViewManager(){
        initializeStage();
        Game_Background();
        Set_up_Game();
        createKeyListeners();
        bombermanRun.setRun(gamePane);

    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void Game_Background() {
        for(int i = 0; i <= GAME_WIDTH; i += 48) {
            for (int j = 0; j <= GAME_HEIGHT; j += 48) {
                ViewImage GrassBackground = new ViewImage(image_classic,288,0,48,48);
                GrassBackground.cutImage_View(i,j,gamePane);
            }
        }
    }

    public static void Set_up_Game(){
        Doc_File a = new Doc_File("src/main/resources/Resources/level/level1.txt");
        a.ReaderFile(level1);
        for (int i = 0; i < GAME_HEIGHT/48; i++) {
            for (int j = 0; j < GAME_WIDTH/48; j++) {
                if (level1[i][j] == '#'){
                    ViewImage GrassBackground = new ViewImage(image_classic,240,0,48,48);
                    GrassBackground.cutImage_View(j * 48, i * 48, gamePane);
                }
                if (level1[i][j] == '*') {
                    ViewImage GrassBackground = new ViewImage(image_classic,336,0,48,48);
                    GrassBackground.cutImage_View(j * 48, i * 48, gamePane);
                }

            }
        }
    }
    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightPressed = true;

                } else if (event.getCode() == KeyCode.UP) {
                    isUpKeyPressed = true;
                } else if (event.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightPressed = false;

                }else if (event.getCode() == KeyCode.UP) {
                    isUpKeyPressed = false;
                } else if (event.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = false;
                }
            }
        });
    }

    // Vòng lặp game
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBomberman();
            }
        };
        gameTimer.start();
    }

    private void moveBomberman() {
        if (!isLeftKeyPressed && isRightPressed && !isDownKeyPressed && !isUpKeyPressed) {
            Bomberman_Run(1);
        }
        if (isLeftKeyPressed && !isRightPressed && !isDownKeyPressed && !isUpKeyPressed) {
            Bomberman_Run(2);
        }
        if (!isLeftKeyPressed && !isRightPressed && !isDownKeyPressed && isUpKeyPressed) {
            Bomberman_Run(3);
        }
        if (!isLeftKeyPressed && !isRightPressed && isDownKeyPressed && !isUpKeyPressed) {
            Bomberman_Run(4);
        }

    }

    private void Bomberman_Run(int test) {
        if (test == 1) {
            bombermanRun.Run(RIGHT);
        }
        if (test == 2) {
            bombermanRun.Run(LEFT);
        }
        if (test == 3) {
            bombermanRun.Run(UP);
        }
        if (test == 4) {
            bombermanRun.Run(DOWN);
        }

    }
    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createGameLoop();
        gameStage.show();

    }
}
