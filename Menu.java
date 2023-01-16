package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 */
public class Menu {

    /**
     * For testing. Reads contents of "menu.txt" from root of project.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
	try {
	    Menu menu = new Menu("/Users/adilmohammad/cp213-master/moha5810_a05/menu.txt");
	    System.out.println(menu.toString());
	} catch (FileNotFoundException e) {
	    System.out.println("Cannot open menu file");
	}
	System.exit(0);
    }

    private List<MenuItem> items = new ArrayList<MenuItem>();

    /**
     * Constructor from a file of MenuItem strings. Each line in the file
     * corresponds to a MenuItem. You have to read the file line by line and add
     * each MenuItem to the ArrayList of items.
     *
     * @param filename The name of the file containing the menu items.
     * @throws FileNotFoundException Thrown if file not found or cannot be read.
     */
    public Menu(String filename) throws FileNotFoundException {

        Scanner reader = new Scanner(System.in);
        File data = new File(filename);
        reader = new Scanner(data);

        while(reader.hasNextLine()){
            String temp = reader.nextLine();
            String val = "";
            String name = "";
            
            for(int i = 0; i < temp.length(); i++){
                if(temp.charAt(i) == ' '){
                    val = temp.substring(0,i);
                    name = temp.substring(i+1, temp.length());
                    i = temp.length();
                }
            }
            double price = Double.parseDouble(val);
            MenuItem obj = new MenuItem(name, price);
            items.add(obj); 
        }
        reader.close();
    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

        MenuItem item = items.get(i);

	return item;
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    4) cheeseburger $ 3.75
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {

        String result = "";

		for(int i = 0; i < items.size(); i++){
            MenuItem item = items.get(i);

            int len = 13-item.getName().length();
            String temp = new String(new char[len]).replace("\0", " ");

            if(item.getPrice().floatValue() <= 9.99){
                result += String.format("%d) %s"+temp+"$ %.2f\n", i+1, item.getName(), item.getPrice());
            }
            else if(item.getPrice().floatValue() >= 9.99 & item.getPrice().floatValue() <= 99.99){
                result += String.format("%d) %s"+temp+"$%.2f\n", i+1, item.getName(), item.getPrice());
            }
                      
        }
	return result;
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	return items.size();
    }

}