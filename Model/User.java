package Model ;

public class User extends Person{
    private History borrowedHistory ;
    private Library library;

    public User(String lastname, String firstname){
        super(lastname, firstname);
        library = Library.getInstance();
    }

    @Override
    public Book[] searchBook(String title) {
        return library.searchBook(title);
    }


    @Override
    public void viewAllBook() {
        library.viewAllBook();
    }


    public void borrowBook(Book book){
        library.borrowBook(this ,book);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void viewHistory(){
        library.viewHistory(this);
    }
}