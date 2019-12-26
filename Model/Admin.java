package Model;

public class Admin extends Person {
    private Library library;

    public Admin(String lastname, String firstname) {
        super(lastname, firstname);
        library = Library.getInstance();
    }

    public void viewWaitingList(){
        library.viewWaitingList();
    }

    @Override
    public void viewAllBook() {
        library.viewAllBook();
    }

    public boolean removeBook(String ID){
        return library.removeBookByBookID(ID);
    }

    public boolean removeRequest(String requestId){
        return library.removeBookRequest(requestId);
    }



    public void insertBook(Book... books){
        library.insertBook(this, books);
    }

    @Override
    public Book[] searchBook(String title) {
        return library.searchBook(title);
    }
}
