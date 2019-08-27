
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.INTERNAL;

import java.util.Random;


class node
{
	private static int levelnosetter=1;
	private int monstertype;
	private int currentlevel;
	private Monster CurrentMonster;
	private LionFang lionFang;
	private node next1;
	private node next2;
	private node next3;
	private node parent;
	private static Random random=new Random();
	public node(int wins,node parent) {
		if(wins<3)
		{
			currentlevel=levelnosetter++;

			int val=random.nextInt(3);
			if(val==0)
			{
				monstertype=1;
				CurrentMonster=new Monster(100,1);
			}
			if(val==1)
			{
				monstertype=2;
				CurrentMonster=new Monster(150,2);
			}
			if(val==2)
			{
				monstertype=3;
				CurrentMonster=new Monster(200,3);
			}
			next1=null;
			next2=null;
			next3=null;
			this.parent=parent;			
		}
		else
		{
			currentlevel=levelnosetter++;

			int val=random.nextInt(4);
			if(val==0)
			{
				monstertype=1;
				CurrentMonster=new Monster(100,1);
			}
			if(val==1)
			{
				monstertype=2;
				CurrentMonster=new Monster(150,2);
			}
			if(val==2)
			{
				monstertype=3;
				CurrentMonster=new Monster(200,3);
			}
			if(val==3)
			{
				monstertype=4;
				lionFang=new LionFang();
			}
			next1=null;
			next2=null;
			next3=null;
			this.parent=parent;			
		}	
	}
	public void CurrentlevelComplted(int wins) {
		node newlevel=new node(wins, this);
		next1=newlevel;
		node newlevel2=new node(wins, this);
		next2=newlevel2;
		node newlevel3=new node(wins, this);
		next3=newlevel3;
	}
	public node getNext1() {
		return next1;
	}
	public node getNext2() {
		return next2;
	}
	public node getNext3() {
		return next3;
	}
	public int getCurrentlevel() {
		return currentlevel;
	}
	public int getMonstertype() {
		return monstertype;
	}
	public Monster getCurrentMonster() {
		return CurrentMonster;
	}
	public LionFang getLionFang() {
		return lionFang;
	}
}

class Graph
{
	node start;
	node currentlocation;
	public static Scanner in=new Scanner(System.in);
	public Graph() {
		start=new node(0, null);
		start.CurrentlevelComplted(0);
		currentlocation=start;
	}
	public void initialselection(user currentuser)
	{
		System.out.println("You are at Starting location. Choose path");
		System.out.println("1) Go to location"+currentlocation.getNext1().getCurrentlevel());
		System.out.println("1) Go to location"+currentlocation.getNext2().getCurrentlevel());
		System.out.println("1) Go to location"+currentlocation.getNext3().getCurrentlevel());
		System.out.println("Enter -1 to exit");
		int selection=in.nextInt();
		if(selection==1)
		{
			currentlocation=currentlocation.getNext1();
		}
		else if(selection==2)
		{
			currentlocation=currentlocation.getNext2();
		}
		else if(selection==3)
		{
			currentlocation=currentlocation.getNext3();
		} 
		else {
			return;
		}
		System.out.println("Fight started : You are fighting level "+currentlocation.getMonstertype()+" Monster");
		if(currentuser.getHerotype()==1)
		{
			
///////////////////////////////////////////////////////////////////////////////////////////
			
			currentuser.getAvtar1().fight(currentlocation.getCurrentMonster());
		}
		
	}
}

class user {
	private final String username;
	private final int Herotype;
	private Warrior avtar1;
	private Thief avtar2;
	private Mage avtar3;
	private Healer avtar4;
	private int wins=0;
	private Graph path=new Graph();
	public user(String username,Warrior avtar) 
	{
		// TODO Auto-generated constructor stub
		this.username=username;
		this.avtar1=avtar;
		this.Herotype=1;	
	}
	public user(String username,Thief avtar) 
	{
		// TODO Auto-generated constructor stub
		this.username=username;
		this.avtar2=avtar;
		this.Herotype=2;	
	}
	public user(String username,Mage avtar) 
	{
		// TODO Auto-generated constructor stub
		this.username=username;
		this.avtar3=avtar;
		this.Herotype=3;	
	}
	public user(String username,Healer avtar) 
	{
		// TODO Auto-generated constructor stub
		this.username=username;
		this.avtar4=avtar;
		this.Herotype=4;	
	}

	public String getUsername() {
		return username;
		}
	public int getHerotype() {
		return Herotype;
	}
	public Warrior getAvtar1() {
		return avtar1;
	}
	public Thief getAvtar2() {
		return avtar2;
	}
	public Mage getAvtar3() {
		return avtar3;
	}
	public Healer getAvtar4() {
		return avtar4;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins += wins;
	}
	public Graph getPath() {
		return path;
	}
}

class character {

