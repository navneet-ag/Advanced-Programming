import java.util.ArrayList;
import java.util.Scanner;



interface Display{
	public void display();
}
interface Menu{
	public void menu();
	public void displaydetails();
}
//class for item

class Item implements Display
{
	private int UniqueCode;						//Unique code for every item		
	private static int UniqueCodeSetter=1;		//static variable to set the unique code
	private String Name;						//name of the item
	private int Price;							//Price of the item
	private int Quantity;						//Quantity of item
	private String Offer;						//Offer applied
	private String Category;					//Category of the item	
	private String MerchantName ;				//MerchantName
	Item(String Name,int Price,String Offer,String Category,int Quantity,String MerchantName )
	{
		this.Name=Name;
		this.Price=Price;
		this.Offer=Offer;
		this.Category=Category;
		this.UniqueCode=UniqueCodeSetter++;
		this.Quantity=Quantity;
		this.MerchantName=MerchantName;
	}
	private void diplayitem()
	{
		System.out.print(UniqueCode);
		System.out.print("  "+Name);
		System.out.print("  "+Price);
		System.out.print("  "+Quantity);
		System.out.print("  "+Offer);
		System.out.print("  "+Category);
	}
	@Override
	public void display()
	{
		this.diplayitem();
	}
	public int getUniqueCode() {
		return UniqueCode;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
		if(this.Quantity<2 && this.Offer.toLowerCase().equals("Buy one Get One".toLowerCase()));
			this.setOffer("None");
	}
	public void setOffer(String offer) {
		Offer = offer;
	}
	public String getOffer() {
		return Offer;
	}
	public int getQuantity() {
		return Quantity;
	}
	public int getPrice() {
		return Price;
	}
	public String getMerchantName() {
		return MerchantName;
	}
	public String getName() {
		return Name;
	}
}
//Item to buy
class BuyItem
{
	private int UniqueCode;
	private int Quantity;
	private Item ItemDetails;
	private int MerchantId;
	BuyItem(int code,int qty,Item Itemdetails,int MerchantId)
	{
		this.Quantity=qty;
		this.UniqueCode=code;
		this.ItemDetails=Itemdetails;
		this.MerchantId=MerchantId;
		
	}
	public int getUniqueCode() {
		return UniqueCode;
	}
	public int getQuantity() {
		return Quantity;
	}
	public Item getItemDetails() {
		return ItemDetails;
	}
	public int getMerchantId() {
		return MerchantId;
	}
	
}
class BoughtItem implements Display
{
	private float Price;
	private String ItemName;
	private String MerchantName;
	private int Quantity;
	BoughtItem(float Price,String ItemName,String MerchantName,int Quantity)
	{
		this.Price=Price;
		this.ItemName=ItemName;
		this.MerchantName=MerchantName;
		this.Quantity=Quantity;
	}
	private void print()
	{
		System.out.println("Bought item:"+this.ItemName+" : "+this.Quantity+" for Rs "+this.Price+" from Merchant "+this.MerchantName);
	}
	@Override
	public void display()
	{
		this.print();
	}
}
//Merchant Class

class Merchant implements Display,Menu
{
	private final String Name;			//Name of the Merchant
	private final String Address;		//Address of the merchant
	private final int UserId;			//UserId of the merchant
	private int ItemNumber;				//Number of items
	private int ItemSlots;				//slots for unique items
	private int UsedSlots;
	private static int UserIdSetter=1;	//Static variable to set the unique id
	private int slots;					//Number of unique items that a merchant can have
	private int RewardSlots;			//Number of reward slots won
	private ArrayList<Item> ItemList=new ArrayList();
	private static ArrayList<String> CategoryList=new ArrayList<String>();
	private static ArrayList<ArrayList<Item>> ItemCategoryList=new ArrayList<ArrayList<Item>>();	
	private static ArrayList<ArrayList<Integer>> MerchantIdList=new ArrayList<ArrayList<Integer>>();	
	private float CompanyBalance=0;
	private static ArrayList<Merchant> MerchantList =new ArrayList<>();
	private static float CompanyBalanceAllMerchants=0;
	Scanner in=new Scanner(System.in);
	Merchant(String Name,String Address)
	{
		this.Name=Name;
		this.UserId=UserIdSetter++;
		this.ItemSlots=10;
		this.ItemNumber=0;
		this.Address=Address;
		this.slots=10;
		this.UsedSlots=0;
		this.RewardSlots=0;
	}
	
