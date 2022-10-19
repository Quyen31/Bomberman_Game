package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import Contruction.MusicGame;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.Music;

import java.util.ArrayList;
import java.util.List;

public class Bomb implements Image_Game, Contruction_Game, MusicGame {

    private AnimationTimer loopBom;
    private int timeBom = 1;

    private AnchorPane GamePane;

    private static char[][] Map;

    private static int GAME_WIDTH;

    private static int GAME_HEIGHT;

    public static int checkNumberBom = 0;// Số thứ tự của Bom hiện tại trong ArrayBom
    public static int checkFire = 1; // Số lửa tạo ra từ bom từ các phía
    private ImageView ImageBom;
    private ImageView ImageBom1;
    private List<ImageView> ImageBomRight;
    private List<ImageView> ImageBomLeft;
    private List<ImageView> ImageBomUp;
    private List<ImageView> ImageBomDown;

    public int toX;

    public int toY;

    public Bomb(AnchorPane GamePane, char[][] Map, int GAME_HEIGHT, int GAME_WIDTH){
        this.GAME_HEIGHT = GAME_HEIGHT;
        this.GAME_WIDTH = GAME_WIDTH;
        this.Map = Map;
        this.GamePane = GamePane;
        ImageBom = new ImageView();
        ImageBom1 = new ImageView();
        ImageBomLeft = new ArrayList<ImageView>();
        ImageBomRight = new ArrayList<ImageView>();
        ImageBomUp = new ArrayList<ImageView>();
        ImageBomDown = new ArrayList<ImageView>();
        GamePane.getChildren().add(ImageBom);
        GamePane.getChildren().add(ImageBom1);
        addAnchoPane(ImageBomRight);
        addAnchoPane(ImageBomLeft);
        addAnchoPane(ImageBomUp);
        addAnchoPane(ImageBomDown);
    }

    public int widthExplosiveMin;
    public int widthExplosiveMax;
    public int hightExplosiveMin;
    public int hightExplosiveMax;
    private int i = 0 ;

    private void addAnchoPane(List<ImageView> Image) {
        for (int j = 0; j < checkFire; j ++) {
            Image.add(new ImageView());
            GamePane.getChildren().add(Image.get(Image.size()-1));
        }
    }

    private void rmoveAnchoPane(List<ImageView> Image) {
        for (int j = 0; j < checkFire; j ++) {
            GamePane.getChildren().remove(Image.get(j));
        }
    }
    public void createBomb()  {
        BombRectnagle();
        if ( i >= 15) {
            i = 0;
        }
        int a1 = (int)toX/48;
        int b1 =  (int)toY/48;
        Animation(8, i, a1, b1, bombRectangle, ImageBom, image_sprites);
        i++;
    }

    /**
     * Ứng với mỗi hướng sẽ tính ra 1 giới hạn.
     * Giới hạn sẽ được kéo dài đến khi gặp tường  hoặc gạch sẽ dừng .
     */

