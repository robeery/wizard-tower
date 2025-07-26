package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_PotionHealth extends Entity {
    GamePanel gp;
    public OBJ_PotionHealth(GamePanel gp) {
        super(gp);


        name = "PotionHealth";

        int width=64+30,height=64*2;
        image1 = setup("/objects/potiuneHP/hp-1-1",width,height);
        image2 = setup("/objects/potiuneHP/hp-2-1",width,height);
        image3 = setup("/objects/potiuneHP/hp-3-1",width,height);
        image4 = setup("/objects/potiuneHP/hp-4-1",width,height);
        image5 = setup("/objects/potiuneHP/hp-5-1",width,height);


        /*
         try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/potiune_viata1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/potiune_viata2.png"));

            uTool.scaleImage(image , 32, 48);
            uTool.scaleImage(image2 , gp.tileSize, gp.tileSize*2);

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

         */

    }
}