	//Adding a Merchant
	public static void AddMerchant(String name,String Address)
	{
		MerchantList.add(new Merchant(name,Address));
	}
	
	
	//Add Item for a merchant Query 1 in merchant menu
	
	public void AddItem()
	{
		if(this.UsedSlots<this.ItemSlots+this.RewardSlots)
		{
			System.out.println("Enter item details");
			System.out.println("Item Name :");
			String name=in.next();
			System.out.println("Item price");
			int price=in.nextInt();
			System.out.println("Item Quantity");
			int quantity=in.nextInt();
			System.out.println("Item Category");
			String Category=in.next();
			Item temp=new Item(name, price, "None", Category,quantity,this.Name);
			ItemList.add(temp);
			System.out.println("Item added succesfuly");
			ItemList.get(ItemList.size()-1).display(); 						//Display details of item added using the interface display
			this.UsedSlots++;
			int j;
			
			// Entering each product in its category
			
			for(j=0;j<CategoryList.size();j++)
			{
				if(CategoryList.get(j).toLowerCase().equals(Category.toLowerCase()))
				{
					ItemCategoryList.get(j).add(temp);
					MerchantIdList.get(j).add(this.UserId);
					break;
				}	
			}
			
			if(j==CategoryList.size())
			{
				CategoryList.add(Category);
				ArrayList<Item> tempitemlis=new ArrayList<>();
				ArrayList<Integer> templistmerchant=new ArrayList<>();
				tempitemlis.add(temp);
				templistmerchant.add(this.UserId);
				ItemCategoryList.add(tempitemlis);
				MerchantIdList.add(templistmerchant);
//				System.out.println("Entering int category list "+templistmerchant.size());
//				temp.display();

			}
			
		}
		
		else
		{
			System.out.println("No free slot");
		}
	}
	//Display all items
	public void displayall()
	{
		for(int i=0;i<ItemList.size();i++)
		{
			ItemList.get(i).display();
			System.out.println();
		}		
	}
	//AddItem ended
	
	//Edit items for a merchant Query 2 in merchant menu
	public void EditItem()
	{
		System.out.println(" Choose Item by code ");
		this.displayall();
		int code=in.nextInt();
		Item temp=this.SearchCode(code);
		if(temp==null)
		{
			return;
		}
		
		System.out.println("Enter item details");
		System.out.println("Enter price");
		int price=in.nextInt();
		System.out.println("Item Quantity");
		int quantity=in.nextInt();
		temp.setPrice(price);
		temp.setQuantity(quantity);
		temp.display();
	}
	
	//Searching an Item Via Code
	public Item SearchCode(int code)
	{
		int i=0;
		for( i=0;i<ItemList.size();i++)
		{
			if(ItemList.get(i).getUniqueCode()==code)
				break;
		}
		if(i==ItemList.size())
		{
			System.out.println("No item with this code");
			return(null);
		}
		else
		{
			return(ItemList.get(i));
		}
	}
	
	//Searching an item via Category query 3 merchant menu
	
	public void SearchCategory()
	{
		System.out.println("Please select category");
		for(int j=0;j<CategoryList.size();j++)
		{
			System.out.println((j+1)+") "+CategoryList.get(j));
		}
		int selection=in.nextInt();
		selection=selection-1;
		if(selection>=CategoryList.size() && selection<0)
			System.out.println("Wrong category Selected");
		else
		{
			int j;
			for(j=0;j<ItemCategoryList.get(selection).size();j++)
			{
				if(MerchantIdList.get(selection).get(j)==this.UserId)
					break;
			}
			if(j==MerchantIdList.get(selection).size())
			{
				System.out.println("You dont have any item in this category");
				return;
			}
			else
			{
				for(j=0;j<MerchantIdList.get(selection).size();j++)
				{
					if(MerchantIdList.get(selection).get(j)==this.UserId)
					{
						System.out.println();
						System.out.println("Your item details");
						ItemCategoryList.get(selection).get(j).display();
					}
					else
					{
						System.out.println();
						System.out.println("Competitior item details");
						ItemCategoryList.get(selection).get(j).display();
					}
				}
			}
			
		}
	}
	
	// Adding offer for an item , Query 4 in Merchant menu
	
