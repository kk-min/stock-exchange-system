package menu;
import order.*;
/**
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class PrintableQuote implements Printable {
    /**
     * The name of the stock to print quote
     */
    String stockName;
    /**
     * The current highest buy price for the stock
     */
    double bid;
    /**
     * The current lowest sell price for the stock
     */
    double ask;
    /**
     * The last traded value of the stock
     */
    double quote;

    /**
     * Constructor for PrintableQuote class.
     * @param X Name of the stock
     */
    public PrintableQuote(String X){
        this.stockName = X;
        this.bid = OrderManager.getBid(X);
        this.ask = OrderManager.getAsk(X);
        this.quote = StockManager.getQuote(X);
    }

    public void print(){
        // There are 8 possible combinations for bid, ask and quote where each variable can either be present or absent.
        if (quote == -1){
            if (ask == -1){
                if(bid == -1){
                    //All 3 are absent.
                    System.out.printf("No records for %s found.\n", this.stockName);
                    return;
                }
                else{
                    //Only bid is present/
                    System.out.printf("%s BID: %.2f ASK: N/A LAST: N/A\n", this.stockName, this.bid);
                    return;
                }
            }
            else{
                if(bid == -1){
                    //Only ask is present
                    System.out.printf("%s BID: N/A ASK: %.2f LAST: N/A\n", this.stockName, this.ask);
                    return;
                }
                else{
                    //ask and bid are present
                    System.out.printf("%s BID: %.2f ASK: %.2f LAST: N/A\n", this.stockName, this.bid, this.ask);
                    return;
                }

            }
        }
        if (bid == -1){
            if (ask == -1){
                // Only quote is present
                System.out.printf("%s BID: N/A ASK: N/A LAST: %.2f\n", this.stockName, this.quote);
                return;
            }
            else{
                // quote and ask are present
                System.out.printf("%s BID: N/A ASK: %.2f LAST: %.2f\n", this.stockName, this.ask, this.quote);
                return;
            }
        }
        if(ask == -1){
            //bid and quote are present
            System.out.printf("%s BID: %.2f ASK: N/A LAST: %.2f\n", this.stockName, this.bid, this.quote);
            return;
        }
        //All are present:
        System.out.printf("%s BID: %.2f ASK: %.2f LAST: %2f\n", this.stockName, this.bid, this.ask, this.quote);
    }
}
