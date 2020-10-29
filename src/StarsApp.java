import java.util.Scanner; // I use scanner because it's command line.

public class StarsApp {
	public static void main(String[] args) {
		 
		Scanner sc = new Scanner(System.in); 
	    Login app = new Login();
	    
	    boolean retry = true; //retry until user/admin is logged in
	    
	    System.out.println("(1) Login as User");
		System.out.println("(2) Login as Admin");
		
		while (retry) {
			System.out.println("Enter the number of your choice:");
			switch (sc.nextInt()) {
		      case 1: 	 
		    	  app.student();
		    	  if (app.getusertype() == 1) {
		    	  retry = false;
		    	  }
		    	  break;
		      case 2: 
		    	  app.admin();
		    	  if (app.getusertype() == 2) {
		    	  retry = false;
		    	  }
		    	  break;
			}
		
		}
		
		if (app.getusertype() == 1) {
			System.out.println(app.getusername());
		}
		
		else if (app.getusertype() == 2) {
			System.out.println(app.getusername());
		}
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

