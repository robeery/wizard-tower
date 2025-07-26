package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_PotionSpeed extends Entity {
    GamePanel gp;
    public OBJ_PotionSpeed(GamePanel gp) {
        super(gp);
        int width=64+30-5,height=64*2-5;
        name = "PotionSpeed";
        image1 = setup("/objects/potiuneSpeed/sp-1-1",width,height);
        image2 = setup("/objects/potiuneSpeed/sp-2-1",width,height);
        image3 = setup("/objects/potiuneSpeed/sp-3-1",width,height);
        image4 = setup("/objects/potiuneSpeed/sp-4-1",width,height);
        image5 = setup("/objects/potiuneSpeed/sp-5-1",width,height);
        //    uTool.scaleImage(image , 32, 48);
        //  uTool.scaleImage(image2 , gp.tileSize, gp.tileSize*2);


    }
}