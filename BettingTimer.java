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
    private PostAnimationTimer postAnimationTimer;

    private static final int INITIAL_SECONDS = 10;  // Initial countdown time.

    public BettingTimer(JLabel displayLabel, JLabel rouletteLabel, RouletteLogic rouletteLogic, PostAnimationTimer postAnimationTimer) {
        this.displayLabel = displayLabel;
        this.rouletteLabel = rouletteLabel;
        this.rouletteLogic = rouletteLogic;
        this.secondsLeft = INITIAL_SECONDS;
        this.timeOver = false;
        this.postAnimationTimer = postAnimationTimer;

        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");

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
    }

    private void updateDisplay() {
        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");
    }

    private void endBetting() {
        timer.stop();
        displayLabel.setText("Time for betting is over");

        // Start the roulette animation on rouletteLabel
        new RouletteAnimation(rouletteLabel, rouletteLogic, postAnimationTimer).startAnimation();
        timeOver = true;
        rouletteLogic.spin(); 
    }

    public boolean isTimeOver() {
        return timeOver;
    }

    public void resetTimer() {
        if(timer.isRunning()) {
            timer.stop();
        }
        secondsLeft = INITIAL_SECONDS;
        timeOver = false;
        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");
        timer.start();
    }

    public void start() {
        if(!timer.isRunning()) {
            timer.start();
        }
    }
}
