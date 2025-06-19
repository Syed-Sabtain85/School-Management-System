import java.util.*;
import java.util.regex.Pattern;

public class Utils{
    public static Scanner sc = new Scanner(System.in);

    public static void showStudents(ArrayList<Student> students){
        if(students.isEmpty()){
            System.out.println("No record found.");
            return;
        }
        System.out.println("------Students List:-------");
         for(Student s:students){
           System.out.println(s);
        }

    }

    public static void updatePassword(ArrayList<User> people) {
        System.out.println("Enter your username:");
        String username = sc.nextLine();
        User user = null;
        
        for (User u : people) {
            if (u.getUsername().equals(username)) {
                user = u;
                break;
            }
        }
        
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        System.out.println("Enter new password:");
        String newPassword = sc.nextLine();
        
        while (newPassword.isEmpty()) {
            System.out.println("Password cannot be empty. Please enter a valid password.");
            newPassword = sc.nextLine();
        }
        
        user.setPassword(newPassword);
        System.out.println("Password updated successfully for user: " + user.getUsername());
    }

    public static void showTeachers(ArrayList<Teacher> teachers){
        if(teachers.isEmpty()){
            System.out.println("No record found.");
            return;
        }
        System.out.println("------Teachers List:-------");
        for(Teacher t:teachers){
          System.out.println(t);
        }
    }

    public static boolean isUniqueCode(String code) {
            for (Subject sub : School.subjects) {
                if (sub.code.equals(code)) {
                    return false; // code already exists
                }
            }
            return true; // no match found, so it's unique
        }


    public static void showAllSubject(){
        System.out.println("------Subjects List:-------");
        if(School.subjects.isEmpty()){
            System.out.println("No subjects found.");
            return;
        }
        for(Subject sub:School.subjects){
            System.out.println(sub);
        }
    }

    public static String subjectCode(){
        System.out.println("Enter subject code:");  
        String code= sc.nextLine();

        while(code.isEmpty()) {
            System.out.println("Subject code cannot be empty.");
            code=sc.nextLine();
        }

        while(!isUniqueCode(code)) {
            System.out.println("Subject code already exists. Please enter a unique code.");
            code=sc.nextLine();
        }
        return code;
    }
    private static String subjectName(){
        System.out.println("Enter subject name:");
        String name = sc.nextLine();
        while(name.isEmpty()) {
            System.out.println("Subject name cannot be empty.");
            name=sc.nextLine();
        }
        return name;
    }

    public static void addSubject(ArrayList<Subject> subjects) {
        String code=subjectCode();
        code.toUpperCase();
        String name=subjectName();
        name.toUpperCase();

        
        Subject subject = new Subject(code, name);
        subjects.add(subject);
        System.out.println("Subject added successfully: " + subject);
    }

    public static void removeSubject(ArrayList<Subject> subjects){
        String code=subjectCode();
        code.toUpperCase();
        boolean removed= false;
        for(int i=0;i<subjects.size();i++){
            if(subjects.get(i).code.equals(code)){
                subjects.remove(i);
                removed= true;
                System.out.println("Subject with code " + code + " has been removed successfully.");
                break;
            }
        }
        if(!removed){
            System.out.println("No subject found with code: " + code);
        }
    }

    public static void totalStudents(ArrayList<Student> students) {

        System.out.println("Total number of students: " + students.size());
    }

    public static void addPeople(ArrayList<User> people){
        String id=getId(people);
        String name=getName();
        String username=getUsername(people);
        String password=getPassword();
        String role = getRole();
        if(role.equalsIgnoreCase("student")){
        addStudent(people, id, name, username, password);
        // people.add(new User(username, password, name, id, User.Role.TEACHER));

        } else if(role.equalsIgnoreCase("teacher")){
            addTeacher(people, id, name, username, password);
        } else {
            System.out.println("Invalid role. Please enter either 'student' or 'teacher'.");
        }

    }
    private static String getId(ArrayList<User> people){
        System.out.println("Enter ID:");
        String id=sc.nextLine();
        while(!isUniqueId(id, people)){
            System.out.println("ID already exists, please enter a unique ID.");
            id = sc.nextLine();
        }
        return id;
    }

    private static String getName(){
        System.out.println("Enter name: ");
        String name= sc.nextLine();
        while(!checkString(name)){
            System.out.println("Invalid name");
            name = sc.nextLine();
        }
        return name;
    }

