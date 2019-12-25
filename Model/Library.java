package Model ;

import java.util.*;

public class Library{
    public static int BOOK_REQUEST_GRANTED = 1;
    public static int BOOK_REQUEST_NOT_AVAILABLE = 2;
    public static int BOOK_REQUEST_NOT_ENOUGH = 3 ;

    private List<User> users;
    private List<Admin> admins;
    private ArrayList<Book> books;
    private List<History> borrowedHistory;


    private WaitingList waitingList;

    private static Library library = null;
    private Library(){
        this.users = new ArrayList<>();
//        this.books_qty = new HashMap<>();
        this.books = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.borrowedHistory = new ArrayList<>();
        this.waitingList = WaitingList.getInstance();
    }


    //singleton
    public static synchronized Library getInstance(){
        if(library == null){
            library = new Library();
        }
        return library;
    }

//    public void insertBook(Admin admin, Book book, int qty){
//        if(qty <= 0) return ;
//        Integer currentQty = this.books_qty.get(book);
//        if(currentQty == null){
//            for(int i = 0 ; i<qty ; i++){
//                this.books_qty.put(book,qty);
//            }
//        }else{
//            this.books_qty.put(book, currentQty + qty);
//        }
//
//    }
    public WaitingList getWaitingList() {
        return waitingList;
    }

    public void insertBook(Admin admin, Book... books){
        for(Book b: books){
            this.books.add(b);
        }
    }

//    public boolean removeBook(Book book, int qty){
//        Integer  currentQty = this.books_qty.get((book));
//        if(currentQty == null){
//            throw new IllegalArgumentException("The book doesn't exist in our library.");
//        }else  if(currentQty < qty){
//            throw new IllegalArgumentException("Remove amount is larger than we currently have." + qty +  " > " + currentQty);
////            return false ;
//        }
//        this.books_qty.put(book, currentQty - qty);
//        return true ;
//    }

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
        for(Book b: this.books){
            System.out.println(b);
        }
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


//    public Book searchBook(String title){
//        for(Book b: this.books_qty.keySet()){
//            if(b.getTitle().equals(title)){
//                return b ;
//            }
//        }
//        return null;
//    }

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

            }
        }

        Book[] temp = new Book[this.books.size()];
        temp = this.books.toArray(temp);
        return temp;
    }

//    public Book[] getAllBooks(){
//        List<Book> availableBooks = new ArrayList<>();
//        //        Book[] books = Arrays.copyOf(this.books_qty.keySet().toArray(), this.books_qty.size(), Book[].class);
//        for(Book b : this.books_qty.keySet()){
//            if(this.books_qty.get(b) != 0){
//                availableBooks.add(b);
//            }
//        }
//        return (Book[])availableBooks.toArray();
//    }

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

//    public int[] borrowBook(String title, int Qty){
//        //return array of int
//        //1st: BOOK_REQUEST_STATUS (Check Constant at top)
//        //2nd: availableAmount
//        //3rd: requestAmount
//        int availableAmount = this.checkBookAvailableAmount(title);
//
//        int[] returned = new int[3];
//        returned[1] = availableAmount;
//        returned[2] = Qty;
//
//        if(availableAmount >= Qty){
//            returned[0] = BOOK_REQUEST_GRANTED;
//        }else if(availableAmount < Qty ){
//            returned[0] = BOOK_REQUEST_NOT_ENOUGH;
//        }
//        return returned;
//    }

    public void borrowBook(User borrower, Book... books){
        ArrayList<History.HistoryDetail> historyDetails = new ArrayList<>();
        for(Book b:books) {
            b.loan(borrower);
            historyDetails.add(new History.HistoryDetail(b));
        }
        addHistory(borrower, historyDetails);
    }

    private void addHistory(User borrower, ArrayList<History.HistoryDetail> details){
        this.borrowedHistory.add(new History(borrower, details));
    }
}