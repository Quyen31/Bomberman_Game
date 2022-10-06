package view;

import Contruction.Contruction_Game;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Contruction.Image_Game;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameViewManager implements Image_Game, Contruction_Game {
    private static AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;
    private static final double GAME_WIDTH = 1056;
    private static final double GAME_HEIGHT = 624;
    private static Bomberman_run bombermanRun;

    private static ImageView[][] GrassBackground = new ImageView[(int) (GAME_HEIGHT/48)][(int) (1488/48)];
    private static Rectangle2D rectangle2D;
    private static char level1[][] = new char[100][100];
    private static boolean isLeftKeyPressed;
    private static boolean isRightPressed;
    private static boolean isUpKeyPressed;
    private static boolean isDownKeyPressed;

    private static boolean isKeySpace;
    private AnimationTimer gameTimer;


    private static TranslateTransition transition = new TranslateTransition();;

    private static int K = 0;

    private static List<KeyEvent> ArrayKeyEvent = new ArrayList<KeyEvent>();

    //private static Bomb Bom1 ;
    private Bomb Bom1;


    public GameViewManager(){
        initializeStage();
        Game_Background();
        Set_up_Game();
        Bom1 = new Bomb(gamePane);
        createKeyListeners();
        bombermanRun.setRun(gamePane);
        transition.setDuration(Duration.seconds(0.001));
        transition.setNode(gamePane);
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gamePane.setLayoutX(0);
        gamePane.setLayoutY(0);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void Game_Background() {
        for(int i = 0; i <= 1488; i += 48) {
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
            for (int j = 0; j < 1488/48; j++) {
                if (level1[i][j] == '#'){
                    GrassBackground[i][j] = new ImageView();
                    GrassBackground[i][j].setImage(image_classic);
                    rectangle2D = new Rectangle2D(240,0,48,48);
                    GrassBackground[i][j].setViewport(rectangle2D);
                    GrassBackground[i][j].setLayoutX(j * 48);
                    GrassBackground[i][j].setLayoutY(i * 48);
                    gamePane.getChildren().add(GrassBackground[i][j]);
                }
                if (level1[i][j] == '*') {
                    GrassBackground[i][j] = new ImageView();
                    GrassBackground[i][j].setImage(image_classic);
                    rectangle2D = new Rectangle2D(336,0,48,48);
                    GrassBackground[i][j].setViewport(rectangle2D);
                    GrassBackground[i][j].setLayoutX(j * 48);
                    GrassBackground[i][j].setLayoutY(i * 48);
                    gamePane.getChildren().add(GrassBackground[i][j]);
                }
                if (level1[i][j] == 'p') {
                    bombermanRun = new Bomberman_run(j * 48,i * 48);
                }

            }
        }
    }
    // Di chuyển Bomberman (87 -> 147)
    private static void checkTrue (KeyCode event) {
        if (event == KeyCode.SPACE) {
            isKeySpace = true;
        } else if (event == KeyCode.LEFT) {
            isLeftKeyPressed = true;

        } else if (event == KeyCode.RIGHT) {
            isRightPressed = true;

        } else if (event == KeyCode.UP) {
            isUpKeyPressed = true;
        } else if (event == KeyCode.DOWN) {
            isDownKeyPressed = true;
        }
    }

    private static void checkFalse (KeyCode event) {
        if (event == KeyCode.SPACE) {
            isKeySpace = false;
        }else if (event == KeyCode.LEFT) {
            isLeftKeyPressed = false;

        } else if (event == KeyCode.RIGHT) {
            isRightPressed = false;

        } else if (event == KeyCode.UP) {
            isUpKeyPressed = false;
        } else if (event == KeyCode.DOWN) {
            isDownKeyPressed = false;
        }
    }
    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (checkBomOne == true && event.getCode() == KeyCode.SPACE) {
                    isKeySpace = false;
                }
                if (ArrayKeyEvent.size() == 0) {
                    ArrayKeyEvent.add(event);
                }
                if (ArrayKeyEvent.size() == 1){
                    if(event.getCode() != ArrayKeyEvent.get(0).getCode()) {
                        ArrayKeyEvent.add(event);
                        checkFalse(ArrayKeyEvent.get(0).getCode());
                    }
                }
                checkTrue(event.getCode());
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.SPACE)
//                    isKeySpace = false;
                if (ArrayKeyEvent.size() == 1) {
                    if (event.getCode() == ArrayKeyEvent.get(0).getCode()) {
                        ArrayKeyEvent.remove(0);
                    }
                }
                if (ArrayKeyEvent.size() == 2) {
                    if (event.getCode() != ArrayKeyEvent.get(0).getCode()) {
                        checkTrue(ArrayKeyEvent.get(0).getCode());
                        ArrayKeyEvent.remove(1);
                    }
                    if (event.getCode() == ArrayKeyEvent.get(0).getCode()) {
                        ArrayKeyEvent.remove(0);
                    }
                }
                checkFalse(event.getCode());
            }
        });
    }
    private void moveMap() {
        if (bombermanRun.getToX() > 504 + 2 * K && bombermanRun.getToX() <= 960) {
            transition.setToX( - 2 * K);
            K ++;
            transition.play();
        }
        if (bombermanRun.getToX() < 504 + 2 * K && bombermanRun.getToX() >= 504) {
            K --;
            transition.setToX( - 2 * K);
            transition.play();
        }
    }

    // Vòng lặp game
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                setBomb();
                moveBomberman();
                moveMap();

            }
        };
        gameTimer.start();
    }
    private static int k = 0;

    private static boolean checkBomOne = false;
    public boolean Bomb () {
        if (isKeySpace == true && checkBomOne == false) {
            k = 1;
        }
        if (k == 1 && isKeySpace == false) {
            Bom1.setToX(bombermanRun.getToX());
            Bom1.setToY(bombermanRun.getToY());
            System.out.println(Bom1.getToX() + " " + Bom1.getToY());
            k = 0;
            return true;
        }
        return false;
    }

    private static int check = 0;
    private static int check1 = 0;

    private static boolean check3 = false;
    private static boolean check4 = true;


    private static Timer timer1 = new Timer();
    private static Timer timer2 = new Timer();
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

    private void setBomb() {
        if (Bomb() == true  || check == 60 || check1 == 15) {
            if (check == 0 && check1 == 0) {
                checkBomOne = true;
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Bom1.createBomb();
                        check ++;
                    }
                };

                timer1 = new Timer();
                timer2 = new Timer();
                long delay = 30;
                timer1.schedule(timerTask, 0, delay);
            }
            if(check == 60 ) {
                timer1.cancel();
                Bom1.resertBomb(gamePane);
                Bom1.explosiveBombs(level1,GrassBackground,gamePane);
                check = 0;
                TimerTask timerTask1 = new TimerTask() {
                    @Override
                    public void run() {
                        Bom1.imageExplosiveBombs(level1, gamePane);
                        check1 ++;
                    }
                };
                timer2.schedule(timerTask1, 0, 30);
            }
            if (check1 == 15) {
                timer2.cancel();
                Bom1.resetMap(level1);
                check1 = 0;
                checkBomOne = false;
            }

        }
    }
    private void Bomberman_Run(int test) {
        bombermanRun.setSpeed(SPEED_MEDIUM);
        if (test == 1) {
            if (bombermanRun.checkMap(bombermanRun.getToX() + 2, bombermanRun.getToY(), level1) == false || check4 == false) {
                bombermanRun.setSpeed(SPEED_STOP);
            }
            bombermanRun.Run(RIGHT);
        }
        if (test == 2) {
            if (bombermanRun.checkMap(bombermanRun.getToX() - 2, bombermanRun.getToY(), level1) == false|| check4 == false) {
                bombermanRun.setSpeed(SPEED_STOP);
            }
            bombermanRun.Run(LEFT);
        }
        if (test == 3) {
            if (bombermanRun.checkMap(bombermanRun.getToX(), bombermanRun.getToY() - 2, level1) == false|| check4 == false) {
                bombermanRun.setSpeed(SPEED_STOP);
            }
            bombermanRun.Run(UP);
        }
        if (test == 4) {
            if (bombermanRun.checkMap(bombermanRun.getToX(), bombermanRun.getToY() + 2, level1) == false || check4 == false) {
                bombermanRun.setSpeed(SPEED_STOP);
            }
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
