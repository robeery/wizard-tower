package entity;

import main.GamePanel;

import java.util.Random;

public class monster_bat extends Entity{
    public monster_bat(GamePanel gp) {
        super(gp);
        name = "bat";
        speed=3;
        maxLife=4;
        life=maxLife;
        type=2;
        attack=1;

        solidArea.x=4;
        solidArea.y=16;
        solidArea.width=27+12;
        solidArea.height=27+12;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        getImage();
    }
    public void getImage()
    {
        up1 = setup("/monster/liliac_sus1");
        up2 = setup("/monster/liliac_sus2");
        up3= setup("/monster/liliac_sus3");


        down1= setup("/monster/liliac_jos1");
        down2= setup("/monster/liliac_jos2");
        down3= setup("/monster/liliac_jos3");

        left1= setup("/monster/liliac_stg1");
        left2= setup("/monster/liliac_stg2");
        left3= setup("/monster/liliac_stg3");

        right1= setup("/monster/liliac_dr1");
        right2= setup("/monster/liliac_dr2");
        right3= setup("/monster/liliac_dr3");

        right_s= setup("/monster/liliac_drdefault");
        up_s=setup("/monster/liliac_susdefault");
        left_s= setup("/monster/liliac_stgdefault");
        down_s= setup("/monster/liliac_josdefault");

    }
    public void setAction()
    {
        actionLockCounter++;
        if(actionLockCounter==50) {
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
    }
    public void damageReaction()
    {
        //fuge dupa lovire
        actionLockCounter=0;
        direction=gp.player.direction;
    }
}
