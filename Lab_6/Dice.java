package Lab6;

import java.util.Random;

public class Dice {
    private static Random rn=new Random();
    public int diceroll()
    {
        return (1+rn.nextInt(6));
    }

}
