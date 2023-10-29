import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RouletteAnimation {
    
    private int currentIndex = 0;
    private ImageIcon[] images = new ImageIcon[24];
    private Timer roulettePhotosTimer;
    private Timer animationTimer; 
    private JLabel rouletteLabel;
    private RouletteLogic rouletteLogic;
    private PostAnimationTimer postAnimationTimer;

    public RouletteAnimation(JLabel rouletteLabel, RouletteLogic rouletteLogic, PostAnimationTimer postAnimationTimer) {
        this.rouletteLabel = rouletteLabel;
        this.rouletteLogic = rouletteLogic;
        this.postAnimationTimer = postAnimationTimer;
        // Load all the images into the array
        for (int i = 0; i < 24; i++) {
            images[i] = new ImageIcon("roulette_animation/roulette" + (i + 1) + ".png");
        }

        int delay = 1000 / 100; // To show all images in 1 second
        roulettePhotosTimer = new Timer(delay, new ActionListener() {
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
        animationTimer = new  Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roulettePhotosTimer.stop();
                animationTimer.stop();
                rouletteLabel.setIcon(images[0]); // Always stop on roulette1.png
                rouletteLogic.placeChipOnTriangle(rouletteLogic.getWinningNumber());
                postAnimationTimer.startPostAnimationTimer();
                rouletteLogic.checkWinningBoardRectangleAndUpdateBalance();
                
            }
        });
        roulettePhotosTimer.start();
        animationTimer.start();
    }

    public void resetAnimation() {
        if (roulettePhotosTimer.isRunning()) {
            roulettePhotosTimer.stop();
        }
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
        currentIndex = 0;
    }
    
}   