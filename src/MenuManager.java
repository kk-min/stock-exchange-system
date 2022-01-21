import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {
    private static final Scanner inputMachine = new Scanner(System.in);
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

    public static void printQuote(String stockName){
        Printable printer = new PrintableQuote(stockName);
        printer.print();
    }

}
