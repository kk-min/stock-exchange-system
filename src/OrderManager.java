import java.util.ArrayList;

/**
 * The OrderManager Class
 * Manages various functionalities pertaining to Order objects.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class OrderManager {
    /**
     * An ArrayList that stores the history of orders made from earliest to latest.
     */
    ArrayList<Order> orderHistory;

    /**
     * Constructor for OrderManager.
     */
     public OrderManager(){
        this.orderHistory = new ArrayList<Order>();
     }

    /**
     *
     * @param X The order to add to the order history.
     */
     public void addtoOrderHistory(Order X){
         this.orderHistory.add(X);
     }

    /**
     * Returns the current order history.
     * @return orderHistory
     */
    public ArrayList<Order> getOrderHistory(){
         return this.orderHistory;
     }
}
