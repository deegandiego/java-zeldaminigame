package zeldaminigame.abilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import zeldaminigame.entities.Player;

public class Bullet extends Rectangle {
    public int dir = 1;

    public int speed = 8;

    public int frames = 0;

    public Bullet(int x, int y, int dir) {
        super(x + 16, y + 16, 10, 10);
        this.dir = dir;
    }

    public void tick() {
        x += speed * dir;

        frames++;
        if (frames == 60) {
            Player.bullets.remove(this);
            return;
        }
    }

    public void render(Graphics g, Color color) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }
}
