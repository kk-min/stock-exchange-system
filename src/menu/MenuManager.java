package menu;

/**
 * Manages all functions pertaining to printing menus on the terminal.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class MenuManager {

    /**
     * Prints the Main Menu of the program.
     */
    public static void printMainMenu(){
        Printable printer = new PrintableMainMenu();
        printer.print();
    }

    /**
     * Prints the Order Menu of the program for making orders.
     */
    public static void printOrderMenu(){
        Printable printer = new PrintableOrderMenu();
        printer.print();
    }

    /**
     * Prints the list of all orders made. Returns an error message if there are no orders to view.
     */
    public static void printOrderHistory(){
        Printable printer = new PrintableOrderHistory();
        printer.print();
    }

    /**
     * Prints ask, bid and last information about a stock.
     * @param stockName Name of the stock
     */
    public static void printQuote(String stockName){
        Printable printer = new PrintableQuote(stockName);
        printer.print();
    }

}
