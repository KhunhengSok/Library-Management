package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BookRequest {
    private String bookTitle;
    private Queue<User> waitingUsers ;
//    private static List<BookRequest> bookRequests ;

    public String getBookTitle() {
        return this.bookTitle;
    }

    //1 book request for 1 book type
    public BookRequest(String title){
        this.bookTitle = title;
        this.waitingUsers = new LinkedList<>();
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
        str += "Waiting Users: ";
        if(this.waitingUsers.size() == 0) str += "None.\n" ;
        else {
            for(User u : this.waitingUsers) {
                str += u.getName() + ", ";
            }
        }
        return str;
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
