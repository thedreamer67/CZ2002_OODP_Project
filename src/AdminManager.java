import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminManager {
    private Admin user;


    public void setUser(Admin a) {
        this.user = a;
    }


    public void editAccessPeriod(DataManager dm) {
        Scanner sc = new Scanner(System.in);
        String tempdate = "0";
        String[] temp = dm.getAccessPeriod();
        System.out.println("\nStart date is: " + temp[0]);
        System.out.println("End date is: " + temp[1]);
        System.out.println("1. Update start date");
        System.out.println("2. Update end date");
        System.out.println("3. Exit");
        int choice = sc.nextInt();

        //update either start or end date depending on user's choice
        if (choice == 1) {
                System.out.println("Enter new start date (YYYY-MM-DD)");
                tempdate = sc.next();
            while (dm.isValid(tempdate)==false) { // check if date entered is valid
                System.out.println("Date entered is invalid or is not in YYYY-MM-DD format.");
                System.out.println("Enter new start date again (YYYY-MM-DD)");
                tempdate = sc.next();
            }
            dm.editAccessPeriod(tempdate, temp[1]); // update start date while keeping end date same
        } else if (choice == 2) {
            System.out.println("Enter new end date (YYYY-MM-DD)");
            tempdate = sc.next();
            while (dm.isValid(tempdate)==false) { // check if date entered is valid
                System.out.println("Date entered is invalid or is not in YYYY-MM-DD format.");
                System.out.println("Enter new start date again (YYYY-MM-DD)");
                tempdate = sc.next();
            }
            dm.editAccessPeriod(temp[0], tempdate); // update end date while keeping start date same
        }

        System.out.println("\nDate successfully updated.");
        System.out.println("Current start date is: " + temp[0]);
        System.out.println("Current end date is: " + temp[1] + "\n");
    }


    public void addAStudent(DataManager dm) {
        Scanner sc = new Scanner(System.in);
        //Add student's name
        System.out.println("Enter student's name:");
        String tempName = sc.next();

        //validation to ensure valid name
        while (tempName.matches("[A-Za-z]+") == false){
            System.out.println("Invalid name! Please enter again.");
            tempName = sc.next();
        };

        //Add student's ID
        System.out.println("Enter student's ID:");
        String tempMatricNo = sc.next().toUpperCase();
        
        //validation to ensure there are no existing same student ID
        while (dm.findStudent(tempMatricNo) != -1){
            System.out.println("Student ID exist! Please enter again.");
            tempMatricNo = sc.next().toUpperCase();
        };

        //Add student's sex
        System.out.println("Enter student's sex (F/M):");
        char tempSex = Character.toUpperCase(sc.next().charAt(0));

        //validation to ensure valid sex
        while (tempSex != 'M' && tempSex != 'F'){
            System.out.println("Invalid sex! Please enter again (F/M):");
            tempSex = Character.toUpperCase(sc.next().charAt(0));
        };

        //Add student's nationality
        System.out.println("Enter student's nationality:");
        String tempNationality = sc.next();

        // input password  will be hashed
        System.out.println("Enter student's password:");
        String tempPassword = LoginManager.hashing(sc.next());

        //Add student's email
        System.out.println("Enter student's email:");
        String tempEmail = sc.next().toLowerCase();

        Student tempStudent = new Student(tempName, tempMatricNo, tempSex, tempNationality, tempPassword, tempEmail, 0);
        dm.addStudent(tempStudent);

        System.out.println("Student successfully added.\n");
        System.out.println("Current names of student(s):");

        //print all the student's name after update
        dm.printStudent();
        System.out.println(" "); // extra spacing
    }


    public void addACourse(DataManager dm) {
        Scanner sc = new Scanner(System.in);
        
        //add course code
        System.out.println("Enter course code:");
        String tempcourseCode = sc.next().toUpperCase();

         //validation to ensure there are no existing course code
        while (dm.findCourse(tempcourseCode) != -1){
            System.out.println("Course code exist! Please enter again.");
            tempcourseCode = sc.next();
        };
       
        //add course name
        System.out.println("Enter course name:");
        String tempcourseName = sc.next();
        

        //add course AUs
        System.out.println("Enter number of AUs the course has:");
        String stringtempAU = sc.next();

        //validation to ensure valid AUs
        while (stringtempAU.matches("[0-9]+") == false){
            System.out.println("Invalid AUs! Please enter again.");
            stringtempAU = sc.next();
        };
        int tempAU=Integer.parseInt(stringtempAU); 

        //add course school
        System.out.println("Enter course's school:");
        String tempschool = sc.next();

        while (!tempschool.matches("[A-Za-z]+"))
        {
            System.out.println("Invalid course school! Please enter again.");
            tempschool = sc.next();

        }
        Course tempcourse = new Course(tempcourseCode, tempcourseName, tempAU, tempschool);
        dm.addCourse(tempcourse);
        
        //find the input course in the arraylist of courses
        int courseSelected = dm.findCourse(tempcourseCode);

        //add course index
        System.out.println("Enter course index:");
        String tempindex = sc.next();

        while (!tempindex.matches("[0-9]+"))
        {
            System.out.println("Invalid course index! Please enter again.");
            tempindex = sc.next();
        }

        dm.getCourse().get(courseSelected).addIndex(tempindex);
        int findCourse = dm.findCourse(tempcourseCode);
        if (dm.findCourseIndex(tempindex, findCourse) == 0){
            System.out.println("Course successfully added.\n");
            System.out.println("Current code of the course(s):");
        }
      
        dm.printCourse();
        System.out.println(" "); // extra spacing
    }


    public void UpdateACourse(DataManager dm) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Current codes of courses:");
        dm.printCourse();
        System.out.println(" "); // extra spacing

        System.out.println("Please enter course code of the course you wish to update:");
        String courseCode = sc.next().toUpperCase();
        int courseindex = dm.findCourse(courseCode);
        boolean cont = true;

        //update particular course details depending on user's choice
        if (courseindex != -1) {
            while (cont) {
                System.out.println("1.Update course code");
                System.out.println("2.Update course name");
                System.out.println("3.Update course AUs");
                System.out.println("4.Update course school");
                System.out.println("5.Update course index");
                System.out.println("6.Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case (1):   // update course code
                        System.out.println("Enter new course code:");
                        String tempcoursecode = sc.next().toUpperCase();
                        dm.getCourse().get(courseindex).setCourseCode(tempcoursecode);


                        System.out.println("Course code successfully updated.\n");
                        System.out.println("Current list of the course(s):");
                        dm.printCourse();
                        System.out.println(" "); // extra spacing
                        break;
                    case (2):   // update course name
                        System.out.println("Enter new course name:");
                        String tempcoursename = sc.next();
                        dm.getCourse().get(courseindex).setCourseName(tempcoursename);

                        System.out.println("Course name successfully updated.\n");
                        System.out.println("Current list of the course(s):");
                        dm.printCourse();
                        System.out.println(" "); // extra spacing
                        break;
                    case (3):   // update course AUs
                        System.out.println("Enter new course AUs");
                        String stringtempAU = sc.next();

                        //validation to ensure valid AUs
                        while (stringtempAU.matches("[0-9]+") == false){
                        System.out.println("Invalid AUs! Please enter again.");
                        stringtempAU = sc.next();
                        };
                        int tempAU=Integer.parseInt(stringtempAU); 
                        dm.getCourse().get(courseindex).setNumOfAUs(tempAU);

                        System.out.println("Course AUs successfully updated.\n");
                        System.out.println("Current list of the course(s):");
                        dm.printCourse();
                        System.out.println(" "); // extra spacing
                        break;
                    case (4):   // update course school
                        System.out.println("Enter new course school");
                        String tempcourseschool = sc.next().toUpperCase();
                        while (!tempcourseschool.matches("[A-Za-z]+"))
                        {
                            System.out.println("Invalid course school! Please enter again.");
                            tempcourseschool = sc.next();
                        }
                        dm.getCourse().get(courseindex).setSchool(tempcourseschool);
                        System.out.println("Course school successfully updated.\n");
                        System.out.println("Current list of the course(s):");
                        dm.printCourse();
                        System.out.println(" "); // extra spacing
                        break;
                    case (5):   // update course index
                        System.out.println("Current list of the course index(s): ");
                        dm.getCourse().get(courseindex).printVacancy();
                        System.out.println("Enter the course index you want to edit: ");
                        String tempindex = sc.next();
                        int indexSelected = dm.findCourseIndex(tempindex, courseindex);
                        if (indexSelected >= 0)
                        {
                            System.out.println("Enter new course index: ");
                            String newindex = sc.next();
                            dm.getCourse().get(courseindex).getIndex().get(indexSelected).setIndexNo(newindex);
                            System.out.println("Course index successfully updated.\n");
                            System.out.println("Updated list of the course index(s) in " + dm.getCourse().get(courseindex).getCourseCode() + ": ");
                            dm.getCourse().get(courseindex).printVacancy();
                            System.out.println(" "); // extra spacing
                        }
                        else
                            System.out.println("No such index exist.");
                        break;
                        
                    case (6):
                        cont = false;
                        break;
                    }
                }
            }
        if (cont == true) {
            System.out.println("Invalid Course Code\n");
        }
    }


    public void checkVacancy(DataManager dm) {
        Scanner sc = new Scanner(System.in);
        dm.printCourse();
        System.out.println("Enter the course code of the course:");
        String courseCode = sc.next().toUpperCase();

        //find the input course in the arraylist of courses
        int courseSelected = dm.findCourse(courseCode);

        //returns a value >=0 when input course is found in the list
        if (courseSelected >= 0){

            //prints the available indexes from the input course
            System.out.println("List of Index that is available for the course");
            ArrayList <CourseIndex> listofIndex = dm.getCourse().get(courseSelected).getIndex();
            for (CourseIndex courseindex: listofIndex)
            {
                System.out.println(courseindex.getIndexNo());
            }

            System.out.println("Enter the course index that you wish to check vacancy for:");
            String indexinput = sc.next();

            //find the input index in the arraylist of the indexes from input course
            int indexSelected = dm.findCourseIndex(indexinput, courseSelected);

            //returns a value >=0 when input index is found in the list
            if (indexSelected >= 0)
            {
                //get the vacancy value from the input index
                int vacancy = dm.getCourse().get(courseSelected).getIndex().get(indexSelected).getVacancy();
                System.out.println("Index " + dm.getCourse().get(courseSelected).getIndex().get(indexSelected).getIndexNo() + ", " + "Vacancies available: " + vacancy); 
            }
            else 
                System.out.println("No index found.");
              
        }
        else
            System.out.println("No course found.");

    }


    public void printStudentByIndex(DataManager dm){
        //print student list by index number
        Scanner sc = new Scanner(System.in);
    
        dm.printCourse();
        System.out.println("Enter the course code of the course that you wish to print the student list from:");
        String courseCode = sc.next().toUpperCase();
        
        //find the input course in the arraylist of courses
        int courseSelected = dm.findCourse(courseCode);

        //returns a value >=0 when input course is found in the list
        if (courseSelected >= 0){

            //prints the available indexes from the input course
            System.out.println("List of Index that is available for the course");
            ArrayList <CourseIndex> listofIndex = dm.getCourse().get(courseSelected).getIndex();
            for (CourseIndex courseindex: listofIndex)
            {
                System.out.println(courseindex.getIndexNo());
            }
            System.out.println("Enter the course index of the course that you wish to print the student list from:");
            String indexinput = sc.next();

            //find the input index in the arraylist of the indexes from input course
            int indexSelected = dm.findCourseIndex(indexinput, courseSelected);

            //returns a value >=0 when input index is found in the list
            if (indexSelected >= 0)
            {
                //find the list of students who have registered for the input course and input index
                ArrayList<Student> students = dm.getStudent();
                for (Student s : students) {
                    List<CourseIndex> index = s.getCourseRegistered();

                    for (CourseIndex indexNo : index){
                        //if there exists a student who have registered for the input course and input index
                        //once both found, if both matches the input course and input index 
                        if (dm.getCourse().get(courseSelected).getIndex().get(indexSelected).getIndexNo() == indexNo.getIndexNo() && courseCode.equals(indexNo.getCourseCode())){
                            //print student's name, sex and nationality
                           System.out.println(s.getName() + "\t" + s.getSex() + "\t" + s.getNationality());
                       }
                    }
                    
                }
                    
            }
            else 
                System.out.println("No index found.");
        }
        else
            System.out.println("No course found.");
        
   }


    public void printStudentByCourse(DataManager dm){
         //print student list by index number
         Scanner sc = new Scanner(System.in);
    
         dm.printCourse();
         System.out.println("Enter the course code of the course that you wish to print the student list from:");
         String courseCode = sc.next().toUpperCase();

         //find the input course in the arraylist of courses
         int courseSelected = dm.findCourse(courseCode);

         //returns a value >=0 when input course is found in the list
        if (courseSelected >= 0){
                ArrayList<Student> students = dm.getStudent();
                //find the list of students who have registered for the input course
                for (Student s : students) {
                    List<CourseIndex> index = s.getCourseRegistered();
                    for (CourseIndex indexNo : index){
                        //if there exists a student who have registered for the input course 
                        //and if the course found matches the input course and input index
                        if (dm.getCourse().get(courseSelected).getCourseCode() == indexNo.getCourseCode() && courseCode.equals(indexNo.getCourseCode())){
                            //print student's name, sex and nationality
                           System.out.println(s.getName() + "\t" + s.getSex() + "\t" + s.getNationality());
                       }
                    }
                    
                }

        }
        else
            System.out.println("No course found.");
    
    }
    

    //method to delete specific students
    public void deleteStudent(DataManager dm){
        Scanner sc = new Scanner(System.in);
        dm.printStudent();
        System.out.println("Enter student ID that you wish to delete");
        String matricNo = sc.nextLine().toUpperCase();
        int indexOfStudent = dm.findStudent(matricNo);
        if(indexOfStudent==-1){
			System.out.println("Student ID not found");
            return;}
        Student s = dm.getStudent().get(indexOfStudent);
        deleteStudentFromCourse(s, dm); //delete student object from student list in every course index
        s.deleteStudent(); //delete all course index objects in student class
        dm.getStudent().remove(indexOfStudent); //dereference student object in data manager
        System.out.println("Student "+s.getName()+" "+s.getUserName()+" successfully deleted\n");
        dm.printStudent();
    }


    //method to delete student object from every course index
    public void deleteStudentFromCourse(Student s,DataManager dm){
        for(int i=0;i<dm.getCourse().size();i++){ //for every course
            for(int j=0;j<dm.getCourse().get(i).getIndex().size();j++){ //for every course index
                for(int a=0;a<dm.getCourse().get(i).getIndex().get(j).getStudentList().size();a++){ //for every item in student list of course index
                    if(s.getUserName().equals(dm.getCourse().get(i).getIndex().get(j).getStudentList().get(a).getUserName())){ //if user ID are the same
                        dm.getCourse().get(i).getIndex().get(j).getStudentList().remove(a); //remove student object from course index student list
                    }
                }
            }
        }
    }


    public void deleteCourse(DataManager dm){
        Scanner sc = new Scanner(System.in);
        dm.printCourse();
        System.out.println("Enter course code that you wish to delete");
        String courseCode = sc.nextLine().toUpperCase();
        int indexOfCourse = dm.findCourse(courseCode);
        if(indexOfCourse==-1){
			System.out.println("\nCourse code not found");
            return;}
        Course c = dm.getCourse().get(indexOfCourse);
        deleteCourseFromStudent(c, dm); //delete course index objects in all students
        c.deleteCourse(); // delete references to other objects in course and course index
        dm.getCourse().remove(indexOfCourse); //dereference student object in data manager
        System.out.println("\nCourse "+c.getCourseCode()+" "+c.getCourseName()+" successfully deleted");
        dm.printCourse();
    }
    

    //method to delete all courses registered in students
    public void deleteCourseFromStudent(Course c,DataManager dm){
        for(int i=0;i<dm.getStudent().size();i++){
            for(int j=0;j<dm.getStudent().get(i).getCourseRegistered().size();j++){
                if(c.getCourseCode().equals(dm.getStudent().get(i).getCourseRegistered().get(j).getCourseCode())){
                    dm.getStudent().get(i).getCourseRegistered().remove(j);
                    break;
                }
            }
        }
    }

}
