package entity;

import main.*;
import object.OBJ_Coins;
import object.OBJ_Fireball;
import object.OBJ_Ice;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;
    SQL db;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int score=0;
    public int allTimeScore;
    public static boolean hasFire=false;
    public static boolean hasIce=false;

    public int manaCooldown=0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        db=new SQL();
        this.keyH = keyH;
        allTimeScore=db.GetKills();
        solidArea = new Rectangle(0, 0, gp.tileSize-15 , gp.tileSize-15 );
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        attackArea.width = gp.tileSize;
        attackArea.height=gp.tileSize;
        setDefaultValues();
        getPlayerImage();
        if(allTimeScore==0)
            db.add(0);


    }

    public void setDefaultValues() {
      worldX = gp.tileSize * 45  - 24 ;//+gp.tileSize*10;
      worldY = gp.tileSize * 86; //pozitie start lvl1

       // worldX = gp.tileSize * 55 ;//+gp.tileSize*10;
    //   worldY = gp.tileSize * 55; //pozitie start lvl1

        //
        score=0;
        hasKey=0; //test
        //
        speed = 6; //=6 la final
        direction = "down";
        attack=1;

        projectile = new OBJ_Fireball(gp);
        projectile2 = new OBJ_Ice(gp);
        //player status
        maxLife = 6;
        life = maxLife;
        maxMana=4;
        mana=maxMana;

    }

    public void getPlayerImage() {

        up1 = setup("/player/spriteuri_zeleea/z_mers_sus_1");
        up2 = setup("/player/spriteuri_zeleea/z_mers_sus_2");
        down1 = setup("/player/spriteuri_zeleea/z_mers_jos_1");
        down2 = setup("/player/spriteuri_zeleea/z_mers_jos_2");
        left1 = setup("/player/spriteuri_zeleea/z_mers_stanga_1");
        left2 = setup("/player/spriteuri_zeleea/z_mers_stanga_2");
        right1 = setup("/player/spriteuri_zeleea/z_mers_dreapta_1");
        right2 = setup("/player/spriteuri_zeleea/z_mers_dreapta_2");


        right_s = setup("/player/spriteuri_zeleea/z_stat_dreapta");
        up_s = setup("/player/spriteuri_zeleea/z_stat_sus");
        left_s = setup("/player/spriteuri_zeleea/z_stat_stanga");
        down_s = setup("/player/spriteuri_zeleea/z_stat_jos");


        attackUp1 = setup("/player/spriteuri_zeleea/z_mers_sus_1");
        attackUp2 = setup("/player/spriteuri_zeleea/z_mers_sus_2");
        attackDown1 = setup("/player/spriteuri_zeleea/z_atac_jos_1");
        attackDown2 = setup("/player/spriteuri_zeleea/z_atac_jos_2");
        attackLeft = setup("/player/spriteuri_zeleea/z_atac_stanga");
        attackRight = setup("/player/spriteuri_zeleea/z_atac_dreapta");


    }

    public void update() {
        if (attacking == true) {
            attacking();
        }
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed == true) {


                direction = "up";
            } else if (keyH.downPressed == true) {


                direction = "down";
            } else if (keyH.leftPressed == true) {

                direction = "left";
            } else if (keyH.rightPressed == true) {


                direction = "right";
            }
            //verificare coliziune tile-uri
            collisionIsOn = false;
            gp.cChecker.checkTile(this);

            //verificare coliziune obiecte
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //verificare coliziune npc uri
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //verificare colliiune monstrii
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            if (collisionIsOn == false & keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;

                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;

                        break;

                    case "right":
                        worldX += speed;

                        break;
                }
            }
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
        if(gp.keyH.shotKeyPressed==true && projectile.alive==false && projectile.haveResource(this)==true)
        {
            //setare directie coordonate, directie si utilizator
            projectile.set(worldX, worldY, direction, true, this);
            //add to the list
            gp.projectileList.add(projectile);
            gp.playSE(7);
            //scade mana
            projectile.substractResource(this);
        }
        if(gp.keyH.shotKeyPressed2==true && projectile2.alive==false && projectile2.haveResource(this)==true)
        {
            //setare directie coordonate, directie si utilizator
            projectile2.set(worldX, worldY, direction, true, this);
            //add to the list
            gp.projectileList.add(projectile2);
            gp.playSE(8);
            //scade mana
            projectile2.substractResource(this);
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 100) //frames
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(life>maxLife)
            life=maxLife;

        if(mana>maxMana)
            mana=maxMana;

        //mana regen
        if(mana<maxMana) {
            manaCooldown++;
            if (manaCooldown > 60 * 5) {
                mana++;
                manaCooldown = 0;
            }
        }

        if(life<=0)
        {
            gp.stopMusic();
            gp.ui.commandNum=-1;
            //sound efect death aici
            gp.gameState=gp.gameOverState;
        }

        if(allTimeScore>db.GetKills())
        {
            db.updateKills(allTimeScore);
        }




    }

    public void attacking()
    {
        spriteCounter++;
        if(spriteCounter<=5)
        {spriteNum=1;}
        if(spriteCounter > 5 && spriteCounter<=25)
        {
            spriteNum=2;
            //salvare worldx,worldy, solidArea curente
            int currentWorldX=worldX;
            int currentWorldY=worldY;
            int solidAreaWidth=solidArea.width;
            int solidAreaHeight=solidArea.height;

            //ajustare
            switch(direction)
            {
                case "up": worldY-=attackArea.height; break;
                case "down": worldY+=attackArea.height; break;
                case "left": worldX+=attackArea.width; break;
                case "right": worldX +=attackArea.width; break;

            }
            //attackArea devine solidArea
            solidArea.width=attackArea.width;
            solidArea.height=attackArea.height;

            //verifica monster collision cu cele actualizate
            int monsterIndex=gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex,attack);

            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width=solidAreaWidth;
            solidArea.height=solidAreaHeight;



        }
        if(spriteCounter>25)
        {
            spriteNum=1;
            spriteCounter=0;
            attacking=false;

        }
    }
    public void pickUpObject(int i)
    {
        if(i!=888)
        {
            String objectName=gp.obj[gp.currentMap][i].name;

            switch(objectName)
            {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[gp.currentMap][i]=null;

                    gp.ui.showMessage("Ai gasit cheia!");
                    break;
                case "Door":
                    if(hasKey > 0)
                    {
                        if(gp.obj[gp.currentMap][i].nr_usa==0) {
                            gp.playSE(3);
                            gp.obj[gp.currentMap][i] = null;
                            hasKey--;
                            gp.ui.showMessage("Ai deschis usa!");
                            teleport(1, 50, 7);
                        }
                        else if(gp.obj[gp.currentMap][i].nr_usa==2){
                            gp.playSE(3);
                            gp.obj[gp.currentMap][i] = null;
                            hasKey--;
                            gp.stopMusic();
                            gp.playMusic(9);
                            teleport(2, 50, 12);
                        }

                    }
                    else
                    {
                        gp.ui.showMessage("Ai nevoie de cheie!");
                    }
                    break;
                case "PotionHealth":
                    gp.playSE(2);
                    maxLife+=2;
                    life=maxLife;
                    gp.obj[gp.currentMap][i]=null;
                    score+=200;
                    allTimeScore+=200;
                    //gp.playerSE();

                    break;

                case "PotionMana":
                    gp.playSE(2);
                    maxMana+=2;
                    mana=maxMana;
                    score+=200;
                    allTimeScore+=200;
                    gp.obj[gp.currentMap][i]=null;

                    //gp.playerSE();

                    break;
                case "PotionSpeed":
                    gp.playSE(2);
                    speed++;
                    score+=200;
                    allTimeScore+=200;
                    gp.obj[gp.currentMap][i]=null;

                    //gp.playerSE();

                    break;

                case "Coins":


                    int soundIndex = 12;
                    if(gp.obj[gp.currentMap][i] instanceof OBJ_Coins)
                    {
                        OBJ_Coins coin = (OBJ_Coins) gp.obj[gp.currentMap][i];
                        switch(coin.type)
                        {
                            case big:
                                soundIndex = 11;
                                break;
                            case medium:
                                soundIndex = 13;
                                break;
                            default:
                                //one, two, three coin types
                                break;
                        }
                    }

                    gp.playSE(soundIndex);
                    score+=gp.obj[gp.currentMap][i].onPickUp();
                    gp.obj[gp.currentMap][i]=null;
                    break;
            }
        }

    }
    public void contactMonster(int i)
    {
        if(i!=888)
        {
            if(invincible==false) {
                life -= attack;
                gp.playSE(6);
                invincible=true;
            }
        }
    }
    public void damageMonster(int i, int attack)
    {
        if(i!=888)
        {
            if(gp.monster[gp.currentMap][i].invincible==false)
            {
                gp.monster[gp.currentMap][i].life-=attack;
                gp.monster[gp.currentMap][i].invincible=true;
                gp.monster[gp.currentMap][i].damageReaction();
                if(gp.monster[gp.currentMap][i].life<=0)
                {


                    if(gp.monster[gp.currentMap][i].name=="BOSS")
                    {
                        gp.monster[gp.currentMap][i]=null;
                        score+=1000;
                        allTimeScore+=1000;
                        gp.stopMusic();
                        gp.playSE(10);
                        gp.gameState=gp.WinState;


                    }
                    else {
                        score += 100;
                        allTimeScore+=100;
                        gp.monster[gp.currentMap][i] = null;
                    }
                }
            }

        }

    }
    public void interactNPC(int i)
    {
        if(gp.keyH.enterPressed==true) {
            if (i != 888) {

                // System.out.println("lovesti mosul!!!");
                if (gp.keyH.enterPressed == true) {
                    gp.gameState = gp.dialogState;
                    gp.npc[gp.currentMap][i].speak();
                }
            } else {


                attacking = true;

            }
        }

        //gp.keyH.enterPressed=false; //poate scos?
    }
    public void draw(Graphics2D g2)
    {

        BufferedImage image = null;
        switch(direction)
        {
            case "up":
                if(attacking==false) {
                    if (keyH.upPressed == false)
                        image = up_s;
                    else {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                }
                if(attacking==true || keyH.shotKeyPressed==true)
                {

                        image = attackUp1;

                }
                break;
            case "down":
                if(attacking==false) {
                    if (keyH.downPressed == false)
                        image = down_s;
                    else {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                }

                 if(attacking || keyH.shotKeyPressed==true)
                 {
                     /*
                     if (spriteNum == 1) {
                         image = attackDown1;
                     }
                     if (spriteNum == 2) {
                         image = attackDown2;
                     }
                     */
                      image=attackDown2;
                 }

                break;
            case "left":
                if(attacking==false) {
                    if (keyH.leftPressed == false)
                        image = left_s;
                    else {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                }
                if(attacking==true || keyH.shotKeyPressed==true)
                {
                    /*
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    */
                     image=attackLeft;
                }
                break;

            case "right":
                if(attacking==false) {
                    if (keyH.rightPressed == false)
                        image = right_s;
                    else {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                }
                if(attacking || keyH.shotKeyPressed==true)
                {
                    image=attackRight;
                }
                break;
        }
        if(invincible==true)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, screenX, screenY,  null);
        g2.setFont(new Font("Arial", Font.PLAIN, 26 ));



        //Reset Alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));



    }
    public void teleport(int map, int col, int row) //y=7
    {


        gp.currentMap=map;
        gp.player.worldX=gp.tileSize*col;
        gp.player.worldY=gp.tileSize*row;
    }


}
