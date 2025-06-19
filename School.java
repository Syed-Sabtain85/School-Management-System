import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class School{
    public static ArrayList<Student> students= new ArrayList<>();
    public static ArrayList<Teacher> teachers= new ArrayList<>();
    private static ArrayList<User> users= new ArrayList<>();
    public static ArrayList<Subject> subjects= new ArrayList<>();
    private static Scanner sc= new Scanner(System.in);
    static {
        
        users.add(new User("admin", "admin123","ali","1001", User.Role.PRINCIPAL));
        users.add(new User("teacher1", "pass123","ahmad","1007", User.Role.TEACHER));
        users.add(new User("student1", "pass123","sabtain","1009", User.Role.STUDENT));
        subjects.add(new Subject("CS101","Computer Science"));
        subjects.add(new Subject("CS256","Database"));
        subjects.add(new Subject("CS290","Artificial Intelligence"));
    }

    public static void main(String[] args){
        
       
        while(User.login(users)){ 
        
        mainMenu();
       
        }
        System.out.println("Thank you for using the School Management System. Goodbye!");


        }
    

    //main menu function
    private static void mainMenu(){
        boolean running= true;
        
        while(running){
            System.out.println("\n -----Welcome to School Management System-----");
            System.out.println("Main Menu");
            if(User.getCurrentUser().getRole() != User.Role.STUDENT){
            System.out.println("1- Show all teachers");
            System.out.println("2- Show all Students");
            if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
            System.out.println("3- Add Teacher or Student");
            System.out.println("4- Total Teachers");
            System.out.println("5- Remove a student");
            System.out.println("6- Remove a teacher");
            System.out.println("7- Add Subject");
            System.out.println("8- Remove Subject");
            System.out.println("9- Show all subjects ");
            System.out.println("10- Update Password");
            }
            System.out.println("11- Total No of students");
            }
            System.out.println("12- Show Marks");
            System.out.println("13- Logout");
            System.out.println("Choose any option (1-13) :  ");
            int choice=Utils.readInt();
           
            switch(choice){
                case 1:
                    if(User.getCurrentUser().getRole() !=User.Role.STUDENT)
                    {
                        Utils.clearScreen();
                    Utils.showTeachers(teachers);}
                    break;
                    
                case 2:
                    if(User.getCurrentUser().getRole() !=User.Role.STUDENT)
                    {
                        Utils.clearScreen();
                        Utils.showStudents(students);}
                    break;
                    
                case 3:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                    Utils.clearScreen();
                    Utils.addPeople(users);}
                    break;
                    
                case 4:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                        Utils.clearScreen();
                    Utils.totalTeachers(teachers);}
                    break;
                    
                case 5:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                        Utils.clearScreen();
                    Utils.removeStudent(students);}
                    break;
                case 6:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                        Utils.clearScreen();
                    Utils.removeTeacher(teachers);}
                    break; 
                    
                case 7:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                        Utils.clearScreen();
                    Utils.addSubject(subjects);}
                    break;
                    
                case 8:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                        Utils.clearScreen();
                    Utils.removeSubject(subjects);}
                    break;
                case 9:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                        Utils.clearScreen();
                    Utils.showAllSubject();}
                    break;
                case 10:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                        Utils.clearScreen();
                    Utils.updatePassword(users);}
                    break;
                case 11:
                    if(User.getCurrentUser().getRole()==User.Role.PRINCIPAL){
                    Utils.clearScreen();
                    Utils.totalStudents(students);}
                    break;
                    
                case 12:
                    Utils.clearScreen();
                    Utils.showMarks(students);
                    break;
                case 13:
                    running = false;
                    System.out.println("Exiting the Sytem.......");
                    break;
                default:
                    Utils.clearScreen();
                    System.out.println("Invalid Choice plz select any number from 1 to 11...\n Thank U");
            }
        }
    }


}