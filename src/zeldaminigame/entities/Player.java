package zeldaminigame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import zeldaminigame.resources.Spritesheet;
import zeldaminigame.world.World;

public class Player extends Rectangle {
    public int spd = 4; // player's speed
    public boolean right, up, down, left;

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void move() {
        if (right && World.isFree(x + spd, y)) {
            x += spd;
        } else if (left && World.isFree(x - spd, y)) {
            x -= spd;
        }

        if (up && World.isFree(x, y - spd)) {
            y -= spd;
        } else if (down && World.isFree(x, y + spd)) {
            y += spd;
        }
    }

    public void render(Graphics g) {
        g.drawImage(Spritesheet.playerFront, x, y, 32, 32, null);
    }
}
