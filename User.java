import java.util.*;
public class User
{
    private String username;
    private String password;
    protected String name;
    protected String id;
    public Role role;
    private static User currentUser= null;
    private static Scanner sc = new Scanner(System.in);

    public enum Role
    {
        PRINCIPAL,
        TEACHER,
        STUDENT
    }
    public String toString(){
        return "User:\n " +
                "username='" + username+ " name='" + name +" id='" + id;
    }

    public User(String username,String password,String name,String id, Role role)
    {
        this.username=username;
        this.password=password;
        this.name=name;
        this.id=id;
        this.role= role;
    }


    public String getPassword(){
        return password;
    }
    public static User getCurrentUser(){
        return currentUser;
    }
    public String getId() {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public Role getRole(){
        return role;
    }
    public String getName() {
        return name;
    }
    protected void setPassword(String password) {
        this.password = password;
    }

    public static boolean login(ArrayList<User> users){
        System.out.println("------LOGIN-------");
        System.out.println("Enter Username: ");
        String username=sc.nextLine();
        System.out.println("Enter Password: ");
        String password=sc.nextLine();

        if(checkCredentials(username,password,users))
        {
            System.out.println("Welcome " + username);
            return true;
        }
        else
        {
            System.out.println("Invalid Credentials");
            System.out.println("Do you want to again login? \nReply with yes or no: ");
            String Login_Again=sc.nextLine().toLowerCase();
            
            if(Login_Again.equals("yes")){
            login(users);
            return true;
            }
        if(Login_Again.equals("no")){
            System.out.println("Exiting the application...");
            return false;
            }
            
        }
        System.out.println("Invalid input, please try again.");
        return false;
    }


private static boolean checkCredentials(String username,String Password,ArrayList<User> users)
{
    for(User user:users){
        if(user.getPassword().equals(Password) && user.getUsername().equals(username)){
            System.out.println("Login Successful as "+ user.getRole());
            currentUser=user;
            return true;
        }
    }
            return false;
}

}
