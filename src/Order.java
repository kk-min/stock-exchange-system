import java.util.ArrayList;

public abstract class Order implements Comparable<Order>{
    public enum STATUS{PENDING, PARTIAL, FILLED};
    public enum TYPE{MARKET, LIMIT};
    /**
     * The status of the order, whether it is not fulfilled (PENDING), partially fulfilled (PARTIAL) or completely fulfilled (FILLED).
     */
    protected STATUS orderStatus;
    /**
     * The type of order, whether it is a market order (takes whatever price is available) or it is a limit order with a specified price.
     */
    protected final TYPE orderType;
    /**
     * The price of the stock the order is trading at, if applicable.
     */
    protected double price;
    /**
     * The total quantity of the stock being traded.
     */
    protected final double quantityTotal;
    /**
     * The total quantity of the stock that has been successfully traded.
     */
    protected double quantityFulfilled;
    /**
     * The stock that the order is trading.
     */
    protected final String stockName;

    /**
     * Constructor for the Order class. (Limit Order)
     * @param stockName The name of the stock
     * @param price The price of the stock
     * @param quantityTotal The total quantity of the stock
     */
    public Order(String stockName, double price, double quantityTotal){
        this.orderStatus = STATUS.PENDING;
        this.orderType = TYPE.LIMIT;

        this.stockName = stockName;
        this.price = price;
        this.quantityTotal = quantityTotal;
        this.quantityFulfilled = 0; // An order is always created with 0 quantity of fulfilled stock
    }

    /**
     * Constructor for the Order class. (Market Order)
     * @param stockName The name of the stock
     * @param quantityTotal The total quantity of the stock
     */
    public Order(String stockName, double quantityTotal){
        this.orderStatus = STATUS.PENDING;
        this.orderType = TYPE.MARKET;

        this.stockName = stockName;
        this.quantityTotal = quantityTotal;
        this.quantityFulfilled = 0; // An order is always created with 0 quantity of fulfilled stock
    }

    /**
     * Accessor method for the order type of the order.
     * @return The type of order (MARKET, LIMIT)
     */
    public TYPE getOrderType(){
        return this.orderType;
    }

    /**
     * Accessor method for the price of the order.
     * @return The price of the order where applicable.
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Acessor method for the total quantity requested of stock specified in the order.
     * @return The total quantity requested of stock being traded in the order.
     */
    public double getQuantityTotal() {
        return this.quantityTotal;
    }

    /**
     * Accessor method for the total quantity fulfilled of stock specified in the order.
     * @return The total quantity fulfilled of stock being traded in this order.
     */
    public double getQuantityFulfilled(){
        return this.quantityFulfilled;
    }

    public String getStockName() {
        return stockName;
    }

    public STATUS getOrderStatus() {
        return orderStatus;
    }

    /**
     * Executes the trade with a matching buy/sell order.
     * @param X The matching buy/sell order
     * @return true: the order has been completely fulfilled. false: the order has been partially
     *
     * fulfilled.
     */
    public abstract void executeTrade(Order X, ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList);

    public abstract Order findMatchingOrder(ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList);
    public void setQuantityFulfilled(double quantityFulfilled) {
        this.quantityFulfilled = quantityFulfilled;
    }

    public abstract String identify();

    public int compareTo(Order anotherOrder){
        return Double.compare(this.price, anotherOrder.getPrice());
    }

}
