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

    public double getBid(Stock X){
        ArrayList<Order> buyList = pendingBuyOrders.get(X); // Get the pending buy orders for Stock X

    }

    public double getAsk(Stock X){
        ArrayList<Order> sellList = pendingSellOrders.get(X) // Get the pending sell orders for Stock X

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
