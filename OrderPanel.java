package cp213;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The GUI for the Order class.
 *
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
	    final PrinterJob printJob = PrinterJob.getPrinterJob();
	    printJob.setPrintable(order);

	    if (printJob.printDialog()) {
		try {
		    printJob.print();
		} catch (final Exception printException) {
		    System.err.println(printException);
		}
	    }
	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {

		@Override
		public void focusGained(final FocusEvent evt) {
			for (int i = 0; i < menu.size(); i++) {
				if (fields[i].getText().length() > 0) {					
					try {
						if (Integer.parseInt(fields[i].getText()) > 0) {
							order.add(menu.getItem(i), Integer.parseInt(fields[i].getText()));
							subtotalLabel.setText(String.format("$%.2f", order.getSubTotal()));
							taxLabel.setText(String.format("$%.2f", order.getTaxes()));
							totalLabel.setText(String.format("$%.2f", order.getTotal()));
						}	
					}catch (NumberFormatException e) {}									
				}
			}
		}

		@Override
		public void focusLost(final FocusEvent evt) {
			for (int i = 0; i<menu.size(); i++) {
				if (fields[i].getText().length() > 0) {
					try {
						if(Integer.parseInt(fields[i].getText()) < 0) {
							fields[i].setText("0");
						}
					}catch (NumberFormatException e) {
						fields[i].setText("0");
					}
				}	
				else if(fields[i].getText().equals("")){
					fields[i].setText("0");
					order.update(menu.getItem(i), 0);
					subtotalLabel.setText(String.format("$%.2f", order.getSubTotal()));
					taxLabel.setText(String.format("$%.2f", order.getTaxes()));
					totalLabel.setText(String.format("$%.2f", order.getTotal()));
				}		
			}
		}
    }

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("$0.00");
    private final JLabel taxLabel = new JLabel("$0.00");
    private final JLabel totalLabel = new JLabel("$0.00");
	private JTextField[] fields;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	fields = new JTextField[this.menu.size()];
	this.layoutView();
	
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {
		
		JLabel blank1 = new JLabel(" ");
		JLabel blank2 = new JLabel(" ");
		JLabel blank3 = new JLabel(" ");
		JLabel blank4 = new JLabel(" ");

		this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		this.setLayout(new GridLayout(this.menu.size()+5, 3));
		
		this.add(new JLabel("Item"));
		this.add(new JLabel("Price"));
		this.add(new JLabel("Quantity"));

		for (int i=0; i<this.menu.size(); i++) {
			String menuItem = this.menu.getItem(i).getName();		
			JLabel itemPrice = new JLabel(String.format("$%.2f",this.menu.getItem(i).getPrice()));
			JTextField field = new JTextField();

			itemPrice.setHorizontalAlignment(SwingConstants.RIGHT);
			field.addFocusListener(new QuantityListener());
			field.setText("0");
			fields[i] = field;	
			
			this.add(new JLabel(menuItem));
			this.add(itemPrice);
			this.add(field);
		}

		JLabel subtotal = new JLabel("Subtotal: ");
		subtotal.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(subtotal);
		subtotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(blank1);
		this.add(subtotalLabel);

		JLabel tax = new JLabel("Tax:");
		tax.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(tax);
		taxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(blank2);
		this.add(taxLabel);

		JLabel total = new JLabel("Total: ");
		total.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(total);
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(blank3);
		this.add(totalLabel);

		this.add(blank4);
		this.printButton.addActionListener(new PrintListener());
		this.add(this.printButton);
	return;
    }
}