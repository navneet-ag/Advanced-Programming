import java.util.ArrayList;
import java.util.Scanner;

class Student1
{

	//All the instance variables of the class 
	
	private final int roll_number;
	private float cgpa;
	private String branch;
	private Boolean placed;
	private String Company_name_placed;
	private ArrayList<String> companies_applied=new ArrayList<>();
	private ArrayList<Integer> tech_score_in_companies_applied=new ArrayList<>();
	private static int roll_no_setter=1;
	private static Boolean[] roll_number_lis=new Boolean[1000000]; 
	private int marks;
	private static int number_of_students;
	private static int placed_student_number=0;
	private static ArrayList<Student1> student_list=new ArrayList();

	//Parameterized constructor of the class
	
	Student1(float cgpa,String branch)
	{	
		this.roll_number=roll_no_setter;
		roll_no_setter+=1;
		roll_number_lis[roll_number]=true;
		this.cgpa=cgpa;
		this.branch=branch;
		placed=false;
		Company_name_placed="";
		this.marks=0;
	}
	
	//Getter and Setter functions used for the instance variables
	
	public void setPlaced(Boolean placed)
	{
		this.placed = placed;	
	}
	public void setCompany_name(String company_name) 
	{
		this.Company_name_placed = company_name;
	}
	public void setMarks(int marks) 
	{
		this.marks = marks;
	}
	public static void setNumber_of_students(int number_of_students) 
	{
		Student1.number_of_students = number_of_students;
	}
	public static int getNumber_of_students() 
	{
		return number_of_students;
	}
	public String getBranch() 
	{
		return branch;
	}
	public int getRoll_number() 
	{
		return roll_number;
	}
	public float getCgpa() 
	{
		return cgpa;
	}
	public Boolean getPlaced() 
	{
		return placed;
	}
	public Boolean  check_rollnum(int roll_number)
	{
		return(roll_number_lis[roll_number]);
	}
	public static int getPlaced_student_number() {
		return placed_student_number;
	}
	public static void setPlaced_student_number(int placed_student_number) {
		Student1.placed_student_number += placed_student_number;
	}
	public int getMarks() {
		return marks;
	}
	public void setCompanies_applied(String company) {
		this.companies_applied.add(company);	
	}
	public void setTech_score_in_companies_applied(int tech_score) {
		this.tech_score_in_companies_applied.add(tech_score);
	}
	public ArrayList<String> getCompanies_applied() {
		return companies_applied;
	}
	public String getCompany_name_placed() {
		return Company_name_placed;
	}
	public ArrayList<Integer> getTech_score_in_companies_applied() {
		return tech_score_in_companies_applied;
	}


	//Finding a particular student object through its roll number
	
	public static Student1 findrollNumber(int roll)
	{
		for(int i=0;i<student_list.size();i++)
		{
			if(student_list.get(i).getRoll_number()==roll)
				return(student_list.get(i));
		}
		System.out.println("No student with the given roll number has an account.");
		return(null);
	}

	//Function for adding a student record in the student_list and also checks if branch is valid 
	public static String AddStudent(float cgpa,String branch)
	{
		if(branch.toLowerCase().equals("cse") || branch.toLowerCase().equals("ece") || branch.toLowerCase().equals("csd") || branch.toLowerCase().equals("csam") || branch.toLowerCase().equals("csb") ||branch.toLowerCase().equals("csss") )
		{
				student_list.add(new Student1( cgpa, branch));
				return("Student Added");
		}
		else
			return("Error in branch");
	}
	
	//Printing the details of all the students enrolled
	public static void printStudent()
	{
		for(int iterator=0;iterator<student_list.size();iterator++)
		{
			System.out.println(student_list.get(iterator).getRoll_number()+" "+student_list.get(iterator).getCgpa()+" "+student_list.get(iterator).getBranch());
		}
	}
	
	//Get the records of the students who are matching the course criteria
	
	public static ArrayList<Student1> getmatchingstudent(String[] criteria)
	{
		ArrayList<Student1> templis=new ArrayList<>();
		for(int i=0;i<criteria.length;i++)
		{
			for(int j=0;j<student_list.size();j++)
			{
				if(student_list.get(j).getBranch().toLowerCase().equals(criteria[i].toLowerCase()))
					templis.add(student_list.get(j));
			}
		}
		return templis;
	}
	
