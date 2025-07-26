package main;

import entity.Entity;
import entity.Player;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Mana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;

    Graphics2D g2;
    //Font arial_40, arial_80B;
    Font DungeonFont;
    BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_empty;
    BufferedImage mana_full, mana_half, mana_empty;

    public boolean gameFinished=false;
    public boolean messageOn = false;
    public String message="";
    public String currentDialogue ="";
    public int commandNum = 0;
    int counter=0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) throws IOException, FontFormatException {
        this.gp=gp;
       // arial_40 = new Font("Arial", Font.PLAIN, 40);
      //  arial_80B = new Font("Arial", Font.BOLD, 80);
        try {
            InputStream is = getClass().getResourceAsStream("/font/DungeonFont.ttf");
            DungeonFont = Font.createFont(Font.TRUETYPE_FONT, is);
            OBJ_Key key = new OBJ_Key(gp);
            keyImage = key.down1;
        } catch(FontFormatException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        Entity heart = new OBJ_Heart(gp);
        heart_full =heart.image;
        heart_half=heart.image2;
        heart_empty=heart.image3;

        Entity mana_crystal=new OBJ_Mana(gp);
        mana_full =mana_crystal.image;
        mana_half=mana_crystal.image2;
        mana_empty=mana_crystal.image3;



    }
    public void showMessage(String text)
    {
        message = text;
        messageOn=true;
    }

    public void draw(Graphics2D g2)
    {
        if (gp.player.hasKey != 0)
            g2.drawImage(keyImage, gp.screenWidth-gp.tileSize*2+10,  gp.screenHeight-gp.tileSize-10, gp.tileSize, gp.tileSize, null);
       // g2.drawString("Scor:" + gp.player.score, gp.tileSize*10, gp.tileSize/2);
        /*
        if(gameFinished==true)
        {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;
            text="You found the potion";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
             x=gp.screenWidth/2 - textLength/2;
             y=gp.screenWidth/2 - (gp.tileSize*3);

             g2.drawString(text, x, y);

            text="Timpul tau este: " + dFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x=gp.screenWidth/2 - textLength/2;
            y=gp.screenWidth/2 - (gp.tileSize*4);

            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text="Ai castigat";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x=gp.screenWidth/2 - textLength/2;
            y=gp.screenWidth/2 - (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread=null;



        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            if (gp.player.hasKey != 0)
                g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            // g2.drawString("Key = " + gp.player.hasKey, 50, 50);

            //TIMP
            playTime +=(double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);
            //mesaj
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

         */

        this.g2=g2;
        g2.setFont(DungeonFont);
        g2.drawString("test", 300, 300);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //TITLE STATE
        if(gp.gameState==gp.titleState)
        {

            drawTitleScreen();
        }
        //PLAYSTATE
        if(gp.gameState==gp.playState)
        {
            drawPlayerLife();


        }
        //PAUSESTATE

        if(gp.gameState==gp.pauseState)
        {
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState== gp.dialogState)
        {
            drawPlayerLife();
            drawDialogueScreen();
        }
        //GAME OVER STATE
        if(gp.gameState==gp.gameOverState)
        {
            drawGameOverScreen();
        }
        if(gp.gameState==gp.WinState)
        {
            drawPlayerLife();
            drawWinScreen();


        }


    }
    public void drawPlayerLife()
    {

        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;

        //DRAW MAX LIFE
        while(i<gp.player.maxLife/2)
        {
            g2.drawImage(heart_empty,x,y,null);
            i++;
            x += gp.tileSize;
        }
        //RESET
         x=gp.tileSize/2;
         y=gp.tileSize/2;
         i=0;

        //DRAW CURRENT LIFE
        while(i<gp.player.life)
        {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i<gp.player.life)
                g2.drawImage(heart_full, x, y,null);
            i++;
            x+=gp.tileSize;
        }
        //DRAW MAX MANA
        x=gp.tileSize/2;
        y=gp.tileSize*2-25;
        i=0;
        while(i<gp.player.maxMana/2)
        {
            g2.drawImage(mana_empty, x, y, null);
            i++;
            x+=gp.tileSize-10;
        }

        //DRAW CURRENT MANA
        x=gp.tileSize/2;
        y=gp.tileSize*2-25;
        i=0;
        int aux_x=x;
        int aux_y=y;
        while(i<gp.player.mana)
        {
            g2.drawImage(mana_half, x, y, null);
            i++;
            if(i<gp.player.mana)
                g2.drawImage(mana_full, x, y,null);
            i++;
            x+=gp.tileSize-10;
        }



        g2.setFont(DungeonFont);
        g2.setColor(Color.white);



        g2.setFont(new Font("Dungeon Font", Font.PLAIN, 30));
        g2.setFont(DungeonFont);
        g2.setFont(g2.getFont().deriveFont(42F));

        aux_x= gp.screenWidth-gp.tileSize*4;
        aux_y=gp.tileSize;


        g2.drawString("Scor: " + gp.player.score, aux_x,aux_y);



    }
    public void drawGameOverScreen()
    {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        //umbra
        text="Sfarsitul Jocului";
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text="Retry";
        x=getXforCenteredText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0)
        {
            g2.drawString(">",x-40,y);
        }

        //inapoi la title screen
        text ="Quit";
        x=getXforCenteredText(text);
        y+=55;
        g2.drawString(text,x,y);
        if(commandNum==1)
        {
            g2.drawString(">",x-40,y);
        }

    }
    public void drawTitleScreen() {
        //fundal
        BufferedImage fundal = null;
        try {
            fundal = ImageIO.read(getClass().getResource("/fundal/fundal1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        g2.drawImage(fundal, 0, 0, gp.screenWidth, gp.screenHeight, null);
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 98F));
        String text = "Wizard Tower";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        //umbra
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 8, y + 8);
        //culoare principala
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //MENIU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "New Game";
        x= getXforCenteredText(text);
        y+=gp.tileSize*4;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+7, y +7);
        g2.setColor(Color.white);
        g2.drawString(text, x,y);

        if(commandNum== 0)
        {
            g2.drawString(">", x-32, y);
        }

        text = "Load Save";
        x= getXforCenteredText(text);
        y+=gp.tileSize*2;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+7, y +7);
        g2.setColor(Color.white);
        g2.drawString(text, x,y);

        if(commandNum== 1)
        {
            g2.drawString(">", x-32, y);
        }

        text = "Quit";
        x= getXforCenteredText(text);
        y+=gp.tileSize*2;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+7, y +7);
        g2.setColor(Color.white);
        g2.drawString(text, x,y);

        if(commandNum== 2)
        {
            g2.drawString(">", x-32, y);
        }





    }
    public void drawPauseScreen()
    {
        String text = "PAUZA";
        int x=getXforCenteredText(text);

        int y=gp.screenWidth/2;

        g2.drawString(text, x, y);
    }
    public void drawWinScreen()
    {
        ;
        g2.setFont(DungeonFont);
        g2.setFont(g2.getFont().deriveFont(60F));
        String text = "Victorie! Ai salvat lumea vrajitorilor!";
        int x=getXforCenteredText(text);

        int y=gp.screenWidth/4;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 8, y + 8);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

    }
    public void drawDialogueScreen()
    {
        //FEREASTRA
        int x=gp.tileSize*2;
        int y=gp.tileSize*7;
        int width=gp.screenWidth-gp.tileSize*4;
        int height=gp.tileSize*4;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,37F));
        drawSubWindow(x,y,width,height);
        x+=gp.tileSize;
        y+=gp.tileSize;
        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y+=40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0,0,0,215);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255,220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

    }
    public int getXforCenteredText(String text)
    {
        int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;
    }

}
