import java.util.*;
public class Teacher extends User{
    private String subject_code;

    public Teacher(String id, String name, String subject_code,String username, String password,User.Role role) {
        super(username, password, name, id,role);
        this.subject_code=subject_code;
    }
public void setSubjectCode(String subject_code){
    this.subject_code = subject_code;
}
public String getSubjectCode(){
    return this.subject_code;
}

    public String toString(){
        return super.toString()+ "\nThe Subject teaches : "+ subject_code;
    }
    
}