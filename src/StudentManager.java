import java.util.*;

public class StudentManager {
	private Student user;
	
	public void setUser(Student student) {
		this.user=student;
	}
	
	public void addCourse(DataManager dm) {
		Scanner sc = new Scanner(System.in);
		dm.printCourse();
		System.out.println("Enter the course code of the course you wish to register in");
		String courseCode = sc.next().toUpperCase();
		//get the index of array where the course is stored.
		int indexOfCourse = dm.findCourse(courseCode); 
		if(indexOfCourse==-1){
			System.out.println("Course Code not found");
			return;}

		//checks if registering for this course exceeds the maximum allowable number of AUs
		Course c = dm.getCourse().get(indexOfCourse);
		if((c.getNumOfAUs()+this.user.getTotalAUs())>23){
			System.out.println("Addition of this course will exceed the maximum number of AUs allowed, please drop a course first");
			return;}

		//check for unique course code within registered courses and waiting list
		if(checkUniqueRegistered(courseCode)==false && checkUniqueWaiting(courseCode)==false){
			System.out.println("The course has already been registered or in waiting list, no duplicates allowed");
			return;}

		dm.getCourse().get(indexOfCourse).printVacancy();
		System.out.println("Enter the index you wish to register for");
		String courseIndex = sc.next().toUpperCase();
		//get the index of array where the course index is stored.
		int indexOfCI = dm.findCourseIndex(courseIndex, indexOfCourse);
		if(indexOfCI==-1){
			System.out.println("Course Index not found");
			return;}

		CourseIndex ci = dm.getCourse().get(indexOfCourse).getIndex().get(indexOfCI);

		//check if there is clash in timetable
		if(checkTimetableClash(ci)==true){
			System.out.println("Clash in timetable");
			return;
		}

		if(ci.getVacancy()>0){ //checks if there is a vacancy for this course index
			this.user.addCourse(ci);
			ci.addStudent(this.user);
			System.out.println("Course successfully registered");
			this.user.checkRegistered();
		}
		else{
			//add to waiting list if vacancy is 0
			ci.addWaitingList(this.user.getUserName());
			this.user.addWaitingList(courseCode,ci.getCourseName(), courseIndex);
			System.out.println("No vacancy for this course index, added to waiting list");
			this.user.checkWaitingList();
		}
	}
	
	public void dropCourse(DataManager dm) {
		Scanner sc = new Scanner(System.in);
		this.user.checkRegistered();
		System.out.println("Enter the Course Code of the course you wish to drop");
		String courseCode = sc.next().toUpperCase();

		//retrieve index of the array where this specific course is stored within this student object
		int indexOfArray = this.user.findCourse(courseCode);
		if(indexOfArray==-1){
			System.out.println("Course Code not found");
			return;}

		CourseIndex ci = this.user.getCourseRegistered().get(indexOfArray);
		this.user.dropCourse(ci);
		ci.removeStudent(this.user);
		System.out.println("Course successfully dropped.");
		this.user.checkRegistered();
	}
	
	public void checkRegistered() {
		this.user.checkRegistered();
	}

	public void checkWaitingList(){
		this.user.checkWaitingList();
	}
	
	public void checkVacancy(DataManager dm) {
		Scanner sc = new Scanner(System.in);
		dm.printCourse();
		System.out.println("Enter the course code of the course you wish to check vacancy for");
		String courseCode = sc.next().toUpperCase();
		int indexOfCourse = dm.findCourse(courseCode);
		if(indexOfCourse==-1){
			System.out.println("Course Code not found");
			return;}
		dm.getCourse().get(indexOfCourse).printVacancy();
	}
	
	public void changeIndex(DataManager dm) {
		Scanner sc = new Scanner(System.in);
		this.user.checkRegistered();
		System.out.println("Enter course code of the course that you wish to swap index of");
		String courseCodeOld = sc.next().toUpperCase();
		int indexOfArrayStudent = this.user.findCourse(courseCodeOld);
		if(indexOfArrayStudent==-1){
			System.out.println("Course Code not found");
			return;}
		CourseIndex ciOld = this.user.getCourseRegistered().get(indexOfArrayStudent);

		int indexOfCourse = dm.findCourse(courseCodeOld);
		dm.getCourse().get(indexOfCourse).printVacancy();
		System.out.println("Enter new index of the course");
		String index = sc.next().toUpperCase();
		int indexOfNewCI = dm.findCourseIndex(index, indexOfCourse);
		if(indexOfNewCI==-1){
			System.out.println("Course index not found");
			return;}
		CourseIndex ciNew = dm.getCourse().get(indexOfCourse).getIndex().get(indexOfNewCI);

		// delete old course index
		this.user.dropCourse(ciOld);

		if(checkTimetableClash(ciNew)==true){
			this.user.addCourse(ciOld); //add back old course index because timetable clash
			System.out.println("Timetable clash, please select another index to swap with");
			return;
		}

		ciOld.removeStudent(this.user);

		// add new course index and update necessary information if there is vacancy else add to waiting list
		if(ciNew.getVacancy()>0){
			this.user.addCourse(ciNew);
			ciNew.addStudent(this.user);
			System.out.println("Changing of course index successful");
			this.user.checkRegistered();
		}
		else{
			this.user.addWaitingList(ciNew.getCourseCode(), ciNew.getCourseName(), ciNew.getIndexNo());
			ciNew.addWaitingList(this.user.getUserName());
			System.out.println("No vacancy for this course index, added to waiting list");
			this.user.checkWaitingList();
		}
	}
	
