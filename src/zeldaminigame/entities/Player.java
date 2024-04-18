package zeldaminigame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import zeldaminigame.abilities.Bullet;
import zeldaminigame.resources.Spritesheet;
import zeldaminigame.world.World;

public class Player extends Rectangle {
    public int spd = 4; // player's speed
    public boolean right, up, down, left;

    public int curAnimation = 0;

    public int curFrames = 0, targetFrames = 15; /* higher targetFrames = slower animation */

    static public List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean isShooting = false;

    public int dir = 1;

    public Player(int x, int y) {
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
                if (curAnimation == Spritesheet.playerFront.length) {
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
        g.drawImage(Spritesheet.playerFront[curAnimation], x, y, 32, 32, null);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g);
        }
    }
}
