class User {
    private String matriNo;
    private String name;
    private String gender;
    private String nationality;
    private String password;
    
    public User(String matriNo, String name, String gender, String nationality, String password) {
    this.matriNo = matriNo;
    this.name = name;
    this.gender = gender;
    this.nationality = nationality;
    this.password = password;
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
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString() {
    return "Student [matriculation no.=" + matriNo + ", name =" + name + ", gender=" + gender + ", nationality =" + nationality
    + "]";
    }
}