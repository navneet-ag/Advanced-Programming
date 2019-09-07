package Lab5;
public class SnakeTile extends Tile{

    SnakeTile(int moves)
    {
        this.MoveTiles=moves;
    }
    @Override
    protected void move() throws SnakeBiteException
    {
        SnakeBiteException exception= new SnakeBiteException("Hiss...! I am a Snake, you go back " + MoveTiles +" tiles!");
        throw (exception);
    }

}
