import java.util.* ;

public class Main{
    private static Scanner scanner  ;

    public static void main(String args[]){
        System.out.println("hello world");
        scanner = new Scanner(System.in);

        while(true){
            String choice = menuPrompt();
            if (choice == "2"){
                break ;
            }else if (choice == "1"){
                operationPrompt();
            }else {
                System.out.println("Invalid Input");
            }
        };
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

