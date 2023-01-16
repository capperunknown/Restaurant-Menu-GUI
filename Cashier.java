package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 */
public class Cashier {

    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }



    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
        Order hash = new Order();
        System.out.println("Welcome to WLU Foodorama!\n\nMenu:");
        System.out.println(menu.toString());
        System.out.println("Press 0 when done.\nPress any other key to see the menu again.\n");

        Scanner input = new Scanner(System.in);
        String item = "";
        String quantity = "";
        System.out.println("Command: ");
        item = input.nextLine();

        while(item.equals("0") != true){
            try{
                if(Integer.parseInt(item) >=1 && Integer.parseInt(item) <= menu.size()){
                    System.out.println("How many do you want? ");
                    quantity = input.nextLine();
                    
                    try{
                        if(Integer.parseInt(quantity) > 0){
                            if(hash.contains(menu.getItem(Integer.parseInt(item)-1))){
                                int amt = hash.retrieveQuantity(menu.getItem(Integer.parseInt(item)-1));
                                hash.update(menu.getItem(Integer.parseInt(item)-1), amt+Integer.parseInt(quantity));
                            }
                            else if(hash.contains(menu.getItem(Integer.parseInt(item)-1)) == false){
                                hash.add(menu.getItem(Integer.parseInt(item)-1), Integer.parseInt(quantity));
                            }
                            else{
                                System.out.println("Not a valid number");                    
                            }
                        }

                    } catch(NumberFormatException e){
                        System.out.println("Not a valid number");
                    }
                }
                else{
                    System.out.println("Menu:");
                    System.out.println(menu.toString());
                }
            } catch(NumberFormatException e){
                System.out.println("Not a valid number\n\nMenu:");
                System.out.println(menu.toString());
                System.out.println("Press 0 when done.\nPress any other key to see the menu again.\n");
            }
            System.out.println("Command: ");
            item = input.nextLine();
        }
        System.out.println("----------------------------------------");
        System.out.println(hash.toString());
	return hash;
    }
}