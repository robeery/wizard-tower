package object;

import entity.Entity;
import main.GamePanel;

import java.util.Random;


public class OBJ_Coins extends Entity {

    public CoinType type;
    public OBJ_Coins(GamePanel gp, CoinType type) {
        super(gp);

        this.type = type;
        name="Coins";
        int width = 32, height = 32;
        String path = getImagePathByType();
        System.out.println(path);
        down1=setup(path , width, height);

    }
    int getValueByType()
    {
        return switch (this.type) {
            case one -> 1;
            case two -> 2;
            case three -> 3;
            case medium -> 10;
            case big -> 20;
        };
    }

    @Override
    public int onPickUp()
    {
        System.out.println("picked up");
        return this.getValueByType();

    }

    String getImagePathByType()
    {
        return switch (this.type) {
            case CoinType.one -> "/objects/coins/coins_one";
            case CoinType.two -> "/objects/coins/coins_two";
            case CoinType.three -> "/objects/coins/coins_three";
            case CoinType.medium -> "/objects/coins/coins_medium";
            case CoinType.big -> "/objects/coins/coins_big";
        };

    }
    CoinType getRandomCoinType()
    {
        Random random = new Random();

        int value = random.nextInt(5)+1;
        return switch (value)
        {
            case 2 -> CoinType.two;
            case 3 -> CoinType.three;
            case 4 -> CoinType.medium;
            case 5 -> CoinType.big;
            default -> CoinType.one;
        };
    }

}
