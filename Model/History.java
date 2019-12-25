package Model ;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;

class History{
    private User user; 
    private Date borrowDate ;
    private Date dueDate ; 
    private Boolean isReturned ; 
    private List<HistoryDetail> histories ;

    public static int DUE_PERIOD =  7 ;

    History(User user, ArrayList<HistoryDetail> historyDetails){
        long milli = java.lang.System.currentTimeMillis();
        long dueMilli = milli + TimeUnit.DAYS.toMillis(DUE_PERIOD);

        this.borrowDate = new Date(milli);
        this.dueDate = new Date(dueMilli);
        this.isReturned = false ;
        this.user = user;
        this.histories = new ArrayList<>();
        this.histories.addAll(historyDetails);
    }


    public User getBorrower() {
        return user;
    }


    public HistoryDetail[] getHistoryDetails(){
//        return (HistoryDetail[]) this.histories.toArray();
        HistoryDetail[] historyDetail = new HistoryDetail[this.histories.size()];
        return this.histories.toArray(historyDetail);
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


    public Book[] getBorrowedBook(){
        ArrayList<Book> borrowedBook = new ArrayList<>();
        for(HistoryDetail d: histories){
            if(d.getReturnedStatus() == false ){
                borrowedBook.add(d.getBook());
            }
        }
//        return (Book[]) borrowedBook.toArray();
        Book[] temp = new Book[borrowedBook.size()];
        return borrowedBook.toArray(temp);
    }

    public static class Builder{
        private User user ;
        private ArrayList<HistoryDetail> historyDetails ;

        Builder(){
            this.historyDetails = new ArrayList<>();

        }

        public Builder setUser(User u){
            this.user = u ;
            return  this;
        }

        public Builder addHistoryDetail(HistoryDetail... details){
            for(HistoryDetail detail: details){
                this.historyDetails.add(detail);
            }
            return this ;
        }

        public History build(){
            return new History(this.user, this.historyDetails);
        }
    }


    @Override
    public String toString() {
        String str = "User: " + this.user + "\n" + "Borrow History: \n" ;
        for(HistoryDetail detail: this.histories){
//            str += "\t" + detail.getBook() + " : " + (detail.getReturnedStatus() ? " Returned." : " Not yet return.");
            str += detail;
        }
        return str;
    }


    //===================================================

    public static class HistoryDetail{
        private Book book ;
        private boolean isRetuned ;

        HistoryDetail(Book book, boolean returnedStatus){
            this.book = book ;
            this.isRetuned = returnedStatus;
        }

        HistoryDetail(Book book){
            this(book, false);
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

        @Override
        public String toString() {
            return  this.getBook().getTitle() + " : " + (this.getReturnedStatus() ? " Returned.\n" : " Not yet return.\n");

        }
    }


}