	public void AddOffer()
	{
		System.out.println("Choose item by code");
		this.displayall();
		int selection=in.nextInt();
		Item temp=this.SearchCode(selection);
		if(temp==null)
		{
			System.out.println("Wrong code chosen");
			return;
		}
		else
		{
			System.out.println("Choose Offer ");
			System.out.println("1) Buy one get one free");
			System.out.println("2) 25% o"
					+ "ff");
			selection=in.nextInt();
			if(selection==1)
			{
				if(temp.getQuantity()>1)
				temp.setOffer("Buy one Get One");
				else
					System.out.println("Offer Cant be Applied");
			}
			else if(selection==2)
				temp.setOffer("25% off");
			else
				System.out.println("Wrong number chosen for the offer");
			temp.display();
		}
	}
	public static Merchant SearchMerchant(int Merchantid)
	{
		for(int i=0;i<MerchantList.size();i++)
		{
			if(MerchantList.get(i).getUserId()==Merchantid)
			{
				return(MerchantList.get(i));
			}
		}
		return null;
	}
	public int getRewardSlots()
	{
		return(this.RewardSlots);
	}
	public static ArrayList<String> getCategoryList() {
		return CategoryList;
	}
	public static ArrayList<ArrayList<Integer>> getMerchantIdList() {
		return MerchantIdList;
	}
	public static ArrayList<ArrayList<Item>> getItemCategoryList() {
		return ItemCategoryList;
	}
	public int getUserId() {
		return UserId;
	}
	public void setCompanyBalance(float companyBalance) {
		CompanyBalance += companyBalance;
		CompanyBalanceAllMerchants+=2*companyBalance;
	}
	public float getCompanyBalance() {
		return CompanyBalance;
	}
	public void setRewardSlots(int rewardSlots) {
		RewardSlots += rewardSlots;
	}
	public static float getCompanyBalanceAllMerchants() {
		return CompanyBalanceAllMerchants;
	}
	public void printmerchantdetails()
	{
		System.out.println(this.UserId+"  "+this.Name);
	}
	@Override
	public void display()
	{
		this.printmerchantdetails();
	}
	public static void displayallMerchant()
	{
		for(int i=0;i<MerchantList.size();i++)
		{
			MerchantList.get(i).display();
		}
	}

	@Override
	public void menu() {
		while(true)
		{
			System.out.println();
			System.out.println("Welcome Merchant :"+this.Name);
			System.out.println("1) Add items for a merchant:");
			System.out.println("2) Edit items for a merchant:");
			System.out.println("3) Searching:");
			System.out.println("4) Add offers for an item belonging to a merchant:");
			System.out.println("5) Print reward:");
			System.out.println("6) Exit");
			int selectedvalue=in.nextInt();
			if(selectedvalue==1)
			{
				this.AddItem();
			}
			else if(selectedvalue==2)
			{
				this.EditItem();
			}
			else if(selectedvalue==3)
			{
				this.SearchCategory();
			}
			else if(selectedvalue==4)
			{
				this.AddOffer();
			}
			else if(selectedvalue==5)
			{
				System.out.println("Number of rewards slots that you have won are :"+this.getRewardSlots());
			}
			else
			{
				break;
			}
		}
	}

	private void displaydetailsmerchant()
	{
		System.out.println(this.Name+" "+this.Address+" "+this.CompanyBalance);
	}
	@Override
	public void displaydetails() {
		// TODO Auto-generated method stub
		this.displaydetailsmerchant();
	}
}

//Customer class
class Customer implements Display,Menu
{
	private final String Name;				//name of the customer
	private final String Address;			//Address of the customer
	private final int UserId;				//UserId of the Customer	
	private static int UserIdSetter=1;		//static variable for setting the UserId
	private float MainMoney;					//Main account money	
	private float RewardMoney;				//Reward Account Money
	private float RewardWon=0;
	private ArrayList<BuyItem> Cart=new ArrayList();
	private ArrayList<ArrayList<BoughtItem>> PreviousTransactions=new ArrayList<ArrayList<BoughtItem>>();	
	private int NumberofTransaction=0;
	private static ArrayList<Customer> CustomerList=new ArrayList<>();

	Scanner in=new Scanner(System.in);
	
	Customer(String Name,String Address)
	{
		this.Name=Name;
		this.UserId=UserIdSetter++;
		this.MainMoney=100;
		this.RewardMoney=0;
		this.Address=Address;
	}
	
	public static void AddCustomer(String Name ,String Address)
	{
		CustomerList.add(new Customer(Name, Address));
	}
	
