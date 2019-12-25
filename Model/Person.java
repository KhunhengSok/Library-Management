package Model;


import java.util.UUID;

public abstract class Person {
    private String lastname ;
    private String firstname ;
    private String id ;
    private String email ;

    public Person(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.id = UUID.randomUUID().toString();

    }

    public String getName(){
        return this.firstname + " " + this.getLastname();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getId() {
        return id;
    }

    public abstract Book[] search(String title);

    public abstract  Book[] viewAllBook();

    public  int checkBookAvailableStatus(String title){
        Library library = Library.getInstance();
        return library.checkBookAvailableAmount(title);
    }

}
