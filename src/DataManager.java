import java.util.ArrayList;

public class DataManager {
	private ArrayList<Student> student;
	private ArrayList<Admin> admin;
	private ArrayList<Course> course;
	private String[] accessperiod;
	
	public DataManager() {
		this.student = new ArrayList<Student>();
		this.course = new ArrayList<Course>();
		this.admin = new ArrayList<Admin>();
		this.accessperiod = new String[3];
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

		return this.accessperiod;
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
	
	public void addAccessPeriod(String a, String b) {
		accessperiod[0] = a; //start date 
		accessperiod[1] = b; //end date 
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
	
}