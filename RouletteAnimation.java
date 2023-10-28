import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RouletteAnimation {

    private ImageIcon[] images = new ImageIcon[24];
    private Timer timer1;
    private Timer stopTimer; 
    private int currentIndex = 0;
    private JLabel rouletteLabel;
    private RouletteLogic rouletteLogic;
    private boolean isAnimationOver = false;

    public RouletteAnimation(JLabel rouletteLabel, RouletteLogic rouletteLogic) {
        this.rouletteLabel = rouletteLabel;
        this.rouletteLogic = rouletteLogic;

        // Load all the images into the array
        for (int i = 0; i < 24; i++) {
            images[i] = new ImageIcon("roulette_animation/roulette" + (i + 1) + ".png");
        }

        int delay = 1000 / 100; // To show all images in 1 second
        timer1 = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage();
            }
        });
    }

    private void updateImage() {
        rouletteLabel.setIcon(images[currentIndex]);
        currentIndex = (currentIndex + 1) % 24; // Cycle through the images
    }

    public void startAnimation() {
        timer1.start();
        
        // To stop the animation after 5,7 seconds (for now unknown reason)
        stopTimer = new  Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer1.stop();
                rouletteLabel.setIcon(images[0]); // Always stop on roulette1.png
                rouletteLogic.placeChipOnTriangle(rouletteLogic.getWinningNumber());
                stopTimer.stop();
                isAnimationOver = true;
                
            }
        });
        stopTimer.start();
    }

    public boolean getIsAnimationOver() {
        return isAnimationOver;
    }
}