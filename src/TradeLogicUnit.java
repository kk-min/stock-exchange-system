import java.sql.SQLOutput;
import java.util.Scanner;

public class TradeLogicUnit implements LogicUnit{

    @Override
    public void executeLogic(){
        int userInput = -1;
        while(userInput != 3){
            String stockName;
            Order buyOrder;
            Order sellOrder;
            int typeSelection;
            double quantity;

            MenuManager.printOrderMenu();
            System.out.println("Select Option: ");
            userInput = inputMachine.nextInt();
            switch(userInput){
                case 1: // Buy a stock
                    System.out.print("Enter stock name: ");
                    stockName = inputMachine.next();
                    System.out.print("Select an option:\n1. Market Order  |  2. Limit Order\n");
                    typeSelection = inputMachine.nextInt();
                    System.out.print("Enter quantity of stock to buy: ");
                    quantity = inputMachine.nextDouble();
                    switch (typeSelection) {
                        case 1 -> {
                            buyOrder = new BuyOrder(stockName, quantity);
                            if(OrderManager.receiveOrder(buyOrder)) {
                                System.out.printf("You have placed a market buy order for %.2f %s shares.\n", quantity, stockName);
                            }
                        }
                        case 2 -> {
                            System.out.println("Enter desired price: ");
                            double desiredPrice = inputMachine.nextDouble();
                            buyOrder = new BuyOrder(stockName, desiredPrice, quantity);
                            if(OrderManager.receiveOrder(buyOrder)) {
                                System.out.printf("You have placed a limit buy order for %.2f %s shares at $%.2f each.\n", quantity, stockName, desiredPrice);
                            }
                        }
                    }
                    break;
                case 2: // Sell a stock
                    System.out.print("Enter stock name: ");
                    stockName = inputMachine.next();
                    System.out.print("Select an option:\n1. Market Order  |  2. Limit Order\n");
                    typeSelection = inputMachine.nextInt();
                    System.out.print("Enter quantity of stock to sell: ");
                    quantity = inputMachine.nextDouble();
                    switch (typeSelection) {
                        case 1 -> {
                            sellOrder = new SellOrder(stockName, quantity);
                            if(OrderManager.receiveOrder(sellOrder)) {
                                System.out.printf("You have placed a market sell order for %.2f %s shares.\n", quantity, stockName);
                            }
                        }
                        case 2 -> {
                            System.out.println("Enter desired price: ");
                            double desiredPrice = inputMachine.nextDouble();
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
        }
    }
}
