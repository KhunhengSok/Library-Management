package Model;

import java.util.ArrayList;
import java.util.List;

public class BorrowingList {
    List<Book> borrowingList;

    BorrowingList(){
        this.borrowingList = new ArrayList<>();
    }

    public void addToBorrowingList(Book... books){
        for(Book b: books){
            this.borrowingList.add(b);
        }
    }

    public Book[] getBorrowingList(){
        Book[] books = new Book[this.borrowingList.size()];
        return this.borrowingList.toArray(books);
    }

    public void returnBook(Book... books){
        for(Book b: books){
            this.borrowingList.remove(b);
        }
    }

    public void viewBorrowingList(){
        if(this.borrowingList.size() == 0) {
            System.out.println("No Loaing book.");
            return ;
        }
        System.out.println("\t\t\t\t\tBorrowing List:");
        for(Book book: borrowingList){
            System.out.println("Book: " + book.getTitle());
            System.out.println("Borrower: " + book.getBorrower());
            System.out.println("Borrowed Date: " + book.getBookId());
            System.out.println("Due Date: " + book.getDueDate() +'\n');
        }
    }

}
