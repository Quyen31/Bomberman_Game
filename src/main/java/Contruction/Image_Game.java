package Contruction;

import javafx.scene.image.Image;

import java.io.File;

public interface Image_Game {
    String Grass = "src/main/resources/Resources/classic.png";
    String Grass1 = "src/main/resources/Resources/sprites.png";
    Image image_classic = new Image(new File(Grass).toURI().toString());
    Image image_sprites = new Image(new File(Grass1).toURI().toString());
}
