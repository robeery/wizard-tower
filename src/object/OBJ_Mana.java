package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {
    GamePanel gp;

    public OBJ_Mana(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Mana Crystal";
        int width=gp.tileSize, height=gp.tileSize;
        image=setup("/objects/mana_shard_16",width,height);
        image2=setup("/objects/mana_jumate_gol",width,height);
        image3=setup("/objects/mana_gol",width,height);

    }
}
