package Model ;

public class User extends Person{
    private History borrowedHistory ;

    User(String lastname, String firstname){
        super(lastname, firstname);
    }

    @Override
    public Book[] search(String title) {
        return new Book[0];
    }



    @Override
    public Book[] viewAllBook() {
        return new Book[0];
    }

    @Override
    public boolean isBookAvailable() {
        return false;
    }

    public boolean borrowBook(){
        return true ;
    }

}