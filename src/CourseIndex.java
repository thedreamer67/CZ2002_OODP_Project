import java.util.ArrayList;

public class CourseIndex extends Course {
	private String indexNo;
	private int vacancy;
	private ArrayList<Lesson> lessons;
	private ArrayList<String> waitingList;
	private ArrayList<Student> studentList;
	
	public CourseIndex(String courseCode, String courseName, String school, int numOfAUs, String indexNo){
		super(courseCode,courseName,numOfAUs,school);
		this.indexNo=indexNo;
		this.waitingList = new ArrayList<String>();
		this.studentList = new ArrayList<Student>();
		this.lessons = new ArrayList<Lesson>();
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

	public void setVacancy(int vacancy) {
		this.vacancy=vacancy;
	}
	

	public void addLesson(String type, String location,String dayOfWeek,int time,int duration,int[] lessonWeek) {
		this.lessons.add(new Lesson(type,location,dayOfWeek,time,duration,lessonWeek));
	}


	public ArrayList<Lesson> getLessons(){
		return this.lessons;
	}
	

	public void addWaitingList(String matricNo) {
		this.waitingList.add(matricNo);
	}
	

	public ArrayList<String> getWaitingList(){
		return this.waitingList;
	}


	public void dequeueWaitingList(){
		this.waitingList.remove(0);
	}
	

	public void addStudent(Student s) {
		this.studentList.add(s);
		this.vacancy-=1;
	}

	public void addStudentFile(Student s) {
		this.studentList.add(s);
	}

	public void removeStudent(Student s) {
		String matric = s.getUserName();
		for(int i=0;i<studentList.size();i++){
			if(studentList.get(i).getUserName().equals(matric)){
				studentList.remove(i);
			}
		}
		this.vacancy+=1;
	}


	public ArrayList<Student> getStudentList(){
		return this.studentList;
    }


	public void printLesson(){
		for(int i=0;i<lessons.size();i++){
			System.out.println(this.lessons.get(i).getType()+","+this.lessons.get(i).getLocation()+","+this.lessons.get(i).getDayOfWeek());
		}
	}
}