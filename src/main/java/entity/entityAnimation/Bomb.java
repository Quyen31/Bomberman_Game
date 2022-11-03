package entity.entityAnimation;

import Contruction.Image_Game;
import entity.Entity;
import entity.Item;
import entity.entityMove.Bomberman;
import entity.entityMove.enemy.Enemy;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Music;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends EntityAnimation {

    private Entity[][] MapEntity;
    private Item[][] MapItem;
    private boolean StopBom = false;

    public boolean isStopBom() {

        return StopBom;
    }

    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    public static int checkFire = 1; // Số lửa tạo ra từ bom mỗi phía
    private List<EntityAnimation> ExplosiveBomRight;
    private List<EntityAnimation> ExplosiveBomLeft;
    private List<EntityAnimation> ExplosiveBomUp;
    private List<EntityAnimation> ExplosiveBomDown;
    private List<EntityAnimation> RemoveBrick;
    private Fire fireMindle;
    private int widthExplosiveMin;
    private int widthExplosiveMax;
    private int hightExplosiveMin;
    private int hightExplosiveMax;

    public int getWidthExplosiveMin() {
        return widthExplosiveMin;
    }

    public int getWidthExplosiveMax() {
        return widthExplosiveMax;
    }

    public int getHightExplosiveMin() {
        return hightExplosiveMin;
    }

    public int getHightExplosiveMax() {
        return hightExplosiveMax;
    }

    public Bomb(int toX, int toY, AnchorPane gamePane, Entity[][] MapEntity, Item[][] MapItem,
                char[][] LEVEL_MAP, int GAME_HEIGHT, int GAME_WIDTH) {
        super(toX, toY, LEVEL_MAP, gamePane);
        this.GAME_HEIGHT = GAME_HEIGHT;
        this.GAME_WIDTH = GAME_WIDTH;
        this.MapEntity = MapEntity;
        this.MapItem = MapItem;
        ExplosiveBomRight = new ArrayList<EntityAnimation>();
        ExplosiveBomLeft = new ArrayList<EntityAnimation>();
        ExplosiveBomUp = new ArrayList<EntityAnimation>();
        ExplosiveBomDown = new ArrayList<EntityAnimation>();
        RemoveBrick = new ArrayList<EntityAnimation>();
    }

    /**
     * Tạo bom nhấp nháy
     * update giới hạn của fire (limit)
     * add các khối lủa vào List cua từng hướng (addFireAnchoPane)
     */
    @Override
    public void update() {
        imageView = new ImageView();
        gamePane.getChildren().add(imageView);
        fireMindle = new Fire(toX, toY, LEVEL_MAP, gamePane, FIRE_MINDLE);
        addFireAnchoPane(ExplosiveBomRight, RemoveBrick, RIGHT);
        addFireAnchoPane(ExplosiveBomLeft, RemoveBrick, LEFT);
        addFireAnchoPane(ExplosiveBomUp, RemoveBrick, UP);
        addFireAnchoPane(ExplosiveBomDown, RemoveBrick, DOWN);
        limit();
    }

    /**
     * Xóa hình ảnh bom nhấp nháy
     */
    @Override
    public void remove() {
        gamePane.getChildren().remove(imageView);
        imageView = null;
        count = 0;
    }

    /**
     * Ứng với mỗi hướng sẽ tính ra 1 giới hạn.
     * Giới hạn sẽ được kéo dài đến khi gặp tường  hoặc gạch sẽ dừng .
     */
    private void limit() {
        widthExplosiveMin = toX * UNIT - ExplosiveBomLeft.size() * UNIT;
        widthExplosiveMax = toX * UNIT + UNIT + ExplosiveBomRight.size() * UNIT;
        hightExplosiveMin = toY * UNIT - ExplosiveBomUp.size() * UNIT;
        hightExplosiveMax = toY * UNIT + UNIT + ExplosiveBomDown.size() * UNIT;
    }

    /**
     * Add các đối tượng fire và brick Animation vào các List
     */
    private void addFireAnchoPane(List<EntityAnimation> Fire, List<EntityAnimation> Brick, String diretion) {
        int nextToX = toX;
        int nextToY = toY;
        String fireTop = "";
        String fire = "";
        for (int j = 0; j < checkFire; j++) {
            if (diretion == RIGHT) {
                nextToX = toX + j + 1;
                fireTop = FIRE_RIGHT;
                fire = FIRE_RL;
            }
            if (diretion == LEFT) {
                nextToX = toX - j - 1;
                fireTop = FIRE_LEFT;
                fire = FIRE_RL;
            }
            if (diretion == UP) {
                nextToY = toY - j - 1;
                fireTop = FIRE_UP;
                fire = FIRE_UD;
            }
            if (diretion == DOWN) {
                nextToY = toY + j + 1;
                fireTop = FIRE_DOWN;
                fire = FIRE_UD;
            }
            if (nextToX < 0 || nextToX > GAME_WIDTH || nextToY < 0 || nextToY > GAME_HEIGHT)
                break;
            if (LEVEL_MAP[nextToY][nextToX] == '#' || LEVEL_MAP[nextToY][nextToX] == '*') {
                if (LEVEL_MAP[nextToY][nextToX] == '*') {
                    Brick.add(new Brick(nextToX * UNIT, nextToY * UNIT, LEVEL_MAP, gamePane));
                }
                break;
            }
            if (j == checkFire - 1) {
                Fire.add(new Fire(nextToX, nextToY, LEVEL_MAP, gamePane, fireTop));
                break;
            }
            Fire.add(new Fire(nextToX, nextToY, LEVEL_MAP, gamePane, fire));
        }
    }

    /**
     * Xóa các phần tử trong list Fire
     */
    private void removeFireAnchoPane(List<EntityAnimation> ArrayFire) {
        for (int i = 0; i < ArrayFire.size(); i++) {
            ArrayFire.remove(i);
            i--;
        }
    }

    /**
     * Chạy hình ảnh của các khối Fire đồng thời xóa
     */
    private void AnimationExplosiveBombs(List<EntityAnimation> ArrayFire, int timeStop) {
        for (int j = 0; j < ArrayFire.size(); j++) {
            ArrayFire.get(j).update();
            ArrayFire.get(j).Animation(timeStop);
        }
        removeFireAnchoPane(ArrayFire);
    }

    /**
     * Xóa hình ảnh tường ban đầu
     * Đồng thới gán LEVEL_MAP lại là đường đi
     * Nếu sau Birck là item thì cũng gán lại giá trị item thật ban đầu LEVEL_MAP
     * vì khi trước do Brick che lên Item nên ta đã gán LEVEL_MAP tại vị trí ITEM là '*' (vào lớp Item để đọc)
     *
     * @param ArrayFire
     */
    private void removeBrick(List<EntityAnimation> ArrayFire) {
        for (int j = 0; j < ArrayFire.size(); j++) {
            MapEntity[ArrayFire.get(j).getToY() / UNIT][ArrayFire.get(j).getToX() / UNIT].remove();
            if (MapItem[ArrayFire.get(j).getToY() / UNIT][ArrayFire.get(j).getToX()/ UNIT] != null) {
                LEVEL_MAP[ArrayFire.get(j).getToY() / UNIT][ArrayFire.get(j).getToX() / UNIT] = MapItem[ArrayFire.get(j).getToY() / UNIT][ArrayFire.get(j).getToX() / UNIT].getItem();
            } else
                LEVEL_MAP[ArrayFire.get(j).getToY() / UNIT][ArrayFire.get(j).getToX() / UNIT] = ' ';
        }
    }

    /**
     * Animation Nổ + ÂM thanh
     */
    private Music musicBomb;

    @Override
    public void Animation(int timeStop) {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (count >= 24) {
                    count = 0;
                }
                Image_Game.Animation(8, count, toX * UNIT, toY * UNIT, bombRectangle, imageView, image_sprites);
                count++;
                if (timeLoop == timeStop) {
                    StopBom = true;
                    loop.stop();
                    timeLoop = 0;
                    remove();
                    musicBomb.musicStart(25);
                    removeBrick(RemoveBrick);
                    fireMindle.update();
                    fireMindle.Animation(15);
                    AnimationExplosiveBombs(ExplosiveBomRight, 15);
                    AnimationExplosiveBombs(ExplosiveBomUp, 15);
                    AnimationExplosiveBombs(ExplosiveBomLeft, 15);
                    AnimationExplosiveBombs(ExplosiveBomDown, 15);
                    AnimationExplosiveBombs(RemoveBrick, 15);
                }
                timeLoop++;

            }
        };
        musicBomb = new Music(music_bomb_url);
        musicBomb.Volume(3);
        LEVEL_MAP[toY][toX] = 'n';
        loop.start();
    }

    public void dieEnemyOrBomber(List<Enemy> ArrayEnemy, Bomberman bomberman) {
        // Enemy die bị bom nổ
        for (int j = 0; j < ArrayEnemy.size(); j++) {

            for (int m = 0; m < 4; m++) {
                if (ArrayEnemy.get(j).ArrayX[m] < widthExplosiveMax && ArrayEnemy.get(j).ArrayX[m] > widthExplosiveMin &&
                        ArrayEnemy.get(j).ArrayY[m] > toY * UNIT && ArrayEnemy.get(j).ArrayY[m] < toY * UNIT + UNIT){
                    ArrayEnemy.get(j).die = true;
                    break;
                }
                if (ArrayEnemy.get(j).ArrayY[m] < hightExplosiveMax && ArrayEnemy.get(j).ArrayY[m] > hightExplosiveMin &&
                        ArrayEnemy.get(j).ArrayX[m] > toX * UNIT && ArrayEnemy.get(j).ArrayX[m] < toX * UNIT + UNIT) {
                    ArrayEnemy.get(j).die = true;
                    break;
                }
            }
        }
        for (int j = 0; j < ArrayEnemy.size(); j++) {

            if (ArrayEnemy.get(j).die == true) {
                ArrayEnemy.get(j).loopDie();
                ArrayEnemy.remove(j);
                j--;
            }
        }

         //Bom die Bomber
        if (bomberman.gameOver == false) {
            for (int m = 0; m < 4; m++) {
                if (bomberman.ArrayX[m] < widthExplosiveMax && bomberman.ArrayX[m] > widthExplosiveMin &&
                        bomberman.ArrayY[m] > toY * UNIT && bomberman.ArrayY[m] < toY * UNIT + UNIT) {
                    bomberman.gameOver = true;
                    break;
                }
                if (bomberman.ArrayY[m] < hightExplosiveMax && bomberman.ArrayY[m] > hightExplosiveMin &&
                        bomberman.ArrayX[m] > toX * UNIT && bomberman.ArrayX[m] < toX * UNIT + UNIT) {
                    bomberman.gameOver = true;
                    break;
                }
            }
        }

    }

    /**
     * Tạo bom nổ liên hoàn
     */
    public void bombContinuousExplosion (List<Bomb> ArrayBom) {
        for (int i = 0; i < ArrayBom.size(); i ++) {
            if ( ArrayBom.get(i) == this)
                continue;
            if (ArrayBom.get(i).getToX() < widthExplosiveMax / UNIT && ArrayBom.get(i).getToX() >= widthExplosiveMin / UNIT &&
                    ArrayBom.get(i).getToY() == toY) {
                ArrayBom.get(i).setTimeLoop(timeBomb);
            }
            if (ArrayBom.get(i).getToY() < hightExplosiveMax / UNIT && ArrayBom.get(i).getToY() >= hightExplosiveMin / UNIT &&
                    ArrayBom.get(i).getToX() == toX) {
                ArrayBom.get(i).setTimeLoop(timeBomb);
            }
        }
    }
}
