import Model.*;

import java.util.* ;

public class Main{
    private static Scanner scanner  ;
    private static Library library ;
    private static Person currentLogin = new User("Khunheng", "Sok");

    /*
        ******Screen Naming Convention
        * Login and Exit : menu
        * Admin and User : role
        * functionality : functionality
     */
    public static void main1(String args[]){
        scanner = new Scanner(System.in);
        library =  Library.getInstance() ;
        library.viewAllBook();
        library.viewBorrowingList();
        library.addToWaitingList((User)currentLogin, "My Book");
        library.getWaitingList().viewWaitingList();
        return ;
    }

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
        boolean flag = true;
        while (flag){
            printAdminFunctionalityPrompt();
            String input = handleInput();
            switch (input){
                case "1": insertBookWithPrompt();break;
                case "2": removeBookWithPrompt();break;
                case "3": removeUserRequestWithPrompt();break;
                case "4": viewAllBooks();break;
                case "5": viewWaitingList();break;
                case "6": viewBorrowingList();break;
                case "7":  flag = false ; break;
                default: println("Invalid input");  break;
            }
        }
    }

    private static void user(){
        boolean flag = true;
        while(flag){
            printUserFunctionalityPrompt();
            String input = handleInput();
            switch (input){
                case "1": break;
                case "2": break;
                case "3": break;
                case "4": break;
                case "5": flag = false ;break;
                default: println("Invalid input");  break;

            }
        }

    }


    //======================Functionality=====================

    //=========Admin===========
    private static void insertBookWithPrompt(){
        print("Enter a book title");
        String title = scanner.nextLine();
        Book book = new Book(title);
        library.insertBook((Admin)currentLogin, book);
    }

    private static void removeBookWithPrompt(){
        print("Enter a book id");
        String id = scanner.nextLine();
        library.removeBookByBookID(id);
    }

    private static void removeUserRequestWithPrompt(){
        print("Enter request id: ");
        String requestID = scanner.nextLine();
        library.removeBookRequest(requestID);
    }

    private static void viewAllBooks() {
        library.viewAllBook();
    }


    private static void viewWaitingList(){
        library.viewWaitingList();
    }

    private static  void viewBorrowingList(){
        library.viewBorrowingList();
    }

    //=========User===========
    private static  void borrowBooksWithPrompt(){
        println("Enter a title");
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
        println("Please enter a number: ");
        println("1. Insert Book");
        println("2. Remove Book");
        println("3. Remove user's request in the waiting list");
        println("4. View all books");
        println("5. View the waiting list");
        println("6. View the borrowing list");
        println("7. Back");
    }

    private static void printUserFunctionalityPrompt(){
        println("1. Borrow books");
        println("2. Search for a book");
        println("3. View history");
        println("4. View all books");
        println("5. Back");
    }
}