	//Setting marks of students to the company currently selecting students
	
	public void set_marks_to_current_company_techscore(String company_name)
	{
		for(int t=0;t<this.companies_applied.size();t++)
			if(this.companies_applied.get(t).toLowerCase().equals(company_name.toLowerCase()))
			{
				this.setMarks(this.tech_score_in_companies_applied.get(t));
				break;
			}
	}
	
	//Removing the records of the students who are placed
	
	public static void remove_placed_students()
	{
		int i=0;
		while(i<student_list.size())
		{
			
			if(student_list.get(i).getPlaced())
			{
				System.out.println(student_list.get(i).getRoll_number());
				student_list.remove(i);
				i--;
			}
			i++;
		}
	}

}
class Company
{
	//All the instance variables of the class are defined here
	
	private String Name;
	private int number_required;
	private int number_of_course;
	private String[] course_criteria;
	private int[] student_placed_here;
	private String Application_Status;
	private ArrayList<Student1> applied_students=new ArrayList<>();
	private static ArrayList<Company> company_list=new ArrayList<>();
	
	//Parameterized Constructor
	
	Company(String Name,int number_required,int number_of_course,String[] course_criteria)
	{
		this.Name=Name;
		this.number_required=number_required;
		this.number_of_course=number_of_course;
		this.course_criteria=course_criteria;
		this.Application_Status="OPEN";
	}
	
	//Adding the company record
	public static void addCompany(String Name,int number_required,int number_of_course,String[] course_criteria)
	{
		Scanner in=new Scanner(System.in);
		company_list.add(new Company(Name, number_required, number_of_course, course_criteria) );
		displayCompany(company_list.get(company_list.size()-1));

		//Statement for getting students matching students
		ArrayList<Student1> temp=Student1.getmatchingstudent(company_list.get(company_list.size()-1).getCourse_criteria());
		
		for(int k=0;k<temp.size();k++)
		{
			if(!temp.get(k).getPlaced())
			{
			System.out.println("Enter the score of tech round for roll number "+temp.get(k).getRoll_number()+" : ");
			int score=in.nextInt();
			temp.get(k).setCompanies_applied(Name);
			temp.get(k).setTech_score_in_companies_applied(score);
			}
		}
		company_list.get(company_list.size()-1).applied_students=temp;	
	}
	//Getter and setter functions of the private variables
	
	public void setApplication_Status(String application_Status) {
		Application_Status = application_Status;
	}

