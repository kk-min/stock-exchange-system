package order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
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

    @Override
    public void executeTrade(Order buyOrder, ArrayList<Order> pendingBuyList, ArrayList<Order> pendingSellList){
        if (buyOrder == null){
            if(this.orderType == TYPE.MARKET){
                this.price = -1; // Since we are selling at market value, any buy value above or equal to 0 is acceptable
            }
            if (!pendingSellList.contains(this)) {
                pendingSellList.add(this);
                Collections.sort(pendingSellList);
            }
            return;
        }

        if ((this.orderType == TYPE.MARKET) && (buyOrder.getOrderType() == TYPE.MARKET)){ // Both are market orders
            if (!pendingSellList.contains(this)) {
                this.price = -1;
                pendingSellList.add(this);
                Collections.sort(pendingSellList);
            }
            return;
        }

        double buyQuantity = buyOrder.getQuantityTotal() - buyOrder.getQuantityFulfilled();
        double sellQuantity = this.quantityTotal-this.quantityFulfilled;

        if(sellQuantity <= buyQuantity){ // The entire order can be fulfilled
            this.quantityFulfilled = this.quantityTotal;
            this.orderStatus = STATUS.FILLED;
            pendingSellList.remove(this);

            buyOrder.setQuantityFulfilled(buyOrder.getQuantityFulfilled()+sellQuantity);
            if(buyOrder.getQuantityTotal() == buyOrder.getQuantityFulfilled()){
                buyOrder.setOrderStatus(STATUS.FILLED);
                pendingBuyList.remove(buyOrder);
            }
            else{
                buyOrder.setOrderStatus(STATUS.PARTIAL);
            }
        }

        else{ // The order can only be partially fulfilled. This implies that the corresponding order can be completely fulfilled.
            this.quantityFulfilled = buyQuantity;
            this.orderStatus = STATUS.PARTIAL;
            if(!pendingSellList.contains(this)){
                pendingSellList.add(this);
                Collections.sort(pendingSellList);
            }

            buyOrder.setQuantityFulfilled(buyOrder.getQuantityTotal());
            buyOrder.setOrderStatus(STATUS.FILLED);
            pendingBuyList.remove(buyOrder);
        }
        StockManager.updateQuote(this.stockName, this.price);
    }

    @Override
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

    @Override
    public String identify(){
        return "SELL";
    }
}
