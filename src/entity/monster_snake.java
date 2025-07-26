package entity;

import main.GamePanel;

import java.util.Random;

public class monster_snake extends Entity{
    public monster_snake(GamePanel gp) {
        super(gp);
        name = "goblin_lvl2";
        speed=3;
        maxLife=10;
        life=maxLife;
        type=2;
        attack=3;

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
        int height=64+20+10;
        down1 = setup("/monster/snake/tile001", width,height);
        down2 = setup("/monster/snake/tile002", width,height);
        down3= setup("/monster/snake/tile003", width,height);


        up1= setup("/monster/snake/tile013", width,height);
        up2= setup("/monster/snake/tile014", width,height);
        up3= setup("/monster/snake/tile015", width,height);

        left1= setup("/monster/snake/tile005", width,height);
        left2= setup("/monster/snake/tile006", width,height);
        left3= setup("/monster/snake/tile007", width,height);

        right1= setup("/monster/snake/tile009", width,height);
        right2= setup("/monster/snake/tile010", width,height);
        right3= setup("/monster/snake/tile011", width,height);

        right_s= setup("/monster/snake/tile008", width,height);
        left_s=setup("/monster/snake/tile004", width,height);
        up_s= setup("/monster/snake/tile012", width,height);
        down_s= setup("/monster/snake/tile000", width,height);
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