	protected int HP;
	protected int AttackPower;
	protected int Attack()
	{
		return 0;
	}
	public int getHP() {
		return HP;
	}
	public void decreaseHP(int hP) {
		HP -= hP;
	}
	public void increaseHP(int hP) {
		HP += hP;
	}
	public int getAttackPower() {
		return AttackPower;
	
}
}


class Hero extends character
{
	protected int maxHP;
	protected int XP;
	protected int DefensePower;
	protected boolean SpecialPowerActivated;
	public static Scanner in=new Scanner(System.in);
	public Hero() {
		this.XP=0;
		this.maxHP=100;
		this.HP=100;
		this.SpecialPowerActivated=false;
		}
	protected void defense(Monster Opponent)
	{
		if(Opponent.getAttackPower()>=DefensePower)
			 HP=HP-Opponent.getAttackPower()+DefensePower;	 
	}
	protected int Attack(Monster Opponent)
	{
		return this.AttackPower;
	}
	public void setSpecialPowerActivated(boolean specialPowerActivated) {
		SpecialPowerActivated = specialPowerActivated;
	}
	public void increaseAttackPower(int attackPower) {
		AttackPower += attackPower;
	}
	public void increaseDefensePower(int defensePower) {
		DefensePower += defensePower;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
}


class Monster extends character{
	protected int Level;
//	private Monster Goblin=new Monster(100, 1);
//	private Monster Zombies=new Monster(100, 1);
//	private Monster Goblin=new Monster(100, 1);

	public Monster() {
		this.HP=-1;
		this.Level=-1;
	}
	public Monster(int HP,int Level) 
	{
		// TODO Auto-generated constructor stub
		this.HP=HP;
		this.Level=Level;
	}
	public int GetAttack(Hero Player)
	{
		int MinVal=0;
		int MaxVal=(int)(this.getHP()*.25);
		int mean=(int)(MinVal+MaxVal)/2;
		Random random=new Random();
		int val=-1;
		while(val>=MinVal && val<=MaxVal)
		{
			val=(int)Math.round(random.nextGaussian())+mean;
		}
		
		return val;
		
	}
	
}
class Warrior extends Hero
{
	private int DefenceSpecialBonus;
	private int AttackSpecialBonus;
	 Warrior() {
		// TODO Auto-generated constructor stub
		this.AttackPower=10;
		this.DefensePower=3;
		this.DefenceSpecialBonus=5;
		this.AttackSpecialBonus=5;
	}
	 @Override
	protected void defense(Monster Opponent)
	{
		 if(SpecialPowerActivated==true)
		 {
			 if(Opponent.getAttackPower()>=DefensePower+AttackSpecialBonus)
				 HP=HP-Opponent.getAttackPower()+DefensePower+AttackSpecialBonus;
		 }
		 
		 if(Opponent.getAttackPower()>=DefensePower)
			 HP=HP-Opponent.getAttackPower()+DefensePower;	 
	}
	 @Override
	 protected int Attack(Monster Opponent)
	 {
		 if(SpecialPowerActivated==true)
			 return(AttackPower+AttackSpecialBonus);
		 else
			 return(AttackPower);
	 }
	public boolean fight(Monster Opponent)
		{
			int moves=0;
			int noofspecialmoves=0;
			while(this.HP>0 && Opponent.getHP()>0)
			{
				System.out.println("Choose Move");
				System.out.println("1) Attack");
				System.out.println("2) Defense");
				if(moves>3)
				{
					System.out.println("3) Special Move");
				}
				
				int val=in.nextInt();
				if(val==1)
				{
					System.out.println("You choose to attack");
					System.out.println("You attacked and inflicted "+this.Attack(Opponent)+" damage to the monster");
					Opponent.decreaseHP(this.Attack(Opponent));
					System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());
					System.out.println("Monster Attack !");
					int opponentattackpower=Opponent.getAttackPower();
					System.out.println("The Monster attacked and inflicted "+opponentattackpower+" damage to you");
					this.decreaseHP(opponentattackpower);
					System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());			
					if(SpecialPowerActivated==true)

						noofspecialmoves++;
					else
						moves++;

				}
				else if(val==2)
				{
					System.out.println("You choose to defend");
					System.out.println("Monster Attack reduced by "+this.DefensePower);
					int opponentattackpower=Opponent.getAttackPower();
					if(opponentattackpower-DefensePower<0)
						opponentattackpower=0;
					
					System.out.println("The Monster attacked and inflicted "+opponentattackpower+" damage to you");
					this.decreaseHP(opponentattackpower);
					System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());			
					moves++;
					if(SpecialPowerActivated==true)

