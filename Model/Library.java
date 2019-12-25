package Model ;

import java.lang.reflect.Array;
import java.util.*;

public class Library{
    private List<User> users;
    private List<User> admins;
//    private List<Book> books ;
//    private Map<Book, Integer> books_qty;
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



    public Book getBookInstance(String title){
//        for(Book b: this.b)
        return new Book("");
    }


//    public Book searchBook(String title){
//        for(Book b: this.books_qty.keySet()){
//            if(b.getTitle().equals(title)){
//                return b ;
//            }
//        }
//        return null;
//    }

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

    public int checkBookAvailableStatus(String title){
        //return -1 if the book doesn't exist
        //return 0 if it's not avaibl
        int currentQty = -1 ;
        for(Book b: this.books){
            if(b.getTitle().equalsIgnoreCase(title)){
                if(currentQty == -1){
                    currentQty = 1 ;
                }else {
                    currentQty += 1 ;
                }
            }
        }
        return currentQty;
    }

}