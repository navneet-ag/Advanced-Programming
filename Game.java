
import java.util.ArrayList;
import java.util.Scanner;

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
	public void CurrentlevelComplted(int wins,node parent) {
		node newlevel=new node(wins, this);
		next1=newlevel;
		node newlevel2=new node(wins, this);
		next2=newlevel2;
		node newlevel3=new node(wins, this);
		next3=newlevel3;
		this.parent=parent;
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
	public node getParent() {
		return parent;
	}
}

class Graph
{
	node start;
	node currentlocation;
	Boolean startstatus;
	node previous;
	public static Scanner in=new Scanner(System.in);
	public Graph() {
		start=new node(0, null);
		start.CurrentlevelComplted(0,start);
		currentlocation=start;
		previous=null;
		startstatus=false;
	}
	public void initialselection(user currentuser)
	{
		System.out.println("You are at Starting location. Choose path");
		System.out.println("1) Go to location"+currentlocation.getNext1().getCurrentlevel());
		System.out.println("2) Go to location"+currentlocation.getNext2().getCurrentlevel());
		System.out.println("3) Go to location"+currentlocation.getNext3().getCurrentlevel());
		System.out.println("Enter -1 to exit");
		int selection=in.nextInt();
		if(selection==1)
		{
			previous=currentlocation;
			currentlocation=currentlocation.getNext1();
		}
		else if(selection==2)
		{
			previous=currentlocation;
			currentlocation=currentlocation.getNext2();
		}
		else if(selection==3)
		{
			previous=currentlocation;
			currentlocation=currentlocation.getNext3();
		} 
		else {
			return;
		}
		System.out.println("Fight started : You are fighting level "+currentlocation.getMonstertype()+" Monster");
		if(currentuser.getHerotype()==1)
		{
			
			this.setStartstatus(true);
			Boolean condition=currentuser.getAvtar1().fight(currentlocation.getCurrentMonster());
			if(condition==true)
			{	
				currentuser.setWins(1);
				System.out.println("Monster Killed");
				System.out.println(currentlocation.getCurrentMonster().Level*20 +"XP awarded");
				currentuser.getAvtar1().increaseXP(currentlocation.getCurrentMonster().Level*20);
				System.out.println("Level Up : "+(currentuser.getWins()+1));
				currentlocation.CurrentlevelComplted(currentuser.getWins(),previous);
				if(currentuser.getWins()==1)
					currentuser.getAvtar1().setMaxHP(100);
				else if(currentuser.getWins()==2)
					currentuser.getAvtar1().setMaxHP(150);
				else if(currentuser.getWins()==3)
					currentuser.getAvtar1().setMaxHP(200);
				else if(currentuser.getWins()==4)
					currentuser.getAvtar1().setMaxHP(250);
				currentuser.getAvtar1().setHP(currentuser.getAvtar1().getMaxHP());
				currentuser.getAvtar1().increaseAttackPower(1);
				currentuser.getAvtar1().increaseDefensePower(1);
				currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
				this.nextselections(currentuser);
				
			}
			else
			{
				currentuser.getAvtar1().setHP(currentuser.getAvtar1().getMaxHP());
				currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
				System.out.println("You Lost you need to start again");
				currentlocation=start;
			}
		}
		else if(currentuser.getHerotype()==2)
		{
			
			this.setStartstatus(true);
			Boolean condition=currentuser.getAvtar2().fight(currentlocation.getCurrentMonster());
			if(condition==true)
			{	
				currentuser.setWins(1);
				System.out.println("Monster Killed");
				System.out.println(currentlocation.getCurrentMonster().Level*20 +"XP awarded");
				currentuser.getAvtar2().increaseXP(currentlocation.getCurrentMonster().Level*20);
				System.out.println("Level Up : "+(currentuser.getWins()+1));
				currentlocation.CurrentlevelComplted(currentuser.getWins(),previous);
				if(currentuser.getWins()==1)
					currentuser.getAvtar2().setMaxHP(100);
				else if(currentuser.getWins()==2)
					currentuser.getAvtar2().setMaxHP(150);
				else if(currentuser.getWins()==3)
					currentuser.getAvtar2().setMaxHP(200);
				else if(currentuser.getWins()==4)
					currentuser.getAvtar2().setMaxHP(250);
				currentuser.getAvtar2().setHP(currentuser.getAvtar2().getMaxHP());
				currentuser.getAvtar2().increaseAttackPower(1);
				currentuser.getAvtar2().increaseDefensePower(1);

				currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
				this.nextselections(currentuser);
				
			}
			else
			{
				currentuser.getAvtar2().setHP(currentuser.getAvtar2().getMaxHP());
				currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
				System.out.println("You Lost you need to start again");
				currentlocation=start;
			}
		}
		if(currentuser.getHerotype()==3)
		{
			
			this.setStartstatus(true);
			Boolean condition=currentuser.getAvtar3().fight(currentlocation.getCurrentMonster());
			if(condition==true)
			{	
				currentuser.setWins(1);
				System.out.println("Monster Killed");
				System.out.println(currentlocation.getCurrentMonster().Level*20 +"XP awarded");
				currentuser.getAvtar3().increaseXP(currentlocation.getCurrentMonster().Level*20);
				System.out.println("Level Up : "+(currentuser.getWins()+1));
				currentlocation.CurrentlevelComplted(currentuser.getWins(),previous);
				if(currentuser.getWins()==1)
					currentuser.getAvtar3().setMaxHP(100);
				else if(currentuser.getWins()==2)
					currentuser.getAvtar3().setMaxHP(150);
				else if(currentuser.getWins()==3)
					currentuser.getAvtar3().setMaxHP(200);
				else if(currentuser.getWins()==4)
					currentuser.getAvtar3().setMaxHP(250);
				currentuser.getAvtar3().setHP(currentuser.getAvtar3().getMaxHP());
				currentuser.getAvtar3().increaseAttackPower(1);
				currentuser.getAvtar3().increaseDefensePower(1);

				currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
				this.nextselections(currentuser);
				
			}
			else
			{
				currentuser.getAvtar3().setHP(currentuser.getAvtar3().getMaxHP());
				currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
				System.out.println("You Lost you need to start again");
				currentlocation=start;
			}
		}
		if(currentuser.getHerotype()==4)
		{
			
			this.setStartstatus(true);
			Boolean condition=currentuser.getAvtar4().fight(currentlocation.getCurrentMonster());
			if(condition==true)
			{	
				currentuser.setWins(1);
				System.out.println("Monster Killed");
				System.out.println(currentlocation.getCurrentMonster().Level*20 +"XP awarded");
				currentuser.getAvtar4().increaseXP(currentlocation.getCurrentMonster().Level*20);
				System.out.println("Level Up : "+(currentuser.getWins()+1));
				currentlocation.CurrentlevelComplted(currentuser.getWins(),previous);
				if(currentuser.getWins()==1)
					currentuser.getAvtar4().setMaxHP(100);
				else if(currentuser.getWins()==2)
					currentuser.getAvtar4().setMaxHP(150);
				else if(currentuser.getWins()==3)
					currentuser.getAvtar4().setMaxHP(200);
				else if(currentuser.getWins()==4)
					currentuser.getAvtar4().setMaxHP(250);
				currentuser.getAvtar4().setHP(currentuser.getAvtar4().getMaxHP());
				currentuser.getAvtar4().increaseAttackPower(1);
				currentuser.getAvtar4().increaseDefensePower(1);

				currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
				this.nextselections(currentuser);
				
			}
			else
			{
				currentuser.getAvtar4().setHP(currentuser.getAvtar4().getMaxHP());
				currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
				System.out.println("You Lost you need to start again");
				currentlocation=start;
			}
		}

		
	
	}
	public void nextselections(user currentuser)
	{
		System.out.println("You are atlocation"+currentlocation.getCurrentlevel()+" Choose path");
		System.out.println("1) Go to location"+currentlocation.getNext1().getCurrentlevel());
		System.out.println("2) Go to location"+currentlocation.getNext2().getCurrentlevel());
		System.out.println("3) Go to location"+currentlocation.getNext3().getCurrentlevel());
		System.out.println("4)Go Back");
		System.out.println("Enter -1 to exit");
		int selection=in.nextInt();
		if(selection==1)
		{
			previous=currentlocation;
			currentlocation=currentlocation.getNext1();
		}
		else if(selection==2)
		{
			previous=currentlocation;
			currentlocation=currentlocation.getNext2();
		}
		else if(selection==3)
		{
			previous=currentlocation;
			currentlocation=currentlocation.getNext3();
		} 
		else if(selection==4)
		{
			previous=currentlocation;
			currentlocation=currentlocation.getParent();
			this.nextselections(currentuser);
			return;
		}
		else {
			return;
		}
		System.out.println("Fight started : You are fighting level "+currentlocation.getMonstertype()+" Monster");
		
		if(currentlocation.getMonstertype()!=4)
		{
				if(currentuser.getHerotype()==1)
				{	
		///////////////////////////////////////////////////////////////////////////////////////////	
					Boolean condition=currentuser.getAvtar1().fight(currentlocation.getCurrentMonster());
					if(condition==true)
					{	
						System.out.println("Monster Killed");
						if(currentlocation.getNext1()==null)
						{
							currentuser.setWins(1);
							System.out.println("Level Up : "+(currentuser.getWins()+1));
							System.out.println(currentlocation.getCurrentMonster().Level*20 +"XP awarded");
							currentuser.getAvtar1().increaseXP(currentlocation.getCurrentMonster().Level*20);
							System.out.println("Creating new locations");
							currentlocation.CurrentlevelComplted(currentuser.getWins(),previous);
						}
						if(currentuser.getWins()==1)
							currentuser.getAvtar1().setMaxHP(100);
						else if(currentuser.getWins()==2)
							currentuser.getAvtar1().setMaxHP(150);
						else if(currentuser.getWins()==3)
							currentuser.getAvtar1().setMaxHP(200);
						else if(currentuser.getWins()==4)
							currentuser.getAvtar1().setMaxHP(250);
						currentuser.getAvtar1().setHP(currentuser.getAvtar1().getMaxHP());
						currentuser.getAvtar1().increaseAttackPower(1);
						currentuser.getAvtar1().increaseDefensePower(1);
		
						currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
						this.nextselections(currentuser);
					}
					else
					{
						currentuser.getAvtar1().setHP(currentuser.getAvtar1().getMaxHP());
						currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
						System.out.println("You Lost you need to start again");	
						currentlocation=currentlocation.getParent();
					}
				}
				else if(currentuser.getHerotype()==2)
				{
					
					this.setStartstatus(true);
					Boolean condition=currentuser.getAvtar2().fight(currentlocation.getCurrentMonster());
					if(condition==true)
					{	
						System.out.println("Monster Killed");
						if(currentlocation.getNext1()==null)
						{
							currentuser.setWins(1);
							System.out.println("Level Up : "+(currentuser.getWins()+1));
							System.out.println(currentlocation.getCurrentMonster().Level*20 +"XP awarded");
							currentuser.getAvtar1().increaseXP(currentlocation.getCurrentMonster().Level*20);
							System.out.println("Creating new locations");
							currentlocation.CurrentlevelComplted(currentuser.getWins(),previous);
						}
							if(currentuser.getWins()==1)
							currentuser.getAvtar2().setMaxHP(100);
						else if(currentuser.getWins()==2)
							currentuser.getAvtar2().setMaxHP(150);
						else if(currentuser.getWins()==3)
							currentuser.getAvtar2().setMaxHP(200);
						else if(currentuser.getWins()==4)
							currentuser.getAvtar2().setMaxHP(250);
						currentuser.getAvtar2().setHP(currentuser.getAvtar2().getMaxHP());
						currentuser.getAvtar2().increaseAttackPower(1);
						currentuser.getAvtar2().increaseDefensePower(1);
		
						currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
						this.nextselections(currentuser);
						
					}
					else
					{
						currentuser.getAvtar2().setHP(currentuser.getAvtar2().getMaxHP());
						currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
						System.out.println("You Lost you need to start again");
						currentlocation=currentlocation.getParent();
					}
				}
				if(currentuser.getHerotype()==3)
				{
					
		///////////////////////////////////////////////////////////////////////////////////////////
					
					Boolean condition=currentuser.getAvtar3().fight(currentlocation.getCurrentMonster());
					if(condition==true)
					{	
						System.out.println("Monster Killed");
						System.out.println("Level Up : "+(currentuser.getWins()+1));
						if(currentlocation.getNext1()==null)
						{
							currentuser.setWins(1);
							System.out.println("Level Up : "+(currentuser.getWins()+1));
							System.out.println(currentlocation.getCurrentMonster().Level*20 +"XP awarded");
							currentuser.getAvtar1().increaseXP(currentlocation.getCurrentMonster().Level*20);
							System.out.println("Creating new locations");
							currentlocation.CurrentlevelComplted(currentuser.getWins(),previous);
						}
							if(currentuser.getWins()==1)
							currentuser.getAvtar3().setMaxHP(100);
						else if(currentuser.getWins()==2)
							currentuser.getAvtar3().setMaxHP(150);
						else if(currentuser.getWins()==3)
							currentuser.getAvtar3().setMaxHP(200);
						else if(currentuser.getWins()==4)
							currentuser.getAvtar3().setMaxHP(250);
						currentuser.getAvtar3().setHP(currentuser.getAvtar3().getMaxHP());
						currentuser.getAvtar3().increaseAttackPower(1);
						currentuser.getAvtar3().increaseDefensePower(1);
		
						currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
						this.nextselections(currentuser);
					}
					else
					{
						currentuser.getAvtar3().setHP(currentuser.getAvtar3().getMaxHP());
						currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
						System.out.println("You Lost you need to start again");	
						currentlocation=currentlocation.getParent();
					}
				}
		
				if(currentuser.getHerotype()==4)
				{
										
					Boolean condition=currentuser.getAvtar4().fight(currentlocation.getCurrentMonster());
					if(condition==true)
					{	
						System.out.println("Monster Killed");
						System.out.println("Level Up : "+(currentuser.getWins()+1));
						if(currentlocation.getNext1()==null)
						{
							currentuser.setWins(1);
							System.out.println("Level Up : "+(currentuser.getWins()+1));
							System.out.println(currentlocation.getCurrentMonster().Level*20 +"XP awarded");
							currentuser.getAvtar1().increaseXP(currentlocation.getCurrentMonster().Level*20);
							System.out.println("Creating new locations");
							currentlocation.CurrentlevelComplted(currentuser.getWins(),previous);
						}
							if(currentuser.getWins()==1)
							currentuser.getAvtar4().setMaxHP(100);
						else if(currentuser.getWins()==2)
							currentuser.getAvtar4().setMaxHP(150);
						else if(currentuser.getWins()==3)
							currentuser.getAvtar4().setMaxHP(200);
						else if(currentuser.getWins()==4)
							currentuser.getAvtar4().setMaxHP(250);
						currentuser.getAvtar4().setHP(currentuser.getAvtar4().getMaxHP());
						currentuser.getAvtar4().increaseAttackPower(1);
						currentuser.getAvtar4().increaseDefensePower(1);
		
						currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
						this.nextselections(currentuser);
					}
					else
					{
						currentuser.getAvtar4().setHP(currentuser.getAvtar4().getMaxHP());
						currentlocation.getCurrentMonster().setHP(currentlocation.getCurrentMonster().getMaxHP());
						System.out.println("You Lost you need to start again");	
						currentlocation=currentlocation.getParent();
					}
				}
		}
		else {
			if(currentuser.getHerotype()==1)
			{	
	///////////////////////////////////////////////////////////////////////////////////////////	
				Boolean condition=currentuser.getAvtar1().fight(currentlocation.getLionFang());
				if(condition==true)
				{	
					currentuser.setWins(1);
					System.out.println("LionFang Killed");
					System.out.println("You Won");
				}
				else
				{
					currentuser.getAvtar1().setHP(currentuser.getAvtar1().getMaxHP());
					currentlocation.getLionFang().setHP(currentlocation.getLionFang().getMaxHP());
					System.out.println("You Lost you need to start again");	
					currentlocation=currentlocation.getParent();
				}
			}
			else if(currentuser.getHerotype()==2)
			{
				this.setStartstatus(true);
				Boolean condition=currentuser.getAvtar2().fight(currentlocation.getLionFang());
				if(condition==true)
				{	
					currentuser.setWins(1);
					System.out.println("LionFang Killed");
					System.out.println("You Won");					
				}
				else
				{
					currentuser.getAvtar2().setHP(currentuser.getAvtar2().getMaxHP());
					currentlocation.getLionFang().setHP(currentlocation.getLionFang().getMaxHP());
					System.out.println("You Lost you need to start again");
					currentlocation=currentlocation.getParent();
				}
			}
			if(currentuser.getHerotype()==3)
			{
				
	///////////////////////////////////////////////////////////////////////////////////////////
				
				Boolean condition=currentuser.getAvtar3().fight(currentlocation.getLionFang());
				if(condition==true)
				{	
					currentuser.setWins(1);
					System.out.println("LionFang Killed");
					System.out.println("You Won");
				}
				else
				{
					currentuser.getAvtar3().setHP(currentuser.getAvtar3().getMaxHP());
					currentlocation.getLionFang().setHP(currentlocation.getLionFang().getMaxHP());
					System.out.println("You Lost you need to start again");	
					currentlocation=currentlocation.getParent();
				}
			}
	
			if(currentuser.getHerotype()==4)
			{
				
	///////////////////////////////////////////////////////////////////////////////////////////
				
				Boolean condition=currentuser.getAvtar4().fight(currentlocation.getLionFang());
				if(condition==true)
				{	
					currentuser.setWins(1);
					System.out.println("LionFang Killed");
					System.out.println("You Won");
				}
				else
				{
					currentuser.getAvtar4().setHP(currentuser.getAvtar4().getMaxHP());
					currentlocation.getLionFang().setHP(currentlocation.getLionFang().getMaxHP());
					System.out.println("You Lost you need to start again");	
					currentlocation=currentlocation.getParent();
				}
			}
			
		}


	}
	public Boolean getStartstatus() {
		return startstatus;
	}
	public void setStartstatus(Boolean startstatus) {
		this.startstatus = startstatus;
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
	protected int MaxHP;
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
		if(HP<0)
			HP=0;
	}
	public void increaseHP(int hP) {
		HP += hP;
		if(HP>MaxHP)
			HP=MaxHP;
		
	}
	public void setHP(int hP) {
		HP = MaxHP;
	}
	public void setMaxHP(int maxHP) {
		MaxHP = maxHP;
	}
	public int getMaxHP() {
		return MaxHP;
	}
	public int getAttackPower() {
		return AttackPower;
	}
	
}