	//Query 1 for the customer menu 
	public void SearchandBuy()
	{
		ArrayList<String> CategoryList=Merchant.getCategoryList();
		ArrayList<ArrayList<Item>> ItemCategoryList=Merchant.getItemCategoryList();	
		ArrayList<ArrayList<Integer>> MerchantIdList=Merchant.getMerchantIdList();
		System.out.println("Please select category");
		for(int j=0;j<CategoryList.size();j++)
		{
			System.out.println((j+1)+") "+CategoryList.get(j));
		}
		int selection=in.nextInt();
		selection=selection-1;
		if(selection>=CategoryList.size() && selection<0)
			System.out.println("Wrong category Selected");
		else
		{		
			int j;
			for( j=0;j<MerchantIdList.get(selection).size();j++)
			{
					
					ItemCategoryList.get(selection).get(j).display();
			}		
			System.out.println();
			System.out.println("Enter Item code");
			int ItemCode=in.nextInt();
			System.out.println("Enter Item quantity");
			int quantity=in.nextInt();
			for(j=0;j<MerchantIdList.get(selection).size();j++)
			{
					if(ItemCategoryList.get(selection).get(j).getUniqueCode()==ItemCode)
					{
						break;
					}
			}
			if(j==MerchantIdList.get(selection).size())
			{
				System.out.println("Wrong Id chosen");
				return;
			}
			Item itemtobuy=ItemCategoryList.get(selection).get(j);
			int MerchantId=MerchantIdList.get(selection).get(j);
			System.out.println("Choose method of transaction");
			System.out.println("1) Buy item");
			System.out.println("2) Add item to cart");
			System.out.println("3) Exit");
			selection=in.nextInt();
			if(selection==1)
			{
				//buy item directly
				this.buydirectly(new BuyItem(ItemCode, quantity, itemtobuy,MerchantId));
			}
			else if(selection==2)
			{
				//Add to cart
				if((itemtobuy.getOffer().toLowerCase().equals("Buy one Get One".toLowerCase()) && itemtobuy.getQuantity()<quantity) || (itemtobuy.getQuantity()<quantity))
				{
					System.out.println("Not enough items in stock");
					return;
				}				
				Cart.add(new BuyItem(ItemCode,quantity,itemtobuy,MerchantId));
			}
			else
			{
				//Exit this menu
				return;
			}	
		}		
	}
	
