import java.util.ArrayList;

public class Student implements User {
	private String name;
	private String userName;
	private char gender;
	private String nationality;
	private String password;
	String email;
	int totalAUs;
	private ArrayList<CourseIndex> courseRegistered;
	
	public Student(String name, String matricNo, char gender, String nationality, String password, String email) {
		this.name=name;
		this.userName=matricNo;
		this.gender=gender;
		this.nationality=nationality;
		this.password=password;
		this.email = email;
		this.totalAUs = 0;
		this.courseRegistered = new ArrayList<CourseIndex>();
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName=userName;
	}
	
	public char getGender() {
		return this.gender;
	}
	public void setGender(char gender) {
		this.gender=gender;
	}
	
	public String getNationality() {
		return this.nationality;
	}
	public void setNationality(String nationality) {
		this.nationality=nationality;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password=password;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email=email;
	}

	public int getTotalAUs() {
		return this.totalAUs;
	}
	public void setTotalAUs(int totalAUs) {
		this.totalAUs=totalAUs;
	}
	
	public ArrayList<CourseIndex> getCourseRegistered() {
		return this.courseRegistered;
	}

	public void addCourse(CourseIndex index) {
		courseRegistered.add(index);
		this.totalAUs += index.getNumOfAUs();
		//index.addStudent(this);
	}
	
	
	public void checkRegistered() {
		System.out.println("\nCourses currently registered for student "+this.name);
		for(int i=0;i<this.courseRegistered.size();i++) {
			System.out.println((i+1)+". "+this.courseRegistered.get(i).getCourseCode()+"\t"+this.courseRegistered.get(i).getCourseName()+"\t"+this.courseRegistered.get(i).getIndexNo());
		}
	}
	public char[] get(int i) {
		return null;
	}
}
