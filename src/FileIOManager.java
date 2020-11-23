import java.io.*;
import java.util.ArrayList;

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
			FileWriter fwStream = new FileWriter("courses.txt", true);
			BufferedWriter bwStream = new BufferedWriter(fwStream);
			PrintWriter pwStream = new PrintWriter(bwStream);
			
			// code to write to courses.txt using info from dm	
			
			pwStream.close();
		}
		catch (IOException e) {
			System.out.print("IO Error!" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}

	/*
	public void writeNewCourse(Course c) {
		try {
			FileWriter fwStream = new FileWriter("courses.txt", true);
			BufferedWriter bwStream = new BufferedWriter(fwStream);
			PrintWriter pwStream = new PrintWriter(bwStream);
			
			// appending new course info to the end of the courses.txt file
			pwStream.println();
			pwStream.print(c.getCourseCode() + "\t" + c.getCourseName() + "\t" + c.getNumOfAUs() + "\t" + c.getSchool() + "\t");
			ArrayList<String> indices = new ArrayList<String>();
			for (CourseIndex i: c.getIndex()) {
				indices.add(i.getIndexNo());
			}
			pwStream.print(String.join(",", indices));	
			
			pwStream.close();
		}
		catch (IOException e) {
			System.out.print("IO Error!" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void writeUpdateCourse(String oldCode, Course c) {
		try {
			// input the file content to the StringBuffer "inputBuffer"
			BufferedReader file = new BufferedReader(new FileReader("courses.txt"));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			ArrayList<String> indices = new ArrayList<String>();
	
			while ((line = file.readLine()) != null) {
				String[] values = line.split("\t");
				// edit course info for the course that was updated
				if (oldCode.equals(values[0])) {
					for (CourseIndex i: c.getIndex()) {
						indices.add(i.getIndexNo());
					}
					line = c.getCourseCode() + "\t" + c.getCourseName() + "\t" + c.getNumOfAUs() + "\t" + c.getSchool() + "\t" + String.join(",", indices);
				}
				inputBuffer.append(line);
				inputBuffer.append('\n');
			
			}
			file.close();
			
			 // write the new string with the replaced line over the same courses.txt file
			 FileOutputStream fileOut = new FileOutputStream("courses.txt");
			 fileOut.write(inputBuffer.toString().getBytes());
			 fileOut.close();
	 
		 } catch (Exception e) {
			 System.out.println("Problem reading file.");
		 }
	 } */


    public void readStudent(DataManager dm){
        String studentFile = "students.txt";
		BufferedReader br2=null;
		String line2 = "";
		try {		
				br2 = new BufferedReader(new FileReader(studentFile));
				while ((line2 = br2.readLine()) != null) {
                    String[] values2 = line2.split("\t");
                    // create Student object, but without course attribute
					dm.addStudent(new Student(values2[0],values2[1],values2[2].charAt(0),values2[3],values2[4],values2[5],Integer.parseInt(values2[6])));
					String[] courses = values2[7].split(",");    // separate different courses
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
                String waiting = values3[4];
                String student = values3[5];
				for(int i=0;i<dm.getCourse().size();i++) {
                    if(course.equals(dm.getCourse().get(i).getCourseCode()))
						arrayindex1=i;
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
				String[] waitingMatric = waiting.split(",");
                for(int c=0;c<waitingMatric.length;c++) { //add Student Matric number into the waiting wuthin the CourseIndex
                    dm.getCourse().get(arrayindex1).getIndex().get(arrayindex2).addWaitingList(waitingMatric[c]);
				}
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
    
	public void readAccessperiod(DataManager dm){
		String AccessperiodFile = "accessperiod.txt";
		BufferedReader br5=null;
		String line5 = "";
		try {		
			br5 = new BufferedReader(new FileReader(AccessperiodFile));
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
	
}
