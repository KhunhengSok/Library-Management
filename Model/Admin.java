package Model;

public class Admin extends Person {
    public Admin(String lastname, String firstname) {
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


    public void removeBook(Book book){

    }

    public boolean removeRequest(){
        return true ;
    }


    public void insertBook(Book... books){
        for(Book book :books){

        }
    }
}
