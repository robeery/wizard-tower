package entity;

import main.GamePanel;
import object.OBJ_Fireball;
import object.OBJ_Ice;

import java.util.Random;

public class monster_BOSS extends Entity{
    public monster_BOSS(GamePanel gp) {
        super(gp);
        name = "BOSS";
        speed=2;
        maxLife=15; //=60
        life=maxLife;
        type=2;
        attack=4;
        projectile=new OBJ_Fireball(gp);
        projectile2=new OBJ_Ice(gp);

        solidArea.x=90;
        solidArea.y=50;
        solidArea.width=240;
        solidArea.height=180;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        getImage();
    }
    public void getImage()
    {
        int width=400;
        int height=229;

        up1 = setup("/sprites_boss/tile000", width,height);

        up2 = setup("/sprites_boss/tile000", width,height);

        up3= setup("/sprites_boss/tile000", width,height);
        up4= setup("/sprites_boss/tile000", width,height);
        up5= setup("/sprites_boss/tile000", width,height);


        down1= setup("/sprites_boss/tile001", width,height);
        down2= setup("/sprites_boss/tile001", width,height);
        down3= setup("/sprites_boss/tile002", width,height);
        down4= setup("/sprites_boss/tile003", width,height);
        down5= setup("/sprites_boss/tile004", width,height);

        left1= setup("/sprites_boss/tile004", width,height);
        left2= setup("/sprites_boss/tile005", width,height);
        left3= setup("/sprites_boss/tile006", width,height);
        left4= setup("/sprites_boss/tile007", width,height);
        left5= setup("/sprites_boss/tile001", width,height);

        right1= setup("/sprites_boss/tile002", width,height);
        right2= setup("/sprites_boss/tile003", width,height);
        right3= setup("/sprites_boss/tile004", width,height);
        right4= setup("/sprites_boss/tile005", width,height);
        right5= setup("/sprites_boss/tile006", width,height);

        right_s= setup("/sprites_boss/tile000", width,height);
        up_s=setup("/sprites_boss/tile000", width,height);
        left_s= setup("/sprites_boss/tile000", width,height);
        down_s= setup("/sprites_boss/tile000", width,height);

    }
    public void setAction()
    {
        actionLockCounter++;
        if(actionLockCounter==100) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //alege numar intre 1 si 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter=0;

        }
        int i=new Random().nextInt(100)+1;
        if(i>98 && projectile.alive==false)
        {
            projectile2.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile2);
            gp.playSE(8);
        }
        else if(i>95 && projectile.alive==false)
        {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            gp.playSE(7);
        }


    }
    public void damageReaction()
    {
        //fuge dupa lovire
        actionLockCounter=0;
        switch(gp.player.direction)
        {
            case "up":
                direction="down";
                break;
            case "down":
                direction="up";
                break;
            case "left":
                direction="right";
                break;
            case "right":
                direction="left";
                break;
        }
    }
}