import java.util.ArrayList;
import java.util.HashMap;

/**
 * The OrderManager Class
 * Manages various functionalities pertaining to Order objects.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class OrderManager {
    /**
     * An ArrayList that stores the history of orders made from earliest to latest.
     */
    ArrayList<Order> orderHistory;
    /**
     * A HashMap that keeps track of the list of currently pending BuyOrders in chronological order.
     */
    HashMap<Stock, ArrayList<Order>> pendingBuyOrders;
    /**
     * A HashMap that keeps track of the list of currently pending SellOrders in chronological order.
     */
    HashMap<Stock, ArrayList<Order>> pendingSellOrders;

    /**
     * Constructor for OrderManager.
     */
     public OrderManager(){
        this.orderHistory = new ArrayList<Order>();
     }

    /**
     *
     * @param X The order to add to the order history.
     */
     public void addToOrderHistory(Order X){
         this.orderHistory.add(X);
     }

    /**
     * Returns the current order history.
     * @return orderHistory
     */
    public ArrayList<Order> getOrderHistory(){
         return this.orderHistory;
     }
    /**
     * Returns the current highest pending buy price
     * @param X The stock that we want to check the buy price for.
     * @return The current highest pending buy price
     */
    public double getBid(Stock X){
        ArrayList<Order> buyList = pendingBuyOrders.get(X); // Get the pending buy orders for Stock X
        double maxPrice = -1; // Actual stock price will never go below 0, so we can set initialize with -1 as the minimum
        for (Order pendingOrder : buyList){
            if(pendingOrder.getPrice() > maxPrice){
                maxPrice = pendingOrder.getPrice(); // Update the max price if a higher one is found in the pending buy list
            }
        }
        return maxPrice;
    }

    /**
     * Returns the current lowest pending ask price.
     * @param X The stock that we want to check the ask price for.
     * @return The current lowest pending ask price.
     */
    public double getAsk(Stock X){
        ArrayList<Order> sellList = pendingSellOrders.get(X); // Get the pending sell orders for Stock X
        double minimumPrice = Double.MAX_VALUE;
        for (Order pendingOrder : sellList) {
            if(pendingOrder.getPrice() < minimumPrice){
                minimumPrice = pendingOrder.getPrice(); // Update the minimum price if a lower one is found in the pending list
            }
        }
        return minimumPrice;
    }

    /**
     * Receives an order and performs any trades with it immediately where applicable.
     * @param X The order to receive and execute.
     */
    public void receiveOrder(Order X){
        X.executeTrade();
    }

    public HashMap<Stock, ArrayList<Order>> getPendingBuyOrders(){
        return this.pendingBuyOrders;
    }

    public HashMap<Stock, ArrayList<Order>> getPendingSellOrders() {
        return pendingSellOrders;
    }
}
