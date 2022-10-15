package Contruction;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public interface Image_Game {
    String Grass = "src/main/resources/Resources/classic.png";
    String Grass1 = "src/main/resources/Resources/sprites.png";
    Image image_classic = new Image(new File(Grass).toURI().toString());
    Image image_sprites = new Image(new File(Grass1).toURI().toString());

    Rectangle2D[] wallRectangle = new Rectangle2D[3];

    Rectangle2D[] leftFireRectangle = new Rectangle2D[3];

    Rectangle2D[] rightFireRectangle = new Rectangle2D[3];

    Rectangle2D[] upFireRectangle = new Rectangle2D[3];

    Rectangle2D[] downFireRectangle = new Rectangle2D[3];

    Rectangle2D[] mindleFireRectangle = new Rectangle2D[3];

    Rectangle2D[] bombRectangle = new Rectangle2D[3];

    Rectangle2D[] LeftEnemy1Retangle = new Rectangle2D[3];

    Rectangle2D[] RightEnemy1Retangle = new Rectangle2D[3];

    Rectangle2D[] LeftEnemy2Retangle = new Rectangle2D[3];

    Rectangle2D[] RightEnemy2Retangle = new Rectangle2D[3];

    Rectangle2D[] FireRLRectangle = new Rectangle2D[3];

    Rectangle2D[] FireUDRectnagle = new Rectangle2D[3];

    Rectangle2D[] EnemyDie = new Rectangle2D[3];

    Rectangle2D GrassRectangle = new Rectangle2D(288,0,48,48);

    Rectangle2D typeEnemyOne = new Rectangle2D(288, 48, 48, 48);

    Rectangle2D typeEnmyTwo = new Rectangle2D(288, 96, 48, 48);
    Rectangle2D BombItem = new Rectangle2D(384, 384, 48, 48);
    Rectangle2D FlameItem = new Rectangle2D(432, 384, 48, 48);
    Rectangle2D SpeedItem = new Rectangle2D(480, 384, 48, 48);
    Rectangle2D Portal = new Rectangle2D(720, 0, 48, 48);
    Rectangle2D WallRectangle = new Rectangle2D(240,0,48,48);
    Rectangle2D BrickRectangle = new Rectangle2D(336,0,48,48);


    default void wallRectangle() {
        wallRectangle[0] = new Rectangle2D(432, 288, 48, 48);
        wallRectangle[1] = new Rectangle2D(480, 288, 48, 48);
        wallRectangle[2] = new Rectangle2D(528, 288, 48, 48);
    }

    default void FireLeftRectangle() {
        leftFireRectangle[0] = new Rectangle2D(144, 384, 48, 48);
        leftFireRectangle[1] = new Rectangle2D(192, 384, 48, 48);
        leftFireRectangle[2] = new Rectangle2D(240, 384, 48, 48);
    }

    default void FireRightRectangle() {
        rightFireRectangle[0] = new Rectangle2D(144, 480, 48, 48);
        rightFireRectangle[1] = new Rectangle2D(192, 480, 48, 48);
        rightFireRectangle[2] = new Rectangle2D(240, 480, 48, 48);
    }

    default void FireUpRectangle() {
        upFireRectangle[0] = new Rectangle2D(0, 432, 48, 48);
        upFireRectangle[1] = new Rectangle2D(48, 432, 48, 48);
        upFireRectangle[2] = new Rectangle2D(96, 432, 48, 48);
    }

    default void FireDownRectangle() {
        downFireRectangle[0] = new Rectangle2D(0, 528, 48, 48);
        downFireRectangle[1] = new Rectangle2D(48, 528, 48, 48);
        downFireRectangle[2] = new Rectangle2D(96, 528, 48, 48);
    }

    default void FireMindleRectangle() {
        mindleFireRectangle[0] = new Rectangle2D(0, 384, 48, 48);
        mindleFireRectangle[1] = new Rectangle2D(48, 384, 48, 48);
        mindleFireRectangle[2] = new Rectangle2D(96, 384, 48, 48);
    }

    default void FireRL() {
        FireRLRectangle[0] = new Rectangle2D(144, 432, 48, 48);
        FireRLRectangle[1] = new Rectangle2D(192, 432, 48, 48);
        FireRLRectangle[2] = new Rectangle2D(240, 432, 48, 48);
    }

    default void FireUD() {
        FireUDRectnagle[0] = new Rectangle2D(0, 480, 48, 48);
        FireUDRectnagle[1] = new Rectangle2D(48, 480, 48, 48);
        FireUDRectnagle[2] = new Rectangle2D(96, 480, 48, 48);
    }
    default void BombRectnagle() {
        bombRectangle[0] = new Rectangle2D(384, 192, 48, 48);
        bombRectangle[1] = new Rectangle2D(432, 192, 48, 48);
        bombRectangle[2] = new Rectangle2D(480, 192, 48, 48);
    }

    default void LeftEnemyOneRectangle() {
        LeftEnemy1Retangle[0] = new Rectangle2D(0, 48, 48, 48);
        LeftEnemy1Retangle[1] = new Rectangle2D(48, 48, 48, 48);
        LeftEnemy1Retangle[2] = new Rectangle2D(96, 48, 48, 48);
    }

    default void RightEnemyOneRectangle() {
        RightEnemy1Retangle[0] = new Rectangle2D(144, 48, 48, 48);
        RightEnemy1Retangle[1] = new Rectangle2D(192, 48, 48, 48);
        RightEnemy1Retangle[2] = new Rectangle2D(240, 48, 48, 48);
    }

    default void LeftEnemyTwoRectangle() {
        LeftEnemy2Retangle[0] = new Rectangle2D(0, 96, 48, 48);
        LeftEnemy2Retangle[1] = new Rectangle2D(48, 96, 48, 48);
        LeftEnemy2Retangle[2] = new Rectangle2D(96, 96, 48, 48);
    }

    default void RightEnemyTwoRectangle() {
        RightEnemy2Retangle[0] = new Rectangle2D(144, 96, 48, 48);
        RightEnemy2Retangle[1] = new Rectangle2D(192, 96, 48, 48);
        RightEnemy2Retangle[2] = new Rectangle2D(240, 96, 48, 48);
    }

    default void dieEnemyRectangle() {
        EnemyDie[0] = new Rectangle2D(528, 192, 48, 48);
        EnemyDie[1] = new Rectangle2D(576, 192, 48, 48);
        EnemyDie[2] = new Rectangle2D(624, 192, 48, 48);
    }



    default void Animation(int delay, int a, int coordinatesX, int coordinatesY, Rectangle2D[] rectangle2DS, ImageView imageView, Image image) {
        imageView.setImage(image);
        for (int j = 0; j < rectangle2DS.length; j++) {
            if (delay * j <= a && a < delay * (j + 1)) {
                imageView.setViewport(rectangle2DS[j]);
            }
        }
        imageView.setLayoutX(coordinatesX * 48);
        imageView.setLayoutY(coordinatesY * 48);

    }

    default void Animation2(int delay, int a, int coordinatesX, int coordinatesY, Rectangle2D[] rectangle2DS, ImageView imageView, Image image) {
        imageView.setImage(image);
        for (int j = 0; j < rectangle2DS.length; j++) {
            if (delay * j <= a && a < delay * (j + 1)) {
                imageView.setViewport(rectangle2DS[j]);
            }
        }
        imageView.setLayoutX(coordinatesX);
        imageView.setLayoutY(coordinatesY);

    }

    default void moveCoordinates(int nextX, int nextY, ImageView imageView) {
        imageView.setLayoutY(nextY);
        imageView.setLayoutX(nextX);
    }

    default void insertImage(int toX, int toY, ImageView imageView, Image image, Rectangle2D rectangle2D, AnchorPane pane) {
        //imageView = new ImageView();
        imageView.setImage(image);
        imageView.setViewport(rectangle2D);
        imageView.setLayoutX(toX);
        imageView.setLayoutY(toY);
        pane.getChildren().add(imageView);
    }
}
