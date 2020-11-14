public interface User {
  /*
    private String name;
    private String gender;
    private String nationality;
    private String userName;
    private String password;

    public User(String name, String gender, String nationality, String userName, String password) {
      this.name = name;
      this.gender = gender;
      this.nationality = nationality;
      this.userName = userName;
      this.password = password;
    }
    */

    public String getName();

    public void setName(String name);

    public String getGender();

    public void setGender(String gender);

    public String getNationality();

    public void setNationality(String nationality);

    public String getUserName();

    public void setUserName(String userName);

    public String getPassword();

    public void setPassword(String password);

    // public String toString() {
    // return "Student [matriculation no.=" + matriNo + ", name =" + name + ", gender=" + gender + ", nationality =" + nationality
    // + "]";
    // }
}
