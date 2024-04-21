package zeldaminigame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zeldaminigame.Game;
import zeldaminigame.abilities.Bullet;
import zeldaminigame.resources.Spritesheet;
import zeldaminigame.world.World;

public class Enemy extends Rectangle {
    public int spd = 2;
    public boolean right = true, up = false, down = false, left = false;

    public int curAnimation = 0;

    public int curFrames = 0, targetFrames = 15;

    static public List<Bullet> bullets = new ArrayList<Bullet>();

    public boolean isMoving = false;

    public boolean isShooting = false;

    public int dir = 1;

    public Enemy(int x, int y) {
        super(x, y, 32, 32);
    }

    public void chasePlayer() {
        Player p = Game.player;

        if (x < p.x && World.isFree(x + spd, y)) {
            if (new Random().nextInt(100) < 50) {
                isMoving = true;
                x += spd;
            }
        } else if (x > p.x && World.isFree(x - spd, y)) {
            if (new Random().nextInt(100) < 50) {
                isMoving = true;
                x -= spd;
            }
        }

        if (y < p.y && World.isFree(x, y + spd)) {
            if (new Random().nextInt(100) < 50) {
                isMoving = true;
                y += spd;
            }
        } else if (y > p.y && World.isFree(x, y - spd)) {
            if (new Random().nextInt(100) < 50) {
                isMoving = true;
                y -= spd;
            }
        }
    }

    public void tick() {
        chasePlayer();

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
