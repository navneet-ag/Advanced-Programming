import java.util.ArrayList;
import java.util.Scanner;

class Student1{
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
	private static ArrayList<Integer> placed_roll_numbers=new ArrayList<>();
	private static ArrayList<Student1> student_list=new ArrayList();
	
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
	public void setPlaced(Boolean placed)
	{
		this.placed = placed;	
	}
	public void setCompany_name(String company_name) {
		this.Company_name_placed = company_name;
	}
	public Boolean  check_rollnum(int roll_number)
	{
		return(roll_number_lis[roll_number]);
	}
	public void setMarks(int marks) 
	{
		this.marks = marks;
	}
	public static void setNumber_of_students(int number_of_students) {
		Student1.number_of_students = number_of_students;
	}
	public static int getNumber_of_students() {
		return number_of_students;
	}
	public String getBranch() {
		return branch;
	}
	public int getRoll_number() {
		return roll_number;
	}
	public float getCgpa() {
		return cgpa;
	}
	public Boolean getPlaced() {
		return placed;
	}
	public static Student1 findrollNumber(int roll)
	{
		for(int i=0;i<student_list.size();i++)
		{
			if(student_list.get(i).getRoll_number()==roll)
				return(student_list.get(i));
		}
		System.out.println("No roll number found");
		return(null);
	}

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
	public static void printStudent()
	{
		for(int iterator=0;iterator<student_list.size();iterator++)
		{
			System.out.println(student_list.get(iterator).getRoll_number()+" "+student_list.get(iterator).getCgpa()+" "+student_list.get(iterator).getBranch());
		}
	}
	public static int getPlaced_student_number() {
		return placed_roll_numbers.size();
	}
	public int getMarks() {
		return marks;
	}
	
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
}
class Company{
	private String Name;
	private int number_required;
	private int number_of_course;
	private String[] course_criteria;
	protected int[] student_placed_here;
	private String Application_Status;
	protected ArrayList<Student1> applied_students=new ArrayList<>();
	private static ArrayList<Company> company_list=new ArrayList<>();
	Company(String Name,int number_required,int number_of_course,String[] course_criteria)
	{
		this.Name=Name;
		this.number_required=number_required;
		this.number_of_course=number_of_course;
		this.course_criteria=course_criteria;
		this.Application_Status="OPEN";
	}
	public static void addCompany(String Name,int number_required,int number_of_course,String[] course_criteria)
	{
		Scanner in=new Scanner(System.in);
		company_list.add(new Company(Name, number_required, number_of_course, course_criteria) );
//		System.out.println("length of company list is :" +company_list.size());
		displayCompany(company_list.get(company_list.size()-1));
		//Statement for getting students matching students
		ArrayList<Student1> temp=Student1.getmatchingstudent(company_list.get(company_list.size()-1).getCourse_criteria());
		for(int k=0;k<temp.size();k++)
		{
			System.out.println("Enter the score of tech round for roll number "+temp.get(k).getRoll_number()+" : ");
			int score=in.nextInt();
			temp.get(k).setMarks(score);
			temp.get(k).setCompanies_applied(Name);
			temp.get(k).setTech_score_in_companies_applied(score);
		}
		company_list.get(company_list.size()-1).applied_students=temp;
		
	}
	public void setApplication_Status(String application_Status) {
		Application_Status = application_Status;
	}
	
	public  void SelectStudents()
	{
		
		if(this!=null)
		{
			if(Student1.getNumber_of_students()-Student1.getPlaced_student_number()>=this.number_required)
			{
				this.student_placed_here=new int[this.number_required];
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
					company_list.get(company_list.size()-1).student_placed_here[outer]=maxroll;
					this.applied_students.get(index).setPlaced(true);
					this.applied_students.get(index).setCompany_name(Name);
				}
				this.setApplication_Status("Closed");
			}
			else
			{
				this.student_placed_here=new int[this.number_required];
				for(int inner=0;inner<this.applied_students.size();inner++)
				{
					company_list.get(company_list.size()-1).student_placed_here[inner]=this.applied_students.get(inner).getRoll_number();
					this.applied_students.get(inner).setPlaced(true);
					this.applied_students.get(inner).setCompany_name(Name);
				}
				
			}
		}
		else
		{
			System.out.println("Company not found ");
			
		}

	}
	public static void displayCompany(Company currentcompany)
	{
		System.out.println("Name of the Company : "+currentcompany.getName());
		System.out.println("Course Criteria : " +currentcompany.printCourse_criteria());
		System.out.println("Number of Students required : "+currentcompany.getNumber_required());
		System.out.println("Application Status : "+currentcompany.getApplication_Status());
		
	}
	public String getName() {
		return Name;
	}
	public int getNumber_required() {
		return number_required;
	}
	public String printCourse_criteria() {
		String s="";
		for(int i=0;i<course_criteria.length;i++)
			s=s+ "  "+course_criteria[i];
		return s;
	}
	public String[] getCourse_criteria() {
		return course_criteria;
	}
	public String getApplication_Status() {
		return Application_Status;
	}
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
	public static void open_company()
	{
		for(int ii=0;ii<company_list.size();ii++)
		{
			if(company_list.get(ii).getApplication_Status().toLowerCase().equals("open"))
				System.out.println(company_list.get(ii).getName());
		}
}
}

public class Placement {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
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
//		Student1.printStudent();
		int temp;
		while(Student1.getPlaced_student_number()!=n)
		{
			temp=in.nextInt();
			if(temp==1)
			{
				String Name=in.next();
				int number_of_course=in.nextInt();
				String[] course_criteria=new String[number_of_course];
				for(int iterate=0;iterate<number_of_course;iterate++)
					course_criteria[iterate]=in.next();
				int number_required=in.nextInt();
				Company.addCompany(Name, number_required, number_of_course, course_criteria);
			}
			else if(temp==4)
			{
				System.out.println(n-Student1.getPlaced_student_number());
			}
			else if(temp==5)
			{
				Company.open_company();
			}
			else if(temp==6)
			{
				System.out.println("Enter the company name :");
				String s=in.next();
				Company current=Company.getCompany(s);

				current.SelectStudents();
			}
			else if(temp==7)
			{
				System.out.println("Enter the name of the Company :");
				String name=in.next();
				Company currentCompany=Company.getCompany(name);
				System.out.println("Name of the company : "+currentCompany.getName());
				System.out.println("Course Criteria :" + currentCompany.printCourse_criteria());
				System.out.println("Number of required Students : "+currentCompany.getNumber_required());
				System.out.println("Application Staus : "+currentCompany.getApplication_Status());
			}
			else if(temp==8)
			{
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
				break;
		}
		
	}	
}
