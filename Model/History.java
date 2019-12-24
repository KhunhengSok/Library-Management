package Model ;

import java.util.*;
import java.util.concurrent.TimeUnit;

class History{
    private User user; 
    private Date borrowDate ;
    private Date dueDate ; 
    private Boolean isReturned ; 
//    private Map<Book, Boolean> book_returnStatus ;
    private List<HistoryDetail> histories ;

    public static int DUE_PERIOD =  7;

    History(User user){
        long milli = java.lang.System.currentTimeMillis();
        long dueMilli = milli + TimeUnit.DAYS.toMillis(DUE_PERIOD);

        this.borrowDate = new Date(milli);
        this.dueDate = new Date(dueMilli);
        this.isReturned = false ;
        this.user = user;
        this.histories = new ArrayList<>();
    }

    public User getUser() {

        return user;
    }


    public void addBook(Book... book) {
    
    }


    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }



    
//    public Book[] getBorrowedBook(){
//        //Get Unreturned books if exist, else return empty list
//        ArrayList<Book> borrowedBook = new ArrayList<>();
//        Book[] books = (Book[])this.book_returnStatus.keySet().toArray();
//        for(Book b : books){
//            if(this.book_returnStatus.get(b) == false){
//                borrowedBook.add(b);
//            }
//        }
//        return (Book[])borrowedBook.toArray();
//    }

    public Book[] getBorrowedBook(){
        ArrayList<Book> borrowedBook = new ArrayList<>();
        for(HistoryDetail d: histories){
            if(d.getReturnedStatus() == false ){
                borrowedBook.add(d.getBook());
            }
        }
        return (Book[]) borrowedBook.toArray();
    }

    public class HistoryDetail{
        private Book book ;
        private int Qty;
        private boolean isRetuned ;

        HistoryDetail(Book book, int Qty, boolean returnedStatus){
            this.book = book ;
            this.Qty = Qty;
            this.isRetuned = returnedStatus;
        }

        public boolean getReturnedStatus(){
            return this.isRetuned;
        }

        public Book getBook(){
            return this.book;
        }

        public void setReturnedStatus(boolean isRetuned){
            this.isRetuned = isRetuned;
        }

        public void setBook(Book book){
            this.book = book;
        }

    }
}