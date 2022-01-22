package logicunit;
import java.util.InputMismatchException;
import order.*;
import menu.*;

/**
 * A logic unit that handles user inputs for creating orders and communicating with OrderManager.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class TradeLogicUnit implements LogicUnit{

    @Override
    public void executeLogic() throws InputMismatchException {
        int userInput = -1;
            while (userInput != 3) {
                try{
                    String stockName;
                    Order buyOrder;
                    Order sellOrder;
                    int typeSelection;
                    double quantity;

                    MenuManager.printOrderMenu();
                    System.out.print("Select Option: ");
                    userInput = inputMachine.nextInt();
                    inputMachine.nextLine();
                    switch (userInput) {
                        case 1: // Buy a stock
                            System.out.print("Enter stock name: ");
                            stockName = inputMachine.next();
                            inputMachine.nextLine();
                            System.out.print("Select an option:\n1. Market Order  |  2. Limit Order\n");
                            typeSelection = inputMachine.nextInt();
                            inputMachine.nextLine();
                            System.out.print("Enter quantity of stock to buy: ");
                            quantity = inputMachine.nextDouble();
                            inputMachine.nextLine();
                            if (quantity < 0) { // We cannot order a quantity less than 0
                                System.out.println("Invalid quantity! Please try again.");
                                System.out.println();
                                break;
                            }
                            switch (typeSelection) {
                                case 1 -> {
                                    buyOrder = new BuyOrder(stockName, quantity);
                                    if (OrderManager.receiveOrder(buyOrder)) {
                                        System.out.printf("You have placed a market buy order for %.2f %s shares.\n", quantity, stockName);
                                    }
                                }
                                case 2 -> {
                                    System.out.print("Enter desired price: ");
                                    double desiredPrice = inputMachine.nextDouble();
                                    inputMachine.nextLine();
                                    if (desiredPrice < 0) {
                                        System.out.println("Invalid price! Please try again.");
                                        System.out.println();
                                    }
                                    buyOrder = new BuyOrder(stockName, desiredPrice, quantity);
                                    if (OrderManager.receiveOrder(buyOrder)) {
                                        System.out.printf("You have placed a limit buy order for %.2f %s shares at $%.2f each.\n", quantity, stockName, desiredPrice);
                                    }
                                }
                            }
                            break;
                        case 2: // Sell a stock
                            System.out.print("Enter stock name: ");
                            stockName = inputMachine.next();
                            inputMachine.nextLine();
                            System.out.print("Select an option:\n1. Market Order  |  2. Limit Order\n");
                            typeSelection = inputMachine.nextInt();
                            inputMachine.nextLine();
                            System.out.print("Enter quantity of stock to sell: ");
                            quantity = inputMachine.nextDouble();
                            inputMachine.nextLine();
                            if (quantity < 0) {
                                System.out.println("Invalid quantity! Please try again.");
                                System.out.println();
                            }
                            switch (typeSelection) {
                                case 1 -> {
                                    sellOrder = new SellOrder(stockName, quantity);
                                    if (OrderManager.receiveOrder(sellOrder)) {
                                        System.out.printf("You have placed a market sell order for %.2f %s shares.\n", quantity, stockName);
                                    }
                                }
                                case 2 -> {
                                    System.out.print("Enter desired price: ");
                                    double desiredPrice = inputMachine.nextDouble();
                                    inputMachine.nextLine();
                                    if (desiredPrice < 0) {
                                        System.out.println("Invalid price! Please try again.");
                                        System.out.println();
                                    }
                                    sellOrder = new SellOrder(stockName, desiredPrice, quantity);
                                    if (OrderManager.receiveOrder(sellOrder)) {
                                        System.out.printf("You have placed a limit sell order for %.2f %s shares at $%.2f each.\n", quantity, stockName, desiredPrice);
                                    }
                                }
                            }
                            break;
                        case 3: // move back
                            return;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                    System.out.println("---------------");
            }catch(InputMismatchException e){
                    System.out.println("Invalid input! Please try again.");
                    inputMachine.next();
                }
        }
    }
}
