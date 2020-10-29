import java.io.*;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
	private boolean login = true; 
	private int usertype = 0;
	private String username;
	
	public void setusertype(int usertype) {
		this.usertype = usertype;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	
	public int getusertype() {
		return this.usertype;
	}
	
	public String getusername() {
		return this.username;
	}
	
	public void student() {
		if (login("studentdetails.csv") == 1) {
			setusertype(1); //set the type of user (user)
		} 
	}
	
	public void admin() {
		if (login("admindetails.csv") == 1) {
			setusertype(2); //set the type of user (admin)	
		}
	}
	
	//hashing  
	public String hashing(String rawinput) {
		try { 
			  
            // Select between 1.MD52 2.SHA-1 3.SHA-256(Strongest)
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
            // convert to array of byte 
            byte[] messageDigest = md.digest(rawinput.getBytes()); 
  
            // Convert to signum representation 
            BigInteger temp = new BigInteger(1, messageDigest); 
  
            // Convert to hex value 
            String convertedinput = temp.toString(16); 
            while (convertedinput.length() < 32) { 
            	convertedinput = "0" + convertedinput; 
            } 
            return convertedinput; 
        }  
  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
	}
	
	private int login(String file) {
		
		int row = 3;
		
		//Input file which needs to be parsed
	    List <User> studentList = new ArrayList <>();
	    String line = null;

	    // Read all lines in from CSV file and add to studentList
	    BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    try {
			while ((line = bufferedReader.readLine()) != null) {
			    String[] temp = line.split(",");
			    String matriNo = temp[0];
			    String name = temp[1];
			    String gender = temp[2];
			    String nationality = temp[3];
			    String password = temp[4];
			    studentList.add(new User(matriNo, name, gender, nationality, password));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try {
			bufferedReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    Scanner keyboard = new Scanner (System.in); //scan keyboard
	  
			System.out.println("Enter Username:");
		    String inputUser = keyboard.nextLine();
		    System.out.println("Enter Password:");
		    String inputPass = hashing(keyboard.nextLine()); // gets input from user 
		    
		    
		    int count = 0; 	
		    while (count < studentList.size()) {
		    		if (inputUser.equals((studentList.get(count)).getMatriNo()) && inputPass.equals((studentList.get(count)).getPassword())) {
		    			System.out.print("You have logged in successfully as " + (studentList.get(count)).getMatriNo() + "\n");
		    			setusername(studentList.get(count).getMatriNo()); 
		    			return 1; 
		    	}
		    	count++;
		    }
		    System.out.print("Incorrect username or password.\n");
	    	return 0;
    		 
	}
}
		    
/* testing array successful by printing
for (User s: studentList){
    System.out.println(s.toString());
}


int count = 0; 		
while (studentList.size() > count) {
	System.out.println((studentList.get(count)));
    count++;
 }
 */
	
