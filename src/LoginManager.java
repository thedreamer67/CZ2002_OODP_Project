import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;

public class LoginManager {

    public String[] Login(DataManager dm){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for admin, 2 for Student");
        int choice = sc.nextInt();
        sc.nextLine();
        boolean check1=false;
        boolean check2=false;
        // admin login
        if(choice==1){
            String inputUser="";
            do{
                System.out.println("Enter Username:");
                inputUser = sc.nextLine().toUpperCase();
                if(searchAdmin(inputUser, dm)==false){
                    System.out.println("Incorrect Username, try again");
                }
                else{
                    check1=true;
                }
            }while(check1==false);
            do{
                System.out.println("Enter Password:");
                String inputPass = hashing(sc.nextLine());
                if(verifyAdmin(inputPass, dm)==false){
                    System.out.println("Incorrect Password, try again");
                }
                else{
                    System.out.println("Successful Login");
                    check2=true;
                }
            }while(check2==false);
            String choiceStr = Integer.toString(choice);
            String[] array = new String[2];
            array[0]=choiceStr;
            array[1]=inputUser;
            return array;
        }
        // student login
        else{
            if (checkAccess(dm) == true) {
                String inputUser="";
                do{
                    System.out.println("Enter Username:");
                    inputUser = sc.nextLine().toUpperCase();
                    if(searchStudent(inputUser, dm)==false){
                        System.out.println("Incorrect Username, try again");
                    }
                    else{
                        check1=true;
                    }
                }while(check1==false);
                do{
                    System.out.println("Enter Password:");
                    String inputPass = hashing(sc.nextLine());
                    if(verifyStudent(inputPass, dm)==false){
                        System.out.println("Incorrect Password, try again");
                    }
                    else{
                        System.out.println("Successful Login");
                        check2=true;
                    }
                }while(check2==false);
                String choiceStr = Integer.toString(choice);
                String[] array = new String[2];
                array[0]=choiceStr;
                array[1]=inputUser;
                return array;
            }
            else {
                String[] array = {"-1", "-1"};
                return array;
            }
        }
    }

    public static String hashing(String rawinput) {
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
    
    public boolean searchStudent(String student, DataManager dm){
        for(int i=0;i<dm.getStudent().size();i++){
            if(student.equals(dm.getStudent().get(i).getUserName())){
                return true;
            }
        }
        return false;
    }

    public boolean searchAdmin(String admin, DataManager dm){
        for(int i=0;i<dm.getAdmin().size();i++){
            if(admin.equals(dm.getAdmin().get(i).getUserName())){
                return true;
            }
        }
        return false;
    }

    public boolean verifyStudent(String password, DataManager dm){
        for(int i=0;i<dm.getStudent().size();i++){
            if(password.equals(dm.getStudent().get(i).getPassword())){
                return true;
            }
        }
        return false;
    }

    public boolean verifyAdmin(String password, DataManager dm){
        for(int i=0;i<dm.getAdmin().size();i++){
            if(password.equals(dm.getAdmin().get(i).getPassword())){
                return true;
            }
        }
        return false;
    }
    

    public boolean checkAccess(DataManager dm) {
        LocalDate startDate, endDate, today;
        startDate = LocalDate.parse(dm.getAccessPeriod()[0]);
        endDate = LocalDate.parse(dm.getAccessPeriod()[1]);
        today = LocalDate.now();
        if (today.isAfter(startDate) && today.isBefore(endDate)) {
            return true;
        }
        return false;
    }

}

