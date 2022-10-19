package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import Contruction.MusicGame;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Music;

public class Bomberman extends Entry implements Image_Game, Contruction_Game, MusicGame {
    private static AnchorPane GamePane;

    private static Stage menuStage;

    private static Stage gameStage;

    private static AnimationTimer loopdie;
    public static ImageView imageBomberman;

    public int speed = SPEED_MEDIUM; // Vận tốc
    public int SPEED = 0; // đơn vị vận tốc để tăng

    public int SPEED_MAX = SPEED_MEDIUM; // Vận tốc max hiện t

    public int NumberBom = 1; // Số bom mà bomber có thể đặt

    public int ExplosiveSize = 1;

    public static int toX ;
    public static int toY ;
    private static int count = 0;

    private static char[][] LEVEL_MAP;

    public boolean gameOver = false;

    private TranslateTransition transition = new TranslateTransition();

    /**
     * Contruction
     */
    public Bomberman(int toX, int toY, char[][] LEVEL_MAP, AnchorPane GamePane, Stage gameStage, Stage menuStage) {
        this.menuStage = menuStage;
        this.gameStage = gameStage;
        this.GamePane = GamePane;
        this.LEVEL_MAP = LEVEL_MAP;
        this.toX = toX ;
        this.toY = toY ;
        imageBomberman = new ImageView();
        transition.setDuration(Duration.seconds(0.01));
        transition.setNode(imageBomberman);
        dieBomber();
    }

    public void setRun () {
        leftBomberRectangle();
        rightBomberRectangle();
        upBomberRectangle();
        downBomberRectangle();
        imageBomberman.setImage(image_sprites);
        imageBomberman.setFitHeight(44);
        imageBomberman.setFitWidth(44);
        insertImage(toX, toY, imageBomberman, image_sprites, bomberDownRectangle[0], GamePane);
    }

    public void move (String direction) {
        if (count > 15) {
            count = 0;
        }
        if (direction == RIGHT) {
            toX = toX + speed;
        }
        if (direction == LEFT) {
            toX = toX - speed;
        }
        if (direction == UP) {
            toY = toY - speed;
        }
        if (direction == DOWN) {
            toY = toY + speed;
        }
        moveImage(count, direction);
        count++;
        imageBomberman.setLayoutX(toX);
        imageBomberman.setLayoutY(toY);
    }

    public void moveImage(int a, String direction) {
        if (direction == RIGHT) {
            Animation2(5, a, toX, toY, bomberRightRectangle, imageBomberman, image_sprites);
        }
        if (direction == LEFT) {
            Animation2(5, a, toX, toY, bomberLeftRectangle, imageBomberman, image_sprites);
        }
        if (direction == UP) {
            Animation2(5, a, toX, toY, bomberUpRectangle, imageBomberman, image_sprites);
        }
        if (direction == DOWN) {
            Animation2(5, a, toX, toY, bomberDownRectangle, imageBomberman, image_sprites);
        }

    }

    // a___b
    // |   |
    // c---d
    public int check = 0;

    public int[] ArrX = new int[4];
    public int[] ArrY = new int[4];

    @Override
    public void forCoordinates() {
        ArrX[0] = toX;
        ArrX[1] = toX + 30;
        ArrX[2] = toX;
        ArrX[3] = toX + 30;

        ArrY[0] = toY;
        ArrY[1] = toY;
        ArrY[2] = toY + 44;
        ArrY[3] = toY + 44;
    }

    public void moveCorrect(int a) {
        if (a == 3 || a == 4) {
            if (toX % 48 <= 28 && toX % 48 > 16 ) {
                toX = toX - toX % 48 + 16;
                imageBomberman.setLayoutX(toX);
            }
            if (toX % 48 >= 35 ) {
                toX = toX + (48 - toX % 48);
                imageBomberman.setLayoutX(toX);
            }
        }
        if (a == 1 || a == 2) {
            if (toY % 48 <= 8 && toY % 48 != 0) {
                toY = toY - toY % 48;
                imageBomberman.setLayoutY(toY);
            }
            if (toY % 48 >= 40 && toY % 48 != 0) {
                toY = toY + (48 - toY % 48);
                imageBomberman.setLayoutY(toY);
            }
        }
    }
    public boolean checkMap(int nextX, int nextY, Bomb bomb) {
        forCoordinates();
        int aToX = nextX / 48;
        int aToY = nextY / 48;

        int bToX = (nextX + 30 ) / 48;
        int bToY = nextY / 48;

        int cToX = nextX / 48;
        int cToY = (nextY + 44 ) / 48;

        int dToX = (nextX + 30 ) / 48;
        int dToY = (nextY + 44 ) / 48;
        check = 0;
        for ( int i = 0 ; i < 4; i++) {
            if (ArrX[i] > ((int)(bomb.toX) / 48) * 48 && ArrX[i] < ((int)(bomb.toX) / 48) * 48 + 48) {
                if (ArrY[i] > ((int)(bomb.toY) / 48) * 48  && ArrY[i] < ((int)(bomb.toY) / 48) * 48  + 48) {
                    check = 1;
                }
            }
        }
        if(bomb.checkNumberBom <= NumberBom && check == 0 ) {
            return !((LEVEL_MAP[aToY][aToX] == 'n' || LEVEL_MAP[bToY][bToX] == 'n' || LEVEL_MAP[cToY][cToX] == 'n' || LEVEL_MAP[dToY][dToX] == 'n') ||
                    CheckForbiddenToMoveIn(LEVEL_MAP[aToY][aToX]) || CheckForbiddenToMoveIn(LEVEL_MAP[bToY][bToX]) ||
                    CheckForbiddenToMoveIn(LEVEL_MAP[cToY][cToX]) || CheckForbiddenToMoveIn(LEVEL_MAP[dToY][dToX]));
        }
        return  ! (CheckForbiddenToMoveIn(LEVEL_MAP[aToY][aToX]) || CheckForbiddenToMoveIn(LEVEL_MAP[bToY][bToX]) ||
                CheckForbiddenToMoveIn(LEVEL_MAP[cToY][cToX]) || CheckForbiddenToMoveIn(LEVEL_MAP[dToY][dToX]));
    }

    private static Music musicEatItem = new Music(music_power_url);
    public void eatItem(ImageView[][] imageView) {
        int a = (toX + 15) / 48;
        int b = (toY + 22) / 48;
        if (LEVEL_MAP[b][a] == 'b' || LEVEL_MAP[b][a] == 'f' || LEVEL_MAP[b][a] == 's') {
            GamePane.getChildren().remove(imageView[b][a]);
            musicEatItem.musicStart(20);
            if (LEVEL_MAP[b][a] == 's') {
                SPEED ++;
            }
            if (LEVEL_MAP[b][a] == 'b') {
                NumberBom ++;
            }
            if (LEVEL_MAP[b][a] == 'f') {
                ExplosiveSize ++;
            }
            LEVEL_MAP[b][a] = ' ';
        }
    }

    @Override
    public void die() {
        if (count > 30) {
            count = 0;
        }
        Animation2(10 ,count, toX, toY, BomberDie, imageBomberman, image_sprites);
        count ++;
    }

    private int timeDie = 0;
    @Override
    public void loopDie() {
        loopdie = new AnimationTimer() {
            @Override
            public void handle(long now) {
                die();
                timeDie ++;
                if (timeDie == 35) {
                    GamePane.getChildren().remove(imageBomberman);
                    loopdie.stop();
                    gameOver = true;
                }
            }
        };
        loopdie.start();
    }


}
