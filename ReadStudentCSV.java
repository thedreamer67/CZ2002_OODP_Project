import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.Collections;

public class ReadStudentCSV
{
    public static void main(String[] args) throws IOException{

    //Input file which needs to be parsed
    List <Student> studentList = new ArrayList <>();
    String file = "SampleCSVFile.csv";
    String line = null;

    // Read all lines in from CSV file and add to studentList
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

    while ((line = bufferedReader.readLine()) != null) {
        String[] temp = line.split(",");
        String matriNo = temp[0];
        String name = temp[1];
        String gender = temp[2];
        String nationality = temp[3];
        studentList.add(new Student(matriNo, name, gender, nationality));
    }
    bufferedReader.close();
    

    for (Student s: studentList){
        System.out.println(s.toString());
    }

    }
}

class Student {
    private String matriNo;
    private String name;
    private String gender;
    private String nationality;
    public Student(String matriNo, String name, String gender, String nationality) {
    this.matriNo = matriNo;
    this.name = name;
    this.gender = gender;
    this.nationality = nationality;
    }

    public String getName() {
    return name;
    }
    public void setName(String name) {
    this.name = name;
    }

    public String getMatriNo() {
    return matriNo;
    }
    public void setMatriNo(String matriNo) {
    this.matriNo = matriNo;
    }

    public String getgender() {
    return gender;
    }
    public void setgender(String gender) {
    this.gender = gender;
    }

    public String getNationality() {
    return nationality;
    }
    public void setNationality(String nationality) {
    this.nationality = nationality;
    }
    
    public String toString() {
    return "Student [matriculation no.=" + matriNo + ", name =" + name + ", gender=" + gender + ", nationality =" + nationality
    + "]";
    }
}
        

        

    
