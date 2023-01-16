package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 */
public class Order implements Printable {

    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);
    private Map<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();

    /**
     * Update the quantity of a particular MenuItem in an order.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(MenuItem item, int quantity) {

        items.put(item, quantity);	    

    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total price for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {

        BigDecimal subtotal = new BigDecimal(0);

        for (Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            int temp = entry.getValue();
            BigDecimal quantity = new BigDecimal(temp);

            subtotal = subtotal.add(item.getPrice().multiply(quantity));
        }

	return subtotal;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {

        BigDecimal subtotal = this.getSubTotal(); 
        BigDecimal taxes = subtotal.multiply(TAX_RATE);

	return taxes;
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {

        BigDecimal subtotal = this.getSubTotal(); 
        BigDecimal total = subtotal.add(subtotal.multiply(TAX_RATE));

	return total;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {

    	String result = "";
    	double ones = 9.99;
    	double tens = 99.99;
    	double hunds = 999.00;

        int len = 0;
        String gap = "";

    	for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();

            len = 15-item.getName().length();
            gap = new String(new char[len]).replace("\0", " ");

            if(quantity <= 9){
                if(item.getPrice().doubleValue() <= ones){
                    if(item.getPrice().doubleValue() * quantity <= ones){
                        result += String.format("%s"+gap+"%d @ $"+" %.2f = $"+"  %.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }

                    else if(item.getPrice().doubleValue() * quantity <= tens){
                        result += String.format("%s"+gap+"%d @ $"+" %.2f = $"+" %.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }

                    else if(item.getPrice().doubleValue() * quantity <= hunds){
                        result += String.format("%s"+gap+"%d @ $"+" %.2f = $"+"%.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }
                }

                else if(item.getPrice().doubleValue() <= tens){
                    if(item.getPrice().doubleValue() * quantity <= ones){
                        result += String.format("%s"+gap+"%d @ $"+"%.2f = $"+"  %.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }

                    else if(item.getPrice().doubleValue() * quantity <= tens){
                        result += String.format("%s"+gap+"%d @ $"+"%.2f = $"+" %.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }

                    else if(item.getPrice().doubleValue() * quantity <= hunds){
                        result += String.format("%s"+gap+"%d @ $"+"%.2f = $"+"%.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }
                }
            }

            else if(quantity <= 99){
                if(item.getPrice().doubleValue() <= ones){
                    if(item.getPrice().doubleValue() * quantity <= ones){
                        result += String.format("%s"+gap+"%d@ $"+" %.2f = $"+"  %.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }

                    else if(item.getPrice().doubleValue() * quantity <= tens){
                        result += String.format("%s"+gap+"%d@ $"+" %.2f = $"+" %.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }

                    else if(item.getPrice().doubleValue() * quantity <= hunds){
                        result += String.format("%s"+gap+"%d@ $"+" %.2f = $"+"%.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }
                }

                else if(item.getPrice().doubleValue() <= tens){
                    if(item.getPrice().doubleValue() * quantity <= ones){
                        result += String.format("%s"+gap+"%d@ $"+"%.2f = $"+"  %.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }

                    else if(item.getPrice().doubleValue() * quantity <= tens){
                        result += String.format("%s"+gap+"%d@ $"+"%.2f = $"+" %.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }

                    else if(item.getPrice().doubleValue() * quantity <= hunds){
                        result += String.format("%s"+gap+"%d@ $"+"%.2f = $"+"%.2f\n",item.getName(), quantity, item.getPrice().floatValue(), item.getPrice().multiply(new BigDecimal(quantity)));
                    }
                }
               
            }

    	}

        result += String.format("\nSubtotal %20s%6.2f\n", "$", this.getSubTotal());
        result += String.format("Taxes %23s%6.2f\n", "$", this.getTaxes());
        result += String.format("Total %23s%6.2f", "$", this.getTotal());


	return result;
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(MenuItem item, int quantity) {

        if(items.containsKey(item) & quantity <= 0){
            items.remove(item);

        }

        else if(items.containsKey(item) & quantity > 0){
            items.remove(item);
            items.put(item, quantity);
        }            

        else{
             items.put(item, quantity);
        }

    }

    public Boolean contains(MenuItem key){

    return items.containsKey(key) ? true : false;
    }

    public Integer retrieveQuantity(MenuItem key){

    return items.get(key);
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

	    String receipt = this.toString();
        String [] words = receipt.split("\n");

        for(int i = 0; i < words.length; i++){
            graphics.drawString(words[i], 80, 80+(i*14));
        }


	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    } 
}