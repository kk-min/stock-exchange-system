import java.util.ArrayList;

public class TestUnit {


    public static void main(String[] args) {
        //Initialize systems:
        OrderManager OrderSystem = new OrderManager();
        StockManager StockSystem = new StockManager();

        Order buyOrder = new BuyOrder("FB", 12, 50);
        Order sellOrder = new SellOrder("FB", 6, 20);

        OrderSystem.receiveOrder(buyOrder);
        OrderSystem.receiveOrder(sellOrder);

        ArrayList<Order> orderHistory = OrderSystem.getOrderHistory();
        for(Order entry : orderHistory){
            switch(entry.getOrderType()){
                case LIMIT:

                    System.out.printf("%s LMT");
            }
            System.out.println();
        }

    }
}
