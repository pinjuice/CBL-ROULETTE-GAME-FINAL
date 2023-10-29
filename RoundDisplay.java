import java.awt.Font;
import javax.swing.*;

/**
 * A specialized JLabel that displays the current round in the game.
 * <p>
 * The class allows the round to be incremented.
 * The round number is prefixed with the text "Round: " and displayed using a large font.
 * </p>
 */
public class RoundDisplay extends JLabel {
    
    private int currentRound;
    private static final String PREFIX = "Round: ";
    private static final Font FONT_LARGE = 
        new Font("SansSerif", Font.BOLD, 24);

    /**
     * Default constructor initializes the label with starting round.
     */
    public RoundDisplay() {
        super();
        this.currentRound = 1; 
        this.setFont(FONT_LARGE);  
        updateDisplay();
    }

    /**
     * Increment the round number by 1 and update the displayed label.
     */
    public void incrementRound() {
        this.currentRound++;
        updateDisplay();
    }

    /**
     * Method to set the text of the JLabel to reflect the current round.
     */
    private void updateDisplay() {
        this.setText(PREFIX + currentRound);
    }
}
