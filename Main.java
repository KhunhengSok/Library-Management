import Model.Book;
import Model.Library;

import java.util.* ;

public class Main{
    private static Scanner scanner  ;
    private static Library library ;

    public static void main(String args[]){
        scanner = new Scanner(System.in);
        library =  Library.getInstance() ;

        library.insertBook(null, new Book( "My book"));
        String bookTitle = scanner.nextLine();
        while(!(bookTitle.isEmpty() || bookTitle.equals(""))){
            library.insertBook(null, new Book(bookTitle));
            bookTitle = scanner.nextLine();
        }

        Book[] books = library.getAllBook();
        for(Book b: books){
            print(b.toString());
        }




        //        while(true){
//            String choice = menuPrompt();
//            if (choice == "2"){
//                break ;
//            }else if (choice == "1"){
//                operationPrompt();
//            }else {
//                System.out.println("Invalid Input");
//            }
//        };
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
}

