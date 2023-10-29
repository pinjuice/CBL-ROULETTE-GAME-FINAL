import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Manages the game's betting mechanics, allowing users to place bets on specific numbers.
 * <p>
 * This class provides mechanisms for detecting board rectangles clicks,
 * displaying bet amounts, and placing bets based on a percentage of user's balance.
 * The betting process is synchronized with other game components
 * such as the renderer and betting timer.
 * </p>
 */
public class BettingMechanic {
    private int lastClickedRectangle = -1;
    private Map<Integer, Chip> chipsByRectangle = new HashMap<>();
    private BoardRectangles boardRectangles;
    private Renderer renderer;
    private BetSlider betSlider;
    private BettingTimer bettingTimer;
    private Balance balance;

    /**
     * Constructs a new betting mechanic linked to the provided game components.
     *
     * @param boardRectangles An instance managing the game's board rectangles.
     * @param renderer An instance rendering the game components.
     * @param betSlider An interface allowing users to specify bet amounts.
     * @param bettingTimer A timer managing the betting period.
     * @param balance A user's balance.
     */
    public BettingMechanic(BoardRectangles boardRectangles, Renderer renderer,
         BetSlider betSlider, BettingTimer bettingTimer, Balance balance) {
        this.boardRectangles = boardRectangles;
        this.renderer = renderer;
        this.betSlider = betSlider;
        this.bettingTimer = bettingTimer;
        this.balance = balance;

        renderer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }
        });

        betSlider.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeBet();
            }
        });
    }

    /**
     * Handles mouse press events to identify if a board rectangle was clicked.
     *
     * @param e The mouse event data.
     */
    private void handleMousePressed(MouseEvent e) {
        int boardRectangleIndex = boardRectangles.getBoardRectangleIndex(e.getPoint());
        if (boardRectangleIndex != -1) {
            lastClickedRectangle = boardRectangleIndex;  // Store the last clicked board rectangle
            if (!bettingTimer.isTimeOver() && balance.getBalance() > 0) {
                betSlider.show();
            }    
        }
    }

    /**
     * Places a bet with amount of balance based on the slider value
     * on the last clicked board rectangle.
     */
    private void placeBet() {
        int percentage = betSlider.getSlider().getValue();
        int betValue = (int) (balance.getBalance() * (percentage / 100.0));

        if (lastClickedRectangle != -1 && balance.canDeduct(betValue)) {
            balance.deduct(betValue);
            boardRectangles.getSumBoardRectangles()[lastClickedRectangle] += betValue;
            Chip chip = chipsByRectangle.get(lastClickedRectangle);

            if (chip == null) {
                // No chip for this square yet, create a new one
                chip = new Chip(0, 0, betValue);
                Rectangle selectedRect = boardRectangles.boardRectangles[lastClickedRectangle];
                chip.setPositionRect(selectedRect);
                if (betValue > 0) {
                    renderer.addChip(chip);
                    chipsByRectangle.put(lastClickedRectangle, chip);  // Stores the chip in the map
                }

            } else {
                // A chip for this square already exists, update its value
                chip.setValue(chip.getValue() + betValue);
            }
            // Displays a message using JOptionPane to notif the user about their bet
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            JOptionPane.showMessageDialog(null, "You bet " + betValue + "$ on number "
                 + (lastClickedRectangle) + ". Remaining balance: " 
                 + balance.getBalance() + "$", "Bet", JOptionPane.INFORMATION_MESSAGE);

            balance.updateDisplay();
            renderer.repaint(); 
        }
        betSlider.hide();
    }

    /**
     * Resets all the bets made and clears the placed chips.
     */
    public void resetBets() {
        for (int i = 0; i < boardRectangles.getNumberOfBoardRectangles(); i++) {
            boardRectangles.sumBoardRectangles[i] = 0;
        }
        chipsByRectangle.clear();
    }
}
