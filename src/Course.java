import java.util.ArrayList;

public class Course {
	protected String courseCode;
	protected String courseName;
	protected String school;
	private ArrayList<CourseIndex> index;
	
	public Course(String courseCode,String courseName, String school) {
		this.courseCode=courseCode;
		this.courseName=courseName;
		this.school=school;
		this.index = new ArrayList<CourseIndex>();
	}
	
	public String getCourseCode() {
		return this.courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode=courseCode;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName=courseName;
	}
	
	public String getSchool() {
		return this.school;
	}
	public void setSchool(String school) {
		this.school=school;
	}
	
	public ArrayList<CourseIndex> getIndex() {
		return this.index;
	}
	
	public void addIndex(String indexNo) {
		CourseIndex ci = new CourseIndex(this.courseCode,this.courseName,this.school,indexNo);
		index.add(ci);
	}
	
	public void printVacancy() {
		System.out.println("Index\tVacancy");
		for(int i=0;i<index.size();i++) {
			System.out.println(index.get(i).getIndexNo()+"\t"+index.get(i).getVacancy());
		}
	}
	
	
}
