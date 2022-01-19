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
}
