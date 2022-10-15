package view;

import Contruction.Contruction_Game;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Contruction.Image_Game;
import javafx.util.Duration;
import model.Doc_File;

import java.util.ArrayList;
import java.util.List;

public class GameViewManager implements Image_Game, Contruction_Game {
    private static AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private static Bomberman_run bombermanRun;

    private static List<Enemy> Enemy = new ArrayList<Enemy>();

    private static ImageView[][] GrassBackground = new ImageView[GAME_HEIGHT][GAME_WIDTH];
    private static ImageView[][] ItemMap = new ImageView[GAME_HEIGHT][GAME_WIDTH];
    private static boolean isLeftKeyPressed;
    private static boolean isRightPressed;
    private static boolean isUpKeyPressed;
    private static boolean isDownKeyPressed;
    private static boolean isKeySpace;
    private AnimationTimer gameTimer;
    private List<Bomb> ArrayBomb = new ArrayList<Bomb>();
    private static int checkSetBom = 0;

    private static TranslateTransition transition = new TranslateTransition();;

    private static int K = 0;

    private static List<KeyEvent> ArrayKeyEvent = new ArrayList<KeyEvent>();



    public GameViewManager(){
        initializeStage();
        Game_Background();
        Set_up_Game();
        createKeyListeners();
        transition.setDuration(Duration.seconds(0.001));
        transition.setNode(gamePane);
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, SCREEN_WIDTH, SCREEN_HEIGHT);
        gamePane.setLayoutX(0);
        gamePane.setLayoutY(0);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void Game_Background() {
        for(int i = 0; i <= GAME_WIDTH; i ++) {
            for (int j = 0; j <= GAME_HEIGHT; j ++) {
                ImageView imageView = new ImageView();
                insertImage(i * 48, j * 48, imageView, image_classic, GrassRectangle, gamePane);
            }
        }
    }

    public void Set_up_Game(){
        a.ReaderFile(LEVEL_MAP);
        System.out.println(a.Arr[0] + " " +a.Arr[1] + " " + a.Arr[2]);
        for (int i = 0; i < GAME_HEIGHT; i++) {
            for (int j = 0; j < GAME_WIDTH; j++) {
                if (LEVEL_MAP[i][j] == '#'){
                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, GrassBackground[i][j], image_classic, WallRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == '*') {
                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, GrassBackground[i][j], image_classic, BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 'b') {
                    LEVEL_MAP[i][j] = 'B';
                    ItemMap[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, ItemMap[i][j], image_sprites, BombItem, gamePane);

                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, GrassBackground[i][j], image_classic,BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 's') {
                    LEVEL_MAP[i][j] = 'S';
                    ItemMap[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, ItemMap[i][j], image_sprites, SpeedItem, gamePane);

                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, GrassBackground[i][j], image_classic,BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 'x') {
                    LEVEL_MAP[i][j] = 'X';
                    ItemMap[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, ItemMap[i][j], image_sprites, Portal, gamePane);

                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, GrassBackground[i][j], image_classic,BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 'f') {
                    LEVEL_MAP[i][j] = 'F';
                    ItemMap[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, ItemMap[i][j], image_sprites, FlameItem, gamePane);

                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * 48, i * 48, GrassBackground[i][j], image_classic,BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 'p') {
                    bombermanRun = new Bomberman_run(j * 48,i * 48);
                }

                if (LEVEL_MAP[i][j] == '1') {
                    Enemy.add(new Enemy(1,j * 48, i* 48, gamePane));
                }

                if (LEVEL_MAP[i][j] == '2') {
                    Enemy.add(new Enemy(2,j * 48, i* 48, gamePane));
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
                if (Bomb.checkNumberBom >= bombermanRun.NumberBom && event.getCode() == KeyCode.SPACE) {
                    isKeySpace = false;
                } else {
                    if (ArrayKeyEvent.size() == 0) {
                        ArrayKeyEvent.add(event);
                    }
                    if (ArrayKeyEvent.size() == 1){
                        if(event.getCode() != ArrayKeyEvent.get(0).getCode()) {
                            ArrayKeyEvent.add(event);
                            checkFalse(ArrayKeyEvent.get(0).getCode());
                        }
                    }
                    if (ArrayKeyEvent.size() == 2){
                        if(event.getCode() != ArrayKeyEvent.get(0).getCode() && event.getCode() != ArrayKeyEvent.get(1).getCode()) {
                            ArrayKeyEvent.add(event);
                            checkFalse(ArrayKeyEvent.get(0).getCode());
                            checkFalse(ArrayKeyEvent.get(1).getCode());
                        }
                    }
                    checkTrue(event.getCode());
                }

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
                if (ArrayKeyEvent.size() == 3) {
                    if (event.getCode() == ArrayKeyEvent.get(0).getCode()) {
                        ArrayKeyEvent.remove(0);
                    }
                    if (event.getCode() == ArrayKeyEvent.get(1).getCode()) {
                        ArrayKeyEvent.remove(1);
                    }
                    if (event.getCode() == ArrayKeyEvent.get(2).getCode()) {
                        ArrayKeyEvent.remove(2);
                        checkTrue(ArrayKeyEvent.get(1).getCode());
                    }
                }
                checkFalse(event.getCode());
            }
        });
    }
    private void moveMap() {
        if (bombermanRun.getToX() > 504 + bombermanRun.SPEED_MAX * K && bombermanRun.getToX() <= 960) {
            transition.setToX( - bombermanRun.SPEED_MAX * K);
            K ++;
            transition.play();
        }
        if (bombermanRun.getToX() < 504 + bombermanRun.SPEED_MAX * K && bombermanRun.getToX() >= 504) {
            K --;
            transition.setToX( - bombermanRun.SPEED_MAX * K);
            transition.play();
        }
    }