	public String getName() {
		return Name;
	}
	public int getNumber_required() {
		return number_required;
	}
	public String[] getCourse_criteria() {
		return course_criteria;
	}
	public String getApplication_Status() {
		return Application_Status;
	}
	// The function that is used to select students for a particular company from those who have applied
	public  void SelectStudents()
	{
		if(this!=null)
		{
			for(int j=0;j<this.applied_students.size();j++)
			{
				if(!this.applied_students.get(j).getPlaced())
				{
					this.applied_students.get(j).set_marks_to_current_company_techscore(this.getName());

					// print statement to check if the marks are getting update or not
					//System.out.println(this.applied_students.get(j).getRoll_number()+" "+this.applied_students.get(j).getMarks());
					
				}
			}
			if(Student1.getNumber_of_students()-Student1.getPlaced_student_number()>=this.number_required)
			{
				this.student_placed_here=new int[this.number_required];
				
				if(this.applied_students.size()<number_required)
				{
					int count=0;
					for(int inner=0;inner<this.applied_students.size();inner++)
					{
						if(!this.applied_students.get(inner).getPlaced())
						{
						this.student_placed_here[inner]=this.applied_students.get(inner).getRoll_number();
						this.applied_students.get(inner).setPlaced(true);
						this.applied_students.get(inner).setCompany_name(Name);
						Student1.setPlaced_student_number(1);	
						count++;
						}
					}
				}
				else
				{
					int count=0;
					for(int outer=0;outer<number_required;outer++)
					{
						int maxroll=0;
						int techscore=0;
						float cgpa=0;
						int index=0;
						for(int inner=0;inner<this.applied_students.size();inner++)
						{
							if(!this.applied_students.get(inner).getPlaced() && this.applied_students.get(inner).getMarks()>techscore)
							{
								maxroll=this.applied_students.get(inner).getRoll_number();
								cgpa=this.applied_students.get(inner).getCgpa();
								techscore=this.applied_students.get(inner).getMarks();
								index=inner;
							}
							else if(!this.applied_students.get(inner).getPlaced() && this.applied_students.get(inner).getMarks()==techscore && this.applied_students.get(inner).getCgpa()>cgpa)
							{
								maxroll=this.applied_students.get(inner).getRoll_number();
								cgpa=this.applied_students.get(inner).getCgpa();
								techscore=this.applied_students.get(inner).getMarks();
								index=inner;
							}
						}
						if(maxroll!=0)
						{
						this.student_placed_here[outer]=maxroll;
						this.applied_students.get(index).setPlaced(true);
						this.applied_students.get(index).setCompany_name(Name);
						Student1.setPlaced_student_number(1);	
						count++;
						}
						else
							break;
					}
					if(count==this.number_required)
						this.setApplication_Status("Closed");
				}
			}
			else
			{
				this.student_placed_here=new int[this.number_required];
				int count=0;
				for(int inner=0;inner<this.applied_students.size();inner++)
				{
					if(!this.applied_students.get(inner).getPlaced())
					{
					company_list.get(company_list.size()-1).student_placed_here[inner]=this.applied_students.get(inner).getRoll_number();
					this.applied_students.get(inner).setPlaced(true);
					this.applied_students.get(inner).setCompany_name(Name);
					Student1.setPlaced_student_number(1);	
					count++;
					}
				}
			}
		}
		else
		{
			System.out.println("Company not found ");
			return;
		}
		System.out.println("Students Placed here are :");
		for(int j=0;j<this.student_placed_here.length && this.student_placed_here[j]!=0;j++)
			System.out.println(this.student_placed_here[j]);

	}
	
	//This function is used to display the details of the company 
	public static void displayCompany(Company currentcompany)
	{
		System.out.println("Name of the Company : "+currentcompany.getName());
		System.out.println("Course Criteria : " +currentcompany.printCourse_criteria());
		System.out.println("Number of Students required : "+currentcompany.getNumber_required());
		System.out.println("Application Status : "+currentcompany.getApplication_Status());	
	}
	
	//Return the course criteria for a particular company
	public String printCourse_criteria() {
		String s="";
		for(int i=0;i<course_criteria.length;i++)
			s=s+ "  "+course_criteria[i];
		return s;
	}
	
	//Return object of class company by taking input as name
	public static  Company getCompany(String Companyname)
	{
		for(int ii=0;ii<company_list.size();ii++)
		{
			if(company_list.get(ii).getName().toLowerCase().equals(Companyname.toLowerCase()))
				return(company_list.get(ii));
		}
		System.out.println("Company Not Found");
		return(null);
	}
	
	//Print out all the companies whose application status is open
	public static void open_company()
	{
		for(int ii=0;ii<company_list.size();ii++)
		{
			if(company_list.get(ii).getApplication_Status().toLowerCase().equals("open"))
				System.out.println(company_list.get(ii).getName());
		}
	}

	//Delete the record of the company whose apllication status is now closed
	
	public static void delete_closed_company()
	{
		int i=0;
		while(i<company_list.size())
		{
			if(company_list.get(i).getApplication_Status().toLowerCase().equals("closed"))
			{
				System.out.println(company_list.get(i).getName());
				company_list.remove(i);
				i--;
			}
			i++;
		}
	}
}

//This is the main class where the input driven menu is set up

public class Placement {

