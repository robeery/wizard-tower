package entity;



import main.GamePanel;



import java.awt.*;
import java.util.Random;


public class NPC_WIZARD_2 extends Entity
{

    public NPC_WIZARD_2(GamePanel gp)
    {
        super(gp);

        direction = "down";
        speed = 1;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize*2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        name="NPC_WIZARD_ICE";

        getImage();
        setDialogue();
    }
    public void getImage()
    {

        up1=setup_npc("/sprites_ice_wizard/g_sus1");
        up2= setup_npc("/sprites_ice_wizard/g_sus2");
        up3= setup_npc("/sprites_ice_wizard/g_sus3");

        down1= setup_npc("/sprites_ice_wizard/g_jos1");
        down2= setup_npc("/sprites_ice_wizard/g_jos2");
        down3= setup_npc("/sprites_ice_wizard/g_jos3");

        left1= setup_npc("/sprites_ice_wizard/g_st1");
        left2= setup_npc("/sprites_ice_wizard/g_st2");
        left3= setup_npc("/sprites_ice_wizard/g_st3");

        right1= setup_npc("/sprites_ice_wizard/g_dr1");
        right2= setup_npc("/sprites_ice_wizard/g_dr2");
        right3= setup_npc("/sprites_ice_wizard/g_dr3");

        right_s= setup_npc("/sprites_ice_wizard/g_drdefault");
        up_s=setup_npc("/sprites_ice_wizard/g_susdefault");
        left_s= setup_npc("/sprites_ice_wizard/g_stdefault");
        down_s= setup_npc("/sprites_ice_wizard/g_josdefault");


    }
    public void setDialogue()
    {
        dialogues[0]="Salutare, fetito.";
        dialogues[1]="Eu sunt vrajitorul de gheata.";
        dialogues[2]="Bine ai sosit la etajul meu. Acesta este mult mai periculos decat\ncelalalt.\nNoroc ca gheata loveste mai tare.\n ";

        dialogues[3]="Insa gheata te costa mai multa mana.\nPoftim, asta e vraja ghetei. Esti ultima noastra salvare.";
        dialogues[4]="Succes! Vei avea nevoie!\n(Y - Foc)\n(U- Gheata)\n(Enter - Atac simplu)";

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
            Player.hasIce = true;

        }
        if(dialogueIndex<4) dialogueIndex++;
        super.speak();



    }



}

