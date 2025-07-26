package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Ice extends Projectile {
    GamePanel gp;
    public OBJ_Ice(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Ice";
        speed = 8;
        maxLife=100;
        life = maxLife;
        attack=4;
        useCost=3;
        alive=false;
        getImage();
    }
    public void getImage()
    {
        int width=64, height=64;
        up1=setup("/projectile/ice_sus1", width, height);
        up2=setup("/projectile/ice_sus2", width, height);
        up3=setup("/projectile/ice_sus3", width, height);
        up4=setup("/projectile/ice_sus4", width, height);
        up5=setup("/projectile/ice_sus4", width, height);


        down1=setup("/projectile/ice_jos1", width, height);
        down2=setup("/projectile/ice_jos2", width, height);
        down3=setup("/projectile/ice_jos3", width, height);
        down4=setup("/projectile/ice_jos4", width, height);
        down5=setup("/projectile/ice_jos4", width, height);


        left1=setup("/projectile/ice_st1", width, height);
        left2=setup("/projectile/ice_st2", width, height);
        left3=setup("/projectile/ice_st3", width, height);
        left4=setup("/projectile/ice_st4", width, height);
        left5=setup("/projectile/ice_st4", width, height);


        right1=setup("/projectile/ice_dr1", width, height);
        right2=setup("/projectile/ice_dr2", width, height);
        right3=setup("/projectile/ice_dr3", width, height);
        right4=setup("/projectile/ice_dr4", width, height);
        right5=setup("/projectile/ice_dr4", width, height);





    }
    public boolean haveResource(Entity user)
    {
        boolean haveResource=false;
        if(user.mana>=useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void substractResource(Entity user)
    {
        user.mana -=useCost;

    }
}
