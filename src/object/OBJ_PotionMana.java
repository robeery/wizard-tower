package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_PotionMana extends Entity {
    GamePanel gp;
    public OBJ_PotionMana(GamePanel gp) {
        super(gp);
        int width=64+30,height=64*2;
        name = "PotionMana";
        image1 = setup("/objects/potiuneMANA/mp-1-1",width,height);
        image2 = setup("/objects/potiuneMANA/mp-2-1",width,height);
        image3 = setup("/objects/potiuneMANA/mp-3-1",width,height);
        image4 = setup("/objects/potiuneMANA/mp-4-1",width,height);
        image5 = setup("/objects/potiuneMANA/mp-5-1",width,height);


    }
}
