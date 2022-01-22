import java.util.Scanner;

/**
 * A abstract logic unit that handles various user input scenarios for different menus.
 */
public interface LogicUnit {
    Scanner inputMachine = new Scanner(System.in);

    /**
     * Executes the unit's logic.
     */
    void executeLogic();
}
