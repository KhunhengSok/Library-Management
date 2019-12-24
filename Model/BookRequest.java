package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BookRequest {
    private Book book;
    private Queue<User> waitingUsers ;
//    private static List<BookRequest> bookRequests ;

    public Book getBook() {
        return book;
    }

    //1 book request for 1 book type
    public BookRequest(Book book){
        this.book = book;
        this.waitingUsers = new LinkedList<>();
    }

    public void addRequestedUser(User user){
        this.waitingUsers.add(user);
    }

    public Queue<User> getWaitingUsers() {
        return waitingUsers;
    }


//    public static synchronized BookRequest getInstance(Book book ){
//        for(BookRequest bookRequestrequest: bookRequests){
//            if(bookRequestrequest.book.getBookId() == book.getBookId() ){
//                return bookRequestrequest;
//            }
//        }
//
//        //create new request since it doesn't exist yet
//        BookRequest newRequest = new BookRequest(book);
//        bookRequests.add((newRequest));
//        return newRequest;
//    }

}
