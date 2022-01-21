import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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

    public void executeTrade(Order sellOrder, ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList){ // Assume a matching sell order has already been found
        if (sellOrder == null){
            if(!pendingBuyList.contains(this)){
                pendingBuyList.add(this);
                Collections.sort(pendingBuyList);
                Collections.reverse(pendingBuyList);
            }
            return;
        }

        double sellQuantity = sellOrder.getQuantityTotal() - sellOrder.getQuantityFulfilled();
        double buyQuantity = this.quantityTotal - this.quantityFulfilled;

        if(buyQuantity <= sellQuantity){ // The entire order can be fulfilled
            this.quantityFulfilled = this.quantityTotal;
            this.orderStatus = STATUS.FILLED;
            pendingBuyList.remove(this);
        }
        else{ // The order can only be partially fulfilled.
            this.quantityFulfilled += sellQuantity;
            this.orderStatus = STATUS.PARTIAL;
            if(!pendingBuyList.contains(this)){
                pendingBuyList.add(this);
                Collections.sort(pendingBuyList);
                Collections.reverse(pendingBuyList);
            }
        }
    }

    public Order findMatchingOrder(ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList){
        if (pendingSellList.isEmpty()){
            return null;
        }
        else{
            Order entry = pendingSellList.get(0); // The first entry will always have the smallest sell price
            switch(this.orderType) {
                case MARKET:
                    return entry;
                case LIMIT:
                    if (entry.getPrice() <= this.price) {
                        return entry;
                    }
                    break;
            }
        }
        return null;
    }

    public String identify(){
        return "BUY";
    }
}
