package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;
    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp=gp;
        name="Fireball";
        speed = 8;
        maxLife=80;
        life = maxLife;
        attack=2;
        useCost=1;
        alive=false;
        getImage();
    }
    public void getImage()
    {
        int width=48, height=48;
        up1=setup("/projectile/foc1sus", width, height);
        up2=setup("/projectile/foc2sus", width, height);
        up3=setup("/projectile/foc3sus", width, height);
        up4=setup("/projectile/foc4sus", width, height);
        up5=setup("/projectile/foc5sus", width, height);

        down1=setup("/projectile/foc1jos", width, height);
        down2=setup("/projectile/foc2jos", width, height);
        down3=setup("/projectile/foc3jos", width, height);
        down4=setup("/projectile/foc4jos", width, height);
        down5=setup("/projectile/foc5jos", width, height);

        left1=setup("/projectile/foc1st", width, height);
        left2=setup("/projectile/foc2st", width, height);
        left3=setup("/projectile/foc3st", width, height);
        left4=setup("/projectile/foc4st", width, height);
        left5=setup("/projectile/foc5st", width, height);

        right1=setup("/projectile/foc1", width, height);
        right2=setup("/projectile/foc2", width, height);
        right3=setup("/projectile/foc3", width, height);
        right4=setup("/projectile/foc4", width, height);
        right5=setup("/projectile/foc5", width, height);




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
