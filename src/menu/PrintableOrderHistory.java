package menu;

import order.*;
import java.util.ArrayList;

/**
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class PrintableOrderHistory implements Printable{
    /**
     * The order history to print
     */
    ArrayList<Order> orderHistory;

    /**
     * Constructor for PrintableOrderHistory.
     */
    public PrintableOrderHistory(){
        this.orderHistory = OrderManager.getOrderHistory();
    }

    public void print(){
        if (this.orderHistory.isEmpty()){
            System.out.println("There are no orders to view.");
            return;
        }
        System.out.println("---------------");
        int entryNumber = 1;
        for (Order entry : this.orderHistory){
            String type = switch(entry.getOrderType()) {
                case LIMIT:
                    yield "LMT";
                case MARKET:
                    yield "MKT";
            };
            String buyOrSell = entry.identify();
            if(type.equals("MKT")){
                switch(entry.getOrderStatus()){
                    case FILLED -> System.out.printf("%d. %s %s %s %.0f/%.0f FILLED\n",entryNumber, entry.getStockName(), type, buyOrSell, entry.getQuantityFulfilled(), entry.getQuantityTotal());
                    case PARTIAL -> System.out.printf("%d. %s %s %s %.0f/%.0f PARTIAL\n",entryNumber, entry.getStockName(), type, buyOrSell, entry.getQuantityFulfilled(), entry.getQuantityTotal());
                    case PENDING -> System.out.printf("%d. %s %s %s %.0f/%.0f PENDING\n",entryNumber, entry.getStockName(), type, buyOrSell, entry.getQuantityFulfilled(), entry.getQuantityTotal());
                }
            }
            else {
                switch (entry.getOrderStatus()) {
                    case FILLED -> System.out.printf("%d. %s %s %s $%.2f %.0f/%.0f FILLED\n", entryNumber, entry.getStockName(), type, buyOrSell, entry.getPrice(), entry.getQuantityFulfilled(), entry.getQuantityTotal());
                    case PARTIAL -> System.out.printf("%d. %s %s %s $%.2f %.0f/%.0f PARTIAL\n", entryNumber, entry.getStockName(), type, buyOrSell, entry.getPrice(), entry.getQuantityFulfilled(), entry.getQuantityTotal());
                    case PENDING -> System.out.printf("%d. %s %s %s $%.2f %.0f/%.0f PENDING\n", entryNumber, entry.getStockName(), type, buyOrSell, entry.getPrice(), entry.getQuantityFulfilled(), entry.getQuantityTotal());
                }
            }
            entryNumber++;
        }
    }
}
