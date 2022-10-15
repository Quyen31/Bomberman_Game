package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class Bomb implements Image_Game, Contruction_Game {

    private AnimationTimer loopBom;
    private int timeBom = 1;

    public static int checkNumberBom = 0;
    public static int checkFire = 1;
    private ImageView ImageBom;
    private ImageView ImageBom1;
    private List<ImageView> ImageBomRight;
    private List<ImageView> ImageBomLeft;
    private List<ImageView> ImageBomUp;
    private List<ImageView> ImageBomDown;

    public int toX = -48;

    public int toY = -48;

    public Bomb(AnchorPane GamePane){
        ImageBom = new ImageView();
        ImageBom1 = new ImageView();
        ImageBomLeft = new ArrayList<ImageView>();
        ImageBomRight = new ArrayList<ImageView>();
        ImageBomUp = new ArrayList<ImageView>();
        ImageBomDown = new ArrayList<ImageView>();
        GamePane.getChildren().add(ImageBom);
        GamePane.getChildren().add(ImageBom1);
        addAnchoPane(ImageBomRight, GamePane);
        addAnchoPane(ImageBomLeft, GamePane);
        addAnchoPane(ImageBomUp, GamePane);
        addAnchoPane(ImageBomDown, GamePane);
    }

    public int widthExplosiveMin;
    public int widthExplosiveMax;
    public int hightExplosiveMin;
    public int hightExplosiveMax;
    private int i = 0 ;

    private void addAnchoPane(List<ImageView> Image, AnchorPane GamePane) {
        for (int j = 0; j < checkFire; j ++) {
            Image.add(new ImageView());
            GamePane.getChildren().add(Image.get(Image.size()-1));
        }
    }
    public void createBomb()  {
        BombRectnagle();
        if ( i >= 15) {
            i = 0;
        }
        int a1 = (int)(toX + 24)/48;
        int b1 =  (int)(toY + 24)/48;
        Animation(8, i, a1, b1, bombRectangle, ImageBom, image_sprites);
        i++;
    }

    /**
     * Ứng với mỗi hướng sẽ tính ra 1 giới hạn.
     * Giới hạn sẽ được kéo dài đến khi gặp tường  hoặc gạch sẽ dừng .
     */

    private void limit(char[][] Map, int[][] Array, int b) {
        for (int j = 0; j < checkFire; j++) {
            if (CheckForbiddenToMoveIn(Map[Array[1][j]][Array[0][j]]) == true) {
                break;
            }
            if ( b == 1) {
                hightExplosiveMin = hightExplosiveMin - 48;
            }
            if (b == 2) {
                hightExplosiveMax = hightExplosiveMax + 48;
            }
            if (b == 3) {
                widthExplosiveMin = widthExplosiveMin - 48;
            }
            if (b == 4) {
                widthExplosiveMax = widthExplosiveMax + 48;
            }
        }
    }

    /**
     *
     * Hàm đưa ra giới hạn của bom.
     * Phục vụ cho Hàm die (tính giới hạn va chạm)
     */
    public void limitExplosive(char[][] Map) {
        int a = (int)(toX + 24)/48 ;
        int b =  (int)(toY + 24)/48 ;
        widthExplosiveMin = a * 48;
        widthExplosiveMax = a * 48 + 48;
        hightExplosiveMin = b * 48;
        hightExplosiveMax = b * 48 + 48;

        limit(Map, ArrayU, 1);
        limit(Map, ArrayD, 2);
        limit(Map, ArrayL, 3);
        limit(Map, ArrayR, 4);
    }

    /**
     *
     * Hàm xóa hình gạch trước khi bom nổ hiện các tia lửa phải xóa gạch
     */
    private void resetBrick(int[][] Array, char[][] Map, AnchorPane GamePane, ImageView[][] ImageMap) {
        for (int j = 0; j < checkFire; j++) {
            if (CheckForbiddenToMoveIn(Map[Array[1][j]][Array[0][j]]) == true) {
                if (Map[Array[1][j]][Array[0][j]] == '#')
                    break;
                GamePane.getChildren().remove(ImageMap[Array[1][j]][Array[0][j]]);
                break;
            }
        }
    }

    public void explosiveBombs(char[][] Map, ImageView[][] ImageMap, AnchorPane GamePane) {
        resetBrick(ArrayR, Map, GamePane, ImageMap);
        resetBrick(ArrayU, Map, GamePane, ImageMap);
        resetBrick(ArrayL, Map, GamePane, ImageMap);
        resetBrick(ArrayD, Map, GamePane, ImageMap);
    }

    private int [][] ArrayR = new int[2][checkFire];
    private int [][] ArrayL = new int[2][checkFire];
    private int [][] ArrayU = new int[2][checkFire];
    private int [][] ArrayD = new int[2][checkFire];


    private void reset (char[][] Map, int[][] Array, List<ImageView> imageViews) {
        for (int j = 0; j< checkFire; j++) {
            if (CheckForbiddenToMoveIn(Map[Array[1][j]][Array[0][j]]) == true) {
                if (Map[Array[1][j]][Array[0][j]] == '*') {
                    Map[Array[1][j]][Array[0][j]] = ' ';
                }
                if (Map[Array[1][j]][Array[0][j]] == 'B') {
                    Map[Array[1][j]][Array[0][j]] = 'b';
                }
                if (Map[Array[1][j]][Array[0][j]] == 'S') {
                    Map[Array[1][j]][Array[0][j]] = 's';
                }
                if (Map[Array[1][j]][Array[0][j]] == 'X') {
                    Map[Array[1][j]][Array[0][j]] = 'x';
                }
                if (Map[Array[1][j]][Array[0][j]] == 'F') {
                    Map[Array[1][j]][Array[0][j]] = 'f';
                }
                break;
            }
        }
        for (int j = 0; j < checkFire; j++) {
            imageViews.get(j).setLayoutX(-48);
            imageViews.get(j).setLayoutY(-48);
        }
    }
    public void resetMap(char[][] Map) {
        reset(Map, ArrayR, ImageBomRight);
        reset(Map, ArrayL, ImageBomLeft);
        reset(Map, ArrayU, ImageBomUp);
        reset(Map, ArrayD, ImageBomDown);
        ImageBom1.setLayoutX(-48);
        ImageBom1.setLayoutY(-48);
        i = 0;
    }
    private void FourWayCoordinates() {
        int a = (int)(toX + 24)/48;
        int b =  (int)(toY + 24)/48;
        for (int j = 0 ; j < checkFire; j++) {
            if (a + 1 + j > GAME_WIDTH) {
                ArrayR[0][j] = ArrayL[0][0];
                ArrayR[1][j] = ArrayL[1][0];
            } else {
                ArrayR[0][j] = a + 1 + j;
                ArrayR[1][j] = b;;
            }
        }
        for (int j = 0 ; j < checkFire; j++) {
            if (a - 1 - j < 0) {
                ArrayL[0][j] = ArrayL[0][0];
                ArrayL[1][j] = ArrayL[1][0];
            } else {
                ArrayL[0][j] = a - 1 - j;
                ArrayL[1][j] = b;
            }
        }
        for (int j = 0 ; j < checkFire; j++) {
            if (b - 1 - j < 0) {
                ArrayU[0][j] = ArrayU[0][0];
                ArrayU[1][j] = ArrayU[1][0];
            } else {
                ArrayU[0][j] = a;
                ArrayU[1][j] = b - 1 - j;
            }
        }
        for (int j = 0 ; j < checkFire; j++) {
            if (b + 1 + j > GAME_HEIGHT) {
                ArrayD[0][j] = ArrayD[0][0];
                ArrayD[1][j] = ArrayD[1][0];
            } else {
                ArrayD[0][j] = a;
                ArrayD[1][j] = b + 1 + j;
            }
        }
    }
    private void insertFire(int [][] Array, char[][] Map, AnchorPane GamePane, List<ImageView> imageViews, Rectangle2D[] rectangle2D, Rectangle2D[] rectangle2D1) {
        for (int j = 0; j< checkFire; j++) {
            if (Map[Array[1][j]][Array[0][j]] == '*' || Map[Array[1][j]][Array[0][j]] == 'B' || Map[Array[1][j]][Array[0][j]] == 'X'
                || Map[Array[1][j]][Array[0][j]] == 'F'|| Map[Array[1][j]][Array[0][j]] == 'S') {
                Animation(5, i, Array[0][j], Array[1][j],wallRectangle,imageViews.get(j) , image_sprites);
                break;
            } else if (Map[Array[1][j]][Array[0][j]] == '#') {
                break;
            } else {
                if (j == checkFire -1)
                    Animation(5, i, Array[0][j], Array[1][j], rectangle2D, imageViews.get(j), image_sprites);
                else
                    Animation(5, i, Array[0][j], Array[1][j], rectangle2D1, imageViews.get(j), image_sprites);
            }
        }
    }
    public void imageExplosiveBombs(char[][] Map, AnchorPane GamePane) {
        FireMindleRectangle();
        FireUpRectangle();
        FireDownRectangle();
        FireLeftRectangle();
        FireRightRectangle();
        FireRL();
        FireUD();
        wallRectangle();

        if ( i > 15) {
            i = 0;
        }
        Animation(5, i, (int)(toX + 24)/48, (int)(toY + 24)/48, mindleFireRectangle, ImageBom1, image_sprites);
        insertFire(ArrayR, Map, GamePane, ImageBomRight, rightFireRectangle, FireRLRectangle);
        insertFire(ArrayL, Map, GamePane, ImageBomLeft, leftFireRectangle, FireRLRectangle);
        insertFire(ArrayU, Map, GamePane, ImageBomUp, upFireRectangle, FireUDRectnagle);
        insertFire(ArrayD, Map, GamePane, ImageBomDown, downFireRectangle, FireUDRectnagle);
        i ++;
    }
    public void resertBomb(AnchorPane gamePane) {
        ImageBom.setLayoutX(-48);
        ImageBom.setLayoutY(-48);
        i = 0;
    }
    public void loopBom(AnchorPane gamePane, char[][] Map, ImageView[][] GrassBackground, List<Enemy> Enemy, List<Bomb> ArrayBomb) {
        loopBom = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (timeBom == 130) {
                    resertBomb(gamePane);
                    FourWayCoordinates();
                    limitExplosive(Map);
                    die(Enemy, gamePane);
                    explosiveBombs(Map,GrassBackground,gamePane);
                } else if (timeBom > 130 && timeBom < 145) {
                    imageExplosiveBombs(Map, gamePane);
                } else if (timeBom > 0 && timeBom < 130) {
                    createBomb();
                } else
                    resetMap(Map);

                if (timeBom == 145) {
                    loopBom.stop();
                    ArrayBomb.remove(this);
                    checkNumberBom --;
                    timeBom = 0;
                    Map[(int)(toY + 24) / 48  ][(int)(toX + 24) / 48] = ' ';
                }
                timeBom ++;
            }
        };
        Map[(int)(toY + 24) / 48  ][(int)(toX + 24) / 48] = 'n';
        loopBom.start();
    }

    private void die(List<Enemy> Enemy, AnchorPane gamePane) {
        int aX = (int) ((toX + 24)/48) * 48;
        int bY = (int) ((toY + 24)/48) * 48;
        for (int j = 0; j < Enemy.size(); j++) {

            for (int m = 0; m < 4; m++) {
                if (Enemy.get(j).ArrayX[m] <= widthExplosiveMax && Enemy.get(j).ArrayX[m] >= widthExplosiveMin ) {
                    if (Enemy.get(j).ArrayY[m] >= bY && Enemy.get(j).ArrayY[m] <= bY + 48) {
                        Enemy.get(j).die = true;
                        break;
                    }
                }
                if (Enemy.get(j).ArrayY[m] <= hightExplosiveMax && Enemy.get(j).ArrayY[m] >= hightExplosiveMin) {
                    if (Enemy.get(j).ArrayX[m] >= aX && Enemy.get(j).ArrayX[m] <= aX + 48) {
                        Enemy.get(j).die = true;
                        break;
                    }
                }
            }
        }
        for (int j = 0; j < Enemy.size(); j++) {

            if (Enemy.get(j).die == true) {
                Enemy.get(j).loopEnemyDie(gamePane);
                Enemy.remove(j);
                j--;
            }
        }
    }
}
