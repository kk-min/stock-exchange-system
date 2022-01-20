import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

    public void executeTrade(Order buyOrder, ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList){
        if (buyOrder == null){
            if (!pendingSellList.contains(this)) {
                pendingSellList.add(this);
                Collections.sort(pendingSellList);
            }
            return;
        }

        double buyQuantity = buyOrder.getQuantityTotal();
        double sellQuantity = this.quantityTotal-this.quantityFulfilled;

        if(sellQuantity <= buyQuantity){
            this.quantityFulfilled = this.quantityTotal;
            this.orderStatus = STATUS.FILLED;
            pendingSellList.remove(this);
        }
        else{
            this.quantityFulfilled = buyQuantity;
            this.orderStatus = STATUS.PARTIAL;
            if(!pendingSellList.contains(this)){
                pendingSellList.add(this);
                Collections.sort(pendingSellList);
            }
        }
    }

    public Order findMatchingOrder(ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList){
        if (pendingBuyList.isEmpty()){ // No pending sell orders for this stock
            return null;
        }
        else{
            Order entry = pendingBuyList.get(0); // The first entry will always have the largest buy price
            switch(this.orderType) {
                case MARKET:
                    return entry;
                case LIMIT:
                    if (entry.getPrice() >= this.price) {
                        return entry;
                    }
                    break;
            }
        }
        return null;
    }
}
