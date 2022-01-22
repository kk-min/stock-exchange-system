import java.util.ArrayList;
/**
 * Order class that contains information about a particular trade request.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public abstract class Order implements Comparable<Order>{
    /**
     * Enum that tags the current status of an order.
     */
    public enum STATUS{PENDING, PARTIAL, FILLED};

    /**
     * Enum that tags the type of trade the order is trying to fulfill.
     */
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

    /**
     * Returns the name of the stock being traded by this order.
     * @return Name of the stock being traded
     */
    public String getStockName() {
        return stockName;
    }

    /**
     * Return the current status (PENDING, PARTIAL, FILLED) of this order.
     * @return Status of the order
     */
    public STATUS getOrderStatus() {
        return orderStatus;
    }

    /**
     * Execute this order's trade with a corresponding buy/sell order, updating relevant quantities and lists in OrderManager
     * @param X The matching Buy/Sell Order
     * @param pendingBuyList The pending Buy list in OrderManager
     * @param pendingSellList The pending Sell List in OrderManager
     */
    public abstract void executeTrade(Order X, ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList);

    /**
     * Finds a matching buy/sell order available
     * @param pendingBuyList The pending Buy list in OrderManager
     * @param pendingSellList The pending Sell List in OrderManager
     * @return The matching order
     */
    public abstract Order findMatchingOrder(ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList);

    /**
     * Updates the total quantity fulfilled of the stock in this order
     * @param quantityFulfilled Current stock quantity that has been successfully traded in this order
     */
    public void setQuantityFulfilled(double quantityFulfilled) {
        this.quantityFulfilled = quantityFulfilled;
    }

    /**
     * Changes the status of this order. (PENDING, PARTIAL, FILLED)
     * @param newStatus Status of the order
     */
    public void setOrderStatus(STATUS newStatus){
        this.orderStatus = newStatus;
    }

    /**
     * Returns a string identifying whether the order is buy or sell.
     * @return String identifying whether the order is buy or sell.
     */
    public abstract String identify();

    /**
     * Compares another order's price with this order's price. Used for sorting Orders by price in pending Order lists.
     * @param anotherOrder Another order to compare with
     * @return -1 if this order's price is lower, 0 if equal, 1 if greater
     */
    public int compareTo(Order anotherOrder){
        return Double.compare(this.price, anotherOrder.getPrice());
    }

}
