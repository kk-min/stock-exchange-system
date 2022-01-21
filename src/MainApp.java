import java.awt.*;
import java.util.Scanner;

public class MainApp {
    public static void main(String args[]){
        Scanner inputMachine = new Scanner(System.in);
        int userInput = -1;

        while(userInput != 4){
            MenuManager.printMainMenu();
            userInput = inputMachine.nextInt();
            switch(userInput){
                case 1: // Create an order
                    TradeLogic.executeLogic();
                    break;
                case 2: // View orders
                    MenuManager.printOrderHistory();
                    break;
                case 3:
                case 4:
                default:
                    System.out.println("Invalid input! Please try again.");
            }
        }

    }
}
