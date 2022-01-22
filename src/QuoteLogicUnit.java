import java.util.InputMismatchException;

/**
 * A logic unit that handles user input for viewing stock information and communicates with MenuManager.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public class QuoteLogicUnit implements LogicUnit{

    @Override
    public void executeLogic() throws InputMismatchException {
        System.out.print("Enter name of stock to enquire quote: ");
        String stockName = inputMachine.next();
        MenuManager.printQuote(stockName);
    }
}
