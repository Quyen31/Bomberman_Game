package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Bomb implements Image_Game, Contruction_Game {

    private static ImageView ImageBom;
    private static ImageView ImageBom1;
    private static ImageView ImageBom2;
    private static ImageView ImageBom3;
    private static ImageView ImageBom4;
    private static ImageView ImageBom5;

    private static int toX = -48;

    private static int toY = -48;

    public Bomb(AnchorPane GamePane){
        ImageBom = new ImageView();
        ImageBom1 = new ImageView();
        ImageBom2 = new ImageView();
        ImageBom3 = new ImageView();
        ImageBom4 = new ImageView();
        ImageBom5 = new ImageView();
        GamePane.getChildren().add(ImageBom);
        GamePane.getChildren().add(ImageBom1);
        GamePane.getChildren().add(ImageBom2);
        GamePane.getChildren().add(ImageBom3);
        GamePane.getChildren().add(ImageBom4);
        GamePane.getChildren().add(ImageBom5);
    }

    public static int getToX() {
        return toX;
    }

    public static int getToY() {
        return toY;
    }

    public static ImageView getImageBom() {
        return ImageBom;
    }

    public static void setImageBom(ImageView imageBom) {
        ImageBom = imageBom;
    }

    public static void setToX(int toX) {
        Bomb.toX = toX;
    }

    public static void setToY(int toY) {
        Bomb.toY = toY;
    }
    private int i = 0 ;
    public void createBomb()  {
        BombRectnagle();
        if ( i >= 15) {
            i = 0;
        }
        int a1 = (int)(toX + 24)/48;
        int b1 =  (int)(toY + 24)/48;
        Animation(i, a1, b1, bombRectangle, ImageBom, image_sprites);
        i++;
    }

    public void explosiveBombs(char[][] Map, ImageView[][] ImageMap, AnchorPane GamePane) {
        int a = (int)(toX + 24)/48;
        int b =  (int)(toY + 24)/48;
        //Map[b][a] = ' ';
        if (Map[b - 1][a] == '*') {
            GamePane.getChildren().remove(ImageMap[b - 1][a]);
        }
        if (Map[b + 1][a] == '*') {
            GamePane.getChildren().remove(ImageMap[b+1][a]);
        }
        if (Map[b][a - 1] == '*' ) {
            GamePane.getChildren().remove(ImageMap[b][a - 1]);
        }
        if (Map[b][a + 1] == '*') {
            GamePane.getChildren().remove(ImageMap[b][a + 1]);
        }
    }

    private static int [] ArrayX = new int[4];
    private static int [] ArrayY = new int[4];

    public void resetMap(char[][] Map) {
        for (int j = 0; j < 4; j++) {
            if (Map[ArrayY[j]][ArrayX[j]] == '*') {
                Map[ArrayY[j]][ArrayX[j]] = ' ';
            }
        }
        ImageBom1.setLayoutX(-48);
        ImageBom1.setLayoutY(-48);
        ImageBom2.setLayoutX(-48);
        ImageBom2.setLayoutY(-48);
        ImageBom3.setLayoutX(-48);
        ImageBom3.setLayoutY(-48);
        ImageBom4.setLayoutX(-48);
        ImageBom4.setLayoutY(-48);
        ImageBom5.setLayoutX(-48);
        ImageBom5.setLayoutY(-48);
        i = 0;

    }
    public void imageExplosiveBombs(char[][] Map, AnchorPane GamePane) {
        FireMindleRectangle();
        FireUpRectangle();
        FireDownRectangle();
        FireLeftRectangle();
        FireRightRectangle();
        wallRectangle();
        int a = (int)(toX + 24)/48;
        int b =  (int)(toY + 24)/48;
        ArrayX[0] = a;
        ArrayX[1] = a;
        ArrayX[2] = a - 1;
        ArrayX[3] = a + 1;

        ArrayY[0] = b - 1;
        ArrayY[1] = b + 1;
        ArrayY[2] = b;
        ArrayY[3] = b;

        if ( i > 15) {
            i = 0;
        }
        Animation(i, a, b, mindleFireRectangle, ImageBom1, image_sprites);
        for (int j = 0; j < 4; j++) {
            if (Map[ArrayY[j]][ArrayX[j]] == ' ') {
                if (j == 0) {
                    Animation(i, a, b - 1, upFireRectangle, ImageBom5, image_sprites);
                }

                if (j == 1) {
                    Animation(i, a, b + 1, downFireRectangle, ImageBom4, image_sprites);
                }
                if (j == 2) {
                    Animation(i, a - 1, b, leftFireRectangle, ImageBom2, image_sprites);
                }

                if (j == 3) {
                    Animation(i, a + 1, b, rightFireRectangle, ImageBom3, image_sprites);

                }
            }
            if (Map[ArrayY[j]][ArrayX[j]] == '*') {
                if (j == 0) {
                    Animation(i, a, b - 1, wallRectangle, ImageBom5, image_sprites);
                }

                if (j == 1) {
                    Animation(i, a, b + 1, wallRectangle, ImageBom4, image_sprites);
                }
                if (j == 2) {
                    Animation(i, a - 1, b, wallRectangle, ImageBom2, image_sprites);
                }

                if (j == 3) {
                    Animation(i, a + 1, b, wallRectangle, ImageBom3, image_sprites);
                }

            }
        }
        i ++;
    }
    public void resertBomb(AnchorPane gamePane) {
        ImageBom.setLayoutX(-48);
        ImageBom.setLayoutY(-48);
        i = 0;
    }
}
