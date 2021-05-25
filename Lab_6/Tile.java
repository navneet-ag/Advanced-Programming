package Lab6;

import java.io.Serializable;

public abstract  class Tile implements Serializable {
    int MoveTiles;
    Tile()
    {
        MoveTiles=0;
    }

    public int getMoveTiles() {
        return MoveTiles;
    }

    public static void main(String[] args) throws SnakeBiteException {
        SnakeBiteException e= new SnakeBiteException("dsadsadas");
        try
        {
            throw e;
        }
        catch (SnakeBiteException f)
        {
            System.out.println(f.getMessage());
        }
        System.out.println("a dmas mld sa");
    }
    protected abstract void  move() throws SnakeBiteException, VultureBiteException, CricketBiteException, TrampolineException;
}