	// Query 1.1 for customer menu
	public void buydirectly(BuyItem current)
	{
		Merchant CurrentMerchant=Merchant.SearchMerchant(current.getMerchantId());
		if(current.getItemDetails().getOffer().toLowerCase().equals("None".toLowerCase()))
		{
			if(this.MainMoney+this.RewardMoney<=current.getItemDetails().getPrice()*current.getQuantity()*1.005)
			{
				System.out.println("Insufficient funds");
				return;
			}
			else if(current.getQuantity()>current.getItemDetails().getQuantity())
			{
				System.out.println("The merchant has less quantity than the desired quantity");
				return;
			}
			else
			{
				float effectivecost=current.getQuantity()*current.getItemDetails().getPrice();
				if(this.MainMoney>effectivecost*1.005)
				{
					this.MainMoney=(float) (this.MainMoney-effectivecost*1.005);
				}
				else
				{
					float amountleft=(float) (effectivecost*1.005-this.MainMoney);
					this.MainMoney=0;
					this.RewardMoney-=amountleft;
				}
				System.out.println("Item Bought Successfuly");
				this.NumberofTransaction++;
				ArrayList<BoughtItem> temp=new ArrayList<>();
				temp.add(new BoughtItem(effectivecost, current.getItemDetails().getName(), current.getItemDetails().getMerchantName(), current.getQuantity()));
				PreviousTransactions.add(temp);
				current.getItemDetails().setQuantity(current.getItemDetails().getQuantity()-current.getQuantity());
				CurrentMerchant.setCompanyBalance((float) (.005*effectivecost));
				
			}
		}
		else if(current.getItemDetails().getOffer().toLowerCase().equals("Buy one Get One".toLowerCase()))
		{
			if(current.getQuantity()*2>current.getItemDetails().getQuantity())
			{
				System.out.println("In sufficient quantity");
				return;
			}
			else if(this.MainMoney+this.RewardMoney<current.getQuantity()*current.getItemDetails().getPrice()*1.005)
			{
				System.out.println("Insufficient funds");
				return;
			}
			else
			{
				float effectivecost=(float) (current.getQuantity()*current.getItemDetails().getPrice());
				if(this.MainMoney>=effectivecost*1.005)
				{
					this.MainMoney-=effectivecost*1.005;
				}
				else
				{
					float amountleft=(float) (effectivecost*1.005-this.MainMoney);
					this.RewardMoney-=amountleft;
					this.MainMoney=0;
				}
				System.out.println("Item Bought Successfuly");
				this.NumberofTransaction++;
				ArrayList<BoughtItem> temp=new ArrayList<>();
				temp.add(new BoughtItem(effectivecost, current.getItemDetails().getName(), current.getItemDetails().getMerchantName(), current.getQuantity()*2));
				PreviousTransactions.add(temp);				
				current.getItemDetails().setQuantity(current.getItemDetails().getQuantity()-2*current.getQuantity());
				CurrentMerchant.setCompanyBalance((float) (.005*effectivecost));

			}
		}
		else
		{
			if(current.getQuantity()>current.getItemDetails().getQuantity())
			{
				System.out.println("In sufficient quantity");
				return;
			}
			else if(this.MainMoney+this.RewardMoney<current.getQuantity()*current.getItemDetails().getPrice()*1.005*.75)
			{
				System.out.println("Insufficient funds");
				return;
			}
			else
			{
				float effectivecost=(float) (.75*current.getQuantity()*current.getItemDetails().getPrice());
				if(this.MainMoney>=effectivecost*1.005)
				{
					this.MainMoney-=effectivecost*1.005;
				}
				else
				{
					float amountleft=(float) (effectivecost*1.005-this.MainMoney);
					this.RewardMoney-=amountleft;
					this.MainMoney=0;
				}
				System.out.println("Item Bought Successfuly");
				this.NumberofTransaction++;
				ArrayList<BoughtItem> temp=new ArrayList<>();
				temp.add(new BoughtItem(effectivecost, current.getItemDetails().getName(), current.getItemDetails().getMerchantName(), current.getQuantity()));
				PreviousTransactions.add(temp);	
				current.getItemDetails().setQuantity(current.getItemDetails().getQuantity()-current.getQuantity());
				CurrentMerchant.setCompanyBalance((float) (.005*effectivecost));
			}			
		}
		if(NumberofTransaction%5==0)
		{
			this.RewardMoney+=10;
			this.RewardWon+=10;
		}
		if(CurrentMerchant.getCompanyBalance()/100.00 > (float)CurrentMerchant.getRewardSlots())
		{	
			CurrentMerchant.setRewardSlots((int)CurrentMerchant.getCompanyBalance()/100 -CurrentMerchant.getRewardSlots());
		}
	}
	
	//Query 2 for customer menu
	
