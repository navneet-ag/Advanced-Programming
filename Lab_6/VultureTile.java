package Lab6;

public class VultureTile extends Tile {
     VultureTile(int moves)
    {
        this.MoveTiles=moves;
    }
    @Override
    protected void move() throws VultureBiteException
    {
        System.out.println("            "+"Yapping...! I am a Vulture, you go back " + MoveTiles +" tiles!");
        VultureBiteException exception= new VultureBiteException("");
        throw (exception);
    }

}
