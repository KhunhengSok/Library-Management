package Model;

public class Email {
    private String msg ;
    private User targetedUser ;

    private Email(String msg, User user ){
        this.msg = msg;
        this.targetedUser = user;
    }

    public void send(){
        java.lang.System.out.println("To " + this.targetedUser + ":\n" +msg);
    }

    public static class Builder{
        private String msg ;
        private User targetedUser;

        Builder(){

        }

        public Builder setTargetedUser(User user){
            this.targetedUser = user;
            return  this;
        }

        public Builder setMessage(String msg){
            this.msg =  msg;
            return this;
        }

        public Email build(){
            return new Email(msg, targetedUser);
        }
    }
}
