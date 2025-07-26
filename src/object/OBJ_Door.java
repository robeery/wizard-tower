package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

    //de modificat randarea la usa

    public OBJ_Door(GamePanel gp,int nr_usa)
    {
        super(gp);
        name="Door";
        down1=setup("/objects/usa_inchisa");
        down1=uTool.scaleImage(down1 , gp.tileSize, gp.tileSize*2);
        collision=true;
        this.nr_usa=nr_usa;
        /*
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/objects/usa_inchisa.png"));
            uTool.scaleImage(image , gp.tileSize, gp.tileSize*2);

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        collision=true;

         */
    }
    public int getNr_usa()
    {
        return this.nr_usa;
    }

}
