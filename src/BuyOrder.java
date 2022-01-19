import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void executeTrade(HashMap<Stock, ArrayList<Order>> pendingSellOrders, HashMap<Stock, ArrayList<Order>> pendingBuyOrders){

        switch(this.orderType){
            case MARKET:
                if (!pendingSellOrders.containsKey(this.orderStock) || (pendingSellOrders.get(this.orderStock).isEmpty())){ // No selling lists exist or empty list
                    System.out.printf("There is currently no market sell order for Stock %s.\n", this.orderStock.getStockName());
                    return;
                }
                break;
            case LIMIT:
                if (!pendingSellOrders.containsKey(this.orderStock) || (pendingSellOrders.get(this.orderStock).isEmpty())){ // No selling lists exist or empty list
                    if (pendingBuyOrders.get(this.orderStock) == null){ // No buying list exists for this stock
                        ArrayList<Order> pendingList = new ArrayList<Order>();
                        pendingList.add(this);
                        pendingBuyOrders.put(this.orderStock, pendingList);
                    }
                    else{
                        pendingBuyOrders.get(this.orderStock).add(this);
                    }
                    return;
                }
                else{

                }
        }
    }
}
