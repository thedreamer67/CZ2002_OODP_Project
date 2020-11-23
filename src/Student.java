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
	private ArrayList<String> waitingList;
	
	public Student(String name, String matricNo, char gender, String nationality, String password, String email, int totalAUs) {
		this.name=name;
		this.userName=matricNo;
		this.gender=gender;
		this.nationality=nationality;
		this.password=password;
		this.email = email;
		this.totalAUs = totalAUs;
		this.courseRegistered = new ArrayList<CourseIndex>();
		this.waitingList = new ArrayList<String>();
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
	public void dropCourse(CourseIndex index) {
		for(int i=0;i<this.courseRegistered.size();i++)
		{
			if(index.getCourseCode().equals(this.courseRegistered.get(i).getCourseCode())){
				this.courseRegistered.remove(i);
				break;
			}
		}
		this.totalAUs -= index.getNumOfAUs();
	}

	public ArrayList<String> getWaitingList(){
		return this.waitingList;
	}

	public void addWaitingList(String courseCode,String courseName,String courseIndex){
		this.waitingList.add(courseCode);
		this.waitingList.add(courseName);
		this.waitingList.add(courseIndex);
	}

	public void checkWaitingList() {
		if(this.waitingList.size()<1){
			System.out.println("Waiting list is currently empty");
			return;
		}
		System.out.println("\nCourses currently in waiting list of student "+this.name);
		for(int i=0;i<this.waitingList.size();i+=3) {
			System.out.println(this.waitingList.get(i)+" "+this.waitingList.get(i+1)+"\t"+this.waitingList.get(i+2));
		}
	}

	//method to remove a course from the waiting of a student
	public boolean dropWaitingList(String courseCode) {
		for(int i=0;i<this.waitingList.size();i+=3){
			if(courseCode.equals(this.waitingList.get(i))){
				this.waitingList.remove(i+2); //remove index
				this.waitingList.remove(i+1); //remove course name
				this.waitingList.remove(i); //remove course code
				return true;
			}
		}
		return false;
	}

	//returns index of array where course code is found
	public int findCourse(String courseCode){
		for(int i=0;i<this.courseRegistered.size();i++){
			if(courseCode.equals(this.courseRegistered.get(i).getCourseCode())){
				return i;
			}
		}
		return -1;
	}
	
	
	public void checkRegistered() {
		if(this.courseRegistered.size()==0){
			System.out.println("There are no courses registered for this student");
			return;
		}
		System.out.println("\nCourses currently registered for student "+this.name);
		for(int i=0;i<this.courseRegistered.size();i++) {
			System.out.println((i+1)+". "+this.courseRegistered.get(i).getCourseCode()+"\t"+this.courseRegistered.get(i).getCourseName()+"\t"+this.courseRegistered.get(i).getIndexNo());
		}
	}
	//method to delete all references to other objects
	public void deleteStudent(){
		for(int i=0;i<this.courseRegistered.size();i++){ //deleting all items in course registered
			this.courseRegistered.remove(i);
		}
	}

	public char[] get(int i) {
		return null;
	}
}
