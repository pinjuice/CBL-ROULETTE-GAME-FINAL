import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BettingTimer {
    private Timer timer;
    private int secondsLeft;
    private JLabel rouletteLabel;  // For the roulette animation
    private JLabel displayLabel;   // For displaying messages
    private boolean timeOver;
    private RouletteLogic rouletteLogic;


    public BettingTimer(JLabel displayLabel, JLabel rouletteLabel, RouletteLogic rouletteLogic) {
        this.displayLabel = displayLabel;
        this.rouletteLabel = rouletteLabel;
        this.rouletteLogic = rouletteLogic;
        this.secondsLeft = 10;  // Initial countdown time.
        this.timeOver = false;

        displayLabel.setText("Time for betting left: "+secondsLeft +" seconds");

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft > 0) {
                    secondsLeft--;
                    updateDisplay();
                } else {
                    endBetting();
                }
            }
        });
        timer.start();
    }

    private void updateDisplay() {
        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");
    }

    private void endBetting() {
        timer.stop();
        displayLabel.setText("Time for betting is over");

        // Start the roulette animation on rouletteLabel
        new RouletteAnimation(rouletteLabel, rouletteLogic).startAnimation();

        timeOver = true;
        rouletteLogic.spin(); 
        rouletteLogic.checkWinningSquareAndUpdateBalance();
    }

    public boolean isTimeOver() {
        return timeOver;
    }
}