	public void swopIndex(DataManager dm) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Matriculation Number of the Student you wish to swap with");
		String matricNo = sc.next().toUpperCase();
		int indexOfOtherStudent = dm.findStudent(matricNo);
		if(indexOfOtherStudent==-1){ //return if invalid matric number
			System.out.println("Student not found");
			return;}
		//creates a reference to the other student object
		Student other = dm.getStudent().get(indexOfOtherStudent);

		this.user.checkRegistered();
		System.out.println("Enter Course Code that you wish to swap in");
		String courseCode = sc.next().toUpperCase();
		int indexOfCourseCurrent = this.user.findCourse(courseCode);
		if(indexOfCourseCurrent==-1){ //return if user enters invalid course code
			System.out.println("You are currently not registered for this course");
			return;}
		//creates a reference to the course index current student is enrolled in
		CourseIndex currentCourseIndex = this.user.getCourseRegistered().get(indexOfCourseCurrent);
		int indexOfCourseOther = other.findCourse(courseCode);
		if(indexOfCourseOther==-1){ //return if user enters a course code not registered for the other student
			System.out.println("The other student is not registered for the course");
			return;}
		//creates a reference to the course index other student is enrolled in
		CourseIndex otherCourseIndex = other.getCourseRegistered().get(indexOfCourseOther);
		

		this.user.dropCourse(currentCourseIndex); //drop current course to check if new timetable clashes
		if(checkTimetableClash(otherCourseIndex)==true){
			this.user.addCourse(currentCourseIndex); //if clash add the old course back
			System.out.println("Timetable clash");
			return;
		}
		currentCourseIndex.removeStudent(this.user);

		other.dropCourse(otherCourseIndex);//drop current course of other student to check if new timetable clashes
		StudentManager sm = new StudentManager();
		sm.setUser(other); /*creates new student manager object to use the check timetable function 
		as this function can only check timetable clash for their own user, hence the need to set the other student
		object as the current user*/

		if(sm.checkTimetableClash(currentCourseIndex)==true){
			other.addCourse(otherCourseIndex);
			System.out.println("Timetable clash for the student you are swapping with");
			return;
		}
		otherCourseIndex.removeStudent(other);

		//add new course index for current student and update student list in the course index
		this.user.addCourse(otherCourseIndex);
		otherCourseIndex.addStudent(this.user);

		//add new course index for other student and update student list in the course index
		other.addCourse(currentCourseIndex);
		currentCourseIndex.addStudent(other);

		System.out.println("Swopping of index successful");
		this.user.checkRegistered();
    }
    
    public void sendEmail() {
        // method to send an email to a student according to the email given, maybe take email as input
	}
	
	//method to check if a course code is unique within a student's registered courses
	public boolean checkUniqueRegistered(String courseCode){
		for(int i=0;i<this.user.getCourseRegistered().size();i++){
			if(courseCode.equals(this.user.getCourseRegistered().get(i).getCourseCode())){
				return false;
			}
		}
		return true;
	}

	//method to check if a course code is unique within a student's waiting list
	public boolean checkUniqueWaiting(String courseCode){
		for(int i=0;i<this.user.getWaitingList().size();i+=3){
			if(courseCode.equals(this.user.getWaitingList().get(i))){
				return false;
			}
		}
		return true;
	}

	public boolean checkTimetableClash(CourseIndex ci){
		for(int a=0;a<ci.getLessons().size();a++){
		//for each lesson in the newly registered course index
			for(int b=0;b<ci.getLessons().get(a).getLessonWeek().length;b++){
			// for each item in the lesson week array of newly registered course
				for(int c=0;c<this.user.getCourseRegistered().size();c++){
				//for each course registered under the student
					for(int d=0;d<this.user.getCourseRegistered().get(c).getLessons().size();d++){
					//for each lesson under this student's courses
						if(ci.getLessons().get(a).getDayOfWeek().equals(this.user.getCourseRegistered().get(c).getLessons().get(d).getDayOfWeek())){
						//comparing day of the week, if same compare further, else break since different day of week will never clash
							int startTimeThis = ci.getLessons().get(a).getTime();
							// multiply duration by 100 and add to start time to get end time since duration is in hours and start time is in 24hr format
							int endTimeThis = startTimeThis+(ci.getLessons().get(a).getDuration()*100);
							int startTimeOther = this.user.getCourseRegistered().get(c).getLessons().get(d).getTime();
							int endTimeOther = startTimeThis+(this.user.getCourseRegistered().get(c).getLessons().get(d).getTime()*100);
							if(startTimeThis<endTimeOther || endTimeThis>startTimeOther){
							//comparing start time and end time, if same compare further, else break since they will never clash
								for(int e=0;e<this.user.getCourseRegistered().get(c).getLessons().get(d).getLessonWeek().length;e++){
								//for each item in lesson week array in student's registered courses
									if(ci.getLessons().get(a).getLessonWeek()[b]==this.user.getCourseRegistered().get(c).getLessons().get(d).getLessonWeek()[e]){
									//if 1 of the weeks clash
										return true; //indicating there is a clash
									}
							}
						}
						else
							break;
						}
						else
							break;
					}
				}
			}
		}
		return false; //indicates no clash
	}
}
