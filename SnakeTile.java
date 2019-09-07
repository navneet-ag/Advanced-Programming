package Lab5;
public class SnakeTile extends Tile{

    SnakeTile(int moves)
    {
        this.MoveTiles=moves;
    }
    @Override
    protected void move() throws SnakeBiteException
    {
        System.out.println("Hiss...! I am a Snake, you go back " + MoveTiles +" tiles!");
        SnakeBiteException exception= new SnakeBiteException("Snake bit you !");
        throw (exception);
    }

}
