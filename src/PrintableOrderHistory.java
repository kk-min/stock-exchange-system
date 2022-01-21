import java.util.ArrayList;

public class PrintableOrderHistory implements Printable{
    ArrayList<Order> orderHistory;

    public PrintableOrderHistory(){
        this.orderHistory = OrderManager.getOrderHistory();
    }

    public void print(){
        int entryNumber = 1;
        for (Order entry : this.orderHistory){
            String type = switch(entry.getOrderType()) {
                case LIMIT:
                    yield "LMT";
                case MARKET:
                    yield "MKT";
            };
            String buyOrSell = entry.identify();
            switch(entry.getOrderStatus()){
                case FILLED -> System.out.printf("%d %s %s %s $%.2f %.0f/%.0f FILLED\n",entryNumber, entry.getStockName(), type, buyOrSell, entry.getPrice(), entry.getQuantityFulfilled(), entry.getQuantityTotal());
                case PARTIAL -> System.out.printf("%d %s %s %s $%.2f %.0f/%.0f PARTIAL\n",entryNumber, entry.getStockName(), type, buyOrSell, entry.getPrice(), entry.getQuantityFulfilled(), entry.getQuantityTotal());
                case PENDING -> System.out.printf("%d %s %s %s $%.2f %.0f/%.0f PENDING\n",entryNumber, entry.getStockName(), type, buyOrSell, entry.getPrice(), entry.getQuantityFulfilled(), entry.getQuantityTotal());
            }
        }
    }
}
