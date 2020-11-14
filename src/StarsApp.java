//package src;
import java.util.Scanner; // I use scanner because it's command line.

public class StarsApp {

	private static Login login = new Login();
	//testing
	//e
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
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
			}
		
		}
		
		if (login.getusertype() == 1) {
			System.out.println(login.getusername());
			AppManager();

		}

		else if (login.getusertype() == 2) {
			System.out.println(login.getusername());
		}
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
		do {
		Scanner sc = new Scanner(System.in);
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



/* Old code
while (scantext.hasNextLine()) {
String line = scantext.nextLine();
System.out.println(line);
}

import java.util.Scanner; // I use scanner because it's command line.
import java.io.*; 

public class login {
	public static void main(String[] args) {
	    Scanner scantext = null; //scan text from keyboard 
	    Scanner keyboard = new Scanner (System.in); //scan keyboard
		try {
			scantext = new Scanner (new File("out.txt"));
		    String user = scantext.nextLine();
		    String pass = scantext.nextLine(); // looks at selected file in scan

		    String inpUser = keyboard.nextLine();
		    String inpPass = keyboard.nextLine(); // gets input from user

		    if (inpUser.equals(user) && inpPass.equals(pass)) {
		        System.out.print("your login message");
		    } else {
		        System.out.print("your error message");
			scantext.close();
		    }
		  
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
}


String test = "testing"; 
System.out.println("Your HashCode Generated is: " + app.hashing(test));

*/

