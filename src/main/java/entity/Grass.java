package entity;

import Contruction.Image_Game;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Grass extends Entity{

    public Grass(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
        imageView = new ImageView();
    }

    public void update() {
        Image_Game.insertImage(toX, toY, imageView, image_classic, GrassRectangle, gamePane);
    }

    public void remove() {

        gamePane.getChildren().remove(imageView);
        imageView = null;
    }
}
