package Lab6;

import java.io.Serializable;

public class Player implements Serializable {
    private final int TotalTiles;
    private String name;
    private int Snakebites;
    private int Vulturebites;
    private int Cricketbites;
    private int TrampolinesOccurred;
    private long Dicerolls;

    Player(int TotalTiles)
    {
        this.TotalTiles=TotalTiles;
        this.Snakebites=0;
        this.Vulturebites=0;
        this.Cricketbites=0;
        this.TrampolinesOccurred=0;
        this.Dicerolls=1;
    }

    public void IncrementCricketbites() {
        Cricketbites += 1;
    }

    public void IncrementDicerolls() {
        Dicerolls += 1;
    }

    public void IncrementSnakebites() {
        Snakebites += 1;
    }

    public void IncrementTrampolinesOccurred() {
        TrampolinesOccurred += 1;
    }

    public void IncrementVulturebites() {
        Vulturebites += 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCricketbites() {
        return Cricketbites;
    }

    public long getDicerolls() {
        return Dicerolls;
    }

    public int getSnakebites() {
        return Snakebites;
    }

    public int getTotalTiles() {
        return TotalTiles;
    }

    public int getTrampolinesOccurred() {
        return TrampolinesOccurred;
    }

    public int getVulturebites() {
        return Vulturebites;
    }
    public void win() throws GameWinnerException {
        GameWinnerException exception=new GameWinnerException(name+" wins the race in "+this.getDicerolls());
        throw  exception;
    }
    public void get_details()
    {
        System.out.println("Total Snake Bites "+getSnakebites());
        System.out.println("Total Vulture Bites "+getVulturebites());
        System.out.println("Total Cricket Bites "+getCricketbites());
        System.out.println("Total Trampoline "+getTrampolinesOccurred());
    }
}
