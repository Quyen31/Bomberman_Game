package Contruction;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    default void BombRectnagle() {
        bombRectangle[0] = new Rectangle2D(384, 192, 48, 48);
        bombRectangle[1] = new Rectangle2D(432, 192, 48, 48);
        bombRectangle[2] = new Rectangle2D(480, 192, 48, 48);
    }

    default void Animation(int a, int coordinatesX, int coordinatesY, Rectangle2D[] rectangle2DS, ImageView imageView, Image image) {
        imageView.setImage(image);
        for (int j = 0; j < rectangle2DS.length; j++) {
            if (5 * j <= a && a < 5 * (j + 1)) {
                imageView.setViewport(rectangle2DS[j]);
            }
        }
        imageView.setLayoutX(coordinatesX * 48);
        imageView.setLayoutY(coordinatesY * 48);

    }
}
