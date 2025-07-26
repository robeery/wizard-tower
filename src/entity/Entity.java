package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//clasa parinte pentru toate entitatile
public class Entity {

    //de facut curat aici poza
    GamePanel gp;
    public int worldX, worldY;


    public int speed;
    public BufferedImage up1, up2, down1, down2, left1,left2,right1,right2,up_s,down_s,left_s,right_s;
    public BufferedImage up3, down3, left3, right3;
    public BufferedImage up4, down4, left4, right4, up5, down5, left5, right5;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft, attackRight;
    public String direction="down";

    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionIsOn=false;
    public int actionLockCounter = 0;
    public boolean invincible=false;
    public int invincibleCounter;
    boolean hpBarOn=false;
    int hpBarCounter=0;
    String dialogues[] = new String[20];
    int dialogueIndex=0;
    public BufferedImage image;
    public BufferedImage image1;
    public BufferedImage image2;
    public BufferedImage image3;
    public BufferedImage image4;
    public BufferedImage image5;

    public Rectangle attackArea = new Rectangle(0, 0, 0 ,0);
    public String name;
    public boolean collision = false;
    boolean attacking = false;
    public int nr_usa;
    public int attack=0;
    public int type; // 0 -player, 1-npc, 2-monster
    public UtilityTool uTool = new UtilityTool();
    public int frame_potiuneHP=0;


    //CHARACTER STATUS
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public Projectile projectile;
    public Projectile projectile2;
    public int useCost;
    public boolean  alive=true;

    public Entity(GamePanel gp)
    {
        this.gp=gp;
    }
    public void setAction()
    {

    }
    public void damageReaction()
    {

    }
    public void speak()
    {
        //aici cresterea index si conditiile le voi face in functiile copii
        switch(gp.player.direction)
        {
            case"up":
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
    public void update()
    {
        setAction();
        collisionIsOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer ==true)
        {
            damagePlayer(attack);
        }
        if(collisionIsOn==false)
        {
            switch(direction)
            {
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

        spriteCounter++;
        if (spriteCounter > 12)
        {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 3;
            else if(spriteNum==3)
                spriteNum=1;
            spriteCounter = 0;
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) //frames
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void damagePlayer (int attack)
    {
        if(gp.player.invincible==false)
        {
            //poate fi lovit player
            gp.player.life -=attack;
            gp.player.invincible=true;
            gp.playSE(6);
        }

    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image=null;
        int screenX=worldX-gp.player.worldX + gp.player.screenX;
        int screenY=worldY-gp.player.worldY+gp.player.screenY;

        //if randare (optimizare lag)
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY )
        {
            switch(direction)
            {
                case "up":

                        image=up_s;

                        if (spriteNum == 1) {

                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                        if(spriteNum==3)
                        {
                            image = up3;
                        }
                        if(spriteNum==4)
                        {
                            image=up4;

                        }
                        if(spriteNum==5)
                        {
                            image=up5;

                        }

                    break;
                case "down":

                        image=down_s;

                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        if(spriteNum==3)
                        {
                            image = down3;
                        }
                         if(spriteNum==4)
                          {
                              image=down4;

                          }
                         if(spriteNum==5)
                          {
                        image=down5;

                         }

                    break;
                case "left":

                        image=left_s;

                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        if(spriteNum==3)
                        {
                            image = left3;
                        }
                         if(spriteNum==4)
                         {
                        image=left4;

                         }
                        if(spriteNum==5)
                         {
                        image=left5;

                         }

                    break;

                case "right":

                    image=right_s;

                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        if(spriteNum==3)
                        {
                            image = right3;
                        }
                         if(spriteNum==4)
                         {
                             image=right4;

                          }
                        if(spriteNum==5)
                         {
                        image=right5;

                         }

                    break;
            }
            if(invincible==true)
            {
                hpBarOn=true;
                hpBarCounter=0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

            }
            if(name.equals("Key")) g2.drawImage(image, screenX+16, screenY+16, 32,32, null);
            else if(name=="PotionHealth")
            {
                 //poate de revizuit putintel aici
                    if(frame_potiuneHP<30) g2.drawImage(image1, screenX , screenY ,  null);
                   else if (frame_potiuneHP >30 && frame_potiuneHP<=60) g2.drawImage(image2, screenX , screenY ,  null);
                  else  if (frame_potiuneHP >60 && frame_potiuneHP<=90) g2.drawImage(image3, screenX , screenY ,  null);
                  else  if (frame_potiuneHP >90 && frame_potiuneHP<=120) g2.drawImage(image4, screenX , screenY ,  null);
                  else  if (frame_potiuneHP >120 && frame_potiuneHP<=150) g2.drawImage(image5, screenX , screenY ,  null);

                frame_potiuneHP++;
                if(frame_potiuneHP==150) frame_potiuneHP=0;

            }
            else if(name=="PotionMana")
            {
                //poate de revizuit putintel aici
                if(frame_potiuneHP<30) g2.drawImage(image1, screenX , screenY ,  null);
                else if (frame_potiuneHP >30 && frame_potiuneHP<=60) g2.drawImage(image2, screenX , screenY,  null);
                else  if (frame_potiuneHP >60 && frame_potiuneHP<=90) g2.drawImage(image3, screenX , screenY ,  null);
                else  if (frame_potiuneHP >90 && frame_potiuneHP<=120) g2.drawImage(image4, screenX , screenY ,  null);
                else  if (frame_potiuneHP >120 && frame_potiuneHP<=150) g2.drawImage(image5, screenX , screenY ,  null);

                frame_potiuneHP++;
                if(frame_potiuneHP==150) frame_potiuneHP=0;

            }
            else if(name=="PotionSpeed")
            {
                //poate de revizuit putintel aici
                if(frame_potiuneHP<30) g2.drawImage(image1, screenX , screenY ,  null);
                else if (frame_potiuneHP >30 && frame_potiuneHP<=60) g2.drawImage(image2, screenX , screenY ,  null);
                else  if (frame_potiuneHP >60 && frame_potiuneHP<=90) g2.drawImage(image3, screenX , screenY ,  null);
                else  if (frame_potiuneHP >90 && frame_potiuneHP<=120) g2.drawImage(image4, screenX , screenY ,  null);
                else  if (frame_potiuneHP >120 && frame_potiuneHP<=150) g2.drawImage(image5, screenX , screenY ,  null);

                frame_potiuneHP++;
                if(frame_potiuneHP==150) frame_potiuneHP=0;

            }


            else if(name=="Door") g2.drawImage(image, screenX-32, screenY+4, gp.tileSize, gp.tileSize*2 , null);
            else {
                g2.drawImage(image, screenX, screenY, null);
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        ///MONSTER HP BAR
        if(type==2 && hpBarOn==true ) {
            double oneScale = (double) gp.tileSize/maxLife;
            double hpBarValue=oneScale*life;
            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX-1, screenY - 16, gp.tileSize+2, 12);
            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
            hpBarCounter++;
            if(hpBarCounter>600)
            {
                hpBarCounter=0;
                hpBarOn=false;
            }

        }




    }
    public BufferedImage setup(String imageName)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(getClass().getResourceAsStream( imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize-12, gp.tileSize-12);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }
    public BufferedImage setup(String imageName, int width, int height)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(getClass().getResourceAsStream( imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, width, height);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }
    public BufferedImage setup_npc(String imageName)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try
        {
            scaledImage = ImageIO.read(getClass().getResourceAsStream( imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, 32*2, 48*2+10);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return scaledImage;
    }

    protected int getNr_usa() {
        return nr_usa;
    }
}
