import java.util.ArrayList;
import java.util.HashMap;

public class StockManager {
    /**
     * A HashMap that keeps track of the last traded price (QUOTE) of a particular stock.
     */
    HashMap<Stock, Double> StockInfo;

    /**
     * Constructor for the StockManager Class.
     */
    public StockManager(){
        this.StockInfo = new HashMap<Stock, Double>();
    }

    /**
     * Returns the last traded value of a stock.
     * @param X The stock whose last traded value we are interested in
     * @return Last traded value of Stock X
     */
    public double getQuote(Stock X){
        return this.StockInfo.get(X);
    }

    /**
     * Updates the quote for a specified stock.
     * @param X Stock to update its latest traded price
     * @param price The newest price of Stock X
     */
    public void updateQuote(Stock X, double price){
        this.StockInfo.replace(X, price);
    }
}
