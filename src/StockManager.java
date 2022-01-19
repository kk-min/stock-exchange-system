import java.util.ArrayList;
import java.util.HashMap;

public class StockManager {
    /**
     * A HashMap that keeps track of the last traded price (QUOTE) of a particular stock.
     */
    HashMap<Stock, Double> StockInfo;
    /**
     * A HashMap that keeps track of the list of currently pending BuyOrders in chronological order.
     */
    HashMap<Stock, ArrayList<Order>> pendingBuyOrders;
    /**
     * A HashMap that keeps track of the list of currently pending SellOrders in chronological order.
     */
    HashMap<Stock, ArrayList<Order>> pendingSellOrders;

    /**
     * Constructor for the StockManager Class.
     */
    public StockManager(){
        this.StockInfo = new HashMap<Stock, Double>();
        this.pendingBuyOrders = new HashMap<Stock, ArrayList<Order>>();
        this.pendingSellOrders = new HashMap<Stock, ArrayList<Order>>();
    }

    public void addOrder(Order orderToAdd){

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
     * Returns the last traded value of a stock.
     * @param X The stock whose last traded value we are interested in
     * @return Last traded value of Stock X
     */
    public double getQuote(Stock X){
        return this.StockInfo.get(X);
    }

    public void receiveOrder(TradeExecutable X){
        X.executeTrade();
    }
}
