package entity.entityMove.enemy;

import Contruction.Image_Game;
import entity.entityMove.Bomberman;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Nightmare extends Enemy{
    Bomberman bomberman;

    private boolean checkChase = false;
    public Nightmare(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane, Bomberman bomberman) {
        super(toX, toY, LEVEL_MAP, gamePane);
        this.bomberman = bomberman;
    }

    @Override
    public void update() {
        imageView = new ImageView();
        imageView.setFitWidth(44);
        imageView.setFitHeight(44);
        random = new Random();
        gamePane.getChildren().add(imageView);
    }

    @Override
    public void remove() {
        gamePane.getChildren().remove(imageView);
        imageView = null;
    }

    @Override
    public void die() {
        if (count > 30) {
            count = 0;
        }
        if (count <= 0) {
            imageView.setViewport(dieNightmare);
        }
        Image_Game.Animation(10 ,count, toX, toY, EnemyDie, imageView, image_sprites);
        count ++;
    }
    @Override
    public boolean checkMap (int nextX, int nextY) {
        int aToX = (int)(nextX) / UNIT;
        int aToY = (int)(nextY) / UNIT;

        int bToX = (int)(nextX + 44) / UNIT;
        int bToY = (int)(nextY) / UNIT;

        int cToX = (int)(nextX) / UNIT;
        int cToY = (int)(nextY + 44) / UNIT;

        int dToX = (int)(nextX + 44 ) / UNIT;
        int dToY = (int)(nextY + 44 ) / UNIT;


        return  ! ((LEVEL_MAP[aToY][aToX] == 'n' || LEVEL_MAP[bToY][bToX] == 'n' || LEVEL_MAP[cToY][cToX] == 'n' || LEVEL_MAP[dToY][dToX] == 'n') ||
                (LEVEL_MAP[aToY][aToX] == '#' || LEVEL_MAP[bToY][bToX] == '#' || LEVEL_MAP[cToY][cToX] == '#' || LEVEL_MAP[dToY][dToX] == '#') ||
                (LEVEL_MAP[aToY][aToX] == 'B' || LEVEL_MAP[bToY][bToX] == 'B' || LEVEL_MAP[cToY][cToX] == 'B' || LEVEL_MAP[dToY][dToX] == 'B'));
    }

    @Override
    public void loopDie() {
        loopDie = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (timeDie >= 30)
                    die();
                timeDie ++;
                if (timeDie >= 60){
                    loopDie.stop();
                    remove();
                }
            }
        };
        count = -10;
        loopDie.start();
    }

    @Override
    public void move() {
        if ( count > 15) {
            count = 0;
        }
        if (checkRandom == false) {
            randomDirection = Random();
        }
        if(enemyChase( bomberman) != 0) {
            randomDirection = enemyChase(bomberman);
            if (checkChase == false) {
                toX = (int) ((toX + 22) / UNIT) * UNIT;
                toY = (int) ((toY + 22) / UNIT) * UNIT;
                Image_Game.moveCoordinates(toX, toY, imageView);
                checkChase = true;
            }

        } else
            checkChase = false;
        directionEnemy(randomDirection);
        forCoordinates();
        heart();
        count ++;
    }
    private void directionEnemy(int randomDirection) {
        if (randomDirection == 1 ) {
            if (checkMap(toX + speed, toY)) {
                checkRandom = true;
                toX = toX + speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, RightNightmareRectangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 2) {
            if (checkMap(toX - speed, toY)) {
                checkRandom = true;
                toX = toX - speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, LeftNightmareRectangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 3) {
            if (checkMap(toX, toY - speed)) {
                checkRandom = true;
                toY = toY - speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, LeftNightmareRectangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
        if (randomDirection == 4) {
            if (checkMap(toX, toY + speed)) {
                checkRandom = true;
                toY = toY + speed;
                Image_Game.Animation(DelayEnemy, count, toX, toY, RightNightmareRectangle, imageView, image_sprites);
            } else {
                checkRandom = false;
            }
        }
    }

    private int enemyChase (Bomberman bomberman) {

        int a = heartX / UNIT;
        int b = heartY / UNIT;
        if ( a == (int)bomberman.heartX / UNIT ) { // Khi Enemy2 cùng trục X với Bomber
            if ( b < (int)bomberman.heartY / UNIT) { // Nếu Enemy2 ở trên Bomber
                for (int i = b; i < (int)bomberman.heartY / UNIT; i ++) { // Xét xem khoảng cách giữu chúng có vật cản không
                    if(LEVEL_MAP[i][a] == '#' || LEVEL_MAP[i][a] == 'B' || LEVEL_MAP[i][a] == 'n')
                        return 0;
                }
                return 4;
            }
            if (b > (int) bomberman.heartY / UNIT) { // Nếu Enemy2 ở dưới Bomber
                for (int i = (int)bomberman.heartY / UNIT; i < b; i ++) { // Xét xem khoảng cách giữu chúng có vật cản không
                    if( LEVEL_MAP[i][a] == '#' || LEVEL_MAP[i][a] == 'B' || LEVEL_MAP[i][a] == 'n')
                        return 0; // nếu có thì randum để chạy
                }
                return 3; // Nếu không thì đuổi theo
            }
        }
        if ( b == (int)bomberman.heartY / UNIT) {
            if ( a < (int)bomberman.heartX / UNIT) {
                for (int i = a; i < (int) bomberman.heartX / UNIT; i ++) {
                    if( LEVEL_MAP[b][i] == '#' || LEVEL_MAP[b][i] == 'B' || LEVEL_MAP[b][i] == 'n')
                        return 0;
                }
                return 1;
            }
            if (a > (int)bomberman.heartX / UNIT) {

                for (int i = (int)bomberman.heartX / UNIT; i < a; i ++) {
                    if( LEVEL_MAP[b][i] == '#' || LEVEL_MAP[b][i] == 'B' || LEVEL_MAP[b][i] == 'n')
                        return 0;
                }
                return 2;
            }
        }
        return 0;
    }
}
