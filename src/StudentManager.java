import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


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
		if((c.getNumOfAUs()+this.user.getTotalAUs())>dm.getMaxAU()){
			System.out.println("Addition of this course will exceed the maximum number of AUs allowed, please drop a course first");
			return;}

		//check for unique course code within registered courses and waiting list
		if(checkUniqueRegistered(courseCode)==false || checkUniqueWaiting(courseCode)==false){
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
		if (ci.getWaitingList().size()>0) {
			updateWaitingList(ci, dm); // once current student drops a course, method is called to register another student for this course if waiting list is not empty
		}
		
	}
	

	public void checkRegistered() {
		this.user.checkRegistered();
	}


	public void printTimetable() {
		this.user.printTimetable();
	}


	public void checkWaitingList(){
		this.user.checkWaitingList();
	}


	public void dropWaitingList(){
		Scanner sc = new Scanner(System.in);
		this.user.checkWaitingList();
		System.out.println("Enter course code to drop");
		String courseCode = sc.nextLine();
		if(this.user.dropWaitingList(courseCode)==false){
			System.out.println("Course code not found in waiting list");
			return;
		}
		else{
			System.out.println("Course successfully removed from waiting list");
			this.user.checkWaitingList();
		}
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
			updateWaitingList(ciOld, dm); //once this student changes course index, registers another student in the queue for the course the current student dropped
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

		System.out.println("Swapping of index successful");
		this.user.checkRegistered();
    }
	
	
	// method to send an email to a recipient, recipient's email taken as argument
    public void sendEmail(String recipientEmail, String courseCode, String courseName, String courseIndex) {
        final String senderEmail = "ntustarsapp@gmail.com"; // sender email
		final String password = "testing123!"; // sender email account password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // to be added an email addr
			message.setSubject("New Course Allocation for NTU STARS");
			message.setText("Dear Student, \n\nPlease check your NTU STARS account, as a vacancy for the course " +courseCode+ " " +courseName+ " index " +courseIndex+ " has been opened up and you have been added to the course.");

			Transport.send(message); // send the email to the recipientEmail

			//System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	

	// method to check if a course code is unique within a student's registered courses
	public boolean checkUniqueRegistered(String courseCode){
		for(int i=0;i<this.user.getCourseRegistered().size();i++){
			if(courseCode.equals(this.user.getCourseRegistered().get(i).getCourseCode())){
				return false;
			}
		}
		return true;
	}


	// method to check if a course code is unique within a student's waiting list
	public boolean checkUniqueWaiting(String courseCode){
		for(int i=0;i<this.user.getWaitingList().size();i+=3){
			if(courseCode.equals(this.user.getWaitingList().get(i))){
				return false;
			}
		}
		return true;
	}


	// method to check if there is a clash in timetable with the new course and the student's registered courses
	public boolean checkTimetableClash(CourseIndex ci) {
		// for each lesson of the new course ci
		for (Lesson newL : ci.getLessons()) {
			// for each registered course of the current student
			for (CourseIndex regC : this.user.getCourseRegistered()) {
				// for each lesson of a registered course
				for (Lesson regL : regC.getLessons()) {
					// if dayOfWeek of new course and registered course clash, move on to check for clash
					if (newL.getDayOfWeek().equals(regL.getDayOfWeek())) {
						int startTimeNew = newL.getTime();
						int endTimeNew = startTimeNew + 100*newL.getDuration();
						int startTimeReg = regL.getTime();
						int endTimeReg = startTimeReg + 100*regL.getDuration();
						// if time of new course and registered course clash, move on to check for clash
						if ((startTimeNew>=startTimeReg && startTimeNew<endTimeReg) || (endTimeNew>startTimeReg && endTimeNew<=endTimeReg)) {
							for (int i : newL.getLessonWeek()) {
								for (int j : regL.getLessonWeek()) {
									// if there is a lesson week that is the same for the new course and the registered course, there is a clash in timetable
									if (i==j) {
										return true; // return true because there is a clash in timetable between the new course and (at least one) of the student's registered courses
									}
								}
							}
						}
					}
				}
			}
		}
		return false; // all the loops finished running without returning true, thus indicating no clash, so return false
	}


	//method to register student once there is vacancy for a course
	public void updateWaitingList(CourseIndex ci, DataManager dm){
		if(ci.getVacancy()>0){
			String matricNo = ci.getWaitingList().get(0); //get first item in the list since the list works like a queue
			int arrayIndexOfStudent = dm.findStudent(matricNo);
			Student s = dm.getStudent().get(arrayIndexOfStudent);
			System.out.println("There is now vacancy for course "+ci.getCourseCode()+" with index "+ci.getIndexNo());
	
			System.out.println("Assigning course "+ci.getCourseCode()+" to student "+s.getUserName()+" "+s.getName()+"..."); // only include this part to show during demo, comment out after demo because the current student that is logged in shouldn't be able to know which student was assigned the course
			StudentManager sm = new StudentManager();
			sm.setUser(s);
			if(sm.checkTimetableClash(ci)==true){
				System.out.println("Course assignment failed as the student has clashing timetable"); // comment out after demo
				return;
			}
			if((ci.getNumOfAUs()+s.getTotalAUs())>dm.getMaxAU()){
				System.out.println("Course assignment failed as the student has exceeded the maximum number of AUs"); // comment out after demo
				return;}
			s.addCourse(ci);
			ci.addStudent(s);
			ci.dequeueWaitingList();
			s.dropWaitingList(ci.getCourseCode());
			sendEmail(s.getEmail(), ci.getCourseCode(), ci.getCourseName(), ci.getIndexNo());
			System.out.println("Assignment of course successful"); // comment out after demo
		}
	}
}
