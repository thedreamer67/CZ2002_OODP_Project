
import java.util.Scanner; // I use scanner because it's command line.
import java.io.*;
public class StarsApp {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DataManager dm = new DataManager();
		FileIOManager file = new FileIOManager();
		file.readAdmin(dm);
		file.readCourse(dm);
		file.readStudent(dm);
		file.readCourseIndex(dm);
		file.readAccessperiod(dm);
		
		LoginManager lm = new LoginManager();
		String[] array = lm.Login(dm);
		AdminManager am = new AdminManager();
		StudentManager sm = new StudentManager();
		int selected = Integer.parseInt(array[0]);

		switch(selected){
			case 1:
				
			for(int i=0;i<dm.getAdmin().size();i++){
				if(array[1].equals(dm.getAdmin().get(i).getUserName())){
							am.setUser(dm.getAdmin().get(i));
							break;
				}
			}

			int choice = 0;
			do{
				System.out.println("1. Edit student access period");
				System.out.println("2. Add a student");
				System.out.println("3. Add a course");
				System.out.println("4. Update a course");
				System.out.println("5. Check availability slot for an index");
				System.out.println("6. Print student list by index");
				System.out.println("7. Print student list by course");
				System.out.println("8. Exit");
				System.out.println("Enter Choice");
				choice = sc.nextInt();
				if(choice==1) 
					am.editAccessPeriod(dm);
				else if(choice==2)
					am.addAStudent(dm);
				else if(choice==3)
					am.addACourse(dm);
				else if(choice==4)
					am.UpdateACourse(dm);	
				else if(choice==5)
					am.checkVacancy(dm);
				else if(choice==6)
					am.printStudentByIndex(dm);
				else if(choice==7)
					am.printStudentByCourse(dm);
			}while(choice!=8);
			break; 

			case 2: 
				
				for(int i=0;i<dm.getStudent().size();i++){
					if(array[1].equals(dm.getStudent().get(i).getUserName())){
							sm.setUser(dm.getStudent().get(i));
							break;
					}
				}
			
			do {
			System.out.println();
			System.out.println("1. Register a course");
			System.out.println("2. Drop a course");
			System.out.println("3. Check/Print Courses Registered");
			System.out.println("4. Check/Print Waiting List");
			System.out.println("5. Check Vacancies Available");
			System.out.println("6. Swap Index Number of Course");
			System.out.println("7. Swap Index with Another Student");
			System.out.println("8. Exit");
			System.out.println("Enter Choice");
			choice = sc.nextInt();
			if(choice==1) {
				sm.addCourse(dm);
			}
			else if(choice==2)
				sm.dropCourse(dm);
			else if(choice==3)
				sm.checkRegistered();
			else if(choice==4)
				sm.checkWaitingList();
			else if(choice==5)
				sm.checkVacancy(dm);
			else if(choice==6)
				sm.changeIndex(dm);
			else if(choice==7)
				sm.swopIndex(dm);
			}while(choice!=8);
			
			break;
				
		}
		
		
	}
}