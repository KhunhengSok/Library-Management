package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

public class BookRequestQueue {
    private String id ;
    private String bookTitle;
    private Queue<User> waitingUsers ;
//    private static List<BookRequestQueue> bookRequests ;

    public String getBookTitle() {
        return this.bookTitle;
    }

    //1 book request for 1 book type
    public BookRequestQueue(String title){
        this.bookTitle = title;
        this.waitingUsers = new LinkedList<>();
        this.id = UUID.randomUUID().toString();
    }

    public void addRequestedUser(User user){
        this.waitingUsers.add(user);
    }

    public Queue<User> getWaitingUsers() {
        return waitingUsers;
    }

    @Override
    public String toString() {
        String str =  "Book title: " + this.bookTitle + "\n" ;
        str += "Request ID: " + this.id +'\n';
        str += "Waiting Users: ";
        if(this.waitingUsers.size() == 0) str += "None.\n" ;
        else {
            for(User u : this.waitingUsers) {
                str += u.getName() + ", ";
            }
        }
        return str;
    }

    public String getId() {
        return id;
    }

    //    public static synchronized BookRequestQueue getInstance(Book book ){
//        for(BookRequestQueue bookRequestrequest: bookRequests){
//            if(bookRequestrequest.book.getBookId() == book.getBookId() ){
//                return bookRequestrequest;
//            }
//        }
//
//        //create new request since it doesn't exist yet
//        BookRequestQueue newRequest = new BookRequestQueue(book);
//        bookRequests.add((newRequest));
//        return newRequest;
//    }

}
