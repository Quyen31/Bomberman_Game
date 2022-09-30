package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Bomberman_run implements Image_Game, Contruction_Game {
    private static ImageView imageBomberman;
    private static Rectangle2D rectangle2D;

    private static int speed = 2; // Vận tốc

    private static int toX = 48;
    private static int toY = 48;
    private static int i = 0;

    public static void setSpeed(int speed) {
        Bomberman_run.speed = speed;
    }

    public static int getSpeed() {
        return speed;
    }

    /**
     * Contruction
     */
    public Bomberman_run() {
        imageBomberman = new ImageView();
    }

    public static void cutRectangleImage(int a, String direction) {
        if (a >= 0 && a <= 4) {
            if (direction == RIGHT)
                rectangle2D = new Rectangle2D(288 , 0, 48, 48);

            if (direction == LEFT)
                rectangle2D = new Rectangle2D(288 + 48 * 3 , 0, 48, 48);
            if (direction == UP)
                rectangle2D = new Rectangle2D(0 , 0, 48, 48);

            if (direction == DOWN)
                rectangle2D = new Rectangle2D(48 * 3 , 0, 48, 48);
            imageBomberman.setViewport(rectangle2D);
        }
        if (a >= 5 && a <= 10) {
            if (direction == RIGHT)
                rectangle2D = new Rectangle2D(288 + 48, 0, 48, 48);

            if (direction == LEFT)
                rectangle2D = new Rectangle2D(288 + 48 * 4 , 0, 48, 48);

            if (direction == UP)
                rectangle2D = new Rectangle2D(48 , 0, 48, 48);

            if (direction == DOWN)
                rectangle2D = new Rectangle2D(48 * 4 , 0, 48, 48);
            imageBomberman.setViewport(rectangle2D);
        }
        if (a >= 10 && a <= 15) {
            if (direction == RIGHT)
                rectangle2D = new Rectangle2D(288 + 48 * 2, 0, 48, 48);

            if (direction == LEFT)
                rectangle2D = new Rectangle2D(288 + 48 * 5 , 0, 48, 48);

            if (direction == UP)
                rectangle2D = new Rectangle2D(48 * 2 , 0, 48, 48);

            if (direction == DOWN)
                rectangle2D = new Rectangle2D(48 * 5 , 0, 48, 48);
            imageBomberman.setViewport(rectangle2D);
        }
    }
    public static void setRun (AnchorPane GamePane) {
        imageBomberman.setImage(image_sprites);
        cutRectangleImage(0,RIGHT);
        imageBomberman.setLayoutX(48);
        imageBomberman.setLayoutY(48);
        GamePane.getChildren().add(imageBomberman);
    }
    public static void Run (String direction) {
        if ( i > 15) {
            i = 0;
        }
        if (direction == RIGHT)
            toX = toX + speed;
        if (direction == LEFT)
            toX = toX - speed;
        if (direction == UP)
            toY = toY - speed;
        if (direction == DOWN)
            toY = toY + speed;
        cutRectangleImage(i, direction);
        i++;
        imageBomberman.setLayoutX(toX);
        imageBomberman.setLayoutY(toY);
    }

}
