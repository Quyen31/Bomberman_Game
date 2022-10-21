package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import Contruction.MusicGame;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Music;

import java.util.Random;

public class Enemy extends Entry implements Image_Game, Contruction_Game, MusicGame {

    private Random random ;
    public static int index = 0;
    public int typeEnemy;
    AnimationTimer DieEnemy ;
    private  int timeDie = 0;
    private int DelayEnemy = 5;
    public boolean die = false;



    public Enemy(int typeEnemy, int toX, int toY,char[][] LEVEL_MAP , AnchorPane gamePane) {
        LeftEnemyOneRectangle();
        RightEnemyOneRectangle();
        LeftEnemyTwoRectangle();
        RightEnemyTwoRectangle();
        dieEnemyRectangle();
        super.gamePane = gamePane;
        super.LEVEL_MAP = LEVEL_MAP;
        super.toX = toX;
        super.toY = toY;
        this.typeEnemy = typeEnemy;
        ArrayX = new int[4];
        ArrayY = new int[4];
        imageView = new ImageView();
        imageView.setFitHeight(44);
        imageView.setFitWidth(44);
        random = new Random();
        gamePane.getChildren().add(imageView);
        index ++;
    }

    @Override
    public void heart() {
        heartX = toX + 22;
        heartY = toY + 22;
    }

    public boolean checkMap (int nextX, int nextY) {
        int aToX = (int)nextX / 48;
        int aToY = (int)nextY / 48;

        int bToX = (int)(nextX + 44) / 48;
        int bToY = (int)nextY / 48;

        int cToX = (int)nextX / 48;
        int cToY = (int)(nextY + 44 ) / 48;

        int dToX = (int)(nextX + 44 ) / 48;
        int dToY = (int)(nextY + 44 ) / 48;


        return  ! ((LEVEL_MAP[aToY][aToX] == 'n' || LEVEL_MAP[bToY][bToX] == 'n' || LEVEL_MAP[cToY][cToX] == 'n' || LEVEL_MAP[dToY][dToX] == 'n') ||
                CheckForbiddenToMoveIn(LEVEL_MAP[aToY][aToX]) || CheckForbiddenToMoveIn(LEVEL_MAP[bToY][bToX]) ||
                CheckForbiddenToMoveIn(LEVEL_MAP[cToY][cToX]) || CheckForbiddenToMoveIn(LEVEL_MAP[dToY][dToX]));
    }

    private int Random() {
        return random.nextInt(4) + 1;
    }

    private void  directionEnemy(int a, int numberEnemy) {
        if (a == 1 ) {
            if (checkMap(toX + SPEED_MEDIUM, toY)) {
                check = true;
                toX = toX + SPEED_MEDIUM;
                if (numberEnemy == 1)
                    Animation2(DelayEnemy, count, toX, toY, RightEnemy1Retangle, imageView, image_sprites);
                if (numberEnemy == 2)
                    Animation2(DelayEnemy, count, toX, toY, RightEnemy2Retangle, imageView, image_sprites);
            } else {
                check = false;
            }
        }
        if (a == 2) {
            if (checkMap(toX - SPEED_MEDIUM, toY)) {
                check = true;
                toX = toX - SPEED_MEDIUM;
                if (numberEnemy == 1)
                    Animation2(DelayEnemy, count, toX, toY, LeftEnemy1Retangle, imageView, image_sprites);
                if (numberEnemy == 2)
                    Animation2(DelayEnemy, count, toX, toY, LeftEnemy2Retangle, imageView, image_sprites);
            } else {
                check = false;
            }
        }
        if (a == 3) {
            if (checkMap(toX, toY - SPEED_MEDIUM)) {
                check = true;
                toY = toY - SPEED_MEDIUM;
                if (numberEnemy == 1)
                    Animation2(DelayEnemy, count, toX, toY, LeftEnemy1Retangle, imageView, image_sprites);
                if (numberEnemy == 2)
                    Animation2(DelayEnemy, count, toX, toY, LeftEnemy2Retangle, imageView, image_sprites);
            } else {
                check = false;
            }
        }
        if (a == 4) {
            if (checkMap(toX, toY + SPEED_MEDIUM)) {
                check = true;
                toY = toY + SPEED_MEDIUM;
                if (numberEnemy == 1)
                    Animation2(DelayEnemy, count, toX, toY, RightEnemy1Retangle, imageView, image_sprites);
                if (numberEnemy == 2)
                    Animation2(DelayEnemy, count, toX, toY, RightEnemy2Retangle, imageView, image_sprites);
            } else {
                check = false;
            }
        }
    }
    private boolean check = false;
    private int b;
    private boolean checkChase = false;

