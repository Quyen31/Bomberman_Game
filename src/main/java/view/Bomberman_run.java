package view;

import Contruction.Contruction_Game;
import Contruction.Image_Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Bomberman_run implements Image_Game, Contruction_Game {
    public static ImageView imageBomberman;
    private static Rectangle2D rectangle2D;

    private static int speed = SPEED_MEDIUM; // Vận tốc
    public static int SPEED = 0;

    public static int SPEED_MAX = SPEED_MEDIUM; // Vận tốc max hiện t

    public static int NumberBom = 1;

    public int ExplosiveSize = 1;

    private static int toX ;
    private static int toY ;
    private static int i = 0;

    public static int getToX() {
        return toX;
    }

    public static int getToY() {
        return toY;
    }

    public static void setSpeed(int speed) {
        Bomberman_run.speed = speed;
    }

    public static int getSpeed() {
        return speed;
    }

    /**
     * Contruction
     */
    public Bomberman_run(int toX, int toY) {
        this.toX = toX ;
        this.toY = toY ;
        imageBomberman = new ImageView();
    }

    public void cutRectangleImage(int a, String direction) {
        if (a >= 0 && a <= 4) {
            if (direction == RIGHT)
                rectangle2D = new Rectangle2D(288 , 0, 48, 48);

            if (direction == LEFT)
                rectangle2D = new Rectangle2D(288 + 48 * 3 , 0, 48, 48);
            if (direction == UP)
                rectangle2D = new Rectangle2D(0 , 0, 48, 48);

            if (direction == DOWN)
                rectangle2D = new Rectangle2D(48 * 3 , 0, 48, 48);
            imageBomberman.setViewport(rectangle2D);
        }
        if (a >= 5 && a <= 10) {
            if (direction == RIGHT)
                rectangle2D = new Rectangle2D(288 + 48, 0, 48, 48);

            if (direction == LEFT)
                rectangle2D = new Rectangle2D(288 + 48 * 4 , 0, 48, 48);

            if (direction == UP)
                rectangle2D = new Rectangle2D(48 , 0, 48, 48);

            if (direction == DOWN)
                rectangle2D = new Rectangle2D(48 * 4 , 0, 48, 48);
            imageBomberman.setViewport(rectangle2D);
        }
        if (a >= 10 && a <= 15) {
            if (direction == RIGHT)
                rectangle2D = new Rectangle2D(288 + 48 * 2, 0, 48, 48);

            if (direction == LEFT)
                rectangle2D = new Rectangle2D(288 + 48 * 5 , 0, 48, 48);

            if (direction == UP)
                rectangle2D = new Rectangle2D(48 * 2 , 0, 48, 48);

            if (direction == DOWN)
                rectangle2D = new Rectangle2D(48 * 5 , 0, 48, 48);
            imageBomberman.setViewport(rectangle2D);
        }
    }
    public void setRun (AnchorPane GamePane) {
        imageBomberman.setImage(image_sprites);
        cutRectangleImage(0,DOWN);
        imageBomberman.setFitHeight(40);
        imageBomberman.setFitWidth(40);
        imageBomberman.setLayoutX(toX);
        imageBomberman.setLayoutY(toY);
        GamePane.getChildren().add(imageBomberman);
    }

    public void Run (String direction) {
        if (i > 15) {
            i = 0;
        }
        if (direction == RIGHT) {
            toX = toX + speed;
        }
        if (direction == LEFT) {
            toX = toX - speed;
        }
        if (direction == UP) {
            toY = toY - speed;
        }
        if (direction == DOWN) {
            toY = toY + speed;
        }
        cutRectangleImage(i, direction);
        i++;
        imageBomberman.setLayoutX(toX);
        imageBomberman.setLayoutY(toY);
    }


    // a___b
    // |   |
    // c---d
    public int check = 0;

    private static int[] ArrX = new int[4];
    private static int[] ArrY = new int[4];
    public boolean checkMap(int nextX, int nextY, char[][] Map, Bomb bomb) {
        ArrX[0] = toX;
        ArrX[1] = toX + 29;
        ArrX[2] = toX;
        ArrX[3] = toX + 29;

        ArrY[0] = toY;
        ArrY[1] = toY;
        ArrY[2] = toY + 35;
        ArrY[3] = toY + 35;
        int aToX = nextX / 48;
        int aToY = nextY / 48;

        int bToX = (nextX + 29 ) / 48;
        int bToY = nextY / 48;

        int cToX = nextX / 48;
        int cToY = (nextY + 35 ) / 48;

        int dToX = (nextX + 29 ) / 48;
        int dToY = (nextY + 35 ) / 48;
        check = 0;
        for ( int i = 0 ; i < 4; i++) {
            if (ArrX[i] > ((int)(bomb.toX + 24) / 48) * 48 && ArrX[i] < ((int)(bomb.toX + 24) / 48) * 48 + 48) {
                if (ArrY[i] > ((int)(bomb.toY + 24) / 48) * 48  && ArrY[i] < ((int)(bomb.toY + 24) / 48) * 48  + 48) {
                    check = 1;
                }
            }
        }
        if(bomb.checkNumberBom <= NumberBom && check == 0 ) {
            return !((Map[aToY][aToX] == 'n' || Map[bToY][bToX] == 'n' || Map[cToY][cToX] == 'n' || Map[dToY][dToX] == 'n') ||
                    CheckForbiddenToMoveIn(Map[aToY][aToX]) || CheckForbiddenToMoveIn(Map[bToY][bToX]) ||
                    CheckForbiddenToMoveIn(Map[cToY][cToX]) || CheckForbiddenToMoveIn(Map[dToY][dToX]));
        }
        return  ! (CheckForbiddenToMoveIn(Map[aToY][aToX]) || CheckForbiddenToMoveIn(Map[bToY][bToX]) ||
                CheckForbiddenToMoveIn(Map[cToY][cToX]) || CheckForbiddenToMoveIn(Map[dToY][dToX]));
    }

    public void eatItem(char Map[][], ImageView[][] imageView, AnchorPane pane) {
        int a = (toX + 24) / 48;
        int b = (toY + 24) / 48;
        if (Map[b][a] == 'b' || Map[b][a] == 'f' || Map[b][a] == 's') {
            pane.getChildren().remove(imageView[b][a]);
            if (Map[b][a] == 's') {
                SPEED ++;
            }
            if (Map[b][a] == 'b') {
                NumberBom ++;
            }
            if (Map[b][a] == 'f') {
                ExplosiveSize ++;
            }
            Map[b][a] = ' ';
        }
    }

}
