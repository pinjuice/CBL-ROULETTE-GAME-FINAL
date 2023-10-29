import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Handles the animation of the roulette spin.
 * <p>
 * This class manages the cycling of roulette images, simulating a spin animation 
 * and then stopping on a specific image and to be exact the first image.
 * It also triggers related actions once the spin is complete.
 * </p>
 */
public class RouletteAnimation {
    
    private int currentIndex = 0;
    private ImageIcon[] images = new ImageIcon[24];
    private Timer roulettePhotosTimer;
    private Timer animationTimer; 
    private JLabel rouletteLabel;
    private RouletteLogic rouletteLogic;
    private PostAnimationTimer postAnimationTimer;

    /**
     * Constructs a new roulette animation instance.
     *
     * @param rouletteLabel The label where the roulette images will be displayed.
     * @param rouletteLogic An instance managing the core game logic.
     * @param postAnimationTimer An instance handling actions post-animation.
     */
    public RouletteAnimation(JLabel rouletteLabel, RouletteLogic rouletteLogic,
         PostAnimationTimer postAnimationTimer) {
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

    /**
     * Cycles through the roulette images.
     * This method updates the roulette label to display the next image in the sequence.
     */
    private void updateImage() {
        rouletteLabel.setIcon(images[currentIndex]);
        currentIndex = (currentIndex + 1) % 24; // Cycle through the images
    }

    /**
     * Begins the roulette spin animation.
     * <p>
     * Starts the cycling of the roulette images, and upon completion of the spin,
     * the ball is placed on the winning "number", 
     * and post-animation actions are triggered.
     * </p>
     */
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

    /**
     * Resets the roulette animation, stopping any ongoing animations.
     */
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