	public static void main(String[] args) {
	
		Scanner in=new Scanner(System.in);
		
		//Taking the inputs of the students
		
		System.out.println("Please Enter the number of students required :");
		int n=in.nextInt();
		Student1.setNumber_of_students(n);
		for(int i=0;i<n;i++)
		{
			float cgpa=in.nextFloat();
			String branch=in.next();
			String temp=Student1.AddStudent( cgpa, branch);
			if(temp.equals("Error in branch"))
			{
				System.out.println("Please Enter your branch (Valid branches are: CSE,CSD,ECE,CSSS,CSB,CSAM)");
				n+=1;
			}	
		}
		System.out.println("--Students Registered--");

		int temp;
		
		//Menu driven loop
		
		while(Student1.getPlaced_student_number()!=n)
		{
			temp=in.nextInt();
			
			
			if(temp==1)
			{
				//Add company
				//Inputs: name, course criteria, number of required students
				//The course criteria input will be taken in the following manner: number of courses eligible
				//(say x), the next x strings will be the courses.
				//Display details of the company (Company Name, Course Criteria, Number of required
				//students, Application Status)

				String Name=in.next();
				int number_of_course=in.nextInt();
				String[] course_criteria=new String[number_of_course];
				for(int iterate=0;iterate<number_of_course;iterate++)
					course_criteria[iterate]=in.next();
				int number_required=in.nextInt();
				Company.addCompany(Name, number_required, number_of_course, course_criteria);
			}
			else if(temp==2)
			{

//				Remove the accounts of the placed students
//				Output: Display roll numbers of students whose accounts were removed

				System.out.println("Accounts removed for :");
				Student1.remove_placed_students();
			}
			else if(temp==3)
			{
//				Remove the accounts of companies whose applications are closed
//				Output: Display name of companies whose accounts were removed
			
				System.out.println("Accounts removed for :");
				Company.delete_closed_company();
			}
			else if(temp==4)
			{
				
//				Display number of unplaced students
				
				System.out.println(Student1.getNumber_of_students()-Student1.getPlaced_student_number() + "  students left");
			}
			else if(temp==5)
			{
				
//				Display names of companies whose applications are open
				
				Company.open_company();
			}
			else if(temp==6)
			{
//				Select students
//				Input: Company name
//				Output: Roll numbers of selected students
				
				System.out.println("Enter the company name :");
				String s=in.next();
				Company current=Company.getCompany(s);
				if(current!=null)
				current.SelectStudents();
			}
			else if(temp==7)
			{
//				Display details of the company
//				Input: Company name
//				Output: Company Name, Course Criteria, Number of required students, Application
//				Status
				
				System.out.println("Enter the name of the Company :");
				String name=in.next();
				Company currentCompany=Company.getCompany(name);
				if(currentCompany!=null)
				{
				System.out.println("Name of the company : "+currentCompany.getName());
				System.out.println("Course Criteria :" + currentCompany.printCourse_criteria());
				System.out.println("Number of required Students : "+currentCompany.getNumber_required());
				System.out.println("Application Staus : "+currentCompany.getApplication_Status());
				}
			}
			else if(temp==8)
			{
//				Display details of the student
//				Input: Roll number
//				Output: Roll number, CGPA, Course, Placement Status, Company in which student is
//				placed (if not placed, display nothing)
				
				System.out.println("Please Enter your roll number");
				int roll=in.nextInt();
				Student1 current=Student1.findrollNumber(roll);
				if(current!=null)
				{
				System.out.println("Roll Number : "+current.getRoll_number());
				System.out.println("CGPA is : "+current.getCgpa());
				System.out.println("Branch : "+current.getBranch());
				if(current.getPlaced())
				{
					System.out.println("Placement Status : Placed");
					System.out.println("Placed in : "+current.getCompany_name_placed());
				}
				else
				{
					System.out.println("Placement Status : Not Placed");
				}
				}
			}
			else if(temp==9)
			{
//				Display names of companies for which the student has applied and their scores in
//				technical round of that company
//				Input: Roll number
				
				System.out.println("Please Enter your roll number");
				int roll=in.nextInt();
				Student1 current=Student1.findrollNumber(roll);
				if(current!=null)
				{
				ArrayList<String> company_names=current.getCompanies_applied();
				ArrayList<Integer> tech_scores=current.getTech_score_in_companies_applied();
				
				for(int j=0;j<company_names.size();j++)
				{
					System.out.println(company_names.get(j)+" "+tech_scores.get(j));
				}
				}
			}
			else
				System.out.println("Wrong Query ");
		}
		
	}	
}
