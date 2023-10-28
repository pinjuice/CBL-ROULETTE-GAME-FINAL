// BettingMechanic.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class BettingMechanic {
    private Squares squares;
    private Renderer renderer;
    private BetSlider betSlider;
    private BettingTimer bettingTimer;
    private Balance balance;
    private int lastClickedSquare = -1;
    private Map<Integer, Chip> chipsBySquare = new HashMap<>();  // New field

    public BettingMechanic(Squares squares, Renderer renderer, BetSlider betSlider, BettingTimer bettingTimer, Balance balance) {
        this.squares = squares;
        this.renderer = renderer;  // Store the ChipRenderer instance
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
        int squareIndex = squares.getSquareIndex(e.getPoint());
        if (squareIndex != -1) {
            lastClickedSquare = squareIndex;  // Store the last clicked square
            if (!bettingTimer.isTimeOver() && balance.getBalance() > 0) {
                betSlider.show();
            }    
        }
    }

    private void placeBet() {
        int percentage = betSlider.getSlider().getValue();
        int betValue = (int) (balance.getBalance() * (percentage / 100.0));

        if (lastClickedSquare != -1 && balance.canDeduct(betValue)) {
            balance.deduct(betValue);
            squares.getSumSquares()[lastClickedSquare] += betValue;

            Chip chip = chipsBySquare.get(lastClickedSquare);
            if (chip == null) {
                // No chip for this square yet, create a new one
                chip = new Chip(0, 0, Color.RED, betValue);
                Rectangle selectedRect = squares.squares[lastClickedSquare];
                chip.setPositionRect(selectedRect);
                renderer.addChip(chip);
                chipsBySquare.put(lastClickedSquare, chip);  // Store the chip in the map
            } else {
                // A chip for this square already exists, update its value
                chip.setValue(chip.getValue() + betValue);
            }

            JOptionPane.showMessageDialog(null, "You bet " + betValue + "$ on square " + (lastClickedSquare) + ". Remaining balance: " + balance.getBalance());
            balance.updateDisplay();
            renderer.repaint();  // Ensure the ChipRenderer is repainted to reflect the new chip value
        }
        betSlider.hide();
    }
}
