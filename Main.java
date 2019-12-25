import Model.*;

import java.util.* ;

public class Main{
    private static Scanner scanner  ;
    private static Library library ;
    private static Person currentLogin;

    public static void main(String args[]){
        scanner = new Scanner(System.in);
        library =  Library.getInstance() ;

        library.getWaitingList().viewWaitingList();
        return ;
    }

    
    private static String menuPrompt(){
        System.out.println("\t\t\t\t\tEnter a number:\n");
        System.out.println("1. Log in");
        System.out.println("2. Exit");
        String input = handleOperationInput();
        return input;
    }

    private static String handleOperationInput(){
        String input = scanner.nextLine();
        if(input.contains("1")){
            return "1";
        }else if(input.contains("2")){
            return "2";
        }else{
            return "Invalid";
        }
    }

    private static String operationPrompt(){
        System.out.println("\t\t\t\t\tEnter a number:\n");
        System.out.println("1. Admin");
        System.out.println("2. User");
        String input = handleOperationInput();
        return input ;
    }
    

    private static void print(String msg){
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
}

