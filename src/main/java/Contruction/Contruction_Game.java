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

    int SCREEN_WIDTH = 1056; // Chiều rộng màn hình
    int SCREEN_HEIGHT = 624; // Chiều cao màn hinhf

    String[] ARRAY_URL_LEVEL = {"src/main/resources/Resources/level/level1.txt",
                                "src/main/resources/Resources/level/level2.txt"};

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
