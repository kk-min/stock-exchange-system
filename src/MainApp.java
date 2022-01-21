import java.util.Scanner;

public class MainApp {
    public static void main(String args[]){
        Scanner inputMachine = new Scanner(System.in);
        LogicUnit TradeSystem = new TradeLogicUnit();
        LogicUnit QuoteSystem =  new QuoteLogicUnit();

        int userInput = -1;
        while(true){
            MenuManager.printMainMenu();
            userInput = inputMachine.nextInt();
            switch(userInput){
                case 1: // Create an order
                    TradeSystem.executeLogic();
                    break;
                case 2: // View orders
                    MenuManager.printOrderHistory();
                    break;
                case 3: // Get quote (the latest traded price of a stock)
                    QuoteSystem.executeLogic();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input! Please try again.");
            }
        }

    }
}
