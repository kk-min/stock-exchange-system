package order;

import java.util.HashMap;

/**
 * Class that tracks the last traded price of stocks.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class StockManager {
    /**
     * A HashMap that keeps track of the last traded price of a particular stock.
     */
    private static HashMap<String, Double> quoteInfo = new HashMap<String, Double>();

    /**
     * Returns the last traded value of a stock.
     * @param X The stock whose last traded value we are interested in
     * @return Last traded value of Stock X. Returns -1 if unavailable
     */
    public static double getQuote(String X){
        Double quote = quoteInfo.get(X);
        if(quote == null){
            return -1;
        }
        return quote;
    }

    /**
     * Updates the quote for a specified stock.
     * @param X Stock to update its latest traded price
     * @param price The newest price of Stock X
     */
    public static void updateQuote(String X, double price){
        if(quoteInfo.containsKey(X)){
            quoteInfo.replace(X, price);
        }
        else{
            quoteInfo.put(X, price);
        }
    }
}
