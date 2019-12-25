package Model;

import java.util.ArrayList;
import java.util.List;

public class WaitingList {
    private List<BookRequest> bookRequests;

    private static WaitingList waitingList ;
    private WaitingList(){
        bookRequests = new ArrayList<>();
    }

    //singleton
    public static synchronized WaitingList getInstance(){
        if(waitingList == null) {
            waitingList = new WaitingList();
        }
        return waitingList;
    }

    public void addBookRequest(String title, User requestedUser){
        BookRequest request = getBookRequest(title);
        if(request == null){
            request = new BookRequest(title);
            this.bookRequests.add(request);
        }
        request.addRequestedUser(requestedUser);
    }

    private BookRequest getBookRequest(String title){
        //return BookRequest if the request of current exists else return null;
        for(BookRequest request: this.bookRequests){
            if(request.getBookTitle().equalsIgnoreCase(title)){
                return request;
            }
        }
        return null;
    }

    public void viewWaitingList(){
        if(this.bookRequests.size() == 0) {
            System.out.println("No Waiting User");
        }

        for(BookRequest bookRequest : this.bookRequests){
            System.out.println(bookRequest);
        }
    }

    public void addToWaitingList(User user, String title){
        BookRequest bookRequest = getBookRequest(title);
        bookRequest.addRequestedUser(user);
    }
}
