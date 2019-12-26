package Model ;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.*;

public class Library{

    private List<User> users;
    private List<Admin> admins;
    private ArrayList<Book> books;
    private List<History> borrowedHistory;
    private List<Book> borrowingList ;

    private WaitingList waitingList;

    private static Library library = null;
    private Library(){
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.borrowedHistory = new ArrayList<>();
        this.waitingList = WaitingList.getInstance();
        this.borrowingList = new ArrayList<>();
    }


    //singleton
    public static synchronized Library getInstance(){
        if(library == null){
            library = new Library();
        }
        return library;
    }

    public WaitingList getWaitingList() {
        return waitingList;
    }

    public void insertBook(Admin admin, Book... books){
        for(Book b: books){
            this.books.add(b);
        }
    }


    public boolean removeBookByBookID(String id){
        for(Book b: this.books){
            if(b.getBookId().equalsIgnoreCase(id)){
                this.books.remove(b);
                return true;
            }
        }
        return  false ;
    }

    public void viewAllBook(){
        if(this.books.size() == 0) {
            System.out.println("No book available in our library.");
        }
        for(Book b: this.books){
            System.out.println(b);
        }
    }


    public void addToWaitingList(User user, String title){
        this.waitingList.addToWaitingList(user, title);
    }

    public User getUserInstance(String id){
        for(User u: this.users){
            if(u.getId().equalsIgnoreCase(id)) return u;
        }
        return  null;
    }

    public User getUserInstance(String lastname, String firstname){
        //assume all usernames are unique
        for(User u: this.users){
            if(u.getLastname().equalsIgnoreCase(lastname) && u.getFirstname().equalsIgnoreCase(firstname)) return u;
        }
        return  null;
    }
    public Admin getAdminInstance(String id){
        for(Admin a: this.admins){
            if(a.getId().equalsIgnoreCase(id)) return a ;
        }
        return null;
    }


    public Book getBookInstance(String id){
        //return Book instance if it exists else return null
        for(Book b: books){
            if(b.getBookId().equalsIgnoreCase(id)){
                return b;
            }
        }
        return null;
    }



    public void viewHistory(User user){
        History[] history = getHistory(user);
        if(history.length == 0){
            System.out.println(user.getName() + " has no borrowed history.");
        }else{
            for(History h: history){
                System.out.println(h);
            }
        }
    }

    private History[] getHistory(User user){
        ArrayList<History> histories = new ArrayList<>();
        for(History h: this.borrowedHistory){
            if(h.getBorrower().getId().equalsIgnoreCase(user.getId())) {
                histories.add(h);
            }
        }
        History[] temp = new History[histories.size()];
        return histories.toArray(temp);
    }

    public Book[] searchBook(String title ){
        List<Book> books = new ArrayList<>();
        for(Book b: this.books){
            if(b.getTitle().equalsIgnoreCase(title)){
                books.add(b);
            }
        }
        if(books.size() == 0 ) return new Book[]{};
        else{
            Book[] temp = new Book[this.books.size()];
            temp = books.toArray(temp);
            return temp;
        }

    }

    public boolean removeBookRequest(String id){
        return this.waitingList.removeBookRequest(id);
    }

    public void viewWaitingList(){
        this.waitingList.viewWaitingList();
    }



    public Book[] getAllBook(){
        ArrayList<Book> books = new ArrayList<>(this.books);
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        Book[] temp = new Book[this.books.size()];
        temp =  this.books.toArray(temp);
        return temp;
    }

    public String[] getAllBookIDByTitle(String title){
        List<String> IDs = new ArrayList<>();
        for(Book b: this.books){
            if(b.getTitle().equalsIgnoreCase(title)){
                IDs.add(b.getBookId());
            }
        }
        String[] ids = new String[IDs.size()];
        return IDs.toArray(ids);
    }

    public int checkBookAvailableAmount(String title){
        //return -1 if the book doesn't exist
        //return 0 if it's not avaibl
        int currentQty = -1 ;
        for(Book b: this.books){
            //check if the book title match and book isn't loan yet.
            if(b.getTitle().equalsIgnoreCase(title) && !b.isLoan()   ){
                if(currentQty == -1){
                    currentQty = 1 ;
                }else {
                    currentQty += 1 ;
                }
            }
        }
        return currentQty;
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

    public void returnBook(User user, Book... books){
        for(Book b: books){
            b.returnBack();
            this.borrowingList.remove(b);
        }
    }

    public void borrowBook(User borrower, Book... books){
        ArrayList<History.HistoryDetail> historyDetails = new ArrayList<>();
        if(books == null) return ;
        for(Book b:books) {
            b.loan(borrower);
            historyDetails.add(new History.HistoryDetail(b));
            borrowingList.add(b);
        }
        addHistory(borrower, historyDetails);
    }

    private void addHistory(User borrower, ArrayList<History.HistoryDetail> details){
        this.borrowedHistory.add(new History(borrower, details));
    }
}