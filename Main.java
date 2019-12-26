import Model.*;

import java.util.* ;

public class Main{
    private static Scanner scanner  ;
    private static Library library ;
    private static Person currentLogin ;

    /*
        ******Screen Naming Convention
        * Login and Exit : menu
        * Admin and User : role
        * functionality : functionality
     */

    public static void main(String args[]){
        scanner = new Scanner(System.in);
        library =  Library.getInstance() ;
        control();
    }
    


    private static String handleInput(){
        String input = scanner.nextLine();

        if(input.contains("1")){
            return "1";
        }else if(input.contains("2")){
            return "2";
        }else if(input.contains("3")){
            return "3";
        }else if(input.contains("4")){
            return "4";
        }else if(input.contains("5")){
            return "5";
        }else if(input.contains("6")){
            return "6";
        }else if(input.contains("7")){
            return "7";
        }else return "Invalid";

    }


    

    private static void print(String msg){
        System.out.print(msg);
    }

    private static void println(String msg){
        System.out.println(msg);
    }

    private static User createUserWithPrompt(){
        System.out.print("Enter a last name: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter a first name: ");
        String firstname = scanner.nextLine();

        return new User(lastname, firstname);
    }

    private static Admin createAdminWithPrompt(){
        System.out.print("Enter a last name");
        String lastname = scanner.nextLine();
        System.out.print("Enter a first name");
        String firstname = scanner.nextLine();

        return new Admin(lastname, firstname);
    }

    private static void bookSearchWithPrompt(){
        System.out.print("Enter a book title: ");
        String title = scanner.nextLine();
        Book[] books = library.searchBook(title);
        if(books.length == 0) return ;
        for(Book b: books){
            System.out.print(b);
        }
    }

    private static void viewHistoryFromAdminWithPrompt(){
        System.out.print("Enter a last name: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter a first name: ");
        String firstname = scanner.nextLine();

        User user = library.getUserInstance(lastname, firstname);
        if(user == null) {
            System.out.println("User not found");
        }else{
            library.viewHistory(user);
        }
    }

    private static void viewHistoryFromUser(){
        User user = (User)currentLogin;
        library.viewHistory(user);
    }

    private static void control(){
        while(true){
            printMenuPrompt();
            String input = handleInput();
            if(input.equalsIgnoreCase("2")) break;
            else if(input.equalsIgnoreCase("1")){
                while(true){
                    printRolePrompt();
                    input = handleInput();
                    if(input.equalsIgnoreCase("1")) admin();
                    else if(input.equalsIgnoreCase("2")) user();
                    else {
                       println("Invalid input");
                    }
                }
            }else {
                println("Invalid input");
            }
        }
    }

    private static void admin(){
        currentLogin = new Admin("Khunheng", "Sok");
        boolean flag = true;
        while (flag){
            printAdminFunctionalityPrompt();
            String input = handleInput();
            switch (input){
                case "1": insertBookWithPrompt();break;
                case "2": removeBookWithPrompt();break;
                case "3": removeUserRequestWithPrompt();break;
                case "4": viewAllBooksByAdmin();break;
                case "5": viewWaitingList();break;
                case "6": viewBorrowingList();break;
                case "7":  flag = false ; break;
                default: println("Invalid input");  currentLogin = null; break;
            }
        }
    }

    private static void user(){
        currentLogin = new User("Khunheng", "Sok");
        boolean flag = true;
        while(flag){
            printUserFunctionalityPrompt();
            String input = handleInput();
            switch (input){
                case "1": borrowBooksWithPrompt();break;
                case "2": searchBookWithPrompt();break;
                case "3": viewHistory();break;
                case "4": viewAllBooksByUser();break;
                case "5": flag = false ;break;
                default: println("Invalid input"); currentLogin = null;  break;

            }
        }

    }


    //======================Functionality=====================

    //=========Admin===========
    private static void insertBookWithPrompt(){
        print("Enter a book title: ");
        String title = scanner.nextLine();
        Book book = new Book(title);
//        library.insertBook((Admin)currentLogin, book);
        ((Admin)currentLogin).insertBook(book);
    }

    private static void removeBookWithPrompt(){
        print("Enter a book id: ");
        String id = scanner.nextLine();
//        library.removeBookByBookID(id);
        ((Admin)currentLogin).removeBook(id);
    }

    private static void removeUserRequestWithPrompt(){
        print("Enter request id: ");
        String requestID = scanner.nextLine();
//        library.removeBookRequest(requestID);
        ((Admin)currentLogin).removeRequest(requestID);
    }

    private static void viewAllBooksByAdmin() {
//        library.viewAllBook();
        ((Admin)currentLogin).viewAllBook();
    }

    private static void viewAllBooksByUser() {
//        library.viewAllBook();
        ((User)currentLogin).viewAllBook();
    }

    private static void viewWaitingList(){
//        library.viewWaitingList();
        ((Admin)currentLogin).viewWaitingList();
    }

    private static  void viewBorrowingList(){
//        library.viewBorrowingList();
        ((Admin)currentLogin).viewWaitingList();
    }

    //=========User===========
    private static  void borrowBooksWithPrompt(){
        println("Enter a Book ID: ");
        String id = scanner.nextLine();
        id = id.trim();
        Book book = library.getBookInstance(id);
        if(book != null) {
//            library.borrowBook((User) currentLogin, book);
            ((User)currentLogin).borrowBook(book);
        }
    }

    private static void searchBookWithPrompt(){
        print("Enter a title: ");
        String title = scanner.nextLine();
//        Book[] books = library.searchBook(title);
        Book[] books = ((User)currentLogin).searchBook(title);
        if(books.length==0){
            println("\"" + title + "\"" + " is not available in our library.");
        }else {
            printBooks(books);
        }
    }

    private static void viewHistory(){
        ((User)currentLogin).viewHistory();
    }

    //======================End functionality===================


    private static void printBooks(Book[] books){
        for(Book b: books){
            println(b.toString());
        }
    }


    //=============================Prompt=================================
    private static void printMenuPrompt(){
        System.out.println("\t\t\t\t\tEnter a number:\n");
        System.out.println("1. Log in");
        System.out.println("2. Exit");

    }

    private static void printRolePrompt(){
        System.out.println("\t\t\t\t\tEnter a number:\n");
        System.out.println("1. Admin");
        System.out.println("2. User");
    }
    private static void printAdminFunctionalityPrompt(){
        println("\nPlease enter a number: ");
        println("1. Insert Book");
        println("2. Remove Book");
        println("3. Remove user's request in the waiting list");
        println("4. View all books");
        println("5. View the waiting list");
        println("6. View the borrowing list");
        println("7. Back");
    }

    private static void printUserFunctionalityPrompt(){
        println("\nPlease enter a number: ");
        println("1. Borrow books");
        println("2. Search for a book");
        println("3. View history");
        println("4. View all books");
        println("5. Back");
    }
}

