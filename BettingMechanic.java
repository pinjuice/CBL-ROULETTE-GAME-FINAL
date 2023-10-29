import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class BettingMechanic {
    private int lastClickedRectangle = -1;
    private Map<Integer, Chip> chipsByRectangle = new HashMap<>();
    private BoardRectangles boardRectangles;
    private Renderer renderer;
    private BetSlider betSlider;
    private BettingTimer bettingTimer;
    private Balance balance;

    public BettingMechanic(BoardRectangles boardRectangles, Renderer renderer, BetSlider betSlider, BettingTimer bettingTimer, Balance balance) {
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

    private void handleMousePressed(MouseEvent e) {
        int squareIndex = boardRectangles.getBoardRectangleIndex(e.getPoint());
        if (squareIndex != -1) {
            lastClickedRectangle = squareIndex;  // Store the last clicked square
            if (!bettingTimer.isTimeOver() && balance.getBalance() > 0) {
                betSlider.show();
            }    
        }
    }

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
                if (betValue > 0){
                    renderer.addChip(chip);
                    chipsByRectangle.put(lastClickedRectangle, chip);  // Store the chip in the map
                }

            } else {
                // A chip for this square already exists, update its value
                chip.setValue(chip.getValue() + betValue);
            }
            UIManager.put("Button.focus", new Color(0,0,0,0));
            JOptionPane.showMessageDialog(null, "You bet " + betValue + "$ on square " + (lastClickedRectangle) + ". Remaining balance: " + balance.getBalance()+"$", "Bet", JOptionPane.INFORMATION_MESSAGE);

            balance.updateDisplay();
            renderer.repaint();  // Ensure the ChipRenderer is repainted to reflect the new chip value
        }
        betSlider.hide();
    }

    public void resetBets() {
        for (int i = 0; i < boardRectangles.getNumberOfBoardRectangles(); i++) {
            boardRectangles.sumBoardRectangles[i] = 0;
        }
        chipsByRectangle.clear();
    }
}
