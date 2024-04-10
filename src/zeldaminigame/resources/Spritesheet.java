package zeldaminigame.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

    public static BufferedImage spritesheetCharacter;

    public static BufferedImage spritesheetMap;

    public static BufferedImage playerFront;

    public static BufferedImage tileBlock;

    public Spritesheet() {
        try {
            spritesheetCharacter = ImageIO.read(getClass().getResource("/spritesheets/character.png"));
            spritesheetMap = ImageIO.read(getClass().getResource("/spritesheets/map.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        playerFront = Spritesheet.getCharacterSprite(1, 11, 16, 16);

        tileBlock = Spritesheet.getMapSprite(1, 1, 32, 32);
    }

    public static BufferedImage getCharacterSprite(int x, int y, int width, int height) {
        return spritesheetCharacter.getSubimage(x, y, width, height);
    }

    public static BufferedImage getMapSprite(int x, int y, int width, int height) {
        return spritesheetMap.getSubimage(x, y, width, height);
    }

}