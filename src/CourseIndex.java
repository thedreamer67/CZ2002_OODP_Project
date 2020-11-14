import java.util.ArrayList;

public class CourseIndex extends Course {
	private String indexNo;
	private int vacancy;
	private ArrayList<Lesson> lessons;
	private ArrayList<String> waitingList;
	private ArrayList<Student> studentList;
	
	public CourseIndex(String courseCode,String courseName,String school, String indexNo){
		super(courseCode,courseName,school);
		this.indexNo=indexNo;
		this.waitingList = new ArrayList<String>();
	}
	
	public String getIndexNo() {
		return this.indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo=indexNo;
	}
	
	public int getVacancy() {
		return this.vacancy;
	}
	public boolean setLimit(int limit) {
		int totalRegistered = this.limit-this.vacancy;
		if(limit>totalRegistered) {
			this.limit = limit;
			return true;
		}
		else
			return false;
    }
    
    public void checkVacancy() {
        // check vacancy of the index
        // if there's vacancy:
            // update waiting list accordingly
            // call add course method for the student
            // call add student method for the course
            // call send email method
    }
	public void setVacancy(int vacancy) {
		this.vacancy=vacancy;
	}
	
	public void addLesson(String type, String location,String dayOfWeek,int time,int duration,int[] lessonWeek) {
		this.lessons.add(new Lesson(type,location,dayOfWeek,time,duration,lessonWeek));
	}
	
	//public void 
	
	/*public void addStudent(Student s) {
		this.studentList.add(s);
	}
	public ArrayList<Student> getStudent(){
		return this.studentList;
	}*/

}