    private static String getUsername(ArrayList<User> people){
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        while(!isUniqueUsername(username, people)){
            System.out.println("Username already exists, please enter a unique username.");
            username = sc.nextLine();
        }
        return username;
    }

    private static String getPassword(){
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        while(password.isEmpty()){
            System.out.println("Password cannot be empty, please enter a valid password.");
            password = sc.nextLine();
        }
        return password;
    }
    private static String getRole(){
        System.out.println("Enter role (student/teacher): ");
        String role = sc.nextLine();
        while(!role.equalsIgnoreCase("student") && !role.equalsIgnoreCase("teacher")){
            System.out.println("Invalid role. Please enter either 'student' or 'teacher'.");
            role = sc.nextLine();
        }
        return role;
    }

    private static boolean isUniqueId(String id, ArrayList<User> people){
        for (User user:people){
            if(user.getId().equals(id)){
                System.out.println("ID already exists for user: " + user.getId());
                return false;
            }
        }
        return true;
    }

    private static boolean isUniqueUsername(String username, ArrayList<User> people){
        for (User user:people){
            if(user.getUsername().equals(username)){
                System.out.println("Username already exists for user: " + user.getUsername());
                return false;
            }
        }
        return true;
    }

    private static void addStudent(ArrayList<User> people, String id,String name,String username,String password){
    
        System.out.println("Enter student's marks: ");
        int marks = readInt();
        people.add(new Student(id, name, marks, username, password, User.Role.STUDENT));
        School.students.add(new Student(id, name, marks, username, password, User.Role.STUDENT));
        System.out.println("Student added successfully.");
    }

    private static void addTeacher(ArrayList<User> people, String id,String name,String username,String password){
        
        showAllSubject();
        String subjectCode=getSubjectCode();

        while(isAssigned(subjectCode,people)){
            subjectCode = sc.nextLine();
        }
        people.add(new Teacher(id, name, subjectCode, username, password, User.Role.TEACHER));
        School.teachers.add(new Teacher(id, name, subjectCode, username, password, User.Role.TEACHER));
        System.out.println("Teacher added successfully.");
    }

    private static String getSubjectCode(){
        System.out.println("Enter the subject code");
        String subjectCode = sc.nextLine();
        subjectCode.toUpperCase();
        return subjectCode;
    }

    public static boolean isAssigned(String code, ArrayList<User> people) {
        for (User p : people) {
            if (p instanceof Teacher) {
                Teacher t = (Teacher) p;
                if (t.getSubjectCode().equals(code)) {
                    System.out.println("Subject Already assigned to " + t.getId());
                    return true;
                }
            }
        }
        return false;
    }

    public static void removeStudent(ArrayList<Student> students) {
        System.out.println("Enter Student Id to remove :");
        String id = sc.nextLine();
        boolean removed = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);  
                System.out.println("Stdent with ID " + id + " has been removed successfully.");
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.out.println("No Student found with ID: " + id);
        }
    }

public static void removeTeacher(ArrayList<Teacher> teachers) {
    System.out.println("Enter Teacher Id to remove :");
    String id = sc.nextLine();
    boolean removed = false;

    for (int i = 0; i < teachers.size(); i++) {
        if (teachers.get(i).getId().equals(id)) {
            teachers.remove(i); 
            System.out.println("Teacher with ID " + id + " has been removed successfully.");
            removed = true;
            break;
        }
    }

    if (!removed) {
        System.out.println("No teacher found with ID: " + id);
        return;
    }
}

private static boolean checkString(String name){
        if(Pattern.matches("[a-zA-Z]+$",name)){
            return true;
        }
        else{
            return false;
        }
    }
    
    //fun to read and validate int
    public static int readInt(){
            while(!sc.hasNextInt()){
                System.out.println("Invalid input! Plz enter a number");
                sc.next();
            }
            int num=sc.nextInt();
            sc.nextLine();
            return num;
        }

    //fun to show marks of a student
    public static void showMarks(ArrayList<Student> students){
        System.out.println("Enter Student ID to show marks:");
        String id = sc.nextLine();
        boolean found = false;
        
        for(Student s: students){
            if(s.getId().equals(id)){
                found= true;
                System.out.println(s);
                System.out.println("Marks For Student With ID "+s.getId()+ "is : "+ s.getMarks());
            }
        }
        if(!found){
            System.out.println("No Student Found \n ERROR 404 Not Found");
        }
    }
//clear screen fun
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public static void totalTeachers(ArrayList<Teacher> teachers) {
        System.out.println("Total number of teachers: " + teachers.size());
    }   

}