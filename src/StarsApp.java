//package src;
import java.util.Scanner; // I use scanner because it's command line.
<<<<<<< Updated upstream

public class StarsApp {

	private static Login login = new Login();
	//testing
	//e
=======
import java.io.*;
public class StarsApp {	
>>>>>>> Stashed changes
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
<<<<<<< Updated upstream
		// Login login = new Login();

		boolean retry = true; // retry until user/admin is logged in

		System.out.println("(1) Login as Student");
		System.out.println("(2) Login as Admin");

		while (retry) {
			System.out.println("Enter the number of your choice:");
			switch (sc.nextInt()) {
				case 1:
					login.student();
		    	  if (login.getusertype() == 1) {
		    	  retry = false;
		    	  }
		    	  break;
		      case 2: 
		    	  login.admin();
		    	  if (login.getusertype() == 2) {
		    	  retry = false;
		    	  }
		    	  break;
=======
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
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	}

	public static void AppManager() {
		AppManager app = new AppManager();
		Course c1 = new Course("CZ2002","Object Oriented Programming","SCSE");
		Course c2 = new Course("CZ2005","Databases","SCSE");
		c1.addIndex("001");
		c1.addIndex("002");
		c2.addIndex("001");
		c2.addIndex("002");
		Student s = login.getstudent();
		app.addCourse(c1);
		app.addCourse(c2);
		app.addStudent(s);
		//app.addStudent(s2);
		//s2.addCourse(c1.getIndex().get(0));
		StudentManager sm = new StudentManager();
		sm.setUser(s);
		int choice;
=======
		else{
			int choice;
>>>>>>> Stashed changes
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