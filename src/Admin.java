public class Admin {
    private int type;
    private String userName;
    private String password;


    public Admin(){
        this.type = 1;
    }

    public int getType(){
        return this.type;
    }

    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
