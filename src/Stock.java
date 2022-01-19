public class Stock {
    /**
     * The name of the Stock.
     */
    private String stockName;

    /**
     * Constructor for the Stock class.
     * @param name The name of the stock.
     */
    public Stock(String name){
        this.stockName = name;
    }

    /**
     * Returns the name of the stock.
     * @return name of the stock
     */
    public String getStockName() {
        return stockName;
    }
}
