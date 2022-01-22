package logicunit;
import java.util.Scanner;

/**
 * A abstract logic unit that handles various user input scenarios for different menus.
 * @author  Min
 * @version 1.0
 * @since   2022-01-18
 */
public interface LogicUnit {
    /**
     * Scanner to take in user input from the terminal
     */
    Scanner inputMachine = new Scanner(System.in);

    /**
     * Executes the unit's logic.
     */
    void executeLogic();
}
