package main;

import entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean shotKeyPressed, shotKeyPressed2;
    GamePanel gp;
    boolean checkDrawTime=false; //debug

    public KeyHandler(GamePanel gp)
    {
        this.gp=gp;
    }
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState==gp.titleState)
        {


                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum<0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S) {

                    gp.ui.commandNum++;
                    if(gp.ui.commandNum>2) {
                        gp.ui.commandNum = 0;
                    }

                }
                if(code==KeyEvent.VK_ENTER)
                {
                    if(gp.ui.commandNum==0)
                    {
                        gp.gameState = gp.playState;
                        gp.stopMusic();
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum==1)
                    {
                        //adauga mai tarziu
                    }
                    if(gp.ui.commandNum==2)
                    {
                        System.exit(0);
                    }
                }

        }


       else if(gp.gameState==gp.playState) {

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;

            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed=true;

            }
            if (code == KeyEvent.VK_Y && Player.hasFire==true) {
                shotKeyPressed=true;

            }

            if (code == KeyEvent.VK_U && Player.hasIce==true) {
                shotKeyPressed2=true;

            }



        }
        //DEBUG
        if(code==KeyEvent.VK_0)
        {
            if(checkDrawTime==false)
            {
                checkDrawTime=true;
            }
            else if(checkDrawTime==true)
            {
                checkDrawTime=false;
            }
        }
        if(code==KeyEvent.VK_M)
        {
            switch(gp.currentMap) {
                case 0:
                    gp.tileM.loadMap("/maps/lvl1_bun.txt",0);
                    break;
                case 1:
                    gp.tileM.loadMap("/maps/lvl2.txt",1);
                    break;
            }

        }

        //PAUSE STATE
        else if(gp.gameState== gp.pauseState)
        {   if(code==KeyEvent.VK_ESCAPE)
            gp.gameState = gp.playState;
        }

        //DIALOGUE STATE
         else if(gp.gameState== gp.dialogState)
        {
            if(code == KeyEvent.VK_ENTER)
            {
                gp.gameState=gp.playState;
            }
        }
         else if(gp.gameState== gp.gameOverState)
        {
            gameOverState(code);
        }


    }
    public void gameOverState(int code)
    {
        if(code==KeyEvent.VK_W)
        {
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0)
            {
                    gp.ui.commandNum=1;
            }
        }
        if(code==KeyEvent.VK_S)
        {
            gp.ui.commandNum++;
            if(gp.ui.commandNum>1) {
                gp.ui.commandNum = 0;
            }
        }
        if(code==KeyEvent.VK_ENTER)
        {
            if(gp.ui.commandNum==0)
            {

                gp.retry();
                gp.gameState=gp.playState;
                if(gp.currentMap!=2)gp.playMusic(0);
                else gp.playMusic(9);
            }
            else if(gp.ui.commandNum==1)
            {
                gp.gameState=gp.titleState;
                gp.restart();
                gp.playMusic(5);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code=e.getKeyCode();
        if(code == KeyEvent.VK_W)
        {
            upPressed=false;
        }
        if(code == KeyEvent.VK_S)
        {
            downPressed=false;
        }
        if(code == KeyEvent.VK_A)
        {
            leftPressed=false;
        }
        if(code == KeyEvent.VK_D)
        {
            rightPressed=false;
        }
        if (code == KeyEvent.VK_Y) {
            shotKeyPressed=false;

        }
        if (code == KeyEvent.VK_U) {
            shotKeyPressed2=false;

        }

    }
}
