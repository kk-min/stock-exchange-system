import java.util.InputMismatchException;
import java.util.Scanner;

import logicunit.*;
import menu.*;

/**
 * Main application that hosts the logic for the main menu.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class MainApp {
    /**
     * The Main application.
     * @param args
     * @throws InputMismatchException When input types don't match
     */
    public static void main(String[] args) throws InputMismatchException{
        Scanner mainInputMachine = new Scanner(System.in);
        LogicUnit TradeSystem = new TradeLogicUnit();
        LogicUnit QuoteSystem = new QuoteLogicUnit();

        int userInput;
        while (true) {
            try {
                MenuManager.printMainMenu();
                System.out.print("Select an option: ");
                userInput = mainInputMachine.nextInt();
                mainInputMachine.nextLine();
                switch (userInput) {
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
            catch(InputMismatchException e){
                System.out.println("Invalid input! Please try again.");
                mainInputMachine.next();
            }
        }
    }
}
