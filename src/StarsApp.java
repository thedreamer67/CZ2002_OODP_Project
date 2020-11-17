
import java.util.Scanner; // I use scanner because it's command line.
import java.io.*;
public class StarsApp {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DataManager data = new DataManager();
		fileIO file = new fileIO();
		file.readAdmin(data);
		file.readCourse(data);
		file.readStudent(data);
		file.readCourseIndex(data);
		LoginManager lm = new LoginManager();
		String[] array = lm.Login(data);
		AdminManager am = new AdminManager();
		StudentManager sm = new StudentManager();
		if(array[0]=="1"){
			for(int i=0;i<data.getAdmin().size();i++){
				if(array[1].equals(data.getAdmin().get(i).getUserName())){
					am.setUser(data.getAdmin().get(i));
					break;
				}
			}
			
		}

		else if(array[0]=="2"){
			for(int i=0;i<data.getStudent().size();i++){
				if(array[1].equals(data.getStudent().get(i).getUserName())){
					sm.setUser(data.getStudent().get(i));
					break;
				}
			}

			int choice = 0;
			do {
			System.out.println("1.Register a course");
			System.out.println("2.Drop a course");
			System.out.println("3.Check/Print Courses Registered");
			System.out.println("4.Check Vacancies Available");
			System.out.println("5.Swap Index Number of Course");
			System.out.println("6.Swop Index with Another Student");
			System.out.println("7.Exit");
			System.out.println("Enter Choice");
			choice = sc.nextInt();
			if(choice==1) {
				sm.addCourse(data);
			}
			else if(choice==2)
				sm.dropCourse(data);
			else if(choice==3)
				sm.checkRegistered();
			else if(choice==4)
				sm.checkVacancy(data);
			else if(choice==5)
				sm.changeIndex(data);
			else if(choice==6)
				sm.swopIndex(data);
			}while(choice!=7);
		}

		
		int choice=0;
			do{
				System.out.println("1.Check availability slot for an index");
				System.out.println("2.Print student list by index");
				System.out.println("3.Print student list by course");
				//System.out.println("4.Check Vacancies Available");
				//System.out.println("5.Swap Index Number of Course");
				//System.out.println("6.Swop Index with Another Student");
				System.out.println("7.Exit");
				System.out.println("Enter Choice");
				choice = sc.nextInt();
				if(choice==1) {
					am.checkVacancy(data);
				}
				else if(choice==2)
					am.printStudent(data);
				else if(choice==3)
					am.printStudentByCourse(data);
				/*else if(choice==4)
					sm.checkVacancy(data);
				else if(choice==5)
				sm.changeIndex(data);
				else if(choice==6)
					sm.swopIndex(data);*/
			}while(choice!=7);
		
	}
}