import java.awt.Font;
import javax.swing.*;

/**
 * Represents a user's balance which can be deducted from, added to, and displayed.
 * <p>
 * The balance can be manipulated through methods and visualized with a JLabel.
 * </p>
 */
public class Balance {
    
    /** Font for the JLabel that displays the balance. */
    private static final Font FONT_LARGE = new Font("SansSerif", Font.BOLD, 15);
    
    /** The current amount of the balance. */
    private int amount;
    
    /** JLabel to display the current balance to the user. */
    private JLabel balanceDisplay;

    /**
     * Constructs a new Balance with a given initial amount and display label.
     *
     * @param initialAmount   the starting amount for this balance.
     * @param balanceDisplay  the JLabel used to display the current balance.
     */
    public Balance(int initialAmount, JLabel balanceDisplay) {
        this.amount = initialAmount;
        this.balanceDisplay = balanceDisplay;

        // Set the font size for balanceDisplay
        balanceDisplay.setFont(FONT_LARGE);

        updateDisplay();  // Initially set the balance text
    }

    /**
     * Returns the current balance amount.
     *
     * @return the current balance.
     */
    public int getBalance() {
        return amount;
    }

    /**
     * Deducts a specified value from the current balance.
     *
     * @param value  the amount to be deducted from the balance.
     */
    public void deduct(int value) {
        this.amount -= value;
        updateDisplay();  // Update the display after deducting the amount
    }

    /**
     * Adds a specified value to the current balance.
     *
     * @param value  the amount to be added to the balance.
     */
    public void add(int value) {
        this.amount += value;
        updateDisplay();  // Update the display after adding the amount
    }

    /**
     * Checks if a specified amount can be deducted from the balance.
     *
     * @param value  the amount to check if it can be deducted.
     * @return true if the balance can be deducted by the specified amount, false otherwise.
     */
    public boolean canDeduct(int value) {
        return amount - value >= 0;
    }

    /**
     * Updates the JLabel to display the current balance amount.
     */
    public void updateDisplay() {
        balanceDisplay.setText("Your balance is: " + getBalance() + "$");
    }
}
