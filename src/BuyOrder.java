public class BuyOrder extends Order{
    /**
     * Constructor for the BuyOrder class. (Limit Order)
     * @param stockName The name of the stock the order is trading.
     * @param price The price of the stock it is requested to trade at, if applicable.
     * @param quantityTotal The total quantity of the stock being traded.
     */
    public BuyOrder(String stockName, double price, double quantityTotal){
        super(stockName, price, quantityTotal);
    }

    /**
     * Constructor for the BuyOrder class. (Market Order)
     * @param stockName The name of the stock the order is trading.
     * @param quantityTotal The total quantity of the stock being traded.
     */
    public BuyOrder(String stockName, double quantityTotal){
        super(stockName, quantityTotal);
    }

    public void executeTrade(){

    }
}
