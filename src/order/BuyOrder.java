package order;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
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

    @Override
    public void executeTrade(Order sellOrder, ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList){ // Assume a matching sell order has already been found
        if (sellOrder == null){
            if(!pendingBuyList.contains(this)){
                if(this.orderType == TYPE.MARKET){
                    this.price = Double.MAX_VALUE; // Since we are buying at market value, any sell price is acceptable so we put our buy price at maximum
                }
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

            sellOrder.setQuantityFulfilled(sellOrder.getQuantityFulfilled()+buyQuantity);
            if(sellOrder.getQuantityTotal() == sellOrder.getQuantityFulfilled()){
                sellOrder.setOrderStatus(STATUS.FILLED);
                pendingSellList.remove(sellOrder);
            }
            else{
                sellOrder.setOrderStatus(STATUS.PARTIAL);
            }
        }
        else{ // The order can only be partially fulfilled. This implies that the corresponding order can be completely fulfilled.
            this.quantityFulfilled += sellQuantity;
            this.orderStatus = STATUS.PARTIAL;
            if(!pendingBuyList.contains(this)){
                pendingBuyList.add(this);
                Collections.sort(pendingBuyList);
                Collections.reverse(pendingBuyList);
            }

            sellOrder.setQuantityFulfilled(sellOrder.getQuantityTotal());
            sellOrder.setOrderStatus(STATUS.FILLED);
            pendingSellList.remove(sellOrder);
        }
        StockManager.updateQuote(this.stockName, this.price); // Update the latest order for this stock in Stock Manager
    }

    @Override
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

    @Override
    public String identify(){
        return "BUY";
    }
}
