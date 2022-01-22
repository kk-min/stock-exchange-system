/**
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */

public class PrintableOrderMenu implements Printable{

    public void print(){
        System.out.println("Select an option:");
        System.out.println("1. Buy a stock");
        System.out.println("2. Sell a stock");
        System.out.println("3. Back");
    }
}
