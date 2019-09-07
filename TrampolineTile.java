package Lab5;
public class TrampolineTile extends Tile{
     TrampolineTile(int moves)
    {
        this.MoveTiles=moves;
    }
    @Override
    protected void move() throws TrampolineException
    {
        TrampolineException exception= new TrampolineException("PingPong! I am a Trampoline, you advance " + MoveTiles +" tiles!");
        throw (exception);
    }
}
