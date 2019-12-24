package Model;

import java.util.List;

public class WaitingList {
    private List<BookRequest> bookRequests;

    private static WaitingList waitingList ;
    private WaitingList(){}

    //singleton
    public static synchronized WaitingList getInstance(){
        if(waitingList == null) {
            waitingList = new WaitingList();
        }
        return waitingList;
    }

    public void addRequest(Book book, User requestedUser){
        BookRequest request = getBookRequest(book);
        if(request == null){
            request = new BookRequest(book);
            this.bookRequests.add(request);
        }
        request.addRequestedUser(requestedUser);
    }

    private BookRequest getBookRequest(Book book){
        //return BookRequest if the request of current exists else return null;
        for(BookRequest request: this.bookRequests){
            if(request.getBook().getBookId() == book.getBookId()){
                return request;
            }
        }
        return null;
    }


}
