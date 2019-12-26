package Model ;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Book{

    private String title  ; 
    private String author = "";
    private String bookId  ;
    private boolean loanStatus ;
    private User borrower;
    private Date borrowedDate;
    private Date dueDate ;

    public Book(String title ){
        this.title = title;
        this.bookId = UUID.randomUUID().toString();
    }

    public void returnBack(){
        this.borrowedDate = null;
        this.borrower = null;
        this.loanStatus = false;
    }

    public boolean isLoan() {
        return loanStatus;
    }

    public User getBorrower() {
        return borrower;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void loan(User borrower){
        this.borrower = borrower;
        this.loanStatus = true;
        this.borrowedDate = new Date(System.currentTimeMillis());
        this.dueDate = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }q

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
        String str  = "Title: " + this.title + "\nAuthor: " + this.author + "\nBook ID: " + this.bookId + "\n";
        if (isLoan()) {
            str += "Loan Status: Loaned\n";
            str += "Borrowed Date: " + this.borrowedDate + '\n';
            str += "Due Date: " + this.dueDate + '\n';
        }else{
            str += "Loan Status: Available\n";
        }
        return str;
    }
}