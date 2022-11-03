package entity.entityAnimation;

import entity.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.AnchorPane;

public abstract class EntityAnimation extends Entity {
    protected int count;
    protected int timeLoop;

    public void setTimeLoop(int timeLoop) {
        this.timeLoop = timeLoop;
    }

    protected AnimationTimer loop;
    public EntityAnimation(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
    }

    public abstract void Animation(int timeStop);
}
