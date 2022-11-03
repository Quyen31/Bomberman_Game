package entity.entityAnimation;

import Contruction.Image_Game;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Brick extends EntityAnimation {

    public Brick(int toX, int toY, char[][] LEVEL_MAP, AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
    }

    public void update() {
        imageView = new ImageView();
        Image_Game.insertImage(toX, toY, imageView, image_classic, BrickRectangle, gamePane);
    }

    public void remove() {
        gamePane.getChildren().remove(imageView);
        imageView = null;
    }

    /**
     * Ảnh động Brick biến mất
     * @param timeStop
     */
    @Override
    public void Animation(int timeStop) {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (count > 15)
                    count = 0;
                Image_Game.Animation(5, count, toX, toY,brickRectangle,imageView, image_sprites);
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
