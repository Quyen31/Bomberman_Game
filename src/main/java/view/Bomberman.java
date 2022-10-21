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
    private TranslateTransition transition = new TranslateTransition();
    private static Stage menuStage;
    private static Stage gameStage;
    private static AnimationTimer loopdie;
    public int speed = SPEED_MEDIUM; // Vận tốc
    public int SPEED = 0; // đơn vị vận tốc để tăng
    public int SPEED_MAX = SPEED_MEDIUM; // Vận tốc max hiện t
    public int NumberBom = 1; // Số bom mà bomber có thể đặt
    public int ExplosiveSize = 1;
    public boolean gameOver = false;

    /**
     * Contruction
     */
    public Bomberman(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane, Stage gameStage, Stage menuStage) {
        this.menuStage = menuStage;
        this.gameStage = gameStage;
        this.gamePane = gamePane;
        this.LEVEL_MAP = LEVEL_MAP;
        this.toX = toX ;
        this.toY = toY ;
        ArrayX = new int[4];
        ArrayY = new int[4];
        imageView = new ImageView();
        transition.setDuration(Duration.seconds(0.01));
        transition.setNode(imageView);
    }

    public void setRun () {
        leftBomberRectangle();
        rightBomberRectangle();
        upBomberRectangle();
        downBomberRectangle();
        dieBomberRectangle();
        imageView.setImage(image_sprites);
        imageView.setFitHeight(44);
        imageView.setFitWidth(44);
        insertImage(toX, toY, imageView, image_sprites, bomberDownRectangle[0], gamePane);
    }

    @Override
    public void forCoordinates() {
        ArrayX[0] = toX;
        ArrayX[1] = toX + 30;
        ArrayX[2] = toX;
        ArrayX[3] = toX + 30;

        ArrayY[0] = toY;
        ArrayY[1] = toY;
        ArrayY[2] = toY + 44;
        ArrayY[3] = toY + 44;
    }
    public void heart() {
        heartX = (int)(toX + 15) / 48;
        heartY = (int)(toY + 22) / 48;
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
        forCoordinates();
        heart();
        moveImage(count, direction);
        count++;
        imageView.setLayoutX(toX);
        imageView.setLayoutY(toY);
    }

    public void moveImage(int a, String direction) {
        if (direction == RIGHT) {
            Animation2(5, a, toX, toY, bomberRightRectangle, imageView, image_sprites);
        }
        if (direction == LEFT) {
            Animation2(5, a, toX, toY, bomberLeftRectangle, imageView, image_sprites);
        }
        if (direction == UP) {
            Animation2(5, a, toX, toY, bomberUpRectangle, imageView, image_sprites);
        }
        if (direction == DOWN) {
            Animation2(5, a, toX, toY, bomberDownRectangle, imageView, image_sprites);
        }

    }


    public void moveCorrect(String direction) {
        if (direction == UP || direction == DOWN) {
            if (toX % 48 <= 28 && toX % 48 > 16 ) {
                toX = toX - toX % 48 + 16;
                imageView.setLayoutX(toX);
            }
            if (toX % 48 >= 35 ) {
                toX = toX + (48 - toX % 48);
                imageView.setLayoutX(toX);
            }
        }
        if (direction == LEFT || direction == RIGHT) {
            if (toY % 48 <= 8 && toY % 48 != 0) {
                toY = toY - toY % 48;
                imageView.setLayoutY(toY);
            }
            if (toY % 48 >= 40 && toY % 48 != 0) {
                toY = toY + (48 - toY % 48);
                imageView.setLayoutY(toY);
            }
        }
    }

    // a___b
    // |   |
    // c---d
    public int check = 0;
    public boolean checkMap(int nextX, int nextY, Bomb bomb) {
        int aToX = nextX / 48;
        int aToY = nextY / 48;

        int bToX = (nextX + 30 ) / 48;
        int bToY = nextY / 48;

        int cToX = nextX / 48;
        int cToY = (nextY + 40 ) / 48;

        int dToX = (nextX + 30 ) / 48;
        int dToY = (nextY + 40 ) / 48;
        check = 0;
        for ( int i = 0 ; i < 4; i++) {
            if (ArrayX[i] > ((int)(bomb.toX) / 48) * 48 && ArrayX[i] < ((int)(bomb.toX) / 48) * 48 + 48) {
                if (ArrayY[i] > ((int)(bomb.toY) / 48) * 48  && ArrayY[i] < ((int)(bomb.toY) / 48) * 48  + 48) {
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
        if (LEVEL_MAP[heartY][heartX] == 'b' || LEVEL_MAP[heartY][heartX] == 'f' || LEVEL_MAP[heartY][heartX] == 's') {
            gamePane.getChildren().remove(imageView[heartY][heartX]);
            musicEatItem.musicStart(20);
            if (LEVEL_MAP[heartY][heartX] == 's') {
                SPEED ++;
            }
            if (LEVEL_MAP[heartY][heartX] == 'b') {
                NumberBom ++;
            }
            if (LEVEL_MAP[heartY][heartX] == 'f') {
                ExplosiveSize ++;
            }
            LEVEL_MAP[heartY][heartX] = ' ';
        }
    }

    @Override
    public void die() {
        if (count > 30) {
            count = 0;
        }
        Animation2(10 ,count, toX, toY, BomberDie, imageView, image_sprites);
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
                    gamePane.getChildren().remove(imageView);
                    loopdie.stop();
                    gameOver = true;
                }
            }
        };
        loopdie.start();
    }


}