    @Override

    public void forCoordinates() {
        ArrayX[0] = toX ;
        ArrayX[1] = toX + 44;
        ArrayX[2] = toX ;
        ArrayX[3] = toX + 44;

        ArrayY[0] = toY ;
        ArrayY[1] = toY ;
        ArrayY[2] = toY + 44;
        ArrayY[3] = toY + 44;
    }
    public void moveEnemyOne (Bomberman bombermanRun) {
        forCoordinates();
        if ( count > 15) {
            count = 0;
        }
        if (check == false) {
            b = Random();
        }
        if (typeEnemy == 1)
            directionEnemy(b, 1);
        if (typeEnemy == 2) {

            if(enemyChase( bombermanRun.toX + 15, bombermanRun.toY + 22) != 0) {
                //checkChase = false;
                b = enemyChase(bombermanRun.toX + 15, bombermanRun.toY + 22);
                if (checkChase == false) {
                    toX = (int) ((toX + 22) / 48) * 48;
                    toY = (int) ((toY + 22) / 48) * 48;
                    moveCoordinates(toX, toY, imageView);
                    checkChase = true;
                }

            } else
                checkChase = false;
            directionEnemy(b, 2);

        }
        count ++;
    }

// enemyChase Đuổi theo người trong quá trình đuổi theo nếu gặp bom sẽ chạy
    // Nếu khoảng cách giữ người và enemy có vật cản (tường, bom, gạch) thì không đuổi theo
    private int enemyChase (int X, int Y) {

        int a = (int) ((toX + 22) / 48);
        int b = (int) ((toY + 22) / 48);
        if ( a == X/ 48) { // Khi Enemy2 cùng trục X với Bomber
            if ( b < Y / 48) { // Nếu Enemy2 ở trên Bomber
                for (int i = b; i < Y / 48; i ++) { // Xét xem khoảng cách giữu chúng có vật cản không
                    if( CheckForbiddenToMoveIn(LEVEL_MAP[i][a]) || LEVEL_MAP[i][a] == 'n')
                        return 0;
                }
                return 4;
            }
            if (b > Y / 48) { // Nếu Enemy2 ở dưới Bomber
                for (int i = (int) (Y/ 48); i < b; i ++) { // Xét xem khoảng cách giữu chúng có vật cản không
                    if( CheckForbiddenToMoveIn(LEVEL_MAP[i][a]) || LEVEL_MAP[i][a] == 'n')
                        return 0; // nếu có thì randum để chạy
                }
                return 3; // Nếu không thì đuổi theo
            }
        }
        if ( b == (int) (Y/ 48)) {
            if ( a < (int)(X / 48)) {
                for (int i = a; i < (int) (X / 48); i ++) {
                    if( CheckForbiddenToMoveIn(LEVEL_MAP[b][i]) || LEVEL_MAP[b][i] == 'n')
                        return 0;
                }
                return 1;
            }
            if (a > (int)(X / 48)) {

                for (int i = (int) (X / 48); i < a; i ++) {
                    if( CheckForbiddenToMoveIn(LEVEL_MAP[b][i]) || LEVEL_MAP[b][i] == 'n')
                        return 0;
                }
                return 2;
            }
        }
        return 0;
    }

    @Override
    public void die() {
        if (count > 30) {
            count = 0;
        }
        if (count <= 0) {
            if (typeEnemy == 1) {
                imageView.setViewport(typeEnemyOne);
            }
            if (typeEnemy == 2) {
                imageView.setViewport(typeEnmyTwo);
            }
        }
        Animation2(10 ,count, toX, toY, EnemyDie, imageView, image_sprites);
        count ++;
    }

    @Override
    public void loopDie() {
        DieEnemy = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (timeDie >= 30)
                    die();
                timeDie ++;
                if (timeDie >= 60){
                    DieEnemy.stop();
                    gamePane.getChildren().remove(imageView);
                }
            }
        };
        count = -10;
        DieEnemy.start();
    }

}