	public void ClearCart()
	{
		ArrayList<BoughtItem> temp=new ArrayList<>();
		int i=0,j=0;
		while(j<Cart.size() && Cart.size()!=0)
		{
			BuyItem current=Cart.get(j);
			Merchant CurrentMerchant=Merchant.SearchMerchant(current.getMerchantId());
			if(current.getItemDetails().getOffer().toLowerCase().equals("None".toLowerCase()))
			{
				if(this.MainMoney+this.RewardMoney<=current.getItemDetails().getPrice()*current.getQuantity()*1.005)
				{
					System.out.println("Insufficient funds for item number "+i+" in cart");
					if(temp.size()!=0)
					{
						this.NumberofTransaction++;
						PreviousTransactions.add(temp);
					}
					return;
				}
				else if(current.getQuantity()>current.getItemDetails().getQuantity())
				{
					System.out.println("The merchant has less quantity than the desired quantity for item number "+i+" in cart");
					if(temp.size()!=0)
					{
						this.NumberofTransaction++;
						PreviousTransactions.add(temp);
					}
					return;
				}
				else
				{
					float effectivecost=current.getQuantity()*current.getItemDetails().getPrice();
					if(this.MainMoney>effectivecost*1.005)
					{
						this.MainMoney=(float) (this.MainMoney-effectivecost*1.005);
					}
					else
					{
						float amountleft=(float) (effectivecost*1.005-this.MainMoney);
						this.MainMoney=0;
						this.RewardMoney-=amountleft;
					}
					System.out.println("Item number " +(i+1)+" in cart Bought Successfuly");
					temp.add(new BoughtItem(effectivecost, current.getItemDetails().getName(), current.getItemDetails().getMerchantName(), current.getQuantity()));
					current.getItemDetails().setQuantity(current.getItemDetails().getQuantity()-current.getQuantity());
					CurrentMerchant.setCompanyBalance((float) (.005*effectivecost));			
					Cart.remove(j);
					j--;
				}
			}
			else if(current.getItemDetails().getOffer().toLowerCase().equals("Buy one Get One".toLowerCase()))
			{
				if(current.getQuantity()*2>current.getItemDetails().getQuantity())
				{
					System.out.println("In sufficient quantity for item number "+i+" in cart");
					if(temp.size()!=0)
					{
						this.NumberofTransaction++;
						PreviousTransactions.add(temp);
					}
					return;
				}
				else if(this.MainMoney+this.RewardMoney<current.getQuantity()*current.getItemDetails().getPrice()*1.005)
				{
					System.out.println("Insufficient funds for item number "+i+" in cart");
					if(temp.size()!=0)
					{
						this.NumberofTransaction++;
						PreviousTransactions.add(temp);
					}
					return;
				}
				else
				{
					float effectivecost=(float) (current.getQuantity()*current.getItemDetails().getPrice());
					if(this.MainMoney>=effectivecost*1.005)
					{
						this.MainMoney-=effectivecost*1.005;
					}
					else
					{
						float amountleft=(float) (effectivecost*1.005-this.MainMoney);
						this.RewardMoney-=amountleft;
						this.MainMoney=0;
					}
					System.out.println("Item nmber " +i+" in cart Bought Successfuly");
					temp.add(new BoughtItem(effectivecost, current.getItemDetails().getName(), current.getItemDetails().getMerchantName(), current.getQuantity()*2));
					current.getItemDetails().setQuantity(current.getItemDetails().getQuantity()-2*current.getQuantity());
					CurrentMerchant.setCompanyBalance((float) (.005*effectivecost));
					Cart.remove(j);
					j--;

				}
			}
			else
			{
				if(current.getQuantity()>current.getItemDetails().getQuantity())
				{
					System.out.println("In sufficient quantity for item number "+i+" in cart");
					if(temp.size()!=0)
					{
						this.NumberofTransaction++;
						PreviousTransactions.add(temp);
					}
					return;
				}
				else if(this.MainMoney+this.RewardMoney<current.getQuantity()*current.getItemDetails().getPrice()*1.005*.75)
				{
					System.out.println("Insufficient funds for item number "+i+" in cart");
					if(temp.size()!=0)
					{
						this.NumberofTransaction++;
						PreviousTransactions.add(temp);
					}

					return;
				}
				else
				{
					float effectivecost=(float) (.75*current.getQuantity()*current.getItemDetails().getPrice());
					if(this.MainMoney>=effectivecost*1.005)
					{
						this.MainMoney-=effectivecost*1.005;
					}
					else
					{
						float amountleft=(float) (effectivecost*1.005-this.MainMoney);
						this.RewardMoney-=amountleft;
						this.MainMoney=0;
					}
					System.out.println("Item nmber " +i+" in cart Bought Successfuly");
					temp.add(new BoughtItem(effectivecost, current.getItemDetails().getName(), current.getItemDetails().getMerchantName(), current.getQuantity()));
					PreviousTransactions.add(temp);	
					current.getItemDetails().setQuantity(current.getItemDetails().getQuantity()-current.getQuantity());
					CurrentMerchant.setCompanyBalance((float) (.005*effectivecost));
					Cart.remove(j);
					j--;					
				}			
				if(CurrentMerchant.getCompanyBalance()/100.00 > (float)CurrentMerchant.getRewardSlots())
				{	
					CurrentMerchant.setRewardSlots((int)CurrentMerchant.getCompanyBalance()/100 -CurrentMerchant.getRewardSlots());
				}
			}
			i++;
			j++;
		}
		if(temp.size()!=0)
		{
			PreviousTransactions.add(temp);
			NumberofTransaction++;
		}

		if(NumberofTransaction%5==0 && NumberofTransaction!=0)
		{
			this.RewardMoney+=10;
			this.RewardWon+=10;
		}
	}
	
