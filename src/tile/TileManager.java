package tile;
import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
   public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[200]; //nr de tiles =100

        getTileImage();
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/maps/lvl1_bun.txt",0);
        loadMap("/maps/lvl2.txt",1);
        loadMap("/maps/final_boss_map_1.txt",2);
    }

    public void getTileImage() {
        setup(0, "/tiles/tile000", true);
        setup(1, "/tiles/tile001", true);
        setup(2, "/tiles/tile002", true);
        setup(3, "/tiles/tile004", true);
        setup(4, "/tiles/tile017", true);
        setup(5, "/tiles/tile197", true);
        setup(6, "/tiles/tile102", true);
        setup(7, "/tiles/tile098", true);
        setup(8, "/tiles/tile016", true);
        setup(9, "/tiles/tile018", true);
        setup(10, "/tiles/tile100", true); //poate de pus false la coliziune
        setup(11, "/tiles/tile008", true);  //poate de inlocuit
        setup(12, "/tiles/tile007", true);  //poate de inlocuit
        setup(13, "/tiles/tile201", true);
        setup(14, "/tiles/tile202", true);
        setup(15, "/tiles/tile1594", true);
        setup(16, "/tiles/tile1867", false);
        setup(17, "/tiles/tile1916", false);

        //lv2
        setup(18, "/tiles/lvl2/0", true);
        setup(19, "/tiles/lvl2/1", true);
        setup(20, "/tiles/lvl2/2", true);
        setup(21, "/tiles/lvl2/3", true);
        setup(22, "/tiles/lvl2/4", true);
        setup(23, "/tiles/lvl2/5", true);
        setup(24, "/tiles/lvl2/6", true);
        setup(25, "/tiles/lvl2/7", true);
        setup(26, "/tiles/lvl2/8", true);
        setup(27, "/tiles/lvl2/9", true);
        setup(28, "/tiles/lvl2/10", true);
        setup(29, "/tiles/lvl2/11", true);
        setup(30, "/tiles/lvl2/12", true);
        setup(31, "/tiles/lvl2/13", true);
        setup(32, "/tiles/lvl2/14", true);
        setup(33, "/tiles/lvl2/15", true);
        setup(34, "/tiles/lvl2/16", true);
        setup(35, "/tiles/lvl2/17", true);
        setup(36, "/tiles/lvl2/18", true);
        setup(37, "/tiles/lvl2/19", true);
        setup(38, "/tiles/lvl2/20", true);
        setup(39, "/tiles/lvl2/21", true);
        setup(40, "/tiles/lvl2/22", true);
        setup(41, "/tiles/lvl2/23_n", false);
        setup(42, "/tiles/lvl2/24_n", false);
        setup(43, "/tiles/lvl2/25_n", false);
        setup(44, "/tiles/lvl2/26_n", false);
        setup(45, "/tiles/lvl2/27", true);
        setup(46, "/tiles/lvl2/28", true);
        setup(47, "/tiles/lvl2/29", true);
        setup(48, "/tiles/lvl2/30", true);
        setup(49, "/tiles/lvl2/31", true);
        setup(50, "/tiles/lvl2/32", true);
        setup(51, "/tiles/lvl2/33", true);
        setup(52, "/tiles/lvl2/34", true);
        setup(53, "/tiles/lvl2/35", true);

        //lv3
        //de verificat

        setup(54, "/tiles/lvl3/t0", true);
        setup(55, "/tiles/lvl3/t1", true);
        setup(56, "/tiles/lvl3/t2", true);
        setup(57, "/tiles/lvl3/t3", true);
        setup(58, "/tiles/lvl3/t4", true);
        setup(59, "/tiles/lvl3/t5", true);
        setup(60, "/tiles/lvl3/t6", true);
        setup(61, "/tiles/lvl3/t7", true);
        setup(62, "/tiles/lvl3/t8", true);
        setup(63, "/tiles/lvl3/t9", true);
        setup(64, "/tiles/lvl3/t10", true);
        setup(65, "/tiles/lvl3/t11", true);
        setup(66, "/tiles/lvl3/t12", true); //aici e problema
        setup(67, "/tiles/lvl3/t13", true);
        setup(68, "/tiles/lvl3/t14", true);
        setup(69, "/tiles/lvl3/t15", true);
        setup(70, "/tiles/lvl3/t16", true);
        setup(71, "/tiles/lvl3/t17", true);
        setup(72, "/tiles/lvl3/t18", true);
        setup(73, "/tiles/lvl3/t19", true);
        setup(74, "/tiles/lvl3/t20", true);
        setup(75, "/tiles/lvl3/t21", true);
        setup(76, "/tiles/lvl3/t22", true);
        setup(77, "/tiles/lvl3/t23", true);
        setup(78, "/tiles/lvl3/t24", true);
        setup(79, "/tiles/lvl3/t25", true);
        setup(80, "/tiles/lvl3/t26", true);
        setup(81, "/tiles/lvl3/t27", true);
        setup(82, "/tiles/lvl3/t28", true);
        setup(83, "/tiles/lvl3/t29", true);
        setup(84, "/tiles/lvl3/t30", true);
        setup(85, "/tiles/lvl3/t31", true);
        setup(86, "/tiles/lvl3/t32", true);
        //de aici poate se modifica
        setup(87, "/tiles/lvl3/t33", true);
        setup(88, "/tiles/lvl3/t34", true);
        setup(89, "/tiles/lvl3/t35_n", false); //aici 35_n
        setup(90, "/tiles/lvl3/t36_n", false);
        setup(91, "/tiles/lvl3/t37", true);
        setup(92, "/tiles/lvl3/t38_n", false);
        setup(93, "/tiles/lvl3/t39_n", false);
        setup(94, "/tiles/lvl3/t40", true);
        setup(95, "/tiles/lvl3/t41", true);
        setup(96, "/tiles/lvl3/t42", true);
        setup(97, "/tiles/lvl3/t43", true);





    }
    public void setup(int index, String imagePath, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index]=new Tile();
            tile[index].image=ImageIO.read(getClass().getResourceAsStream(  imagePath + ".png"));
            tile[index].image=uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision=collision;
        }
        catch(IOException e)
        {
            e.printStackTrace();

        }
    }

   public void loadMap(String filePath, int map) {
        try {
           // InputStream is = getClass().getResourceAsStream("/maps/map01.txt"); //demo map
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row]=num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }

            br.close();



        } catch(Exception e)
        {

        }
    }


        public void draw (Graphics2D g2)
        {
            int worldCol = 0;
            int worldRow = 0;


            while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
            {
                int tileNum=mapTileNum[gp.currentMap][worldCol][worldRow];

                int worldX=worldCol*gp.tileSize;
                int worldY=worldRow*gp.tileSize;
                int screenX=worldX-gp.player.worldX + gp.player.screenX;
                int screenY=worldY-gp.player.worldY+gp.player.screenY;

                //if randare (optimizare lag)
                  if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                      worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                      worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                      worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
                        if(this.tile[tileNum]!=null)g2.drawImage(tile[tileNum].image, screenX, screenY, null);

                    worldCol++;


                if (worldCol == gp.maxWorldCol) {
                    worldCol = 0;

                    worldRow++;

                }
            }
        }


    }

