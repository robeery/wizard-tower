package main;

import entity.*;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter (GamePanel gp)
    {
        this.gp=gp;
    }
    public void setObject()
    {
        int mapNum=0,i=0;
        gp.obj[mapNum][i]=new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX=84*gp.tileSize;
        gp.obj[mapNum][i].worldY=36* gp.tileSize;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionHealth(gp);
        gp.obj[mapNum][i].worldX=6*gp.tileSize;
        gp.obj[mapNum][i].worldY=44*gp.tileSize;
        i++;
        gp.obj[mapNum][i]=new OBJ_Door(gp,0);
        gp.obj[mapNum][i].worldX=45*gp.tileSize;
        gp.obj[mapNum][i].worldY=18*gp.tileSize;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionMana(gp);
        gp.obj[mapNum][i].worldX=46*gp.tileSize;
        gp.obj[mapNum][i].worldY=44*gp.tileSize;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionSpeed(gp);
        gp.obj[mapNum][i].worldX=25*gp.tileSize-25;
        gp.obj[mapNum][i].worldY=7*gp.tileSize;


        mapNum=1;
        i=0;

        gp.obj[mapNum][i]=new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX=5*gp.tileSize;
        gp.obj[mapNum][i].worldY=92* gp.tileSize;
        i++;
        gp.obj[mapNum][i]=new OBJ_Door(gp,2);
        gp.obj[mapNum][i].worldX=87*gp.tileSize;
        gp.obj[mapNum][i].worldY=65* gp.tileSize;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionMana(gp);
        gp.obj[mapNum][i].worldX=2525;
        gp.obj[mapNum][i].worldY=958;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionHealth(gp);
        gp.obj[mapNum][i].worldX=3845;
        gp.obj[mapNum][i].worldY=958;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionMana(gp);
        gp.obj[mapNum][i].worldX=6112;
        gp.obj[mapNum][i].worldY=5656;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionSpeed(gp);
        gp.obj[mapNum][i].worldX=1820;
        gp.obj[mapNum][i].worldY=2638;

        mapNum=2;
        i=0;
        gp.obj[mapNum][i]=new OBJ_PotionMana(gp);
        gp.obj[mapNum][i].worldX=2976;
        gp.obj[mapNum][i].worldY=1000;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionHealth(gp);
        gp.obj[mapNum][i].worldX=3310+10;
        gp.obj[mapNum][i].worldY=1000;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionMana(gp);
        gp.obj[mapNum][i].worldX=4390;
        gp.obj[mapNum][i].worldY=3186;
        i++;

        gp.obj[mapNum][i]=new OBJ_PotionHealth(gp);
        gp.obj[mapNum][i].worldX=1954;
        gp.obj[mapNum][i].worldY=3749;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionHealth(gp);
        gp.obj[mapNum][i].worldX=4376;
        gp.obj[mapNum][i].worldY=3749;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionMana(gp);
        gp.obj[mapNum][i].worldX=1970;
        gp.obj[mapNum][i].worldY=3183;
        i++;
        gp.obj[mapNum][i]=new OBJ_PotionSpeed(gp);
        gp.obj[mapNum][i].worldX=gp.tileSize*45;
        gp.obj[mapNum][i].worldY=gp.tileSize*75;



    }
    public void setNPC()
    {
        int mapNum=0,i=0;
        gp.npc[mapNum][i] = new NPC_WIZARD(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*45+32;
        gp.npc[mapNum][i].worldY = gp.tileSize*65;


        mapNum=1;
        i=0;
        gp.npc[mapNum][i] = new NPC_WIZARD_2(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*52;
        gp.npc[mapNum][i].worldY = gp.tileSize*12;


    }
    public void setMonster()
    {
        int mapNum=0,i=0;
        gp.monster[mapNum][i] = new monster_bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25+32;
        gp.monster[mapNum][i].worldY = gp.tileSize*65;
        i++;
        gp.monster[mapNum][i] = new monster_goblin_lvl1(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25+32;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        i++;
        gp.monster[mapNum][i] = new monster_goblin_lvl1(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*85+32;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        i++;
        gp.monster[mapNum][i] = new monster_bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*65+32;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        i++;
        gp.monster[mapNum][i] = new monster_bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*65+32;
        gp.monster[mapNum][i].worldY = gp.tileSize*25;
        i++;
        gp.monster[mapNum][i] = new monster_goblin_lvl1(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*45+32;
        gp.monster[mapNum][i].worldY = gp.tileSize*25;
        i++;
        gp.monster[mapNum][i] = new monster_bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25+32;
        gp.monster[mapNum][i].worldY = gp.tileSize*25;
        i++;
        gp.monster[mapNum][i] = new monster_bat(gp);
        gp.monster[mapNum][i].worldX = 4130;
        gp.monster[mapNum][i].worldY = 4090;

        mapNum=1;
        i=0;
        gp.monster[mapNum][i] = new monster_goblin_lvl2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*75;
        i++;

        gp.monster[mapNum][i] = new monster_goblin_lvl2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*85;
        gp.monster[mapNum][i].worldY = gp.tileSize*25;
        i++;

        gp.monster[mapNum][i] = new monster_goblin_lvl2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*80;
        gp.monster[mapNum][i].worldY = gp.tileSize*85;
        i++;

        gp.monster[mapNum][i] = new monster_goblin_lvl2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        i++;

        gp.monster[mapNum][i] = new monster_snake(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;

        gp.monster[mapNum][i] = new monster_snake(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*6;
        gp.monster[mapNum][i].worldY = gp.tileSize*92;
        i++;

        gp.monster[mapNum][i] = new monster_snake(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*45;
        gp.monster[mapNum][i].worldY = gp.tileSize*45;
        i++;
        gp.monster[mapNum][i] = new monster_snake(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*48;
        gp.monster[mapNum][i].worldY = gp.tileSize*48;
        i++;
        gp.monster[mapNum][i] = new monster_snake(gp);
        gp.monster[mapNum][i].worldX = 1190;
        gp.monster[mapNum][i].worldY = 538;
        i++;

        gp.monster[mapNum][i] = new monster_snake(gp);
        gp.monster[mapNum][i].worldX = 5345;
        gp.monster[mapNum][i].worldY = 643;
        i++;

        gp.monster[mapNum][i] = new monster_goblin_lvl2(gp);
        gp.monster[mapNum][i].worldX = 1205;
        gp.monster[mapNum][i].worldY = 4963;
        i++;

        gp.monster[mapNum][i] = new monster_goblin_lvl2(gp);
        gp.monster[mapNum][i].worldX = 2330;
        gp.monster[mapNum][i].worldY = 5518;


        mapNum=2;
        i=0;
        gp.monster[mapNum][i] = new monster_BOSS(gp);
        gp.monster[mapNum][i].worldX = 3158;
        gp.monster[mapNum][i].worldY = 2666;







    }
}
