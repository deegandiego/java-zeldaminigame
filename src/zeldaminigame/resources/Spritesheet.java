package zeldaminigame.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

    public static BufferedImage spritesheet;

    public static BufferedImage player_front;

    public Spritesheet() {
        try {
            spritesheet = ImageIO.read(getClass().getResource("/spritesheets/character.png"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        player_front = Spritesheet.getSprite(1, 11, 16, 16);
    }

    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }

}