package entity;

import main.GamePanel;

import java.util.Random;

public class monster_goblin_lvl1 extends Entity{
    public monster_goblin_lvl1(GamePanel gp) {
        super(gp);
        name = "goblin_lvl1";
        speed=2;
        maxLife=8;
        life=maxLife;
        type=2;
        attack=2;

        solidArea.x=7;
        solidArea.y=3;
        solidArea.width=51+32;
        solidArea.height=73+30;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        getImage();
    }
    public void getImage()
    {
        int width=64+32;
        int height=80+20+10;
        up1 = setup("/monster/goblin1_sus1", width,height);

        up2 = setup("/monster/goblin1_sus2", width,height);

        up3= setup("/monster/goblin1_sus3", width,height);


        down1= setup("/monster/golbin1_jos1", width,height);
        down2= setup("/monster/golbin1_jos2", width,height);
        down3= setup("/monster/golbin1_jos3", width,height);

        left1= setup("/monster/goblin1_stg1", width,height);
        left2= setup("/monster/goblin1_stg2", width,height);
        left3= setup("/monster/goblin1_stg3", width,height);

        right1= setup("/monster/goblin1_dr1", width,height);
        right2= setup("/monster/goblin1_dr2", width,height);
        right3= setup("/monster/goblin1_dr3", width,height);

        right_s= setup("/monster/goblin1_drdefault", width,height);
        up_s=setup("/monster/goblin1_susdefault", width,height);
        left_s= setup("/monster/goblin1_stgdefault", width,height);
        down_s= setup("/monster/goblin1_josdefault", width,height);
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
