package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {

    //de modificat randarea la usa
    GamePanel gp;
    public OBJ_Key(GamePanel gp)
    {
        super(gp);
        name="Key";
        down1=setup("/objects/031-Key03");
        uTool.scaleImage(image,32, 32);
        /*
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/031-Key03.png"));
            uTool.scaleImage(image,32, 32);

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        */

    }
}
