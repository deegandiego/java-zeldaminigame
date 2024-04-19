package zeldaminigame.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import zeldaminigame.Game;
import zeldaminigame.environment.Block;

public class World {

    static public List<Block> blocks = new ArrayList<Block>();

    public World() {
        for (int xx = 0; xx < Game.WIDTH/32; xx++) {
            blocks.add(new Block(xx * 32, 0));
        }

        for (int xx = 0; xx < Game.WIDTH/32; xx++) {
            blocks.add(new Block(xx * 32, Game.HEIGHT - 32));
        }

        for (int yy = 0; yy < Game.HEIGHT/32; yy++) {
            blocks.add(new Block(0, yy * 32));
        }

        for (int yy = 0; yy < Game.HEIGHT/32; yy++) {
            blocks.add(new Block(Game.WIDTH - 32, yy * 32));
        }

        blocks.add(new Block(32*4, 32*7));
    }

    public static boolean isFree(int x, int y) {
        for (Block b : blocks) {
            if (b.intersects(new Rectangle(x, y, 32, 32))) {
                return false;
            }
        }

        return true;
    }

    public void render(Graphics g) {
        for (Block b : blocks) {
            b.render(g);
        }
    }
}
