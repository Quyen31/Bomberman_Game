package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import Contruction.MusicGame;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Music;

import java.util.ArrayList;
import java.util.List;

public class GameViewManager implements Contruction_Game, Image_Game, MusicGame {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuLevelStage;
    private Stage menuStage;

    private int GAME_HEIGHT;
    private int GAME_WIDTH;
    private char[][] LEVEL_MAP;
    private Bomberman bombermanRun;

    private List<Enemy> Enemy = new ArrayList<Enemy>();

    private ImageView[][] GrassBackground;
    private ImageView[][] ItemMap;
    private boolean isLeftKeyPressed;
    private boolean isRightPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isKeySpace;
    private AnimationTimer gameTimer;
    private List<Bomb> ArrayBomb = new ArrayList<Bomb>();
    private int checkSetBom = 0;

    private TranslateTransition transition = new TranslateTransition();

    private int K = 0;

    private List<KeyEvent> ArrayKeyEvent = new ArrayList<KeyEvent>();

    private boolean gameOver = false;

    private Music musicGame ;
    private Music musicWalk;

    private Music musicSetBomb;


    public GameViewManager(int GAME_WIDTH, int GAME_HEIGHT, char[][] LEVEL_MAP){
        this.GAME_WIDTH = GAME_WIDTH;
        this.GAME_HEIGHT= GAME_HEIGHT;
        this.LEVEL_MAP = LEVEL_MAP;
        GrassBackground = new ImageView[GAME_HEIGHT][GAME_WIDTH];
        ItemMap = new ImageView[GAME_HEIGHT][GAME_WIDTH];
        initializeStage();
        Game_Background();
        Set_up_Game();
        createKeyListeners();
        transition.setDuration(Duration.seconds(0.001));
        transition.setNode(gamePane);
        musicSetBomb = new Music(music_placed_bomb_url);
        musicSetBomb.Volume(3);
        musicGame = new Music(music_game_url);
        musicGame.Volume(-10);
        musicGame.musicLoop();
        musicWalk = new Music(music_walk_url);
        musicWalk.Volume(3);
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, SCREEN_WIDTH, SCREEN_HEIGHT);
        gamePane.setLayoutX(0);
        gamePane.setLayoutY(0);
        gameStage = new Stage();
        gameStage.setTitle("Game Bomberman");
        gameStage.setScene(gameScene);
    }

    public void Game_Background() {
        for(int i = 0; i <= GAME_WIDTH; i ++) {
            for (int j = 0; j <= GAME_HEIGHT; j ++) {
                ImageView imageView = new ImageView();
                insertImage(i * UNIT, j * UNIT, imageView, image_classic, GrassRectangle, gamePane);
            }
        }
    }

    public void Set_up_Game(){
        for (int i = 0; i < GAME_HEIGHT; i++) {
            for (int j = 0; j < GAME_WIDTH; j++) {
                if (LEVEL_MAP[i][j] == '#'){
                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, GrassBackground[i][j], image_classic, WallRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == '*') {
                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, GrassBackground[i][j], image_classic, BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 'b') {
                    LEVEL_MAP[i][j] = 'B';
                    ItemMap[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, ItemMap[i][j], image_sprites, BombItem, gamePane);

                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, GrassBackground[i][j], image_classic,BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 's') {
                    LEVEL_MAP[i][j] = 'S';
                    ItemMap[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, ItemMap[i][j], image_sprites, SpeedItem, gamePane);

                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, GrassBackground[i][j], image_classic,BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 'x') {
                    LEVEL_MAP[i][j] = 'X';
                    ItemMap[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, ItemMap[i][j], image_sprites, Portal, gamePane);

                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, GrassBackground[i][j], image_classic,BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 'f') {
                    LEVEL_MAP[i][j] = 'F';
                    ItemMap[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, ItemMap[i][j], image_sprites, FlameItem, gamePane);

                    GrassBackground[i][j] = new ImageView();
                    insertImage(j * UNIT, i * UNIT, GrassBackground[i][j], image_classic,BrickRectangle, gamePane);

                }
                if (LEVEL_MAP[i][j] == 'p') {
                    bombermanRun = new Bomberman(j * UNIT,i * UNIT, LEVEL_MAP, gamePane, gameStage, menuStage);
                }

                if (LEVEL_MAP[i][j] == '1') {
                    Enemy.add(new Enemy(1,j * UNIT, i* UNIT, LEVEL_MAP ,gamePane));
                }

                if (LEVEL_MAP[i][j] == '2') {
                    Enemy.add(new Enemy(2,j * UNIT, i* UNIT, LEVEL_MAP, gamePane));
                }
            }
        }
    }
    private void checkTrue (KeyCode event) {
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

    private void checkFalse (KeyCode event) {
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
                if ((Bomb.checkNumberBom >= bombermanRun.NumberBom || LEVEL_MAP[(int)(bombermanRun.toY + 22)/ 48] [(int)(bombermanRun.toX + 15)/ 48] == 'n' )&& event.getCode() == KeyCode.SPACE) {
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
                    } else if (event.getCode() == ArrayKeyEvent.get(1).getCode()) {
                        ArrayKeyEvent.remove(1);
                    } else {
                        ArrayKeyEvent.remove(2);
                        checkTrue(ArrayKeyEvent.get(1).getCode());
                    }
                }
                checkFalse(event.getCode());
            }
        });
    }
    private void moveMap() {
        if (bombermanRun.toX > SCREEN_WIDTH/2 + bombermanRun.SPEED_MAX * K && bombermanRun.toX <= GAME_WIDTH * UNIT - SCREEN_WIDTH/2) {
            transition.setToX( - bombermanRun.SPEED_MAX * K);
            K ++;
            transition.play();
        }
        if (bombermanRun.toX < SCREEN_WIDTH/2 + bombermanRun.SPEED_MAX * K && bombermanRun.toX >= SCREEN_WIDTH/2) {
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
                gameOver();
                moveEnemy();
                moveBomberman();
                moveMap();
                Bomb();
                nextLevel();
                EnemyEatBomber();
            }
        };
        gameTimer.start();
    }

    private AnimationTimer loopGameOver;
    private int timeGameOver = 0;
    private void gameOver() {
        if (bombermanRun.gameOver == true) {
            loopGameOver = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    timeGameOver ++;
                    if (timeGameOver == 35) {
                        musicSetBomb.clip.stop();
                        musicGame.clip.stop();
                        musicWalk.clip.stop();
                        loopGameOver.stop();
                        gameStage.close();
                        GameEnd gameEnd = new GameEnd();
                        gameEnd.gameEnd(menuStage, MENU_LEVEL.musicMenu, true);
                    }
                }
            };
            MENU_LEVEL.index = 0;
            loopGameOver.start();
            gameTimer.stop();
        }
    }

    private void nextLevel() {
        int a = (bombermanRun.toX + 24) / 48;
        int b = (bombermanRun.toY + 24) / 48;
        if (LEVEL_MAP[b][a] == 'x' && Enemy.size() == 0) {
            gameTimer.stop();
            musicSetBomb.clip.stop();
            musicGame.clip.stop();
            musicWalk.clip.stop();
            MENU_LEVEL menu_level = new MENU_LEVEL();
            menu_level.index ++;
            if (MENU_LEVEL.index > ARRAY_URL_LEVEL.length - 1) {
                gameStage.close();
                MENU_LEVEL.index = 0;
                GameEnd gameEnd = new GameEnd();
                gameEnd.gameEnd(menuStage, MENU_LEVEL.musicMenu, false);
            } else
                menu_level.createNewGame(gameStage, 1);
        }
    }

    private void EnemyEatBomber() {
        if (bombermanRun.gameOver == false) {
            for (int j = 0; j < Enemy.size(); j++) {
                for (int m = 0; m < 4; m++) {
                    if (bombermanRun.ArrX[m] <= Enemy.get(j).ArrayX[1] && bombermanRun.ArrX[m] >= Enemy.get(j).ArrayX[0] &&
                            bombermanRun.ArrY[m] >= Enemy.get(j).ArrayY[0] && bombermanRun.ArrY[m] <= Enemy.get(j).ArrayY[2]) {
                        bombermanRun.loopDie();
                        bombermanRun.gameOver = true;
                        break;
                    }
                }
                if (bombermanRun.gameOver == true) {
                    break;
                }
            }
        }
    }
    private void moveEnemy() {
        for ( int j = 0; j < Enemy.size(); j++) {
            Enemy.get(j).moveEnemyOne(bombermanRun);
        }

    }
    public void Bomb () {
        if (isKeySpace == true) {
            checkSetBom = 1;
        }
        if (checkSetBom == 1 && isKeySpace == false ) {
            checkSetBom = 0;
            musicSetBomb.musicStart(15);
            Bomb.checkFire = bombermanRun.ExplosiveSize;
            Bomb.checkNumberBom ++;
            ArrayBomb.add(new Bomb(gamePane, LEVEL_MAP, GAME_HEIGHT, GAME_WIDTH));
            ArrayBomb.get(ArrayBomb.size()-1).toX = bombermanRun.toX + 15;
            ArrayBomb.get(ArrayBomb.size()-1).toY = bombermanRun.toY + 22;
            ArrayBomb.get(ArrayBomb.size()-1).loopBom(GrassBackground, Enemy,ArrayBomb, bombermanRun);
        }
    }

    private int checkMusic = 0;
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
        if (isLeftKeyPressed || isRightPressed || isDownKeyPressed || isUpKeyPressed) {
            if (checkMusic == 20)
                checkMusic = 0;
            if (checkMusic == 0) {
                musicWalk.musicStart(15);
            }
            checkMusic ++;
        }
    }
    private void Bomberman_Run(int test) {
        bombermanRun.eatItem(ItemMap);

        if (bombermanRun.SPEED_MAX < SPEED_MEDIUM + bombermanRun.SPEED) {
            bombermanRun.SPEED_MAX = SPEED_MEDIUM + bombermanRun.SPEED;
        }
        bombermanRun.speed = SPEED_MEDIUM + bombermanRun.SPEED;
        if (ArrayBomb.size() == 0) {
            ArrayBomb.add(new Bomb(gamePane, LEVEL_MAP, GAME_HEIGHT, GAME_WIDTH));
        }
        bombermanRun.moveCorrect(test);
        if (test == 1) {
            if (bombermanRun.checkMap(bombermanRun.toX + bombermanRun.speed, bombermanRun.toY, ArrayBomb.get(ArrayBomb.size()-1)) == false) {
                bombermanRun.speed = SPEED_STOP;
            }
            bombermanRun.move(RIGHT);
        }
        if (test == 2) {
            if (bombermanRun.checkMap(bombermanRun.toX - bombermanRun.speed, bombermanRun.toY, ArrayBomb.get(ArrayBomb.size()-1)) == false) {
                bombermanRun.speed = SPEED_STOP;
            }
            bombermanRun.move(LEFT);
        }
        if (test == 3) {
            if (bombermanRun.checkMap(bombermanRun.toX, bombermanRun.toY - bombermanRun.speed, ArrayBomb.get(ArrayBomb.size()-1)) == false) {
                bombermanRun.speed = SPEED_STOP;
            }
            bombermanRun.move(UP);
        }
        if (test == 4) {
            if (bombermanRun.checkMap(bombermanRun.toX, bombermanRun.toY + bombermanRun.speed, ArrayBomb.get(ArrayBomb.size()-1)) == false) {
                bombermanRun.speed = SPEED_STOP;
            }
            bombermanRun.move(DOWN);
        }
        if (ArrayBomb.size() == 0) {
            ArrayBomb.remove(ArrayBomb.get(0));
        }

}
    public void createNewGame(Stage menuLevelStage,Stage menuStage) {
        this.menuStage = menuStage;
        this.menuLevelStage = menuLevelStage;
        this.menuLevelStage.close();
        bombermanRun.setRun();
        createGameLoop();
        gameStage.show();

    }
}
