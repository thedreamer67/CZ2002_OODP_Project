import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DataManager {
	private ArrayList<Student> student;
	private ArrayList<Admin> admin;
	private ArrayList<Course> course;
	private String[] accessPeriod;
	private final int MAX_AU=23; 
	
	public DataManager() {
		this.student = new ArrayList<Student>();
		this.course = new ArrayList<Course>();
		this.admin = new ArrayList<Admin>();
		this.accessPeriod = new String[3];
	}
	
	public ArrayList<Course> getCourse(){
		return this.course;
	}
	
	public ArrayList<Student> getStudent(){
		return this.student;
	}

	public ArrayList<Admin> getAdmin(){
		return this.admin;
	}
	public String[] getAccessPeriod(){

		return this.accessPeriod;
	}
	
	public void addStudent(Student s) {
		student.add(s);
		// write to student.txt to add student
	}
	
	public void addCourse(Course c) {
		course.add(c);
		// write to course.txt method to add course
	}

	public void addAdmin(Admin a) {
		admin.add(a);
		// write to admin.txt to add admin
	}
	
	public void editAccessPeriod(String a, String b) {
		accessPeriod[0] = a; //start date 
		accessPeriod[1] = b; //end date
		// write to accessperiod.txt to change access period
	}

	public void printCourse() {
		for(int i=0;i<this.course.size();i++) {
			System.out.println(course.get(i).getCourseCode()+" "+course.get(i).getCourseName());
		}
	}

	public void printStudent() {
		for (int i = 0; i < this.getStudent().size(); i++) {
            Student s = this.getStudent().get(i);
            System.out.println((i + 1) + ". " + s.getName());
        }
	}

	// returns the index of the array where Course Code is found
	public int findCourse(String courseCode){
		for(int i=0;i<this.course.size();i++){
			if(courseCode.equals(this.course.get(i).getCourseCode())){
				return i; //returns index of array if found
			}
		}
		return -1; //else return -1 indicating Course Code do not exist
	}

	//returns the index of the array where course index is found
	public int findCourseIndex(String courseIndex,int arrayIndexofCourse){
		for(int i=0;i<this.course.get(arrayIndexofCourse).getIndex().size();i++){
			if(courseIndex.equals(this.course.get(arrayIndexofCourse).getIndex().get(i).getIndexNo())){
				return i; //returns index of array if found
			}
		}
		return -1; //else return -1 indicating Couse Index  do not exist in the current course
	}

	//returns the index of the array where student matriculation number is found
	public int findStudent(String matricNo){
		for(int i=0;i<this.getStudent().size();i++){
			if(matricNo.equals(this.getStudent().get(i).getUserName()))
			return i; //returns index of array if found
		}
		return -1; //else return -1 indicating student matric number do not exist in the current list
	}

	public int getMaxAU() {
		return this.MAX_AU;
	}


	// method to check if a date entered is valid
	public boolean isValid(String date) {
        boolean valid = false;
        try {

            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            LocalDate.parse(date,
                DateTimeFormatter.ofPattern("uuuu-MM-dd")
                    .withResolverStyle(ResolverStyle.STRICT)
            );

            valid = true;
        } catch (DateTimeParseException e) {
            //e.printStackTrace();
            valid = false;
        }
        return valid;
	}
}