    private void limit(int[][] Array, int b) {
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
    public void limitExplosive() {
//        int a = (int)(toX + 24)/48 ;
//        int b =  (int)(toY + 24)/48 ;
        int a = (int)toX/48 ;
        int b =  (int)toY/48 ;
        widthExplosiveMin = a * 48;
        widthExplosiveMax = a * 48 + 48;
        hightExplosiveMin = b * 48;
        hightExplosiveMax = b * 48 + 48;

        limit(ArrayU, 1);
        limit(ArrayD, 2);
        limit(ArrayL, 3);
        limit(ArrayR, 4);
    }

    /**
     *
     * Hàm xóa hình gạch trước khi bom nổ hiện các tia lửa phải xóa gạch
     */
    private void resetBrick(int[][] Array, ImageView[][] ImageMap) {
        for (int j = 0; j < checkFire; j++) {
            if (CheckForbiddenToMoveIn(Map[Array[1][j]][Array[0][j]]) == true) {
                if (Map[Array[1][j]][Array[0][j]] == '#')
                    break;
                GamePane.getChildren().remove(ImageMap[Array[1][j]][Array[0][j]]);
                break;
            }
        }
    }

    public void explosiveBombs(ImageView[][] ImageMap) {
        resetBrick(ArrayR, ImageMap);
        resetBrick(ArrayU, ImageMap);
        resetBrick(ArrayL, ImageMap);
        resetBrick(ArrayD, ImageMap);
    }

    private int [][] ArrayR = new int[2][checkFire];
    private int [][] ArrayL = new int[2][checkFire];
    private int [][] ArrayU = new int[2][checkFire];
    private int [][] ArrayD = new int[2][checkFire];


    private void reset (int[][] Array, List<ImageView> imageViews) {
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
        rmoveAnchoPane(imageViews);
    }
    public void resetMap() {
        reset(ArrayR, ImageBomRight);
        reset(ArrayL, ImageBomLeft);
        reset(ArrayU, ImageBomUp);
        reset(ArrayD, ImageBomDown);
        GamePane.getChildren().remove(ImageBom1);
        i = 0;
    }
    private void FourWayCoordinates() {
//        int a = (int)(toX + 24)/48;
//        int b =  (int)(toY + 24)/48;
        int a = (int)(toX)/48;
        int b =  (int)(toY)/48;
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
    private void insertFire(int [][] Array, List<ImageView> imageViews, Rectangle2D[] rectangle2D, Rectangle2D[] rectangle2D1) {
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
    public void imageExplosiveBombs() {
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
//        Animation(5, i, (int)(toX + 24)/48, (int)(toY + 24)/48, mindleFireRectangle, ImageBom1, image_sprites);
        Animation(5, i, (int)(toX)/48, (int)(toY)/48, mindleFireRectangle, ImageBom1, image_sprites);

        insertFire(ArrayR, ImageBomRight, rightFireRectangle, FireRLRectangle);
        insertFire(ArrayL, ImageBomLeft, leftFireRectangle, FireRLRectangle);
        insertFire(ArrayU, ImageBomUp, upFireRectangle, FireUDRectnagle);
        insertFire(ArrayD, ImageBomDown, downFireRectangle, FireUDRectnagle);
        i ++;
    }
    public void resertBomb(AnchorPane gamePane) {
        GamePane.getChildren().remove(ImageBom);
        i = 0;
    }

    private Music musicBomb;
    public void loopBom(ImageView[][] GrassBackground, List<Enemy> Enemy, List<Bomb> ArrayBomb, Bomberman bombermanRun) {
        loopBom = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (timeBom == 130) {
                    resertBomb(GamePane);
                    FourWayCoordinates();
                    limitExplosive();
                    musicBomb.musicStart(25);
                    die(Enemy, bombermanRun);
                    explosiveBombs(GrassBackground);
                } else if (timeBom > 130 && timeBom < 145) {
                    imageExplosiveBombs();
                } else if (timeBom > 0 && timeBom < 130) {
                    createBomb();
                } else
                    resetMap();

                if (timeBom == 145) {
                    loopBom.stop();
                    ArrayBomb.remove(this);
                    checkNumberBom --;
                    timeBom = 0;
//                    Map[(int)(toY + 24) / 48  ][(int)(toX + 24) / 48] = ' ';
                    Map[(int)(toY) / 48  ][(int)(toX) / 48] = ' ';
                }
                timeBom ++;
            }
        };
        musicBomb = new Music(music_bomb_url);
        musicBomb.Volume(3);
//        Map[(int)(toY + 24) / 48  ][(int)(toX + 24) / 48] = 'n';
        Map[(int)(toY) / 48  ][(int)(toX) / 48] = 'n';
        loopBom.start();
    }


    private void die(List<Enemy> Enemy, Bomberman bombermanRun) {
//        int aX = (int) ((toX + 24)/48) * 48;
//        int bY = (int) ((toY + 24)/48) * 48;
        int aX = (int) ((toX)/48) * 48;
        int bY = (int) ((toY)/48) * 48;

        // Enemy die bị bom nổ
        for (int j = 0; j < Enemy.size(); j++) {

            for (int m = 0; m < 4; m++) {
                if (Enemy.get(j).ArrayX[m] < widthExplosiveMax && Enemy.get(j).ArrayX[m] > widthExplosiveMin &&
                        Enemy.get(j).ArrayY[m] > bY && Enemy.get(j).ArrayY[m] < bY + 48){
                    Enemy.get(j).die = true;
                    break;
                }
                if (Enemy.get(j).ArrayY[m] < hightExplosiveMax && Enemy.get(j).ArrayY[m] > hightExplosiveMin &&
                        Enemy.get(j).ArrayX[m] > aX && Enemy.get(j).ArrayX[m] < aX + 48) {
                    Enemy.get(j).die = true;
                    break;
                }
            }
        }
        for (int j = 0; j < Enemy.size(); j++) {

            if (Enemy.get(j).die == true) {
                Enemy.get(j).loopDie();
                Enemy.remove(j);
                j--;
            }
        }


        // Bom die Bomber
        for (int m = 0; m < 4; m++) {
            if (bombermanRun.ArrX[m] < widthExplosiveMax && bombermanRun.ArrX[m] > widthExplosiveMin &&
                    bombermanRun.ArrY[m] > bY && bombermanRun.ArrY[m] < bY + 48) {
                bombermanRun.loopDie();
                break;
            }
            if (bombermanRun.ArrY[m] < hightExplosiveMax && bombermanRun.ArrY[m] > hightExplosiveMin &&
                    bombermanRun.ArrX[m] > aX && bombermanRun.ArrX[m] < aX + 48) {
                bombermanRun.loopDie();
                break;
            }
        }
    }
}
