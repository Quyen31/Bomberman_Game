package entity;

import Contruction.Image_Game;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Wall extends Entity{
    public Wall(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
        imageView = new ImageView();
    }

    public void update() {
        Image_Game.insertImage(toX, toY, imageView, image_classic, WallRectangle, gamePane);
    }

    public void remove() {

        gamePane.getChildren().remove(imageView);
        imageView = null;
    }
}
