//package src;
import java.util.Scanner; // I use scanner because it's command line.
import java.io.*;
public class StarsApp {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DataManager app = new DataManager();
		FileIO file = new FileIO();
		file.readCourse(app);
		file.readStudent(app);
		file.readCourseIndex(app);
		LoginManager lm = new LoginManager();
		String[] array = lm.Login(app);
		AdminManager am = new AdminManager();
		StudentManager sm = new StudentManager();
		if(array[0]=="1"){
			for(int i=0;i<app.getAdmin().size();i++){
				if(array[1].equals(app.getAdmin().get(i).getUserName())){
					am.setUser(app.getAdmin().get(i));
					break;
				}
			}
		}
		else{
			for(int i=0;i<app.getStudent().size();i++){
				if(array[1].equals(app.getStudent().get(i).getUserName())){
					sm.setUser(app.getStudent().get(i));
					break;
				}
			}
		}
		if(array[0]=="1"){
			//Admin Options
		}
		else{
			int choice;
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
			sm.addCourse(app);
		}
		else if(choice==2)
			sm.dropCourse(app);
		else if(choice==3)
			sm.checkRegistered();
		else if(choice==4)
			sm.checkVacancy(app);
		else if(choice==5)
			sm.changeIndex(app);
		else if(choice==6)
			sm.swopIndex(app);
		}while(choice!=7);
		}
	}
}