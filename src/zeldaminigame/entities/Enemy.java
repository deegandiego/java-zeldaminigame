package zeldaminigame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import zeldaminigame.abilities.Bullet;
import zeldaminigame.resources.Spritesheet;
import zeldaminigame.world.World;

public class Enemy extends Rectangle {
    public int spd = 2;
    public boolean right = true, up = false, down = false, left = false;

    public int curAnimation = 0;

    public int curFrames = 0, targetFrames = 15;

    static public List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean isShooting = false;

    public int dir = 1;

    public Enemy(int x, int y) {
        super(x, y, 32, 32);
    }

    public void tick() {
        boolean isMoving = false;
        if (right && World.isFree(x + spd, y)) {
            isMoving = true;
            x += spd;
            dir = 1;
        } else if (left && World.isFree(x - spd, y)) {
            isMoving = true;
            x -= spd;
            dir = -1;
        }

        if (up && World.isFree(x, y - spd)) {
            isMoving = true;
            y -= spd;
        } else if (down && World.isFree(x, y + spd)) {
            isMoving = true;
            y += spd;
        }

        if (isMoving) {
            curFrames++;
            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;
                if (curAnimation == Spritesheet.enemyFront.length) {
                    curAnimation = 0;
                }
            }
        }

        if (isShooting) {
            isShooting = false;
            bullets.add(new Bullet(x, y, dir));
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).tick();
        }
    }

    public void render(Graphics g) {
        g.drawImage(Spritesheet.enemyFront[curAnimation], x, y, 32, 32, null);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g, Color.RED);
        }
    }
}
