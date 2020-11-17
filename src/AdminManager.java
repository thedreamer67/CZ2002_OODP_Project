import java.util.*;

public class AdminManager {
    private Admin user;

    public void setUser(Admin a){
        this.user = a;
    }

    public void checkVacancy(DataManager app) {
		Scanner sc = new Scanner(System.in);
		app.printCourse();
		System.out.println("Enter the course code of the course you wish to check vacancy for:");
		String courseCode = sc.next().toUpperCase();
		for(int i=0;i<app.getCourse().size();i++) {
			if(courseCode.equals(app.getCourse().get(i).getCourseCode())) {
				app.getCourse().get(i).printVacancy();
			}
		}
    }

    public void printStudent(DataManager app){
        //print student list by index number
        Scanner sc = new Scanner(System.in);
    
        app.printCourse();
        System.out.println("Enter the course code of the course that you wish to print the student list from:");
        String courseCode = sc.next().toUpperCase();
        for (int i=0; i<app.getCourse().size(); i++){
            Student s = app.getStudent().get(i);
            Course c = app.getCourse().get(i);
            if (courseCode.equals(c.getCourseCode()) && courseCode.equals(s.getCourseRegistered().get(i).getCourseCode())){
            
                app.getCourse().get(i).printVacancy();
                System.out.println("Enter the course index that you wish to print the student list from:");
                String courseIndex = sc.next();
                for (int j=0; j<s.getCourseRegistered().size(); j++){
                    if (courseIndex.equals(s.getCourseRegistered().get(j).getIndexNo())){
                        System.out.println(s.getUserName() + "\t" + s.getName());
                    }
                }
                System.out.println();
                break;
               
            }
            
        }

    }

    public void printStudentByCourse(DataManager app){
        Scanner sc = new Scanner(System.in);
        app.printCourse();
        System.out.println("Enter the course code of the course that you wish to print the student list from:");
        String courseCode = sc.next().toUpperCase();
        for (int i=0; i<app.getCourse().size(); i++)
        {
            Student s = app.getStudent().get(i);
            Course c = app.getCourse().get(i);
            if (courseCode.equals(c.getCourseCode()) && courseCode.equals(s.getCourseRegistered().get(i).getCourseCode())){
                System.out.println(s.getUserName() + "\t" + s.getName());
                
            }
            System.out.println();
            break;
        }
        

    }
    
}
