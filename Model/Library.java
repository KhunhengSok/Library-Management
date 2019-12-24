package Model ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library{
    private List<User> users;
    private List<User> admins;
//    private List<Book> books ;
    private Map<Book, Integer> books_qty;
    private History borrowHistory;
    private WaitingList waitingList;

    private static Library library = null;
    private Library(){
        this.users = new ArrayList<>();
        this.books_qty = new HashMap<>();
        this.admins = new ArrayList<>();
        this.borrowHistory = new History();
        this.waitingList = WaitingList.getInstance();
    }


    //singleton
    public static synchronized Library getInstance(){
        if(library == null){
            library = new Library();
        }
        return library;
    }

    public void insertBook(Admin admin, Book book, int qty){
        Integer currentQty = this.books_qty.get(book);
        if(currentQty == null){
            this.books_qty.put(book,qty);
        }else{
            this.books_qty.put(book, currentQty + qty);
        }

    }

    public boolean removeBook(Book book, int qty){
        int currentQty = this.books_qty.get((book));
        if(currentQty < qty){
            throw new IllegalArgumentException("Remove amount is larger than we currently have." + qty +  " > " + currentQty);
//            return false ;
        }
        this.books_qty.put(book, currentQty - qty);
        return true ;
    }

    public Book searchBook(String title){
        for(Book b: this.books_qty.keySet()){
            if(b.getTitle().equals(title)){
                return b ;
            }
        }
        return null;
    }

    public boolean isBookAvailable(Book book){
        Integer currentQty = this.books_qty.get(book);
        if(currentQty == null || currentQty == 0){
            return  false;
        }else if (currentQty >=1 ){
            return true;
        }
        return false ;
    }

}