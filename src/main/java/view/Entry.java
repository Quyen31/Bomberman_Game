package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class Entry {

    protected ImageView imageView;
    protected static char[][] LEVEL_MAP;
    protected static AnchorPane gamePane;
    public int toX;
    public int toY;
    public int heartX;
    public int heartY;
    public int[] ArrayX;
    public int[] ArrayY;
    protected int count = 0;
    public abstract void die();
    public abstract void loopDie();
    public abstract void forCoordinates();

    public abstract void heart();
}
