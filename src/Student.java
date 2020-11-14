import java.util.ArrayList;

public class Student {
	private String name;
	private String matricNo;
	private String gender;
	private String nationality;
	private String password;
	private ArrayList<CourseIndex> courseRegistered;
	public Object matriNo;
	
	
	public Student(String matricNo, String name, String gender, String nationality, String password) {
		this.matricNo=matricNo;
		this.name=name;
		this.gender = gender;
		this.nationality = nationality;
		this.password = password;
		this.courseRegistered = new ArrayList<CourseIndex>();
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getMatricNo() {
		return this.matricNo;
	}
	public void setMatricNo(String matricNo) {
		this.matricNo=matricNo;
	}
	
	public String getGender() {
		return this.gender;
	}
	public void setGender(String gender) {
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
	
	public ArrayList<CourseIndex> getCourseRegistered() {
		return this.courseRegistered;
	}
	public void addCourse(CourseIndex index) {
		courseRegistered.add(index);
		//index.addStudent(this);
	}
	
	
	public void checkRegistered() {
		System.out.println("\nCourses currently registered for student "+this.name);
		for(int i=0;i<this.courseRegistered.size();i++) {
			System.out.println((i+1)+". "+this.courseRegistered.get(i).getCourseCode()+"\t"+this.courseRegistered.get(i).getCourseName()+"\t"+this.courseRegistered.get(i).getIndexNo());
		}
	}

	public boolean contains(String user) {
		return false;
	}
}
