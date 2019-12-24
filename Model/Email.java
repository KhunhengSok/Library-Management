package Model;

public class Email {
    private String msg ;
    private User targetedUser ;

    Email(String msg, User user ){
        this.msg = msg;
        this.targetedUser = user;
    }

    public void send(){
        java.lang.System.out.println("To " + this.targetedUser + ":\n" +msg);
    }
}
