import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostAnimationTimer {

    private Timer postAnimationTimer;

    public PostAnimationTimer(Gui gui) {

        postAnimationTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postAnimationTimer.stop();
                gui.endOfRound();
            }
        });
    }

    public void startPostAnimationTimer() {
        postAnimationTimer.start();
    }
}

