import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class LoginManager {
    public String[] Login(DataManager app){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for admin, 2 for Student");
        int choice = sc.nextInt();
        sc.nextLine();
        boolean check1=false;
        boolean check2=false;
        if(choice==1){
            String inputUser="";
            do{
                System.out.println("Enter Username:");
                inputUser = sc.nextLine();
                if(searchAdmin(inputUser, app)==false){
                    System.out.println("Incorrect Username, try again");
                }
                else{
                    check1=true;
                }
            }while(check1==false);
            do{
                System.out.println("Enter Password:");
                String inputPass = hashing(sc.nextLine());
                if(searchAdmin(inputPass, app)==false){
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
        else{
            String inputUser="";
            do{
                System.out.println("Enter Username:");
                inputUser = sc.nextLine();
                if(searchStudent(inputUser, app)==false){
                    System.out.println("Incorrect Username, try again");
                }
                else{
                    check1=true;
                }
            }while(check1==false);
            do{
                System.out.println("Enter Password:");
                String inputPass = sc.nextLine();
                if(verifyStudent(inputPass, app)==false){
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
    }

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
    
    public boolean searchStudent(String student,DataManager app){
        for(int i=0;i<app.getStudent().size();i++){
            if(student.equals(app.getStudent().get(i).getUserName())){
                return true;
            }
        }
        return false;
    }

    public boolean searchAdmin(String admin,DataManager app){
        for(int i=0;i<app.getAdmin().size();i++){
            if(admin.equals(app.getAdmin().get(i).getUserName())){
                return true;
            }
        }
        return false;
    }

    public boolean verifyStudent(String password,DataManager app){
        for(int i=0;i<app.getStudent().size();i++){
            if(password.equals(app.getStudent().get(i).getPassword())){
                return true;
            }
        }
        return false;
    }

    public boolean verifyAdmin(String password,DataManager app){
        for(int i=0;i<app.getAdmin().size();i++){
            if(password.equals(app.getAdmin().get(i).getPassword())){
                return true;
            }
        }
        return false;
    }

    /*public <Any>Any createManager(int choice,String inputUser,DataManager app){
        if(choice==1){
            for(int i=0;i<app.getAdmin().size();i++){
                if(inputUser.equals(app.getAdmin().get(i).getUserName())){
                    AdminManager am = new AdminManager(app.getAdmin().get(i));
                    return ((Any)((AdminManager)(am)));;
                }
            }
        }
        else if(choice==2){
            for(int i=0;i<app.getStudent().size();i++){
                if(inputUser.equals(app.getStudent().get(i).getUserName())){
                    StudentManager sm = new StudentManager(app.getStudent().get(i));
                    return ((Any)((StudentManager)(sm)));
                }
            }
        }*/
    }
