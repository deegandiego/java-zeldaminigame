package zeldaminigame.environment;

import java.awt.Graphics;
import java.awt.Rectangle;

import zeldaminigame.resources.Spritesheet;

public class Block extends Rectangle {

    public Block(int x, int y) {
        super(x, y, 32, 32);
    }

    public void render(Graphics g) {
        g.drawImage(Spritesheet.tileBlock, x, y, 32, 32, null);
    }
}
