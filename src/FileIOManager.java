import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileIOManager {

    public void readCourse(DataManager dm){
        String courseFile = "courses.txt";
		BufferedReader br=null;
		String line = "";
		try {
				br = new BufferedReader(new FileReader(courseFile));
				while ((line = br.readLine()) != null) {
					String[] values = line.split("\t");
					dm.addCourse(new Course(values[0],values[1],Integer.parseInt(values[2]),values[3]));
					String[] index = values[4].split(",");
					for(int i=0;i<index.length;i++) {
						dm.getCourse().get(dm.getCourse().size()-1).addIndex(index[i]);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}

	public void writeCourse(DataManager dm) {
		try {
			FileWriter fwStream = new FileWriter("courses.txt");
			BufferedWriter bwStream = new BufferedWriter(fwStream);
			PrintWriter pwStream = new PrintWriter(bwStream);
			
			// write to courses.txt using the arraylist of courses attribute in DataManager object
			for (Course c : dm.getCourse()) {
				pwStream.print(c.getCourseCode() +"\t"+ c.getCourseName() +"\t"+ c.getNumOfAUs() +"\t"+ c.getSchool() +"\t");
				ArrayList<String> indices = new ArrayList<String>();
				for (CourseIndex i: c.getIndex()) {
					indices.add(i.getIndexNo());
				}
				pwStream.print(String.join(",", indices));
				pwStream.println();
			}
			
			pwStream.close();
		}
		catch (IOException e) {
			System.out.print("IO Error!" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}


    public void readStudent(DataManager dm){
        String studentFile = "students.txt";
		BufferedReader br2=null;
		String line2 = "";
		try {		
				br2 = new BufferedReader(new FileReader(studentFile));
				while ((line2 = br2.readLine()) != null) {
					String[] values2 = line2.split("\t");
					if(values2.length>6){ //if there is courses that the student already registered
					// create Student object, but without course attribute
					dm.addStudent(new Student(values2[0],values2[1],values2[2].charAt(0),values2[3],values2[4],values2[5],Integer.parseInt(values2[7])));
					String[] courses = values2[6].split(",");    // separate different courses
					for(int i=0;i<courses.length;i++) {
						String[] courseIndex = courses[i].split(" ");   // separate course index from respective course
						for(int j=0;j<dm.getCourse().size();j++) {
							if(courseIndex[0].equals(dm.getCourse().get(j).getCourseCode())) {
								for(int a=0;a<dm.getCourse().get(j).getIndex().size();a++) {
									if(courseIndex[1].equals(dm.getCourse().get(j).getIndex().get(a).getIndexNo())) {
										dm.getStudent().get(dm.getStudent().size()-1).addCourse(dm.getCourse().get(j).getIndex().get(a));
										}
									}
								}
							}
						}
					}
					else //if student did not register for any course
					dm.addStudent(new Student(values2[0],values2[1],values2[2].charAt(0),values2[3],values2[4],values2[5]));
					}
		
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br2 != null) {
						try {
							br2.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
	}

	public void writeStudent(DataManager dm) {
		try {
			FileWriter fwStream2 = new FileWriter("students.txt");
			BufferedWriter bwStream2 = new BufferedWriter(fwStream2);
			PrintWriter pwStream2 = new PrintWriter(bwStream2);
			
			// write to students.txt using the arraylist of students attribute in DataManager object
			for (Student s : dm.getStudent()) {
				pwStream2.print(s.getName() +"\t"+ s.getUserName() +"\t"+ s.getGender() +"\t"+ s.getPassword() +"\t"+ s.getEmail() +"\t");
				ArrayList<String> registeredCourses = new ArrayList<String>();
				for (CourseIndex i: s.getCourseRegistered()) {
					registeredCourses.add(i.getCourseCode()+" "+i.getIndexNo());
				}
				pwStream2.print(String.join(",", registeredCourses));
				pwStream2.print("\t"+ s.getTotalAUs());
				pwStream2.println();
			}
			
			pwStream2.close();
		}
		catch (IOException e) {
			System.out.print("IO Error!" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}


    public void readCourseIndex(DataManager dm){
        String courseIndexFile = "courseindex.txt";
		BufferedReader br3=null;
		String line3 = "";	
		int arrayindex1=-1;
		int arrayindex2=-1;
		try {
			br3 = new BufferedReader(new FileReader(courseIndexFile));
			while ((line3 = br3.readLine()) != null) {
				String[] values3 = line3.split("\t");
                String course  = values3[0];
                String index = values3[1];
                String vacancy = values3[2];
				String lesson = values3[3];
				String student="";
				String waiting="";
				boolean checkRegistered=false;
				boolean checkWaiting=false;
				if(values3.length>4){ //if course already have students registered for it
					checkRegistered=true;
					student = values3[4];
					if(values3.length>5) //if course has a waiting list
					checkWaiting=true;
						waiting = values3[5];
				}
				for(int i=0;i<dm.getCourse().size();i++) {
                    if(course.equals(dm.getCourse().get(i).getCourseCode()))
						arrayindex1=i;
						System.out.println(arrayindex1+"test");
                        for(int j=0;j<dm.getCourse().get(arrayindex1).getIndex().size();j++) {
                            if(index.equals(dm.getCourse().get(arrayindex1).getIndex().get(j).getIndexNo())) {
                                arrayindex2 = j;
                                break;
                            }
                        }
				}
				dm.getCourse().get(arrayindex1).getIndex().get(arrayindex2).setVacancy(Integer.parseInt(vacancy));// add vacancy into course index
				String[] noOfLessons = lesson.split("@");
				for(int a=0;a<noOfLessons.length;a++) { //add lesson into course index
                    String[] lessonAttr = noOfLessons[a].split(",");
                    String [] lessonWeek = lessonAttr[4].split(" ");
                    int[] lessonWeekInt = new int[lessonWeek.length];
                    for(int b=0;b<lessonWeek.length;b++) {
                        lessonWeekInt[b] = Integer.parseInt(lessonWeek[b]);
                    }
					dm.getCourse().get(arrayindex1).getIndex().get(arrayindex2).addLesson(lessonAttr[0], lessonAttr[1], lessonAttr[2], Integer.parseInt(lessonAttr[3]), Integer.parseInt(lessonAttr[4]), lessonWeekInt);
				}
				if(checkWaiting==true){ //if there is a waiting list
					String[] waitingMatric = waiting.split(",");
					for(int c=0;c<waitingMatric.length;c++) { //add Student Matric number into the waiting wuthin the CourseIndex
						dm.getCourse().get(arrayindex1).getIndex().get(arrayindex2).addWaitingList(waitingMatric[c]);
					}
				}

				if(checkRegistered==true){ //if there exists students who are registered for this course
					String [] currentStudent = student.split(",");
					for(int d=0;d<currentStudent.length;d++){
						String matricNo = currentStudent[d];
						for(int e=0;e<dm.getStudent().size();e++){
							if(matricNo.equals(dm.getStudent().get(e).getUserName())){
								Student s = dm.getStudent().get(e);
								dm.getCourse().get(arrayindex1).getIndex().get(arrayindex2).addStudent(s);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br3 != null) {
				try {
					br3.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writeCourseIndex(DataManager dm) {
		
		try {
			FileWriter fwStream3 = new FileWriter("students.txt");
			BufferedWriter bwStream3 = new BufferedWriter(fwStream3);
			PrintWriter pwStream3 = new PrintWriter(bwStream3);
			
			// write to students.txt using the arraylist of students attribute in DataManager object
			for (Course c : dm.getCourse()) {
				ArrayList<CourseIndex> courseIndexList = (ArrayList<CourseIndex>) c.getIndex().clone();
				for (CourseIndex ci : courseIndexList) {
					pwStream3.print(ci.getCourseCode() +"\t"+ ci.getIndexNo() +"\t"+ ci.getVacancy() +"\t");
					
					ArrayList<String> allLessons = new ArrayList<String>();
					for (Lesson l : ci.getLessons()) {
						String weeks = Arrays.toString(l.getLessonWeek()).replace("[", "").replace("]", "").replace(",", "").trim();
						allLessons.add(l.getType() +","+ l.getLocation() +","+ l.getDayOfWeek() +","+ l.getTime() +","+ l.getDuration() +","+ weeks);
					}
					pwStream3.print(String.join("@", allLessons) +"\t");
					
					ArrayList<String> registeredStudents = new ArrayList<String>();
					for (Student s : ci.getStudentList()) {
						registeredStudents.add(s.getUserName());
					}
					pwStream3.print(String.join(",", registeredStudents));
					
				}
			}

			for (Student s : dm.getStudent()) {
				pwStream3.print(s.getName() +"\t"+ s.getUserName() +"\t"+ s.getGender() +"\t"+ s.getPassword() +"\t"+ s.getEmail() +"\t");
				ArrayList<String> registeredCourses = new ArrayList<String>();
				for (CourseIndex i: s.getCourseRegistered()) {
					registeredCourses.add(i.getCourseCode()+" "+i.getIndexNo());
				}
				pwStream3.print(String.join(",", registeredCourses));
				pwStream3.print("\t"+ s.getTotalAUs());
				pwStream3.println();
			}
			
			pwStream3.close();
		}
		catch (IOException e) {
			System.out.print("IO Error!" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}

	
	public void readAdmin(DataManager dm){
        String adminFile = "admins.txt";
		BufferedReader br4=null;
		String line4 = "";
		try {		
				br4 = new BufferedReader(new FileReader(adminFile));
				while ((line4 = br4.readLine()) != null) {
					String[] values4 = line4.split("\t");
					dm.addAdmin(new Admin(values4[0],values4[1],values4[2].charAt(0),values4[3],values4[4]));
					//System.out.println("Admin: " + dm.getAdmin());
					}
		
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br4 != null) {
						try {
							br4.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
    }
    
	public void readAccessPeriod(DataManager dm){
		String AccessPeriodFile = "accessperiod.txt";
		BufferedReader br5=null;
		String line5 = "";
		try {		
			br5 = new BufferedReader(new FileReader(AccessPeriodFile));
			while ((line5 = br5.readLine()) != null) {
				String[] values5 = line5.split("\t");
				dm.editAccessPeriod(values5[0],values5[1]);
				//System.out.println("Admin: " + app.getAdmin());
				}
	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br5 != null) {
					try {
						br5.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}

	public void writeAccessPeriod(DataManager dm) {
		try {
			FileWriter fwStream5 = new FileWriter("accessperiod.txt");
			BufferedWriter bwStream5 = new BufferedWriter(fwStream5);
			PrintWriter pwStream5 = new PrintWriter(bwStream5);
			
			// write to accessperiod.txt using the accessPeriod array attribute in DataManager object
			pwStream5.print(dm.getAccessPeriod()[0] +" "+ dm.getAccessPeriod()[1]);
					
			pwStream5.close();
		}
		catch (IOException e) {
			System.out.print("IO Error!" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}
