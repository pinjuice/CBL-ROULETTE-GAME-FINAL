import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Represents a timer that triggers an end-of-round event in the given GUI after a specified delay.
 * <p>
 * Once the timer is started, it waits for a predefined delay and then calls the {@code endOfRound}
 * method of the specified {@code Gui} instance. 
 * </p>
 */
public class PostAnimationTimer {

    /** The timer responsible for the post-animation delay. */
    private Timer postAnimationTimer;

    /**
     * Initializes a new {@code PostAnimationTimer}
     * with the specified GUI as its target for the end-of-round callback.
     *
     * @param gui the GUI instance on which {@code endOfRound} will be called when the timer ends.
     */
    public PostAnimationTimer(Gui gui) {
        postAnimationTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postAnimationTimer.stop();
                gui.endOfRound();
            }
        });
    }

    /**
     * Starts the post-animation timer. Once started, the timer will wait for its predefined delay
     * and then call the {@code endOfRound} method on its associated GUI.
     */
    public void startPostAnimationTimer() {
        postAnimationTimer.start();
    }
}
