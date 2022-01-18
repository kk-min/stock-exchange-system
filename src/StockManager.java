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
        switch(orderToAdd.get)
    }
}
