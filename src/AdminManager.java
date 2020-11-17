import java.util.*;
import java.util.ArrayList;

public class AdminManager {
    private Admin user;

    public void setUser(Admin a){
        this.user = a;
    }

    public void editAccessPeriod(DataManager app) {
        Scanner sc = new Scanner(System.in);
        String tempdate = "0";
        String[] temp = app.getAccessPeriod();
        System.out.println("\nStart date is : " + temp[0]);
        System.out.println("End date is : " + temp[1]);
        System.out.println("1.Update start date");
        System.out.println("2.Update end date");

        if (sc.nextInt() == 1){
            System.out.println("Enter new start date");
            tempdate = sc.next();
            app.addAccessPeriod(tempdate, temp[1]); // update start while keeping end same
        }
        else if (sc.nextInt() == 2){
            System.out.println("Enter new end date");
            tempdate = sc.next();
            app.addAccessPeriod(temp[0], tempdate); // update end while keeping start same
        }

        System.out.println("\nDate successfully updated.");
        System.out.println("Current start date is : " + temp[0]);
        System.out.println("Current end date is : " + temp[1] + "\n");

    }

    public void addAStudent(DataManager app) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter student's name:");
        String tempname = sc.next();

        System.out.println("Enter student's ID:");
        String tempmatricNo = sc.next().toUpperCase();

        System.out.println("Enter student's sex:");
        char tempgender = sc.next().charAt(0);

        System.out.println("Enter student's nationality:");
        String tempnationality = sc.next();

        System.out.println("Enter student's password:");
        String temppassword = sc.next();

        Student tempstudent = new Student (tempname, tempmatricNo, tempgender, tempnationality,temppassword);
        app.addStudent(tempstudent);

        System.out.println("Student successfully added.");
        System.out.println("Current names of student(s):");
        for (int i=0; i<app.getStudent().size(); i++){
            Student s = app.getStudent().get(i);
            System.out.println((i+1) + ". " + s.getName());
        }

    }

    public void addACourse(DataManager app) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter course code:");
        String tempcourseCode = sc.next();

        System.out.println("Enter course name:");
        String tempcourseName = sc.next().toUpperCase();

        System.out.println("Enter course's school:");
        String tempschool = sc.next();

        Course tempcourse = new Course (tempcourseCode, tempcourseName, tempschool);
        app.addCourse(tempcourse);

        System.out.println("Course successfully added.\n");
        
        System.out.println("Current code of the course(s):");
        app.printCourse();
        System.out.println(" "); //extra spacing
    }

    public void UpdateACourse(DataManager app) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Current code of the course(s):");
        app.printCourse();
        System.out.println(" "); //extra spacing
        
        System.out.println("Please enter course code of the course you wish to update:");
        String courseCode = sc.next().toUpperCase();
        boolean contiune = true;
		for(int i=0;i<app.getCourse().size();i++) {
			if(courseCode.equals(app.getCourse().get(i).getCourseCode())) {
                while(contiune){
                    System.out.println("1.Update course code");
                    System.out.println("2.Update course name");
                    System.out.println("3.Update course school");
                    System.out.println("4.Exit");	
                    int choice = sc.nextInt();
                    switch(choice){
                        case (1):
                            System.out.println("Enter new course code");
                            String tempcoursecode = sc.next().toUpperCase();
                            app.getCourse().get(i).setCourseCode(tempcoursecode);
                            System.out.println("Course code successfully updated.\n");
                            System.out.println("Current code of the course(s):");
                            app.printCourse();
                            System.out.println(" "); //extra spacing
                            break;
                        case (2):
                            System.out.println("Enter new course name");
                            String tempcoursename = sc.next();
                            app.getCourse().get(i).setCourseName(tempcoursename);
                            System.out.println("Course name successfully updated.\n");
                            System.out.println("Current code of the course(s):");
                            app.printCourse();
                            System.out.println(" "); //extra spacing
                            break;
                        case (3):
                            System.out.println("Enter new course school");
                            String tempcourseschool = sc.next();
                            app.getCourse().get(i).setSchool(tempcourseschool);
                            System.out.println("Course school successfully updated.\n");
                            System.out.println("Current code of the course(s):");
                            app.printCourse();
                            System.out.println(" "); //extra spacing
                            break;
                        case (4):
                            contiune = false;
                    }
                }	
            }
        }
        if (contiune == true){
            System.out.println("Invalid Course Code\n");
        }
    }

    public void checkVacancy(DataManager app) {
		Scanner sc = new Scanner(System.in);
		app.printCourse();
		System.out.println("Enter the course code of the course you wish to check vacancy for:");
		String courseCode = sc.next().toUpperCase();
		for(int i=0;i<app.getCourse().size();i++) {
			if(courseCode.equals(app.getCourse().get(i).getCourseCode())) {
                app.getCourse().get(i).getIndex().get(i).getVacancy();				
			}
		}
    }

    public void printStudent(DataManager app){
        //print student list by index number
        Scanner sc = new Scanner(System.in);
    
        app.printCourse();
        System.out.println("Enter the course code of the course that you wish to print the student list from:");
        String courseCode = sc.next().toUpperCase();
        for (int i=0; i<app.getStudent().size(); i++){
            Student s = app.getStudent().get(i);
            Course c = app.getCourse().get(i);
            if (courseCode.equals(c.getCourseCode()) && courseCode.equals(s.getCourseRegistered().get(i).getCourseCode())){
                app.getCourse().get(i).printVacancy();
                System.out.println("Enter the course index that you wish to print the student list from:");
                String courseIndex = sc.next();
                if (courseIndex.equals(s.getCourseRegistered().get(i).getIndexNo()))
                    System.out.println(s.getUserName() + "\t" + s.getName());
               
            }
        }

    }

    public void printStudentByCourse(DataManager app){
        Scanner sc = new Scanner(System.in);
        app.printCourse();
        System.out.println("Enter the course code of the course that you wish to print the student list from:");
        String courseCode = sc.next().toUpperCase();
        for (int i=0; i<app.getStudent().size(); i++)
        {
            Student s = app.getStudent().get(i);
            Course c = app.getCourse().get(i);
            if (courseCode.equals(c.getCourseCode()) && courseCode.equals(s.getCourseRegistered().get(i).getCourseCode())){
                System.out.println(s.getUserName() + "\t" + s.getName());
                
            }
        }
        

    }
    
}
