import java.util.ArrayList;

public class AppManager {
	private ArrayList<Student> student;
	//private ArrayList<Admin> admin;
	private ArrayList<Course> course;
	
	public AppManager() {
		this.student = new ArrayList<Student>();
		this.course = new ArrayList<Course>();
	}
	
	public ArrayList<Course> getCourse(){
		return this.course;
	}
	
	public ArrayList<Student> getStudent(){
		return this.student;
	}
	
	public void addStudent(Student s) {
		student.add(s);
	}
	
	public void addCourse(Course c) {
		course.add(c);
	}
	
	public void printCourse() {
		for(int i=0;i<this.course.size();i++) {
			System.out.println(course.get(i).getCourseCode()+" "+course.get(i).getCourseName());
		}
	}
	
}