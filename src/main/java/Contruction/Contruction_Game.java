package Contruction;

public interface Contruction_Game {
    String RIGHT = "right";
    String LEFT = "left";
    String UP = "up";
    String DOWN = "down";

    String FIRE_RIGHT = "Fire Right";
    String FIRE_LEFT = "Fire Left";
    String FIRE_UP = "Fire Up";
    String FIRE_DOWN = "Fire Down";
    String FIRE_RL = "Fire RL";
    String FIRE_UD = "Fire UD";
    String FIRE_MINDLE = "Fire Mindle";

    int timeBomb = 150;

    int SPEED_MEDIUM = 2; // Vận tốc bình thường

    int SPEED_ENEMY = 1; //Vận tốc enemy
    int SPEED_STOP = 0; // Không Vận Tốc

    int UNIT = 48; // Độ dài 1 khối

    int SCREEN_WIDTH = 1056; // Chiều rộng màn hình
    int SCREEN_HEIGHT = 624; // Chiều cao màn hinhf

    String[] ARRAY_URL_LEVEL = {"src/main/resources/Resources/level/level1.txt",
            "src/main/resources/Resources/level/level2.txt"};

    char[] ForbiddenToMoveIn = {'#', '*', 'B'}; // Các ký tự không được đi vào

    static boolean CheckForbiddenToMoveIn(char A) {
        for (int i = 0; i < ForbiddenToMoveIn.length; i++) {
            if (A == ForbiddenToMoveIn[i]) {
                return true;
            }
        }
        return false;
    }

}
