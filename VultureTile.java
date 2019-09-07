package Lab5;
public class VultureTile extends Tile{
     VultureTile(int moves)
    {
        this.MoveTiles=moves;
    }
    @Override
    protected void move() throws VultureBiteException
    {
        VultureBiteException exception= new VultureBiteException("Yapping...! I am a Vulture, you go back " + MoveTiles +" tiles!");
        throw (exception);
    }

}
