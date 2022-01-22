import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages various functionalities pertaining to Order objects.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class OrderManager {
    /**
     * An ArrayList that stores the history of orders made from earliest to latest.
     */
    private static ArrayList<Order> orderHistory = new ArrayList<Order>();;
    /**
     * A HashMap that keeps track of the list of currently pending BuyOrders in chronological order.
     */
    private static HashMap<String, ArrayList<Order>> pendingBuyOrders = new HashMap<String, ArrayList<Order>>();
    /**
     * A HashMap that keeps track of the list of currently pending SellOrders in chronological order.
     */
    private static HashMap<String, ArrayList<Order>> pendingSellOrders = new HashMap<String, ArrayList<Order>>();

    /**
     *
     * @param X The order to add to the order history.
     */
     public static void addToOrderHistory(Order X){
         orderHistory.add(X);
     }

    /**
     * Returns the current order history.
     * @return orderHistory
     */
    public static ArrayList<Order> getOrderHistory(){
         return orderHistory;
     }

    /**
     * Returns the current highest pending buy price
     * @param X The stock that we want to check the buy price for. Returns -1 if none is found.
     * @return The current highest pending buy price
     */
    public static double getBid(String X){
        ArrayList<Order> buyList = pendingBuyOrders.get(X); // Get the pending buy orders for Stock X
        if (buyList == null){
            return -1;
        }
        if(buyList.isEmpty()){
            return -1;
        }
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
     * @param X The stock that we want to check the ask price for. Returns -1 if none is found.
     * @return The current lowest pending ask price.
     */
    public static double getAsk(String X){
        ArrayList<Order> sellList = pendingSellOrders.get(X); // Get the pending sell orders for Stock X
        if(sellList == null){
            return -1;
        }
        if(sellList.isEmpty()){
            return -1;
        }
        double minimumPrice = Double.MAX_VALUE;
        for (Order pendingOrder : sellList) {
            if(pendingOrder.getPrice() < minimumPrice){
                minimumPrice = pendingOrder.getPrice(); // Update the minimum price if a lower one is found in the pending list
            }
        }
        if (minimumPrice == Double.MAX_VALUE){ // No records found
            return -1;
        }
        return minimumPrice;
    }

    /**
     * Receives an order and performs any trades with it immediately where applicable.
     * @param X The order to receive and execute.
     * @return true if order received successfully, false otherwise
     */
    public static boolean receiveOrder(Order X){
        String stockName = X.getStockName();
        ArrayList<Order> pendingBuyList = pendingBuyOrders.get(stockName);
        if (pendingBuyList == null){
            pendingBuyList = new ArrayList<Order>();
            pendingBuyOrders.put(stockName, pendingBuyList);
        }
        ArrayList<Order> pendingSellList = pendingSellOrders.get(stockName);
        if (pendingSellList == null){
            pendingSellList = new ArrayList<Order>();
            pendingSellOrders.put(stockName, pendingSellList);
        }

        Order matchingOrder = X.findMatchingOrder(pendingBuyList, pendingSellList);
        /**
        if ((matchingOrder == null) && (X.getOrderType() == Order.TYPE.MARKET)){
            // If there is no matching order and it was a market order, we need to reject.
            System.out.println("There are currently no available trades for this stock.");
            return false;
        }
         **/
        X.executeTrade(matchingOrder, pendingBuyList, pendingSellList);
        addToOrderHistory(X);
        return true;
    }
}
