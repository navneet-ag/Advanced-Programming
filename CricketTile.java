package Lab5;
public class CricketTile extends Tile{
     CricketTile(int moves)
    {
        this.MoveTiles=moves;
    }
    @Override
    protected void move() throws CricketBiteException
    {
        System.out.println("Chirp...! I am a Cricket, you go back " + MoveTiles +" tiles!");
        CricketBiteException exception= new CricketBiteException("Cricket bit you !");
        throw (exception);
    }
}
