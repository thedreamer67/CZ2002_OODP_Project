import java.io.*;
public class FileIO {
    public void readCourse(DataManager app){
        String courseFile = "/Users/user/Desktop/courses.txt";
		BufferedReader br=null;
		String line = "";
		try {
				br = new BufferedReader(new FileReader(courseFile));
				while ((line = br.readLine()) != null) {
					String[] values = line.split("\t");
					app.addCourse(new Course(values[0],values[1],values[2]));
					String[] index = values[3].split(",");
					for(int i=0;i<index.length;i++) {
						app.getCourse().get(app.getCourse().size()-1).addIndex(index[i]);
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
    public void readStudent(DataManager app){
        String studentFile = "/Users/user/Desktop/students.txt";
		BufferedReader br2=null;
		String line2 = "";
		try {		
				br2 = new BufferedReader(new FileReader(studentFile));
				while ((line2 = br2.readLine()) != null) {
					String[] values = line2.split("\t");
					app.addStudent(new Student(values[0],values[1],values[2].charAt(0),values[3],values[4]));
					String[] courses = values[5].split(",");
					for(int i=0;i<courses.length;i++) {
						String[] courseIndex = courses[i].split(" ");
						for(int j=0;j<app.getCourse().size();j++) {
							if(courseIndex[0].equals(app.getCourse().get(j).getCourseCode())) {
								for(int a=0;a<app.getCourse().get(j).getIndex().size();a++) {
									if(courseIndex[1].equals(app.getCourse().get(j).getIndex().get(a).getIndexNo())) {
										app.getStudent().get(app.getStudent().size()-1).addCourse(app.getCourse().get(j).getIndex().get(a));
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
    public void readCourseIndex(DataManager app){
        String courseIndexFile = "/Users/user/Desktop/courseindex.txt";
		BufferedReader br3=null;
		String line3 = "";	
		int arrayindex1=-1;
		int arrayindex2=-1;
		try {
			br3 = new BufferedReader(new FileReader(courseIndexFile));
			while ((line3 = br3.readLine()) != null) {
				String[] values = line3.split("\t");
                String course  = values[0];
                String index = values[1];
                String vacancy = values[2];
                String lesson = values[3];
                String waiting = values[4];
                String student = values[5];
				for(int i=0;i<app.getCourse().size();i++) {
                    if(course.equals(app.getCourse().get(i).getCourseCode()))
						arrayindex1=i;
                        for(int j=0;j<app.getCourse().get(arrayindex1).getIndex().size();j++) {
                            if(index.equals(app.getCourse().get(arrayindex1).getIndex().get(j).getIndexNo())) {
                                arrayindex2 = j;
                                break;
                            }
                        }
				}
				app.getCourse().get(arrayindex1).getIndex().get(arrayindex2).setVacancy(Integer.parseInt(vacancy));// add vacancy into course index
				String[] noOfLessons = lesson.split("@");
				for(int a=0;a<noOfLessons.length;a++) { //add lesson into course index
                    String[] lessonAttr = noOfLessons[a].split(",");
                    String [] lessonWeek = lessonAttr[4].split(" ");
                    int[] lessonWeekInt = new int[lessonWeek.length];
                    for(int b=0;b<lessonWeek.length;b++) {
                        lessonWeekInt[b] = Integer.parseInt(lessonWeek[b]);
                    }
					app.getCourse().get(arrayindex1).getIndex().get(arrayindex2).addLesson(lessonAttr[0], lessonAttr[1], lessonAttr[2], Integer.parseInt(lessonAttr[3]), Integer.parseInt(lessonAttr[4]), lessonWeekInt);
				}
				String[] waitingMatric = waiting.split(",");
                for(int c=0;c<waitingMatric.length;c++) { //add Student Matric number into the waiting wuthin the CourseIndex
                    app.getCourse().get(arrayindex1).getIndex().get(arrayindex2).addWaitingList(waitingMatric[c]);
				}
				String [] currentStudent = student.split(",");
				for(int d=0;d<currentStudent.length;d++){
                    String matricNo = currentStudent[d];
                    for(int e=0;e<app.getStudent().size();e++){
                        if(matricNo.equals(app.getStudent().get(e).getUserName())){
							Student s = app.getStudent().get(e);
							app.getCourse().get(arrayindex1).getIndex().get(arrayindex2).addStudent(s);
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
    
}