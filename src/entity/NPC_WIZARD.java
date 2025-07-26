package entity;

import main.GamePanel;



import java.awt.*;
import java.util.Random;


public class NPC_WIZARD extends Entity
{

    public NPC_WIZARD(GamePanel gp)
    {
        super(gp);

        direction = "down";
        speed = 1;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize*2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        name="NPC_WIZARD";

        getImage();
        setDialogue();
    }
        public void getImage()
        {

            up1=setup_npc("/sprites_fire_wizard/sus_1");
            up2= setup_npc("/sprites_fire_wizard/sus_2");
            up3= setup_npc("/sprites_fire_wizard/sus_3");

            down1= setup_npc("/sprites_fire_wizard/jos_1");
            down2= setup_npc("/sprites_fire_wizard/jos_2");
            down3= setup_npc("/sprites_fire_wizard/jos_3");

            left1= setup_npc("/sprites_fire_wizard/stg_1");
            left2= setup_npc("/sprites_fire_wizard/stg_2");
            left3= setup_npc("/sprites_fire_wizard/stg_3");

            right1= setup_npc("/sprites_fire_wizard/drt_1");
            right2= setup_npc("/sprites_fire_wizard/drt_2");
            right3= setup_npc("/sprites_fire_wizard/drt_3");

            right_s= setup_npc("/sprites_fire_wizard/drt_default");
            up_s=setup_npc("/sprites_fire_wizard/sus_default");
            left_s= setup_npc("/sprites_fire_wizard/stg_default");
            down_s= setup_npc("/sprites_fire_wizard/jos_default");


        }
        public void setDialogue()
        {
            dialogues[0]="Salutare, fetito.";
            dialogues[1]="Eu sunt vrajitorul focului.";
            dialogues[2]="Daca ai ajuns aici prin cartea bunicului tau, ai \najuns la momentul potrivit pentru a ne salva \npe noi si mostenirea bunicului tau.";

            dialogues[3]="Nu vei avea sanse totusi fara a sti vreo vraja.\nPoftim, asta e vraja focului. Esti ultima noastra\nsalvare.";
            dialogues[4]="Succes! Vei avea nevoie!\n(Y - Foc)\n(Enter - Atac simplu)";

        }
        public void setAction()
        {
            actionLockCounter++;
            if(actionLockCounter==36) {
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
        public void speak()
        {

            gp.ui.currentDialogue=dialogues[dialogueIndex];
            if(dialogueIndex==3) {
                gp.playSE(4);
               Player.hasFire = true;
            }
            if(dialogueIndex<4) dialogueIndex++;
            super.speak();



        }



}