    // Vòng lặp game
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                loopEnemy();
                moveBomberman();
                moveMap();
                Bomb();
                //System.out.println(Bomb.checkNumberBom + " " + ArrayBomb.size());

            }
        };
        gameTimer.start();
    }

    private void loopEnemy() {
        for ( int j = 0; j < Enemy.size(); j++) {
            Enemy.get(j).moveEnemyOne(LEVEL_MAP, bombermanRun);
        }

    }
    public void Bomb () {
        if (isKeySpace == true) {
            checkSetBom = 1;
        }
        if (checkSetBom == 1 && isKeySpace == false) {
            Bomb.checkFire = bombermanRun.ExplosiveSize;
            Bomb.checkNumberBom ++;
            ArrayBomb.add(new Bomb(gamePane));
            ArrayBomb.get(ArrayBomb.size()-1).toX = bombermanRun.getToX();
            ArrayBomb.get(ArrayBomb.size()-1).toY = bombermanRun.getToY();
            ArrayBomb.get(ArrayBomb.size()-1).loopBom(gamePane,LEVEL_MAP,GrassBackground, Enemy,ArrayBomb);
            checkSetBom = 0;
        }
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
        bombermanRun.eatItem(LEVEL_MAP, ItemMap, gamePane);

        if (bombermanRun.SPEED_MAX < SPEED_MEDIUM + bombermanRun.SPEED) {
            bombermanRun.SPEED_MAX = SPEED_MEDIUM + bombermanRun.SPEED;
        }
        bombermanRun.setSpeed(SPEED_MEDIUM + bombermanRun.SPEED);
        if (ArrayBomb.size() == 0) {
            ArrayBomb.add(new Bomb(gamePane));
        }
        if (test == 1) {
            if (bombermanRun.checkMap(bombermanRun.getToX() + bombermanRun.getSpeed(), bombermanRun.getToY(), LEVEL_MAP, ArrayBomb.get(ArrayBomb.size()-1)) == false) {
                bombermanRun.setSpeed(SPEED_STOP);
            }
            bombermanRun.Run(RIGHT);
        }
        if (test == 2) {
            if (bombermanRun.checkMap(bombermanRun.getToX() - bombermanRun.getSpeed(), bombermanRun.getToY(), LEVEL_MAP, ArrayBomb.get(ArrayBomb.size()-1)) == false) {
                bombermanRun.setSpeed(SPEED_STOP);
            }
            bombermanRun.Run(LEFT);
        }
        if (test == 3) {
            if (bombermanRun.checkMap(bombermanRun.getToX(), bombermanRun.getToY() - bombermanRun.getSpeed(), LEVEL_MAP, ArrayBomb.get(ArrayBomb.size()-1)) == false) {
                bombermanRun.setSpeed(SPEED_STOP);
            }
            bombermanRun.Run(UP);
        }
        if (test == 4) {
            if (bombermanRun.checkMap(bombermanRun.getToX(), bombermanRun.getToY() + bombermanRun.getSpeed(), LEVEL_MAP, ArrayBomb.get(ArrayBomb.size()-1)) == false) {
                bombermanRun.setSpeed(SPEED_STOP);
            }
            bombermanRun.Run(DOWN);
        }
        if (ArrayBomb.size() == 0) {
            ArrayBomb.remove(ArrayBomb.get(0));
        }

}
    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        bombermanRun.setRun(gamePane);
        createGameLoop();
        gameStage.show();

    }
}
