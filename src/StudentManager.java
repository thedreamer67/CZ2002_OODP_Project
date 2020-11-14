import java.util.*;

public class StudentManager {
	private Student user;
	
	public void setUser(Student student) {
		this.user=student;
	}
	
	public void addCourse(AppManager app) {
		Scanner sc = new Scanner(System.in);
		app.printCourse();
		System.out.println("Enter the course code of the course you wish to register in");
		String courseCode = sc.next().toUpperCase();
		for(int i=0;i<app.getCourse().size();i++) {
			if(courseCode.equals(app.getCourse().get(i).getCourseCode())) {
				Course c = app.getCourse().get(i);
				System.out.println("Enter the index you wish to register for");
				c.printVacancy();
				String index = sc.next().toUpperCase();
				for(int j=0;j<c.getIndex().size();j++) {
					if(c.getIndex().get(j).getIndexNo().equals(index)) {
						user.addCourse(c.getIndex().get(j));
						System.out.println("Successful");
						user.checkRegistered();
						break;
					}
				}
				break;
			}
			}
	}
	
	public void dropCourse() {
		Scanner sc = new Scanner(System.in);
		this.user.checkRegistered();
		System.out.println("Enter the Course Code of the course you wish to drop");
		String index = sc.next().toUpperCase();
		for(int i=0;i<this.user.getCourseRegistered().size();i++) {
			if(this.user.getCourseRegistered().get(i).getCourseCode().equals(index)) {
				/*for(int j=0;j<this.user.getCourseRegistered().get(i).getStudent().size();j++) {
					if(this.user.getMatricNo().equals(this.user.getCourseRegistered().get(i).getStudent().get(j).getMatricNo())) {
						this.user.getCourseRegistered().get(i).getStudent().remove(j);
						break;
					}
				}*/
				this.user.getCourseRegistered().remove(i);
				break;
			}
		}
		System.out.println("Course successfully dropped.");
		System.out.println("\n");
		this.user.checkRegistered();
		
		
		
	}
	
	public void checkRegistered() {
		this.user.checkRegistered();
	}
	
	public void checkVacancy(AppManager app) {
		Scanner sc = new Scanner(System.in);
		app.printCourse();
		System.out.println("Enter the course code of the course you wish to check vacancy for");
		String courseCode = sc.next().toUpperCase();
		for(int i=0;i<app.getCourse().size();i++) {
			if(courseCode.equals(app.getCourse().get(i).getCourseCode())) {
				app.getCourse().get(i).printVacancy();
			}
		}
	}
	
	public void changeIndex(AppManager app) {
		Scanner sc = new Scanner(System.in);
		this.user.checkRegistered();
		System.out.println("Enter course code of the course that you wish to swap index");
		String courseCode = sc.next().toUpperCase();
		int arrayIndexInStudent = -1;
		for(int i=0;i<this.user.getCourseRegistered().size();i++) {
			if(courseCode.equals(this.user.getCourseRegistered().get(i).getCourseCode())) {
				arrayIndexInStudent = i;
				break;
			}
		}
		int arrayIndexInApp=-1;
		for(int j=0;j<app.getCourse().size();j++) {
			if(courseCode.equals(app.getCourse().get(j).getCourseCode())){
				arrayIndexInApp=j;
				app.getCourse().get(j).printVacancy();
				break;
			}
		}
		System.out.println("Enter new index of the course");
		String index = sc.next().toUpperCase();
		for(int a=0;a<app.getCourse().get(arrayIndexInApp).getIndex().size();a++) {
			if(index.equals(app.getCourse().get(arrayIndexInApp).getIndex().get(a).getIndexNo())) {
				this.user.getCourseRegistered().remove(arrayIndexInStudent);
				this.user.addCourse(app.getCourse().get(arrayIndexInApp).getIndex().get(a));
				System.out.println("Changing of course index successful");
				this.user.checkRegistered();
			}
		}
	}
	
	public void swopIndex(AppManager app) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Matriculation Number of the Student you wish to swap with");
		String matricNo = sc.next().toUpperCase();
		System.out.println("Enter Course Code that you wish to swap in");
		String courseCode = sc.next().toUpperCase();
		CourseIndex temp;
		int arrayIndexOfOtherStudent=-1;
		int arrayIndex1=-1;
		for(int i=0;i<app.getStudent().size();i++) {
			if(matricNo.equals(app.getStudent().get(i).getMatricNo())) {
				arrayIndexOfOtherStudent = i;
				for(int j=0;j<app.getStudent().get(i).getCourseRegistered().size();j++) {
					if(courseCode.equals(app.getStudent().get(i).getCourseRegistered().get(j).getCourseCode())) {
						arrayIndex1 = j;
						break;
					}
				}
				break;
			}
		}
		int arrayIndex2=-1;
		for(int a=0;a<this.user.getCourseRegistered().size();a++) {
			if(this.user.getCourseRegistered().get(a).getCourseCode().equals(courseCode)) {
				arrayIndex2 = a;
				break;
			}
		}
		temp = app.getStudent().get(arrayIndexOfOtherStudent).getCourseRegistered().get(arrayIndex1);
		app.getStudent().get(arrayIndexOfOtherStudent).getCourseRegistered().remove(arrayIndex1);
		app.getStudent().get(arrayIndexOfOtherStudent).addCourse(this.user.getCourseRegistered().get(arrayIndex2));
		this.user.getCourseRegistered().remove(arrayIndex2);
		this.user.addCourse(temp);
		System.out.println("Swopping of index successful");
		this.user.checkRegistered();
	}
	
}
