package Model ;

import java.util.UUID;

public class Book{

    private String title  ; 
    private String author = "";
    private String bookId  ;
    
    public Book(String title ){
        this.title = title;
        this.bookId = UUID.randomUUID().toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setID(String ID) {
        this.bookId = ID;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\nAuthor: " + this.author + "\nBook ID: " + this.bookId;
    }
}