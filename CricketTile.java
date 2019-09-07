package Lab5;
public class CricketTile extends Tile{
     CricketTile(int moves)
    {
        this.MoveTiles=moves;
    }
    @Override
    protected void move() throws CricketBiteException
    {
        CricketBiteException exception= new CricketBiteException("Chirp...! I am a Cricket, you go back " + MoveTiles +" tiles!");
        throw (exception);
    }
}
