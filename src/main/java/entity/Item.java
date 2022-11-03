package entity;

import Contruction.Image_Game;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Item extends Entity{
    private char item;

    public char getItem() {
        return item;
    }

    public Item(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
        imageView = new ImageView();
    }

    public void update() {
        if (LEVEL_MAP[toY / UNIT][toX/ UNIT] == 'b')
            Image_Game.insertImage(toX, toY, imageView, image_sprites, BombItem, gamePane);
        if (LEVEL_MAP[toY / UNIT][toX/ UNIT] == 'f')
            Image_Game.insertImage(toX, toY, imageView, image_sprites, FlameItem, gamePane);
        if (LEVEL_MAP[toY / UNIT][toX/ UNIT] == 's')
            Image_Game.insertImage(toX, toY, imageView, image_sprites, SpeedItem, gamePane);
        if (LEVEL_MAP[toY / UNIT][toX/ UNIT] == 'x')
            Image_Game.insertImage(toX, toY, imageView, image_sprites, Portal, gamePane);
        item = LEVEL_MAP[toY / UNIT][toX/ UNIT];
        LEVEL_MAP[toY / UNIT][toX/ UNIT] = '*';
    }

    public void remove() {
        gamePane.getChildren().remove(imageView);
        imageView = null;
    }

}
