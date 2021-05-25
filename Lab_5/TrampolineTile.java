public class TrampolineTile extends Tile{
     TrampolineTile(int moves)
    {
        this.MoveTiles=moves;
    }
    @Override
    protected void move() throws TrampolineException
    {
        System.out.println("            PingPong! I am a Trampoline, you advance " + MoveTiles +" tiles!");
        TrampolineException exception= new TrampolineException("");
        throw (exception);
    }
}
