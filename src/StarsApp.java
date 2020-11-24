import java.util.Scanner; // use scanner because it's command line.

public class StarsApp {	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DataManager dm = new DataManager();
		FileIOManager file = new FileIOManager();
		file.readAdmin(dm);
		file.readCourse(dm);
		file.readStudent(dm);
		file.readCourseIndex(dm);
		file.readAccessPeriod(dm);

		LoginManager lm = new LoginManager();
		String[] array = lm.Login(dm);
		AdminManager am = new AdminManager();
		StudentManager sm = new StudentManager();
		int selected = Integer.parseInt(array[0]);

		switch(selected){
			// failed student login
			case -1:
				System.out.println("Student login failed.");
				System.out.println("You cannot access NTU STARS now because it is not within the access period stated by the administrators.\nPlease contact them if you think this is a mistake.");
				break;
			// admin login
			case 1:
				for(int i=0;i<dm.getAdmin().size();i++){
					if(array[1].equals(dm.getAdmin().get(i).getUserName())){
								am.setUser(dm.getAdmin().get(i));
								break;
					}
				}
				int choice = 0;
				do{
					System.out.println();
					System.out.println("Admin options:");
					System.out.println("1. Edit student access period");
					System.out.println("2. Add a student");
					System.out.println("3. Delete a student");
					System.out.println("4. Add a course");
					System.out.println("5. Delete a course");
					System.out.println("6. Update a course");
					System.out.println("7. Check availability slot for an index");
					System.out.println("8. Print student list by index");
					System.out.println("9. Print student list by course");
					System.out.println("10. Exit");
					System.out.println("Enter Choice");
					choice = sc.nextInt();
					if(choice==1) 
						am.editAccessPeriod(dm);
					else if(choice==2)
						am.addAStudent(dm);
					else if(choice==3)
						am.deleteStudent(dm);
					else if(choice==4)
						am.addACourse(dm);
					else if(choice==5)
						am.deleteCourse(dm);
					else if(choice==6)
						am.UpdateACourse(dm);	
					else if(choice==7)
						am.checkVacancy(dm);
					else if(choice==8)
						am.printStudentByIndex(dm);
					else if(choice==9)
						am.printStudentByCourse(dm);
				}while(choice!=10);
				break; 

			// student login
			case 2: 
				for(int i=0;i<dm.getStudent().size();i++){
					if(array[1].equals(dm.getStudent().get(i).getUserName())){
							sm.setUser(dm.getStudent().get(i));
							break;
					}
				}
			
				do {
				System.out.println();
				System.out.println("Student options:");
				System.out.println("1. Register for a course");
				System.out.println("2. Drop a course");
				System.out.println("3. Check/Print Courses Registered");
				System.out.println("4. Check/Print Timetable");
				System.out.println("5. Check/Print Waiting List");
				System.out.println("6. Drop a course from the Waiting List");
				System.out.println("7. Check Vacancies Available");
				System.out.println("8. Swap Index Number of Course");
				System.out.println("9. Swap Index with Another Student");
				System.out.println("10. Exit");
				System.out.println("Enter Choice");
				choice = sc.nextInt();
				if(choice==1) {
					sm.addCourse(dm);
				}
				else if(choice==2)
					sm.dropCourse(dm);
				else if(choice==3)
					sm.checkRegistered();
				else if (choice==4)
					sm.printTimetable();
				else if(choice==5)
					sm.checkWaitingList();
				else if(choice==6)
					sm.dropWaitingList();
				else if(choice==7)
					sm.checkVacancy(dm);
				else if(choice==8)
					sm.changeIndex(dm);
				else if(choice==9)
					sm.swopIndex(dm);
				} while(choice!=10);
				break;

			default:
				break;
				
		}

		// write back to the files to save whatever was done during the session
		file.writeCourse(dm);
		file.writeStudent(dm);
		file.writeCourseIndex(dm);
		file.writeAccessPeriod(dm);
	
	}
}