	//Query 4 in Customer menu
	public void ListRecentOrders()
	{
		
		for(int i=PreviousTransactions.size()-1, j=0;i>=0&&j<10;i--,j++)
		{
			System.out.println("Transaction number "+(i+1));
			ArrayList<BoughtItem> currentlist=PreviousTransactions.get(i);
			for(int k=0;k<currentlist.size();k++)
			{
				currentlist.get(k).display();
			}
		}
	}
	//Query 3 in Customer menu
	public void PrintRewardWon()
	{
		System.out.println("The reward won till now is : "+this.RewardWon);
		System.out.println("The reward money left is :"+this.RewardMoney);
	}
	
	public void displaycustomer()
	{
		System.out.println(this.UserId+" "+this.Name);
	}
	@Override
	public void display()
	{
		this.displaycustomer();
	}
	public static void displayallcustomers()
	{
		for(int i=0;i<CustomerList.size();i++)
		{
			CustomerList.get(i).display();
		}
	}
	public static Customer SearchCustomer(int userid)
	{
		for(int i=0;i<CustomerList.size();i++)
		{
			if(userid==CustomerList.get(i).UserId)
				return(CustomerList.get(i));
		}
		System.out.println("No customer forund");
		return(null);
	}
	@Override
	public void menu()
	{
		while(true)
		{
			System.out.println("Welcome Customer :"+this.Name);
			System.out.println("1) Searching");
			System.out.println("2) Checkout option");
			System.out.println("3) Print Reward");
			System.out.println("4) List Recent orders");
			System.out.println("5) Exit");
			int selectedvalue=in.nextInt();
			if(selectedvalue==1)
			{
				this.SearchandBuy();
			}
			else if(selectedvalue==2)
			{
				this.ClearCart();
			}
			else if(selectedvalue==3)
			{
				this.PrintRewardWon();
			}
			else if(selectedvalue==4)
			{
				this.ListRecentOrders();
			}
			else
			{
				break;
			}
		}

	}
	private void dispaycustomerdetails()
	{
		System.out.println(this.Name+" "+this.Address+" "+this.NumberofTransaction);
	}

	@Override
	public void displaydetails() {
		// TODO Auto-generated method stub
		this.dispaycustomerdetails();
	}
}


public class Mercury {
	public static void showmenu(Menu MenuDisplayer)
	{
		MenuDisplayer.menu();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in =new Scanner(System.in);
		Merchant.AddMerchant("Jack","Rajendra Nagar");
		Merchant.AddMerchant("John","Civil Lines");
		Merchant.AddMerchant("James","Queens");
		Merchant.AddMerchant("Jeff","Brooklyn");
		Merchant.AddMerchant("Joseph","London");
		Customer.AddCustomer("Ali", "Rajendra Nagar");
		Customer.AddCustomer("Nobby", "Civil Lines");
		Customer.AddCustomer("Bruno", "Queens");
		Customer.AddCustomer("Borat", "London");
		Customer.AddCustomer("Aladeen", "BrookLyn");
		while(true)
		{
			System.out.println("Welcome to Mercury :");
			System.out.println("1) Enter as Merchant");
			System.out.println("2) Enter as Customer");
			System.out.println("3) See User Details");
			System.out.println("4) Company account balance");
			System.out.println("5) Exit");
			int selectedvalue=in.nextInt();
			if(selectedvalue==1)
			{
				System.out.println("Select Merchant");
				Merchant.displayallMerchant();
				int userid=in.nextInt();
				Menu temp=Merchant.SearchMerchant(userid);
				if(temp!=null)
				{
					showmenu(temp);
				}
			}
			else if(selectedvalue==2)
			{
				System.out.println("Select Customer");
				Customer.displayallcustomers();
				int userid=in.nextInt();
				Menu temp=Customer.SearchCustomer(userid);	
				if(temp!=null)
				{
					showmenu(temp);
				}
			}
			else if(selectedvalue==3)
			{
				System.out.println("Select Merchant");
				Merchant.displayallMerchant();
				System.out.println("Select Customer");
				Customer.displayallcustomers();
				String person=in.next();
				int userid=in.nextInt();
				if(person.toUpperCase().equals("M"))
				{
					Merchant temp=Merchant.SearchMerchant(userid);	
					temp.displaydetails();
				}
				else
				{
					Customer temp=Customer.SearchCustomer(userid);	
					temp.displaydetails();
				}
			}
			else if(selectedvalue==4)
			{
				System.out.println("Company Account Balance is : "+Merchant.getCompanyBalanceAllMerchants());
			}
			else
				break;
		}
	}

}
