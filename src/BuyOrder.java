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

    public boolean executeTrade(Order sellOrder){ // Assume a matching sell order has already been found
        double sellQuantity = sellOrder.getQuantityTotal();

        if(this.quantityTotal <= sellQuantity){ // The entire order can be fulfilled
            this.quantityFulfilled = this.quantityTotal;
            this.orderStatus = STATUS.FILLED;
            return true;
        }
        else{
            this.quantityFulfilled = this.quantityTotal - sellQuantity;
            this.orderStatus = STATUS.PARTIAL;
            return false;
        }
        /*switch(this.orderType){
            case MARKET:
                if (!pendingSellOrders.containsKey(this.orderStock) || (pendingSellOrders.get(this.orderStock).isEmpty())){ // No selling lists exist or empty list
                    System.out.printf("There is currently no market sell order for Stock %s.\n", this.orderStock.getStockName());
                    return;
                }
                else{
                    ArrayList<Order> sellList = pendingSellOrders.get(this.orderStock);
                    Order marketSellOrder =
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
        }*/
    }
}