class Hero extends character
{
	protected int XP;
	protected int DefensePower;
	protected boolean SpecialPowerActivated;
	public static Scanner in=new Scanner(System.in);
	public Hero() {
		this.XP=0;
		this.MaxHP=100;
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
	public void increaseXP(int xP) {
		XP += xP;
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
		this.MaxHP=HP;
	}
	public int GetAttack(Hero Player)
	{
		int MinVal=0;
		int MaxVal=(int)(this.getHP()*.25);
		int mean=(int)(MaxVal)/2;
		Random random=new Random();
		int val=-1;
		if(mean==0)
			return 0;
		int standarddeviation=(int)Math.round(Math.sqrt(MaxVal*((MaxVal+2)/12)));
		while(val<MinVal || val>MaxVal)
		{
			val=(int)Math.round(random.nextGaussian()*standarddeviation)+mean;
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
				if(moves>=3)
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
					int opponentattackpower=Opponent.GetAttack(this);
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
					int opponentattackpower=Opponent.GetAttack(this);;

					if(opponentattackpower-DefensePower<0)
					{
						opponentattackpower=0;
						System.out.println("The Monster attacked and inflicted "+(opponentattackpower)+" damage to you");
					
					}
					else
					System.out.println("The Monster attacked and inflicted "+(opponentattackpower-this.DefensePower)+" damage to you");
					this.decreaseHP(opponentattackpower);
					System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());			
					if(SpecialPowerActivated==true)
						noofspecialmoves++;
					else
						moves++;

				}
				else if(val==3 && moves>=3)
				{
					System.out.println("Special Power Activated");
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
			{
				return true;
			}
			else
			{
				return false;
			}
		
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
	public boolean fight(Monster Opponent)
	{
		int moves=0;
		int noofspecialmoves=0;
		while(this.HP>0 && Opponent.getHP()>0)
		{
			System.out.println("Choose Move");
			System.out.println("1) Attack");
			System.out.println("2) Defense");
			if(moves>=3)
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
				int opponentattackpower=Opponent.GetAttack(this);
				System.out.println("The Monster attacked and inflicted "+opponentattackpower+" damage to you");
				this.decreaseHP(opponentattackpower);
				System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());			
					moves++;

			}
			else if(val==2)
			{
				System.out.println("You choose to defend");
				System.out.println("Monster Attack reduced by "+this.DefensePower);
				int opponentattackpower=Opponent.GetAttack(this);;

				if(opponentattackpower-DefensePower<0)
				{
					opponentattackpower=0;
					System.out.println("The Monster attacked and inflicted "+(opponentattackpower)+" damage to you");
				
				}
				else
				System.out.println("The Monster attacked and inflicted "+(opponentattackpower-this.DefensePower)+" damage to you");
				this.decreaseHP(opponentattackpower);
				System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());			
					moves++;

			}
			else if(val==3 && moves>=3)
			{
				System.out.println("Special Power Activated");
				System.out.println("Performing special attack");
				int steal=(int)Math.round(Opponent.getHP()*.30);
				System.out.println("You have stolen "+steal+ " Hp from the monster!");
				Opponent.decreaseHP(steal);
				this.increaseHP(steal);
				System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());
				System.out.println("Monster Attack !");
				int opponentattackpower=Opponent.GetAttack(this);
				System.out.println("The Monster attacked and inflicted "+opponentattackpower+" damage to you");
				this.decreaseHP(opponentattackpower);
				System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());			
				System.out.println("Special Power deactivated");
				moves=0;
			}
			else
			{
				System.out.println("Wrong selection");
			}
		}
		if(Opponent.HP==0)
		{
			return true;
		}
		else
		{
			return false;
		}
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
			 System.out.println("Brcause of sepcial power enemy health reduced by :"+Math.round(Opponent.getHP()*SpecialBonus));
			 Opponent.decreaseHP(Math.round(Opponent.getHP()*SpecialBonus));

			 if(Opponent.getAttackPower()>=DefensePower)
				 HP=HP-Opponent.getAttackPower()+DefensePower;
			 
		 }
		 if(Opponent.getAttackPower()>=DefensePower)
			 HP=HP-Opponent.getAttackPower()+DefensePower;	 
	}
	@Override
	 protected int Attack(Monster Opponent)
	 {
		 if(SpecialPowerActivated==true)
		 {
			 System.out.println("Brcause of sepcial power enemy health reduced by :"+Math.round(Opponent.getHP()*SpecialBonus));
			 Opponent.decreaseHP(Math.round(Opponent.getHP()*SpecialBonus));
		 }
		 if(SpecialPowerActivated==true)
			 return(AttackPower+Math.round(SpecialBonus*Opponent.getHP()));
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
			if(moves>=3)
			{
				System.out.println("3) Special Move");
			}
			int val=in.nextInt();
			if(val==1)
			{
				if(SpecialPowerActivated==true)
					System.out.println("Special Power is activated");
				
				System.out.println("You choose to attack");
				System.out.println("You attacked and inflicted "+this.Attack(Opponent)+" damage to the monster");
				Opponent.decreaseHP(this.Attack(Opponent));
				System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());
				System.out.println("Monster Attack !");
				int opponentattackpower=Opponent.GetAttack(this);
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
				if(SpecialPowerActivated==true)
					System.out.println("Special Power is activated");
				System.out.println("You choose to defend");
				System.out.println("Monster Attack reduced by "+this.DefensePower);
				int opponentattackpower=Opponent.GetAttack(this);;

				if(opponentattackpower-DefensePower<0)
				{
					opponentattackpower=0;
					System.out.println("The Monster attacked and inflicted "+(opponentattackpower)+" damage to you");
				
				}
				else
				System.out.println("The Monster attacked and inflicted "+(opponentattackpower-this.DefensePower)+" damage to you");
				this.decreaseHP(opponentattackpower);
				System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());			
				if(SpecialPowerActivated==true)
					noofspecialmoves++;
				else
					moves++;

			}
			else if(val==3 && moves>=3)
			{
				System.out.println("Special Power Activated");
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
		{
			return true;
		}
		else
		{
			return false;
		}
	
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
			System.out.println("Double to SpecialPowerActivated Power HP increased by :"+(int)Math.round(this.getHP()*HealPower));
				this.increaseHP((int)Math.round(this.getHP()*HealPower));
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
			if(moves>=3)
			{
				System.out.println("3) Special Move");
			}
			int val=in.nextInt();
			if(val==1)
			{
				if(SpecialPowerActivated==true)
				{
					System.out.println("Special Power is activated");
					this.SpecialPower();
				}
				System.out.println("You choose to attack");
				System.out.println("You attacked and inflicted "+this.Attack(Opponent)+" damage to the monster");
				Opponent.decreaseHP(this.Attack(Opponent));
				System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());
				System.out.println("Monster Attack !");
				int opponentattackpower=Opponent.GetAttack(this);
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
				if(SpecialPowerActivated==true)
				{
					System.out.println("Special Power is activated");
					this.SpecialPower();
				}
				System.out.println("You choose to defend");
				System.out.println("Monster Attack reduced by "+this.DefensePower);
				int opponentattackpower=Opponent.GetAttack(this);;

				if(opponentattackpower-DefensePower<0)
				{
					opponentattackpower=0;
					System.out.println("The Monster attacked and inflicted "+(opponentattackpower)+" damage to you");
				
				}
				else
				System.out.println("The Monster attacked and inflicted "+(opponentattackpower-this.DefensePower)+" damage to you");
				this.decreaseHP(opponentattackpower);
				System.out.println("Your HP "+this.HP+" Monster HP: "+Opponent.getHP());			
				if(SpecialPowerActivated==true)
					noofspecialmoves++;
				else
					moves++;

			}
			else if(val==3 && moves>=3)
			{
				System.out.println("Special Power Activated");
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
		{
			return true;
		}
		else
		{
			return false;
		}
	
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
 					if(currentuser.getPath().getStartstatus()==false)
 						currentuser.getPath().initialselection(currentuser);
 					else
 						currentuser.getPath().nextselections(currentuser);
 						
 				}
 			}
 			else {
				break;
			}
 			
 		}	
 	}
 }
