package entity.entityMove;

import entity.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.AnchorPane;

public abstract class EntityMove extends Entity {
    protected AnimationTimer loopDie;
    protected int speed = SPEED_MEDIUM; // Vận tốc
    protected int timeDie;
    public int heartX;
    public int heartY;
    public int[] ArrayX;
    public int[] ArrayY;
    protected int count = 0;

    public EntityMove(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
    }

    public abstract void die();
    public abstract void loopDie();
    public abstract void forCoordinates();
    public abstract void heart();
    protected abstract boolean checkMap(int nextToX, int nextToY);
}
