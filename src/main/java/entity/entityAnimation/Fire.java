package entity.entityAnimation;

import Contruction.Image_Game;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Fire extends EntityAnimation {
    private String Fire;

    private Rectangle2D[] rectangle2D;

    public Fire(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane, String Fire) {
        super(toX, toY, LEVEL_MAP, gamePane);
        this.Fire = Fire;
    }

    @Override
    public void update() {
        imageView = new ImageView();
        gamePane.getChildren().add(imageView);
        if (Fire == FIRE_RIGHT)
            rectangle2D = rightFireRectangle;
        if (Fire == FIRE_LEFT)
            rectangle2D = leftFireRectangle;
        if (Fire == FIRE_UP)
            rectangle2D = upFireRectangle;
        if (Fire == FIRE_DOWN)
            rectangle2D = downFireRectangle;
        if (Fire == FIRE_RL)
            rectangle2D = FireRLRectangle;
        if (Fire == FIRE_UD)
            rectangle2D = FireUDRectnagle;
        if (Fire == FIRE_MINDLE)
            rectangle2D = mindleFireRectangle;
    }

    @Override
    public void remove() {
        gamePane.getChildren().remove(imageView);
        imageView = null;
    }

    @Override
    public void Animation(int timeStop) {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (count > 15)
                    count = 0;
                Image_Game.Animation(5, count, toX * UNIT, toY * UNIT, rectangle2D, imageView, image_sprites);
                count ++;
                timeLoop ++;
                if (timeLoop == timeStop) {
                    timeLoop = 0;
                    loop.stop();
                    remove();
                }
            }
        };
        loop.start();
    }

}
