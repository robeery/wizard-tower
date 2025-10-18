package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    //setari ecran
    public final int  originalTileSize = 32; //32x32 tiles
    public final int scale = 2; //scalare pentru a vizibilitate raportat la rezolutie //sau 3
    public final int tileSize=originalTileSize*scale; //64x64 tile
    public final int maxScreenCol=20; //16
    public final int maxScreenRow=12; //12
    //4:3 vedere
    public final int screenWidth = tileSize*maxScreenCol; //1024 pe
    public final int screenHeight=tileSize*maxScreenRow; //768 rezolutia (pixeli)

    //FULL SCREEN
    int screenWidth2=screenWidth;
    int screenHeight2=screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    //WORLD SETTINGS
    public final int maxWorldCol =100;
    public final int maxWorldRow= 100;
    public final int worldWidth=tileSize* maxWorldCol; //poate voi folosi
    public final int worldHeight=tileSize* maxWorldRow; //poate voi folosi
    public final int maxMap=5;
    public int currentMap=0;


    //FPS
    int FPS=60;
    //SISTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();


    public CollisionChecker cChecker=new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread; //la crearea obiectului se apeleaza automat metoda run() de mai jos
    public UI ui = new UI(this);

    //ENTITATI SI OBIECTE
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][10]; //numere maxime din joc in timp real
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][]= new Entity[maxMap][20];
    public ArrayList<Entity> projectileList=new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();


    //GAMESTATE
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogState=3;
    public final int gameOverState=4;
    public final int WinState=5;


    //int playerSpeed=4;
    public GamePanel() throws IOException, FontFormatException {

        this.setPreferredSize(new Dimension (screenWidth, screenHeight));
        this.setBackground(new Color(8, 14, 22)); //this color coresponds to the black tile
        this.setDoubleBuffered(true); //toate desenele componente se fac intr-un buffer si imbunatateste performanta
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame()
    {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();

       playMusic(5);
        gameState=titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2=(Graphics2D) tempScreen.getGraphics();


    }
    public void startGameThread()
    {
        gameThread=new Thread (this);
        gameThread.start();
    }
    @Override
    public void run() {

        //FPS VECHI


        double drawInterval = 1000000000/FPS; //0.01(6) secunde
        double nextDrawTime= System.nanoTime() + drawInterval;

        while(gameThread!=null)
        {
           // System.out.println("game loop ul merge");
            long currentTime = System.nanoTime();
          //  System.out.println("Current Time: "+currentTime);
            //update (1)
            update();
            //deseneaza(2)
           repaint();
           //drawToTempScreen(); //deseneaza tot pe imaginea buffered
          //  drawToScreen(); //deseneaza imaginea buffered pe ecran

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //nano spre mili secunde
                if(remainingTime < 0 )
                {
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        /*
        double drawInterval = 1000000000/FPS; //0.01(6) secunde
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;

        while(gameThread!=null)
        {
            currentTime=System.nanoTime();

            delta+=(currentTime-lastTime)/drawInterval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;

            if(delta>=1)
            {
                update();
                drawToTempScreen();
                drawToScreen();
                delta--;
                drawCount++;
            }
            if(timer>=1000000000)
            {
                drawCount=0;
                timer=0;
            }
        }
        */


    }
    public void update()
    {
        if(gameState==playState)
        {
            //player
            player.update();
         //   System.out.println(player.worldX/tileSize + " " + player.worldY/tileSize);
            //npc
            for(int i=0; i<npc[1].length; i++)
                if(npc[currentMap][i] != null)
                {
                    npc[currentMap][i].update();
                }
            for(int i=0; i<monster[1].length; i++)
                if(monster[currentMap][i] != null)
                {
                    monster[currentMap][i].update();
                }
            for(int i=0; i<projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive)
                        projectileList.get(i).update();
                }
                if (!projectileList.get(i).alive) {
                    projectileList.remove(i);
                }
            }
        }
        if(gameState==pauseState)
        {
            //
        }
        if(gameState==gameOverState) {
            // music.stop();
        }
        if(gameState==titleState)
        {

        }
        if(gameState==WinState)
        {


        }
    }
    ///FULL SCREEN POATE
    /*
    public void drawToTempScreen()
    {
        //DEBUG
        long drawStart=0;
        if (keyH.checkDrawTime == true)
        {


            drawStart = System.nanoTime();
        }
        //TITLE SCREEN
        if(gameState==titleState)
        {

            ui.draw(g2);
        }
        else {
            //TILE
            tileM.draw(g2);
            //ADD ENTITIES TO LIST
            entityList.add(player);
            for(int i=0; i<npc.length; i++)
            {
                if(npc[i]!=null)
                {
                    entityList.add(npc[i]);
                }
            }
            for(int i=0; i<obj.length; i++)
            {
                if(obj[i]!=null)
                {
                    entityList.add(obj[i]);
                }
            }
            for(int i=0; i<monster.length; i++)
            {
                if(monster[i]!=null)
                {
                    entityList.add(monster[i]);
                }
            }
            for(int i=0; i<projectileList.size(); i++)
            {
                if(projectileList.get(i)!=null)
                {
                    entityList.add(projectileList.get(i));
                }
            }
            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result=Integer.compare(e1.worldY, e2.worldY);
                    return 0;
                }
            });
            //DRAW ENTITIES
            for(int i=0; i<entityList.size(); i++)
            {
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST;
            entityList.clear();
            //UI
            ui.draw(g2);

            //DEBUG
            if (keyH.checkDrawTime) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time: " + passed, 10, 400);
                System.out.println("Draw Time: " + passed);
            }
        }


    }
    public void drawToScreen()
    {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2,screenHeight2,null);
        g2.dispose();
    }

     */

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D) g;

        //DEBUG
        long drawStart=0;
        if (keyH.checkDrawTime == true)
        {


            drawStart = System.nanoTime();
        }
        //TITLE SCREEN
        if(gameState==titleState)
        {

            ui.draw(g2);
        }
        else {
            //TILE
            tileM.draw(g2);
            //ADD ENTITIES TO LIST
            entityList.add(player);
            for(int i=0; i<npc[1].length; i++)
            {
                if(npc[currentMap][i]!=null)
                {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i=0; i<obj[1].length; i++)
            {
                if(obj[currentMap][i]!=null)
                {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i=0; i<monster[1].length; i++)
            {
                if(monster[currentMap][i]!=null)
                {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i=0; i<projectileList.size(); i++)
            {
                if(projectileList.get(i)!=null)
                {
                    entityList.add(projectileList.get(i));
                }
            }
            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result=Integer.compare(e1.worldY, e2.worldY);
                    return 0;
                }
            });
            //DRAW ENTITIES
            for(int i=0; i<entityList.size(); i++)
            {
                    entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST;
            entityList.clear();
            //UI
            ui.draw(g2);

            //DEBUG
            if (keyH.checkDrawTime == true) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time: " + passed, 10, 400);
                System.out.println("Draw Time: " + passed);
            }
        }
        g2.dispose(); //poate scos
    }


    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic()
    {
        music.stop();
    }
    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }
    public void retry()
    {
        player.setDefaultValues();

       // player.restoreLifeAndMana();
        tileM.loadMap("/maps/lvl1_bun.txt",0);
        tileM.loadMap("/maps/lvl2.txt",1);
        tileM.loadMap("/maps/final_boss_map_1.txt",2);
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setObject();
        if(currentMap==1) teleport(1, 50, 7);
        else if(currentMap==2)
        {
            teleport(2,50,12);
        }


    }

    private void teleport(int map, int x, int y) {
        currentMap=map;
        player.worldX=x*tileSize;
       player.worldY=y*tileSize;
    }

    public void restart()
    {
        player.setDefaultValues();
        // player.restoreLifeAndMana();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setObject();

    }
}
