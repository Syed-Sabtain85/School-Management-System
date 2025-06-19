import java.util.*;

public class Student extends User{
    private int marks;


    public Student(String id, String name, int marks,String username, String password, User.Role role){
        super(username, password,name,id, role);
        this.marks=marks;
    }

    public int getMarks(){
        return marks;
    }
    
    public String toString(){
        return super.toString()+" Marks : "+marks;
    }
    
}