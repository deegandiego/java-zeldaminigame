package zeldaminigame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
    public int spd = 4; // player's speed
    public boolean right, up, down, left;

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void move() {
        if (right) {
            x += spd;
        } else if (left) {
            x -= spd;
        }

        if (up) {
            y -= spd;
        } else if (down) {
            y += spd;
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(21, 82, 24));
        g.fillRect(x, y, width, height);
    }
}
