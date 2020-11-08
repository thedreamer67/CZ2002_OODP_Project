import java.util.ArrayList;


public class Student extends User {
	private String matricNo;
	private ArrayList<Course> courses;

	public Student (String name, char gender, String nationality, String password, String matricNo, ArrayList<Course> courses) {
		super(name, gender, nationality, password);
		this.matricNo = matricNo;
		for (Course c : courses)
			this.courses.add(c);
	}

	public void setMatricNo(String matricNo) {
		this.matricNo = matricNo;
	}

	public String getMatricNo() {
		return this.matricNo;
	}

	public void addCourse(String courseCode, String courseIndex) {

	}
}
