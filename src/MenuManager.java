import java.util.ArrayList;

public class MenuManager {

    public static void printMainMenu(){
        Printable printer = new PrintableMainMenu();
        printer.print();
    }

    public static void printOrderMenu(){
        Printable printer = new PrintableOrderMenu();
        printer.print();
    }

    public static void printOrderHistory(){
        Printable printer = new PrintableOrderHistory();
        printer.print();
    }

}
