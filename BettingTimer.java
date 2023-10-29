import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Manages the timer for the betting period in the roulette game.
 * <p>
 * This class provides a countdown timer that represents the time allowed for placing bets. 
 * Once the time is over, the betting period is closed, and the roulette spin animation starts.
 * </p>
 */
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
    private BetSlider betSlider;

    /**
     * Constructs a new betting timer instance.
     *
     * @param displayLabel The label to display the countdown timer fo betting.
     * @param rouletteLabel The label where the roulette images will be displayed.
     * @param rouletteLogic An instance managing the core game logic.
     * @param postAnimationTimer An instance handling actions post-animation.
     * @param betSlider The slider to place bets.
     */
    public BettingTimer(JLabel displayLabel, JLabel rouletteLabel, RouletteLogic rouletteLogic,
         PostAnimationTimer postAnimationTimer, BetSlider betSlider) {
        this.secondsLeft = INITIAL_SECONDS;
        this.timeOver = false;
        this.displayLabel = displayLabel;
        this.rouletteLabel = rouletteLabel;
        this.rouletteLogic = rouletteLogic;
        this.postAnimationTimer = postAnimationTimer;
        this.betSlider = betSlider;

        displayLabel.setFont(FONT_LARGE);
        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");

        bettingTimer = new Timer(1000, new ActionListener() {
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

    /**
     * Updates the display with the remaining betting time.
     */
    private void updateDisplay() {
        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");
    }

    /**
     * Ends the betting phase, triggers the roulette spin animation,
     * and closes slider and any open dialogs.
     */
    private void endBetting() {
        bettingTimer.stop();
        displayLabel.setText("Time for betting is over");
        timeOver = true;
        new RouletteAnimation(rouletteLabel, rouletteLogic, postAnimationTimer).startAnimation();
        rouletteLogic.spin();
        betSlider.hide(); 
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

    /**
     * Checks if the betting time is over.
     * 
     * @return true if the betting time is over, false otherwise.
     */
    public boolean isTimeOver() {
        return timeOver;
    }

    /**
     * Resets the betting timer to the initial time and restarts it.
     */
    public void resetTimer() {
        secondsLeft = INITIAL_SECONDS;
        timeOver = false;
        displayLabel.setText("Time for betting left: " + secondsLeft + " seconds");
        bettingTimer.start();
    }

    /**
     * Starts the betting timer.
     */
    public void start() {
        bettingTimer.start();
    }
}
