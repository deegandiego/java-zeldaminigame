package zeldaminigame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import zeldaminigame.resources.Spritesheet;
import zeldaminigame.world.World;

public class Player extends Rectangle {
    public int spd = 4; // player's speed
    public boolean right, up, down, left;

    public int curAnimation = 0;

    public int curFrames = 0, targetFrames = 15; /* higher targetFrames = slower animation */

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void move() {
        boolean isMoving = false;
        if (right && World.isFree(x + spd, y)) {
            isMoving = true;
            x += spd;
        } else if (left && World.isFree(x - spd, y)) {
            isMoving = true;
            x -= spd;
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
    }

    public void render(Graphics g) {
        g.drawImage(Spritesheet.playerFront[curAnimation], x, y, 32, 32, null);
    }
}
