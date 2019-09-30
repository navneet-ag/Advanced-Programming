package Lab6;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class Serialiazationtest {
    Game testgame1 = new Game(1000);
    @Before
    public void runonce()
    {
        Game.setCurrentgame(testgame1);
        testgame1.InitialSetup("hello");
        try {
            testgame1.play1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test1() throws IOException, ClassNotFoundException {
        Game.deserialize();
        Game temp=null;
        int i=0;
        for( i=0;i<Game.listofgames.size();i++)
        {
            if(Game.listofgames.get(i).getPlayer().getName().equals(testgame1.getPlayer().getName()))
            {
                break;
            }
        }
        System.out.println(testgame1.equals(Game.listofgames.get(i)));
        assertEquals(Game.listofgames.get(i),testgame1);

    }
}
