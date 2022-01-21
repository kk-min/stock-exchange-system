import java.util.ArrayList;
import java.util.HashMap;

public class StockManager {
    /**
     * A HashMap that keeps track of the last traded price (QUOTE) of a particular stock.
     */
    HashMap<String, Double> quoteInfo;

    /**
     * Constructor for the StockManager Class.
     */
    public StockManager(){
        this.quoteInfo = new HashMap<String, Double>();
    }

    /**
     * Returns the last traded value of a stock.
     * @param X The stock whose last traded value we are interested in
     * @return Last traded value of Stock X
     */
    public double getQuote(String X){
        return this.quoteInfo.get(X);
    }

    /**
     * Updates the quote for a specified stock.
     * @param X Stock to update its latest traded price
     * @param price The newest price of Stock X
     */
    public void updateQuote(String X, double price){
        this.quoteInfo.replace(X, price);
    }
}
