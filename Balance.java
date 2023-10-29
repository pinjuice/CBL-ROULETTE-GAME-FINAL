import java.awt.Font;
import javax.swing.*;

public class Balance {
    private static final Font FONT_LARGE = new Font("SansSerif", Font.BOLD, 15);
    private int amount;
    private JLabel balanceDisplay;

    public Balance(int initialAmount, JLabel balanceDisplay) {
        this.amount = initialAmount;
        this.balanceDisplay = balanceDisplay;

        // Set the font size for balanceDisplay
        balanceDisplay.setFont(FONT_LARGE);

        updateDisplay();  // Initially set the balance text
    }

    public int getBalance() {
        return amount;
    }

    public void deduct(int value) {
        this.amount -= value;
        updateDisplay();  // Update the display after deducting the amount
    }

    public void add(int value) {
        this.amount += value;
        updateDisplay();  // Update the display after adding the amount
    }

    public boolean canDeduct(int value) {
        return amount - value >= 0;
    }

    public void updateDisplay() {
        balanceDisplay.setText("Your balance is: " + getBalance() + "$");
    }
}
