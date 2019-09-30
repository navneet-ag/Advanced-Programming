package Lab6;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class test2 {

    @Test(expected = GameWinnerException.class)
    public void GameWinner() throws  GameWinnerException {
        Game testgame1 = new Game(1000);
        Game.setCurrentgame(testgame1);
        testgame1.InitialSetup("hello");
            testgame1.play2();
    }
    @Test(expected = SnakeBiteException.class)
    public void SnakebiteException() throws SnakeBiteException {
        Game testgame1 = new Game(10000);
        Game.setCurrentgame(testgame1);
        testgame1.InitialSetup("hello");
        int val=testgame1.play3();
        if(val==0)
            throw (new SnakeBiteException(" "));
    }
    @Test(expected = TrampolineException.class)
    public void TrampolineException() throws  TrampolineException {
        Game testgame1 = new Game(10000);
        Game.setCurrentgame(testgame1);
        testgame1.InitialSetup("hello");
        int val=testgame1.play6();
        if(val==0)
            throw (new TrampolineException(" "));
    }
    @Test(expected = CricketBiteException.class)
    public void CricketbiteException() throws CricketBiteException {
        Game testgame1 = new Game(10000);
        Game.setCurrentgame(testgame1);
        testgame1.InitialSetup("hello");
        int val=testgame1.play5();
        if(val==0)
            throw (new CricketBiteException(" "));
    }
    @Test(expected = VultureBiteException.class)
    public void VulturebiteException() throws VultureBiteException, GameWinnerException {
        Game testgame1 = new Game(10000);
        Game.setCurrentgame(testgame1);
        testgame1.InitialSetup("hello");
        int val=testgame1.play4();
        if(val==0)
            throw (new VultureBiteException(" "));
    }
    @Test(expected = SnakeBiteException.class)
    public void Snakebite() throws SnakeBiteException {
        SnakeTile a=new SnakeTile(5);
        a.move();
    }
    @Test(expected = VultureBiteException.class)
    public void Vulturebite() throws VultureBiteException {
        VultureTile a=new VultureTile(4);
        a.move();
    }
    @Test(expected = CricketBiteException.class)
    public void Cricketbite() throws CricketBiteException {
        CricketTile a=new CricketTile(3);
        a.move();
    }
    @Test(expected = TrampolineException.class)
    public void Trampolinebite() throws TrampolineException {
        TrampolineTile a=new TrampolineTile(2);
        a.move();
    }


}
