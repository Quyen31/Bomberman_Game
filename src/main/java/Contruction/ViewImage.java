package Contruction;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ViewImage {
    private ImageView imageView;
    private Image image;

    private Rectangle2D rectangle2D;

    public ViewImage(Image image) {
        this.image = image;
        imageView = new ImageView();
    }

    public ViewImage(Image image, double toX, double toY, double Width, double Height) {
        this.image = image;
        imageView = new ImageView();
        rectangle2D = new Rectangle2D(toX, toY, Width, Height);
    }

    public void cutImage_View(double toX, double toY, AnchorPane gameAnchor) {
        imageView.setImage(this.image);
        imageView.setViewport(rectangle2D);
        imageView.setLayoutX(toX);
        imageView.setLayoutY(toY);
        gameAnchor.getChildren().add(imageView);
    }

    public Image amm() {
        imageView.setImage(this.image);
        imageView.setViewport(rectangle2D);
        return imageView.getImage();
    }

}
