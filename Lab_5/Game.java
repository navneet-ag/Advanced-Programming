
import java.util.Currency;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    private static Random rn=new Random();
    private static Scanner in=new Scanner(System.in);
    private Tile[] Track;
    private Boolean[] Trackobstacle;
    private int SnakeTiles;
    private int VultureTiles;
    private int CricketTiles;
    private int TrampolineTiles;
    private int WhiteTiles;
    private int SnakeTilesMovement;
    private int VultureTilesMovement;
    private int CricketTilesMovement;
    private int TrampolineTilesMovement;
    private int CurrentTileNumber;
    private boolean FreeStatus;
    private static Player player;
    private static Dice mydice=new Dice();
    Game(int value)
    {
        player=new Player(value);
        SnakeTiles=0;
        VultureTiles=0;
        CricketTiles=0;
        TrampolineTiles=0;
        WhiteTiles=0;
        Track=new Tile[value];
        CurrentTileNumber=1;
        FreeStatus=false;
        Trackobstacle=new Boolean[value];
    }
    private static int gettilesmovement()
    {
        int digits=0;
        int num=player.getTotalTiles();
        while(num>0)
        {
            num=num/10;
            digits++;
        }
        digits=digits-2;
        int MaxLimit=digits*10;
        if(MaxLimit==0 || MaxLimit<0)
            MaxLimit=1;
        return (1+rn.nextInt(MaxLimit));
    }
    private int diceroll()
    {
        return (1+rn.nextInt(6));
    }
    private void InitialSetup()
    {
        System.out.println("Setting up the race track...");
        for(int i=0;i<Trackobstacle.length;i++)
            {
                Trackobstacle[i]=false;
                Track[i]=new WhiteTile();
            }

        Track[0]= new WhiteTile();
        int i=5,temp;
        SnakeTilesMovement=gettilesmovement();
        VultureTilesMovement=gettilesmovement();
        CricketTilesMovement=gettilesmovement();
        TrampolineTilesMovement=gettilesmovement();

         while(i<Track.length-1)
        {
            temp=rn.nextInt(8);
            if(temp<4 || Trackobstacle[i]==true)
            {
                Track[i]=new WhiteTile();
            }
            else if(temp==4) {
                    Track[i] = new SnakeTile(SnakeTilesMovement);
                    SnakeTiles++;
            }
            else if(temp==5)
            {
                    Track[i] = new CricketTile(CricketTilesMovement);
                    CricketTiles++;
            }
            else if(temp==6)
            {
                    Track[i]=new VultureTile(VultureTilesMovement);
                    VultureTiles++;
            }
            else if(temp==7)
            {
                Track[i]=new TrampolineTile(TrampolineTilesMovement);
                TrampolineTiles++;
                try
                {
                    if(i+TrampolineTilesMovement<player.getTotalTiles()-1)
                        Trackobstacle[i+TrampolineTilesMovement]=true;
                }
                catch (IndexOutOfBoundsException e)
                {
                    continue;
                }
            }
            i+=3;
        }
        System.out.println("Danger: There are "+ SnakeTiles +","+ CricketTiles+"," + VultureTiles +" numbers of Snakes, Cricket, and Vultures respectively on your track!");
        System.out.println("Danger: Each Snake, Cricket, and Vultures can throw you back by "+SnakeTilesMovement+","+ CricketTilesMovement+","+ VultureTilesMovement +","+" number of Tiles respectively!");
        System.out.println("Good News: There are "+TrampolineTiles +" number of Trampolines on your track!");
        System.out.println("Good News: Each Trampoline can help you advance by "+ TrampolineTilesMovement+" number of Tiles");
        Boolean condition=false;
        String name="";
        //Error handling of string
        while(!condition)
        {
            System.out.println("Enter player name");
            try{
                name=in.next();
                condition=true;
            }
            catch(InputMismatchException e) {
                System.out.println("Wrong input");
                System.out.println("Try Again");
            }
        }
        player.setName(name);
        CurrentTileNumber=1;

    }
    private  void play()
    {
        System.out.println("Starting the game with "+ player.getName()+" at Tile- " +CurrentTileNumber );
        System.out.println("Control transferred to Computer for rolling the Dice for "+ player.getName());
        Boolean condition=false;
        String hitenter="nothit";
        while(!condition)
        {
            System.out.println("Hit enter to start the game");
            try{
                Scanner input=new Scanner(System.in);
                hitenter=input.nextLine();
                if(hitenter.equals(""))
                    condition=true;
            }
            catch(InputMismatchException e) {
                System.out.println("Wrong input");
                System.out.println("Try Again");
            }
        }
        //Check when enter is hit
        if(hitenter.equals("")) {

            System.out.println("Enter hit successfully");
            System.out.println("Game Started ======================>");
            int DiceHead=0;
            while (CurrentTileNumber != player.getTotalTiles() ) {
                DiceHead=mydice.diceroll();
                if(CurrentTileNumber==1 && FreeStatus==false && DiceHead==6) {
                    System.out.println("[Roll-" + player.getDicerolls() + "]: " + player.getName() + " rolled " + DiceHead + " at Tile-" + CurrentTileNumber + ", You are out of the cage! You get a free roll");
                    FreeStatus=true;
                }
                else if(CurrentTileNumber==1 && FreeStatus==false )
                    System.out.println("[Roll-" + player.getDicerolls() + "]: " + player.getName() + " rolled " + DiceHead+" at Tile-"+CurrentTileNumber+", OOPs you need 6 to start");
                else
                {
                    int temp=CurrentTileNumber;
                    if(CurrentTileNumber+DiceHead<=player.getTotalTiles())
                    {
                        temp=CurrentTileNumber+DiceHead;
                    }
                    System.out.println("[Roll-" + player.getDicerolls() + "]: " + player.getName() + " rolled " + DiceHead+" at Tile-"+CurrentTileNumber+", landed on Tile-"+temp);
                    CurrentTileNumber=temp;
                }
                if(Track[CurrentTileNumber-1]  instanceof  WhiteTile && CurrentTileNumber!=player.getTotalTiles())
                {
                    System.out.println("            Trying to shake the Tile-"+ CurrentTileNumber);
                    System.out.println("            This is a white tile");
                }
                while( !(Track[CurrentTileNumber-1]  instanceof  WhiteTile) && CurrentTileNumber!=1 && CurrentTileNumber!=player.getTotalTiles()) {
//                    System.out.println(!(Track[CurrentTileNumber]  instanceof  WhiteTile) +"   "+ (CurrentTileNumber!=1));
                    System.out.println("            Trying to shake the Tile-" + CurrentTileNumber);
                    if (Track[CurrentTileNumber - 1] instanceof SnakeTile) {
                        SnakeTile snake = (SnakeTile) Track[CurrentTileNumber - 1];
                        try {
                            snake.move();
                        } catch (SnakeBiteException ex) {
                            System.out.println("            "+ex);
                            int temp = CurrentTileNumber - Track[CurrentTileNumber - 1].getMoveTiles();
                            if (temp < 1) {
                                System.out.println(player.getName()+" moved to Tile 1 as it can’t go back further");
                                FreeStatus = false;
                                CurrentTileNumber = 1;
                            } else if (temp == 1) {
                                System.out.println(player.getName()+" moved to Tile 1");
                                FreeStatus = false;
                                CurrentTileNumber = 1;
                            } else {
                                System.out.println(player.getName()+" moved to Tile " + temp);
                                CurrentTileNumber = temp;
                            }
                            player.IncrementSnakebites();
                        }
                    } else if (Track[CurrentTileNumber - 1] instanceof VultureTile) {
                        VultureTile vulture = (VultureTile) Track[CurrentTileNumber - 1];

                        try {
                            vulture.move();
                        } catch (VultureBiteException ex) {
                            System.out.println("            "+ex);
                            int temp = CurrentTileNumber - Track[CurrentTileNumber - 1].getMoveTiles();
                            if (temp < 1) {
                                System.out.println(player.getName()+" moved to Tile 1 as it can’t go back further");
                                FreeStatus = false;
                                CurrentTileNumber = 1;
                            } else if (temp == 1) {
                                System.out.println(player.getName()+" moved to Tile 1");
                                FreeStatus = false;
                                CurrentTileNumber = 1;
                            } else {
                                System.out.println(player.getName()+" moved to Tile " + temp);
                                CurrentTileNumber = temp;
                            }
                            player.IncrementVulturebites();
                        }
                    } else if (Track[CurrentTileNumber - 1] instanceof CricketTile) {
                        CricketTile Cricket = (CricketTile) Track[CurrentTileNumber - 1];

                        try {
                            Cricket.move();
                        } catch (CricketBiteException ex) {
                            System.out.println("            "+ex);
                            int temp = CurrentTileNumber - Track[CurrentTileNumber - 1].getMoveTiles();
                            if (temp < 1) {
                                System.out.println(player.getName()+" moved to Tile 1 as it can’t go back further");
                                FreeStatus = false;
                                CurrentTileNumber = 1;
                            } else if (temp == 1) {
                                System.out.println(player.getName()+" moved to Tile 1");
                                FreeStatus = false;
                                CurrentTileNumber = 1;
                            } else {
                                System.out.println(player.getName()+" moved to Tile " + temp);
                                CurrentTileNumber = temp;
                            }
                            player.IncrementCricketbites();
                        }
                    }
                    //If cant jump from a trampoline suppoose at 98 the loop will keep iterating there
                    else if (Track[CurrentTileNumber - 1] instanceof TrampolineTile) {
                        TrampolineTile Trampoline = (TrampolineTile) Track[CurrentTileNumber - 1];
                        try {
                            Trampoline.move();
                        } catch (TrampolineException ex) {
                            System.out.println("            "+ex);
                            int temp = CurrentTileNumber + Track[CurrentTileNumber - 1].getMoveTiles();
                            if (temp > player.getTotalTiles()) {
                                System.out.println(player.getName()+" moved to Tile-" + CurrentTileNumber + "as it can’t Jump further");
                                break;
                            } else if (temp == player.getTotalTiles()) {
                                System.out.println(player.getName()+" moved to Tile-"+player.getTotalTiles());
                                CurrentTileNumber = 1;
                                break;
                            } else {
                                System.out.println(player.getName()+" moved to Tile " + temp);
                                CurrentTileNumber = temp;
                            }
                            player.IncrementTrampolinesOccurred();
                        }
                    }
                    else
                    {
                        System.out.println("This is a white tile");
                        break;
                    }
                    if(CurrentTileNumber==player.getTotalTiles())
                        break;
                }
                if(CurrentTileNumber!=player.getTotalTiles())
                    player.IncrementDicerolls();
                //The below 6 lines can be de-commented so as to check each roll with a delay
//                    try
//                    {
//                        TimeUnit.MILLISECONDS.sleep(1000);
//
//                    }
//                    catch (InterruptedException e)
//                    {
//                        System.out.println(e);
//                    }
            }
            try {
                player.win();
            }
            catch (GameWinnerException e)
            {
                System.out.println(e);
                player.get_details();
            }
        }
    }
    public static void check(int val) throws NegativeValueException,ZeroValueException
    {
        if(val<0)
            throw (new NegativeValueException("Track size cannot be negative"));
        else if(val==0)
            throw (new ZeroValueException("Track size cant be zero"));
        else if(val>9999999)
            throw (new OutOfMemoryError());
    }
    public static void main(String[] args) throws InputMismatchException
    {
        Boolean condition=false;
        int TotalTiles=0;

        while(!condition)
        {
            System.out.println("Enter total number of tiles on the track(length)");
            try{
                Scanner input=new Scanner(System.in);
                TotalTiles=input.nextInt();
                check(TotalTiles);
                condition=true;
            }
            catch(InputMismatchException e) {
            System.out.println("Wrong input");
            }
            catch (NegativeValueException e)
            {
                System.out.println(e);
            }
            catch (ZeroValueException e)
            {
                System.out.println(e);
            }
            catch (OutOfMemoryError e)
            {
                System.out.println("Memory limit exceeded");
                System.out.println("Please enter a suitable track length");
            }
            finally {
                if(condition==false)
                    System.out.println("Try Again");

            }
        }
        Game currentgame=new Game(TotalTiles);
        currentgame.InitialSetup();
        currentgame.play();
    }
}
