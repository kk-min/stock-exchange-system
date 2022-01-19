public class SellOrder extends Order{
    /**
     * Constructor for SellOrder. (Limit Order)
     * @param stockName The name of the stock the order is trading.
     * @param price The price of the stock it is requested to trade at, if applicable.
     * @param quantityTotal The total quantity of the stock being traded.
     */
    public SellOrder(String stockName, double price, double quantityTotal){
        super(stockName, price, quantityTotal);
    }

    /**
     * Constructor for SellOrder. (Market Order)
     * @param stockName The name of the stock the order is trading.
     * @param quantityTotal The total quantity of the stock being traded.
     */
    public SellOrder(String stockName, double quantityTotal){
        super(stockName, quantityTotal);
    }

    public boolean executeTrade(Order buyOrder){
        double buyQuantity = buyOrder.getQuantityTotal();

        if(this.quantityTotal <= buyQuantity){
            this.quantityFulfilled = this.quantityTotal;
            this.orderStatus = STATUS.FILLED;
            return true;
        }
        else{
            this.quantityFulfilled = buyQuantity;
            this.orderStatus = STATUS.PARTIAL;
            return false;
        }
    }
}
