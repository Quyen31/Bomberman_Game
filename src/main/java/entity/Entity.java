package entity;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import Contruction.MusicGame;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public abstract class Entity implements Contruction_Game, Image_Game, MusicGame {
    protected int toX;
    protected int toY;
    protected ImageView imageView;
    protected AnchorPane gamePane;

    protected static char[][] LEVEL_MAP;

    public void setToX(int toX) {
        this.toX = toX;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    public Entity(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        this.toX = toX;
        this.toY = toY;
        this.LEVEL_MAP = LEVEL_MAP;
        this.gamePane = gamePane;
    }

    public abstract void update();
    public abstract void remove();
}
