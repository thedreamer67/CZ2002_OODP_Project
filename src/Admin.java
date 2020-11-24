public class Admin implements User {
    private String userName;
    private String password;
    private String name;
    private char sex;
    private String nationality;

    public Admin(String name, String userName,char sex,String nationality,String password) {
		this.name=name;
		this.userName=userName;
		this.sex=sex;
		this.nationality=nationality;
		this.password=password;
		
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }


    public char getSex() {
		return this.sex;
    }
    
	public void setSex(char sex) {
		this.sex=sex;
	}
    
    
	public String getNationality() {
		return this.nationality;
    }
    
	public void setNationality(String nationality) {
		this.nationality=nationality;
	}
}
