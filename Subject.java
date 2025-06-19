public class Subject {
    public String code;
    public String name;
    //public String assingedTo;

    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
        //this.assingedTo=assingedTo;
    }
    public String toString() {
        return "Subject Code: " + code + ", Name: " + name;
    }
}
