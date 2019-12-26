package Model;

import java.util.ArrayList;
import java.util.List;

public class WaitingList {
    private List<BookRequestQueue> bookRequestQueues;

    private static WaitingList waitingList ;
    private WaitingList(){
        bookRequestQueues = new ArrayList<>();
    }

    //singleton
    public static synchronized WaitingList getInstance(){
        if(waitingList == null) {
            waitingList = new WaitingList();
        }
        return waitingList;
    }

    public void addBookRequest(String title, User requestedUser){
        BookRequestQueue request = getBookRequest(title);
        if(request == null){
            request = new BookRequestQueue(title);
            this.bookRequestQueues.add(request);
        }
        request.addRequestedUser(requestedUser);
    }

    private BookRequestQueue getBookRequest(String title){
        //return BookRequestQueue if the request of current exists else create a empty request;
        BookRequestQueue queue = null;
        for(BookRequestQueue request: this.bookRequestQueues){
            if(request.getBookTitle().equalsIgnoreCase(title)){
                queue = request;
            }
        }
        if(queue == null){
            queue = new BookRequestQueue(title);
            this.bookRequestQueues.add(queue);
        }
        return queue;

    }

    public void viewWaitingList(){
        if(this.bookRequestQueues.size() == 0) {
            System.out.println("No Waiting User");
        }

        for(BookRequestQueue bookRequestQueue : this.bookRequestQueues){
            System.out.println(bookRequestQueue);
        }
    }

    public void addToWaitingList(User user, String title){
        BookRequestQueue bookRequestQueue = getBookRequest(title);
        bookRequestQueue.addRequestedUser(user);
    }

    public boolean removeBookRequest(String id){
        for(BookRequestQueue bookRequestQueue : this.bookRequestQueues){
            if(bookRequestQueue.getId().equalsIgnoreCase(id)){
                this.removeBookRequest(bookRequestQueue);
                return true;
            }
        }
        return false;
    }

    public boolean removeBookRequest(BookRequestQueue bookRequestQueue){
        return this.bookRequestQueues.remove(bookRequestQueue);
    }
}
