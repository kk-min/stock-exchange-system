public class PrintableQuote implements Printable {
    String stockName;
    double bid;
    double ask;
    double quote;
    public PrintableQuote(String X, double bid, double ask, double quote){
        this.stockName = X;
        this.bid = bid;
        this.ask = ask;
        this.quote = quote;
    }
    public void print(){
        System.out.printf("%s BID: %.2f ASK: %.2f LAST: %2f\n", this.stockName, this.bid, this.ask, this.quote);
    }
}
