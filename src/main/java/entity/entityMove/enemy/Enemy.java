package entity.entityMove.enemy;

import Contruction.Contruction_Game;
import entity.entityMove.Bomberman;
import entity.entityMove.EntityMove;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public abstract class Enemy extends EntityMove {

    protected Random random ;
    protected int timeDie = 0;
    protected int DelayEnemy = 5;
    public boolean die = false;
    protected boolean checkRandom = false;
    protected int randomDirection;



    public Enemy(int toX, int toY, char[][] LEVEL_MAP , AnchorPane gamePane) {
        super(toX, toY, LEVEL_MAP, gamePane);
        ArrayX = new int[4];
        ArrayY = new int[4];
        speed = SPEED_ENEMY;
    }

    public abstract void move();

    @Override
    public void heart() {
        heartX = toX + 22;
        heartY = toY + 22;
    }

    @Override
    public boolean checkMap (int nextX, int nextY) {
        int aToX = (int)(nextX) / UNIT;
        int aToY = (int)(nextY) / UNIT;

        int bToX = (int)(nextX + 44) / UNIT;
        int bToY = (int)(nextY) / UNIT;

        int cToX = (int)(nextX) / UNIT;
        int cToY = (int)(nextY + 44) / UNIT;

        int dToX = (int)(nextX + 44 ) / UNIT;
        int dToY = (int)(nextY + 44 ) / UNIT;


        return  ! ((LEVEL_MAP[aToY][aToX] == 'n' || LEVEL_MAP[bToY][bToX] == 'n' || LEVEL_MAP[cToY][cToX] == 'n' || LEVEL_MAP[dToY][dToX] == 'n') ||
                Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[aToY][aToX]) || Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[bToY][bToX]) ||
                Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[cToY][cToX]) || Contruction_Game.CheckForbiddenToMoveIn(LEVEL_MAP[dToY][dToX]));
    }

    @Override

    public void forCoordinates() {
        ArrayX[0] = toX;
        ArrayX[1] = toX + 44;
        ArrayX[2] = toX;
        ArrayX[3] = toX + 44;

        ArrayY[0] = toY;
        ArrayY[1] = toY;
        ArrayY[2] = toY + 44;
        ArrayY[3] = toY + 44;
    }

    public void EnemyEatBomber(Bomberman bomberman) {
        if (bomberman.gameOver == false) {
            for (int m = 0; m < 4; m++) {
                if (bomberman.ArrayX[m] <= ArrayX[1] && bomberman.ArrayX[m] >= ArrayX[0] &&
                        bomberman.ArrayY[m] >= ArrayY[0] && bomberman.ArrayY[m] <= ArrayY[2]) {
                    bomberman.gameOver = true;
                    break;
                }
            }
        }
    }

    protected int Random() {
        return random.nextInt(4) + 1;
    }

}
