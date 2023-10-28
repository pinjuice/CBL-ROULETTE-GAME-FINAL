import javax.swing.*;

public class Balance {
    private int amount;
    private JLabel balanceDisplay;

    public Balance(int initialAmount, JLabel balanceDisplay) {
        this.amount = initialAmount;
        this.balanceDisplay = balanceDisplay;
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

    // Private method to update the JLabel with the current balance
    public void updateDisplay() {
        balanceDisplay.setText("Your balance is: " + getBalance()+ "$");
    }
}
