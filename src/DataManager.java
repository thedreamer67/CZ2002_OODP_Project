import java.util.ArrayList;

public class DataManager {
	private ArrayList<Student> student;
	private ArrayList<Admin> admin;
	private ArrayList<Course> course;
	
	public DataManager() {
		this.student = new ArrayList<Student>();
		this.course = new ArrayList<Course>();
		this.admin = new ArrayList<Admin>();
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
	
	public void addStudent(Student s) {
		student.add(s);
	}
	
	public void addCourse(Course c) {
		course.add(c);
	}

	public void addAdmin(Admin a) {
		admin.add(a);
	}
	
	public void printCourse() {
		for(int i=0;i<this.course.size();i++) {
			System.out.println(course.get(i).getCourseCode()+" "+course.get(i).getCourseName());
		}
	}
	
}