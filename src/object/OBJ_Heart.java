package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {

    GamePanel gp;
    public OBJ_Heart(GamePanel gp)
    {
        super(gp);
        name="Heart";
       image = setup("/objects/heart_full");
        image2=setup("/objects/heart_half");
        image3=setup("/objects/heart_empty");

        image = uTool.scaleImage(image , 64, 64);
        image2 = uTool.scaleImage(image2 , 64, 64);
        image3 = uTool.scaleImage(image3 , 64, 64);
        /*
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2= ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3= ImageIO.read(getClass().getResourceAsStream("/objects/heart_empty.png"));



           image = uTool.scaleImage(image , 64, 64);
           image2 = uTool.scaleImage(image2 , 64, 64);
           image3 = uTool.scaleImage(image3 , 64, 64);


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

         */
    }
}
