package entity.entityMove;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import entity.Item;
import entity.entityAnimation.Bomb;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Music;

import java.util.List;

public class Bomberman extends EntityMove{
    private static Stage menuStage;

    private static Stage gameStage;
//    public int speed = SPEED_MEDIUM; // Vận tốc
    private int SPEED = 0; // đơn vị vận tốc để tăng

    public int SPEED_MAX = SPEED_MEDIUM; // Vận tốc max hiện t

    public int NumberBom = 1; // Số bom mà bomber có thể đặt

    public int ExplosiveSize = 1; // Đơn vị Fire

    public boolean gameOver = false;


    /**
     * Contruction
     */
    public Bomberman(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane, Stage gameStage, Stage menuStage) {
        super(toX, toY, LEVEL_MAP, gamePane);
        this.menuStage = menuStage;
        this.gameStage = gameStage;
        ArrayX = new int[4];
        ArrayY = new int[4];
        imageView = new ImageView();
    }

    @Override
    public void update() {
        imageView.setImage(image_sprites);
        imageView.setFitHeight(44);
        imageView.setFitWidth(44);
        Image_Game.insertImage(toX, toY, imageView, image_sprites, bomberDownRectangle[0], gamePane);
        heart();
        forCoordinates();
    }

    @Override
    public void remove() {
        gamePane.getChildren().remove(imageView);
        imageView = null;
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
    @Override
    public void heart() {
        heartX = (int)(toX + 15);
        heartY = (int)(toY + 22);
    }
    public void Bomberman_Run(String direction, Item[][] ItemMap, List<Bomb> ArrayBomb) {
        eatItem(ItemMap);
        if (SPEED_MAX < SPEED_MEDIUM + SPEED) {
            SPEED_MAX = SPEED_MEDIUM + SPEED;
        }
        speed = SPEED_MEDIUM + SPEED;
        int nextToX = toX;
        int nextToY = toY;
        moveCorrect(direction);
        if (direction == RIGHT) {
            nextToX = toX + speed;
        }
        if (direction == LEFT) {
            nextToX = toX - speed;
        }
        if (direction == UP) {
            nextToY = toY - speed;
        }
        if (direction == DOWN) {
            nextToY = toY + speed;
        }
        if (ArrayBomb.size() == 0) {
            if (checkMap(nextToX, nextToY) == false) {
                speed = SPEED_STOP;
            }
        } else {
            if (checkMap(nextToX, nextToY, ArrayBomb.get(ArrayBomb.size() - 1)) == false) {
                speed = SPEED_STOP;
            }
        }
        move(direction);
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
        moveImage(direction);
        count++;
        imageView.setLayoutX(toX);
        imageView.setLayoutY(toY);
    }
    public void moveImage(String direction) {
        if (direction == RIGHT) {
            Image_Game.Animation(5, count, toX, toY, bomberRightRectangle, imageView, image_sprites);
        }
        if (direction == LEFT) {
            Image_Game.Animation(5, count, toX, toY, bomberLeftRectangle, imageView, image_sprites);
        }
        if (direction == UP) {
            Image_Game.Animation(5, count, toX, toY, bomberUpRectangle, imageView, image_sprites);
        }
        if (direction == DOWN) {
            Image_Game.Animation(5, count, toX, toY, bomberDownRectangle, imageView, image_sprites);
        }

    }

    public void moveCorrect(String direction) {
        if (direction == UP || direction == DOWN) {
            if (toX % UNIT <= 28 && toX % UNIT > 16 ) {
                toX = toX - toX % UNIT + 16;
                imageView.setLayoutX(toX);
            }
            if (toX % UNIT >= 35 ) {
                toX = toX + (UNIT - toX % UNIT);
                imageView.setLayoutX(toX);
            }
        }
        if (direction == RIGHT || direction == LEFT) {
            if (toY % UNIT <= 8 && toY % UNIT != 0) {
                toY = toY - toY % UNIT;
                imageView.setLayoutY(toY);
            }
            if (toY % UNIT >= 36 && toY % UNIT != 0) {
                toY = toY + (UNIT - toY % UNIT);
                imageView.setLayoutY(toY);
            }
        }
    }

    @Override
    // a___b
    // |   |
    // c---d
    protected boolean checkMap(int nextX, int nextY) {
        int aToX = nextX / UNIT;
        int aToY = nextY / UNIT;

        int bToX = (nextX + 30 ) / UNIT;
        int bToY = nextY / UNIT;

        int cToX = nextX / UNIT;
        int cToY = (nextY + 44 ) / UNIT;

        int dToX = (nextX + 30 ) / UNIT;
        int dToY = (nextY + 44 ) / UNIT;
        return  ! (Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[aToY][aToX]) || Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[bToY][bToX]) ||
                Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[cToY][cToX]) || Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[dToY][dToX]));
    }

    public int check = 0;
    private boolean checkMap(int nextX, int nextY, Bomb bomb) {
        check = 0;
        for ( int i = 0 ; i < 4; i++) {
            if (ArrayX[i] > bomb.getToX() * UNIT && ArrayX[i] < bomb.getToX() * UNIT + UNIT) {
                if (ArrayY[i] > bomb.getToY() * UNIT  && ArrayY[i] < bomb.getToY() * UNIT  + UNIT) {
                    check = 1;
                    break;
                }
            }
        }
        if( check == 0 ) {
            LEVEL_MAP[bomb.getToY()][bomb.getToX()] = 'B';
        }
        return checkMap(nextX, nextY);
    }

    private static Music musicEatItem = new Music(music_power_url);
    public void eatItem(Item[][] ItemMap) {
        if (LEVEL_MAP[heartY / UNIT][heartX / UNIT] == 'b' || LEVEL_MAP[heartY / UNIT][heartX / UNIT] == 'f' || LEVEL_MAP[heartY / UNIT][heartX / UNIT] == 's') {
            ItemMap[heartY / UNIT][heartX / UNIT].remove();
            musicEatItem.musicStart(20);
            if (LEVEL_MAP[heartY / UNIT][heartX / UNIT] == 's') {
                SPEED ++;
            }
            if (LEVEL_MAP[heartY / UNIT][heartX / UNIT] == 'b') {
                NumberBom ++;
            }
            if (LEVEL_MAP[heartY / UNIT][heartX / UNIT] == 'f') {
                ExplosiveSize ++;
            }
            LEVEL_MAP[heartY / UNIT][heartX / UNIT] = ' ';
        }
    }

    @Override
    public void die() {
        if (count > 30) {
            count = 0;
        }
        Image_Game.Animation(10 ,count, toX, toY, BomberDie, imageView, image_sprites);
        count ++;
    }

    @Override
    public void loopDie() {
        loopDie = new AnimationTimer() {
            @Override
            public void handle(long now) {
                die();
                timeDie ++;
                if (timeDie == 35) {
                    loopDie.stop();
                    remove();
                    gameOver = true;
                }
            }
        };
        loopDie.start();
    }


}
