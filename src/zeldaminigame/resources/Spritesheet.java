package zeldaminigame.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

    public static BufferedImage spritesheetCharacter;

    public static BufferedImage spritesheetMap;

    public static BufferedImage[] playerFront;

    public static BufferedImage tileBlock;

    public Spritesheet() {
        try {
            spritesheetCharacter = ImageIO.read(getClass().getResource("/spritesheets/character.png"));
            spritesheetMap = ImageIO.read(getClass().getResource("/spritesheets/map.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        playerFront = new BufferedImage[2];
        
        playerFront[0] = Spritesheet.getCharacterSprite(1, 11, 16, 16);
        playerFront[1] = Spritesheet.getCharacterSprite(18, 11, 16, 16);

        tileBlock = Spritesheet.getMapSprite(1, 1, 32, 32);
    }

    public static BufferedImage getCharacterSprite(int x, int y, int w, int h) {
        return spritesheetCharacter.getSubimage(x, y, w, h);
    }

    public static BufferedImage getMapSprite(int x, int y, int w, int h) {
        return spritesheetMap.getSubimage(x, y, w, h);
    }

}