						noofspecialmoves++;
					else
						moves++;

				}
				else if(val==3 && moves>3)
				{
					SpecialPowerActivated=true;
					moves=0;
				}
				else
				{
					System.out.println("Wrong selection");
				}
				if(noofspecialmoves>=3)
				{
					noofspecialmoves=0;
					SpecialPowerActivated=false;
				}
			}
			if(Opponent.HP==0)
				return true;
			else
				return false;
		}

}

class Thief extends Hero
{
	private float StealingPower=.30f;
	Thief() {
		// TODO Auto-generated constructor stub
		this.AttackPower=6;
		this.DefensePower=4;

	 }
	private void SpecialPower(Monster Opponent)
		 {
		
				///check for max health here
				int steal=(int)Math.round(Opponent.getHP()*StealingPower);
				Opponent.decreaseHP(steal);
				this.increaseHP(steal);
		 }
}


class Mage extends Hero
{
	private float SpecialBonus; 
	Mage() {
		// TODO Auto-generated constructor stub
		this.AttackPower=5;
		this.DefensePower=5;
		this.SpecialBonus=.05f;
	}
	@Override
	protected void defense(Monster Opponent)
	{
		 if(SpecialPowerActivated==true)
		 {
			 if(Opponent.getAttackPower()>=DefensePower)
				 HP=HP-Opponent.getAttackPower()+DefensePower;
			 
			 
			 Opponent.decreaseHP(Math.round(Opponent.getHP()*SpecialBonus));
		 }
		 if(Opponent.getAttackPower()>=DefensePower)
			 HP=HP-Opponent.getAttackPower()+DefensePower;	 
	}
	@Override
	 protected int Attack(Monster Opponent)
	 {
		 if(SpecialPowerActivated==true)
			 return(AttackPower+Math.round(SpecialBonus*Opponent.getHP()));
		 else
			 return(AttackPower);
	 }
}


class Healer extends Hero
{
	
	private float HealPower=.05f;
	Healer() {
		// TODO Auto-generated constructor stub
		this.AttackPower=4;
		this.DefensePower=8;

	 }
	private void SpecialPower()
		 {
				// Check for the max health hereeeeeeeee
			///
		///
		
		
				this.increaseHP((int)Math.round(this.getHP()*HealPower));
		 }
}




class LionFang extends Monster{

	public LionFang() {
		// TODO Auto-generated constructor stub
		this.HP=250;
		this.Level=4;
	}
	@Override
	public int GetAttack(Hero player) {
		Random randomval=new Random();
		int val=randomval.nextInt(10);
		if(val==0)
		{
			return(Math.round(player.getHP()/2));
		}
		return super.GetAttack(player);
	}
}

 public class Game {
	
 	public Monster Goblin=new Monster(100,1);
 	public Monster Zombies=new Monster(150,2);
 	public Monster Fiends=new Monster(200,3);
 	public LionFang lionFang=new LionFang();
 	public static ArrayList<user> users=new ArrayList<>();
	public static user finduser(String username)
	{
		for(int i=0;i<users.size();i++)
		{
			if(users.get(i).getUsername().equals(username))
				return(users.get(i));
		}
		return null;
	}
 	public static void main(String[] args) {
 		Scanner in=new Scanner(System.in);
 		int selection=0;
 		while(selection!=3)
 		{
 			System.out.println("Welcome to ArchLegends");
 			System.out.println("Choose Option");
 			System.out.println("1) New User");
 			System.out.println("2) Existing User");
 			System.out.println("3) Exit");
 			selection=in.nextInt();
 			if(selection==1)
 			{
 				System.out.println("Enter Username");
 				String username=in.next();
 				System.err.println("Choose Your Hero");
 				System.out.println("1) Warrior");
 				System.out.println("2) Thief");
 				System.out.println("3) Mage");
 				System.out.println("4) Healer");
 				int hero=in.nextInt();
 				if(hero==1)
 				{
 					users.add(new user(username, new Warrior()));
 	 				System.out.println("User creation done : Username is :"+username+" Hero Type : Warrior   Log in to play the game");
 				}
 				if(hero==2)
 				{
 					users.add(new user(username, new Thief()));
 	 				System.out.println("User creation done : Username is :"+username+" Hero Type : Thief  Log in to play the game");

 				}
 				if(hero==3)
 				{
 					users.add(new user(username, new Mage()));
 	 				System.out.println("User creation done : Username is :"+username+" Hero Type : Mage  Log in to play the game");

 				}
 				if(hero==4)
 				{
 					users.add(new user(username, new Healer()));
 	 				System.out.println("User creation done : Username is :"+username+" Hero Type : Healer  Log in to play the game");
 				}
 			}
 			else if (selection==2) 
 			{
 				System.out.println("Enter Username");
 				String username=in.next();
 				user currentuser=finduser(username);
 				if(currentuser!=null)
 				{
 					System.out.println("User found... Logging in");
 					currentuser.getPath().initialselection(currentuser);
 				}
 			}
 			else {
				break;
			}
 			
 		}	
 	}
 }
