import javax.swing.*;
import java.awt.Font;

public class RoundDisplay extends JLabel {
    
    private int currentRound;
    private static final String PREFIX = "Round: ";
    private static final Font FONT_LARGE = new Font("SansSerif", Font.BOLD, 24);  // Using a bold SansSerif font of size 24

    // Constructor initializes the label with starting round
    public RoundDisplay() {
        super();
        this.currentRound = 1;  // starting from round 1
        this.setFont(FONT_LARGE);  // Set the font to the large size
        updateDisplay();
    }

    // Increment the round and update the label
    public void incrementRound() {
        this.currentRound++;
        updateDisplay();
    }

    // Reset to round 1
    public void resetRound() {
        this.currentRound = 1;
        updateDisplay();
    }

    // Update the display label with the current round
    private void updateDisplay() {
        this.setText(PREFIX + currentRound);
    }
}
