public abstract class Order {
    public enum STATUS{PENDING, PARTIAL, FILLED};
    public enum TYPE{MARKET, LIMIT};
    /**
     * The status of the order, whether it is not fulfilled (PENDING), partially fulfilled (PARTIAL) or completely fulfilled (FILLED).
     */
    private STATUS orderStatus;
    /**
     * The type of order, whether it is a market order (takes whatever price is available) or it is a limit order with a specified price.
     */
    private TYPE orderType;
    /**
     * The price of the stock the order is trading at, if applicable.
     */
    private double price;
    /**
     * The total quantity of the stock being traded.
     */
    private double quantityTotal;
    /**
     * The total quantity of the stock that has been successfully traded.
     */
    private double quantityFulfilled;
    /**
     * The stock that the order is trading.
     */
    private Stock orderStock;

    /**
     * Constructor for the Order class. (Limit Order)
     * @param stockName The name of the stock
     * @param price The price of the stock
     * @param quantityTotal The total quantity of the stock
     */
    public Order(String stockName, double price, double quantityTotal){
        this.orderStatus = STATUS.PENDING;
        this.orderType = TYPE.LIMIT;

        this.orderStock = new Stock(stockName);
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

        this.orderStock = new Stock(stockName);
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
     * Acessor method for the total quantity of stock specified in the order.
     * @return The total quantity of stock being traded in the order.
     */
    public double getQuantityTotal() {
        return quantityTotal;
    }

    public abstract void execute(Order X);
}
