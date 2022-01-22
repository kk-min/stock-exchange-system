/**
 * A logic unit that handles user input for viewing stock information and communicates with MenuManager.
 */
public class QuoteLogicUnit implements LogicUnit{

    @Override
    public void executeLogic() {
        System.out.print("Enter name of stock to enquire quote: ");
        String stockName = inputMachine.next();
        MenuManager.printQuote(stockName);
    }
}
