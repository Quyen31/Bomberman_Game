package Contruction;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

import java.io.File;

public interface Image_Game {
    String Grass = "src/main/resources/Resources/classic.png";
    String Grass1 = "src/main/resources/Resources/sprites.png";
    Image image1 = new Image(new File("src/main/resources/Resources/blue.png").toURI().toString(),256, 256, false, true);
    BackgroundImage backgroundImage1 = new BackgroundImage(image1, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
    String TEXT_LEVE1 = "src/main/resources/Resources/image_level/level1.png";
    String TEXT_LEVE2 = "src/main/resources/Resources/image_level/level2.png";

    String GAME_OVER = "src/main/resources/Resources/GAME_OVER.png";
    String GAME_WIN = "src/main/resources/Resources/GAME_WIN.png";
    Image image_classic = new Image(new File(Grass).toURI().toString());
    Image image_sprites = new Image(new File(Grass1).toURI().toString());
    Image image_text_level1 = new Image(new File(TEXT_LEVE1).toURI().toString());
    Image image_text_level2 = new Image(new File(TEXT_LEVE2).toURI().toString());

    Image[] image_text_level = {image_text_level1, image_text_level2};

    Image image_game_over = new Image(new File(GAME_OVER).toURI().toString());
    Image image_game_win = new Image(new File(GAME_WIN).toURI().toString());

    Rectangle2D[] bomberLeftRectangle = {
            new Rectangle2D(288 + 48 * 3 , 0, 48, 48),
            new Rectangle2D(288 + 48 * 4 , 0, 48, 48),
            new Rectangle2D(288 + 48 * 5 , 0, 48, 48)
    };
    Rectangle2D[] bomberRightRectangle = {
            new Rectangle2D(288 , 0, 48, 48),
            new Rectangle2D(288 + 48, 0, 48, 48),
            new Rectangle2D(288 + 48 * 2, 0, 48, 48)};
    Rectangle2D[] bomberUpRectangle = {
            new Rectangle2D(0 , 0, 48, 48),
            new Rectangle2D(48 , 0, 48, 48),
            new Rectangle2D(48 * 2 , 0, 48, 48)
    };
    Rectangle2D[] bomberDownRectangle = {
            new Rectangle2D(48 * 3 , 0, 48, 48),
            new Rectangle2D(48 * 4 , 0, 48, 48),
            new Rectangle2D(48 * 5 , 0, 48, 48)
    };

    Rectangle2D[] brickRectangle = {
            new Rectangle2D(432, 288, 48, 48),
            new Rectangle2D(480, 288, 48, 48),
            new Rectangle2D(528, 288, 48, 48)
    };

    Rectangle2D[] leftFireRectangle = {
            new Rectangle2D(144, 384, 48, 48),
            new Rectangle2D(192, 384, 48, 48),
            new Rectangle2D(240, 384, 48, 48),
    };

    Rectangle2D[] rightFireRectangle = {
            new Rectangle2D(144, 480, 48, 48),
            new Rectangle2D(192, 480, 48, 48),
            new Rectangle2D(240, 480, 48, 48),
    };

    Rectangle2D[] upFireRectangle = {
            new Rectangle2D(0, 432, 48, 48),
            new Rectangle2D(48, 432, 48, 48),
            new Rectangle2D(96, 432, 48, 48)
    };

    Rectangle2D[] downFireRectangle = {
            new Rectangle2D(0, 528, 48, 48),
            new Rectangle2D(48, 528, 48, 48),
            new Rectangle2D(96, 528, 48, 48)
    };

    Rectangle2D[] mindleFireRectangle = {
            new Rectangle2D(0, 384, 48, 48),
            new Rectangle2D(48, 384, 48, 48),
            new Rectangle2D(96, 384, 48, 48)
    };

    Rectangle2D[] bombRectangle = {
            new Rectangle2D(384, 192, 48, 48),
            new Rectangle2D(432, 192, 48, 48),
            new Rectangle2D(480, 192, 48, 48)
    };

    Rectangle2D[] FireRLRectangle = {
            new Rectangle2D(144, 432, 48, 48),
            new Rectangle2D(192, 432, 48, 48),
            new Rectangle2D(240, 432, 48, 48)
    };

    Rectangle2D[] FireUDRectnagle = {
            new Rectangle2D(0, 480, 48, 48),
            new Rectangle2D(48, 480, 48, 48),
            new Rectangle2D(96, 480, 48, 48)
    };
    Rectangle2D[] LeftBalloomRetangle = {
            new Rectangle2D(0, 48, 48, 48),
            new Rectangle2D(48, 48, 48, 48),
            new Rectangle2D(96, 48, 48, 48)

    };

    Rectangle2D[] RightBalloomRetangle = {
            new Rectangle2D(144, 48, 48, 48),
            new Rectangle2D(192, 48, 48, 48),
            new Rectangle2D(240, 48, 48, 48)
    };

    Rectangle2D[] LeftOnealRetangle = {
            new Rectangle2D(0, 96, 48, 48),
            new Rectangle2D(48, 96, 48, 48),
            new Rectangle2D(96, 96, 48, 48)
    };

    Rectangle2D[] RightOnealRetangle = {
            new Rectangle2D(144, 96, 48, 48),
            new Rectangle2D(192, 96, 48, 48),
            new Rectangle2D(240, 96, 48, 48)
    };

    Rectangle2D[] LeftDollRectangle = {
            new Rectangle2D(0, 3 * 48, 48, 48),
            new Rectangle2D(48, 3 * 48, 48, 48),
            new Rectangle2D(48 * 2, 3 * 48, 48, 48)
    };

    Rectangle2D[] RightDollRectangle = {
            new Rectangle2D(48 * 3, 3 * 48, 48, 48),
            new Rectangle2D(48 * 4, 3 * 48, 48, 48),
            new Rectangle2D(48 * 5, 3 * 48, 48, 48)
    };

    Rectangle2D[] RightNightmareRectangle = {
            new Rectangle2D(48 * 11, 48, 48, 48),
            new Rectangle2D(48 * 12, 48, 48, 48),
            new Rectangle2D(48 * 13,  48, 48, 48)
    };

    Rectangle2D[] LeftNightmareRectangle = {
            new Rectangle2D(48 * 8, 48, 48, 48),
            new Rectangle2D(48 * 9, 48, 48, 48),
            new Rectangle2D(48 * 10, 48, 48, 48)
    };
    Rectangle2D[] BomberDie = {
            new Rectangle2D(576, 0, 48, 48),
            new Rectangle2D(624, 0, 48, 48),
            new Rectangle2D(672, 0, 48, 48)

    };
    Rectangle2D[] EnemyDie = {
            new Rectangle2D(528, 192, 48, 48),
            new Rectangle2D(576, 192, 48, 48),
            new Rectangle2D(624, 192, 48, 48)
    };

    Rectangle2D GrassRectangle = new Rectangle2D(288,0,48,48);

    Rectangle2D dieBalloom = new Rectangle2D(288, 48, 48, 48);

    Rectangle2D dieNightmare = new Rectangle2D(48 * 14, 48, 48, 48);
    Rectangle2D dieDoll = new Rectangle2D(48 * 6, 48 * 3, 48, 48);

    Rectangle2D dieOneal = new Rectangle2D(288, 96, 48, 48);
    Rectangle2D BombItem = new Rectangle2D(384, 384, 48, 48);
    Rectangle2D FlameItem = new Rectangle2D(432, 384, 48, 48);
    Rectangle2D SpeedItem = new Rectangle2D(480, 384, 48, 48);
    Rectangle2D Portal = new Rectangle2D(720, 0, 48, 48);
    Rectangle2D WallRectangle = new Rectangle2D(240,0,48,48);
    Rectangle2D BrickRectangle = new Rectangle2D(336,0,48,48);

    /**
     * delay là tần số hình ảnh nhấp nháy delay càng nhều thì nhấp nháy càng chậm
     * cout là biến đếm mỗi lần biến đếm tăng đến một khoảng nhất định nào đó thì sẽ đổi hình
     * Rectangle là bộ khung hình chữ nhật hiện ảnh
     * image là ảnh lớn
     * imageView là hiện ảnh đấy ra với khung hình nào trên 1 ảnh lớn
     * Hàm này dùng để kiểm soát độ nhấp nháy
     * Nó được kết hợp với một vòng loop (AnimationTime) và một biến count truyền vào
     * để tạo một ảnh động (VD Bom ... hay bomber để di chuyển nhờ kết hợp nó với loop cuả game)
     * count bên ngoài phải có giới hạn count < delay * rectangle2DS.length rồi phải quay lại count = 0;
     */
    static void Animation(int delay, int count, int coordinatesX, int coordinatesY, Rectangle2D[] rectangle2DS, ImageView imageView, Image image) {
        imageView.setImage(image);
        for (int j = 0; j < rectangle2DS.length; j++) {
            if (delay * j <= count && count < delay * (j + 1)) {
                imageView.setViewport(rectangle2DS[j]);
            }
        }
        imageView.setLayoutX(coordinatesX);
        imageView.setLayoutY(coordinatesY);

    }

    /**
     *Di chuyển ảnh sang một vị trí khác
     */
    static void moveCoordinates(int nextX, int nextY, ImageView imageView) {
        imageView.setLayoutY(nextY);
        imageView.setLayoutX(nextX);
    }

    /**
     * Hiện ảnh imageView tại một khung chữ nhật rectangle
     * (mô hình ảnh to thì chỉ hiện phần ảnh trong cái khung chữ nhật đó)
     * đồng thời add vào AnchorPane
     */
    static void insertImage(int toX, int toY, ImageView imageView, Image image, Rectangle2D rectangle2D, AnchorPane pane) {
        imageView.setImage(image);
        imageView.setViewport(rectangle2D);
        imageView.setLayoutX(toX);
        imageView.setLayoutY(toY);
        pane.getChildren().add(imageView);
    }

    /**
     * Hiện cả một hình ảnh chứ không cắt
     */
    static void insertImage(int toX, int toY, ImageView imageView, Image image, AnchorPane pane) {
        imageView.setImage(image);
        imageView.setLayoutX(toX);
        imageView.setLayoutY(toY);
        pane.getChildren().add(imageView);
    }
}
