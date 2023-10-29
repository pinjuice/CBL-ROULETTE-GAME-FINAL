import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BettingTimer {
    private static final Font FONT_LARGE = new Font("SansSerif", Font.BOLD, 24);
    private static final int INITIAL_SECONDS = 20;
    private int secondsLeft;
    private boolean timeOver;
    private Timer bettingTimer;
    private JLabel displayLabel;   
    private JLabel rouletteLabel;   
    private RouletteLogic rouletteLogic;
    private PostAnimationTimer postAnimationTimer;

    public BettingTimer(JLabel displayLabel, JLabel rouletteLabel, RouletteLogic rouletteLogic,
         PostAnimationTimer postAnimationTimer, BetSlider betSlider) {
        this.secondsLeft = INITIAL_SECONDS;
        this.timeOver = false;
        this.displayLabel = displayLabel;
        displayLabel.setFont(FONT_LARGE);
        this.rouletteLabel = rouletteLabel;
        this.rouletteLogic = rouletteLogic;
        this.postAnimationTimer = postAnimationTimer;

        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");

        bettingTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft > 0) {
                    secondsLeft--;
                    updateDisplay();
                } else {
                    endBetting();
                    betSlider.hide();
                }
            }
        });
    }

    private void updateDisplay() {
        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");
    }

    private void endBetting() {
        bettingTimer.stop();
        displayLabel.setText("Time for betting is over");
        timeOver = true;
        new RouletteAnimation(rouletteLabel, rouletteLogic, postAnimationTimer).startAnimation();
        rouletteLogic.spin(); 
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                if (dialog.getContentPane().getComponentCount() == 1
                    && dialog.getContentPane().getComponent(0) instanceof JOptionPane) {
                    dialog.dispose();
                }
            }
        }
    }

    public boolean isTimeOver() {
        return timeOver;
    }

    public void resetTimer() {
        secondsLeft = INITIAL_SECONDS;
        timeOver = false;
        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");
        bettingTimer.start();
    }

    public void start() {
        bettingTimer.start();
    }
}
