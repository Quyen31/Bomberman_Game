package Contruction;

import model.Doc_File;

public interface Contruction_Game {
    String RIGHT = "right";
    String LEFT = "left";
    String UP = "up";
    String DOWN = "down";

    int SPEED_MEDIUM = 2; // Vận tốc bình thường

    int SPEED_STOP = 0; // Không Vận Tốc

    int UNIT = 48; // Độ dài 1 khối

    double SCREEN_WIDTH = 1056; // Chiều rộng màn hình
    double SCREEN_HEIGHT = 624; // Chiều cao màn hinhf

    Doc_File a = new Doc_File("src/main/resources/Resources/level/level1.txt");

    int GAME_WIDTH = a.Arr[2];

    int GAME_HEIGHT = a.Arr[1];

    int LEVEL = a.Arr[0];
    char [][] LEVEL_MAP = new char[GAME_HEIGHT][GAME_WIDTH];


    char[] ForbiddenToMoveIn = {'#', '*', 'S', 'B', 'F', 'X'}; // Các ký tự không được đi vào
    default boolean CheckForbiddenToMoveIn(char A) {
        for (int i = 0; i < ForbiddenToMoveIn.length; i++) {
            if (A == ForbiddenToMoveIn[i]) {
                return true;
            }
        }
        return false;
    }

}
