import java.util.ArrayList;

public class Student implements User {
	private String name;
    private String gender;
    private String nationality;
    private String matricNo;
    private String password;
	private String email;
	private ArrayList<CourseIndex> courseRegistered;
	//public Object matriNo;
	
	public Student(String name, String matricNo,char gender,String nationality,String password) {
		this.name=name;
		this.matricNo=matricNo;
		this.gender=gender;
		this.nationality=nationality;
		this.password=password;
		this.courseRegistered = new ArrayList<CourseIndex>();
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getUserName() {
		return this.matricNo;
	}
	public void setUserName(String matricNo) {
		this.matricNo=matricNo;
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
}
