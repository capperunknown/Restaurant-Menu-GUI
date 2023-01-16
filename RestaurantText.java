package cp213;

import java.io.FileNotFoundException;

/**
 * Executes the text-based interface for this program.
 *
 */
public class RestaurantText {

    /**
     * @param args Unused.
     */
    public static void main(String[] args)  {
	try {
	    Menu menu = new Menu("menu.txt");
	    Cashier cashier = new Cashier(menu);
	    cashier.takeOrder();
	} catch (FileNotFoundException e) {
	    System.out.println("Cannot open menu file");
	}
	System.exit(0);
    }
}