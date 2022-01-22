/**
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class PrintableMainMenu implements Printable{

    public void print(){
        System.out.println("Welcome to Min's Simple Stock Exchange Program.");
        System.out.println("Select an option:");
        System.out.println("1. Create an Order");
        System.out.println("2. View Orders");
        System.out.println("3. Enquire Quote");
        System.out.println("4. Exit");
    }
}
