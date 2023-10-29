import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * A custom JPanel responsible for rendering both chips and balls.
 * <p>
 * This class manages and displays a collection of Chip and Ball objects
 * on the game board. It extends the basic functionalities of a JPanel to 
 * provide specific drawing capabilities for the game's elements.
 * </p>
 */
public class Renderer extends JPanel {
    /** List of chips to be rendered on the panel. */
    private List<Chip> chips;
    
    /** List of balls to be rendered on the panel. */
    private List<Ball> balls;

    /**
     * Constructor initializes empty lists for chips and balls.
     */
    public Renderer() {
        chips = new ArrayList<>();
        balls = new ArrayList<>();
    }

    /**
     * Adds a chip to the list of chips to be rendered.
     * 
     * @param chip The chip object to be added.
     */
    public void addChip(Chip chip) {
        chips.add(chip);
    }

    /**
     * Adds a ball to the list of balls to be rendered.
     * 
     * @param ball The ball object to be added.
     */
    public void addBall(Ball ball) {
        balls.add(ball);
    }

    /**
     * Resets the renderer by clearing both the chips and balls collections.
     * Also triggers a repaint to reflect the cleared state on the UI.
     */
    public void reset() {
        chips.clear();
        balls.clear();
        repaint();
    }

    /**
     * Overrides the paintComponent method to draw chips and balls.
     * <p>
     * This method is called whenever the JPanel is rendered. It goes through each 
     * chip and ball in their respective collections and calls their draw method 
     * to render them on the panel.
     * </p>
     * 
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Chip chip : chips) {
            chip.draw(g);
        }

        for (Ball ball : balls) {
            ball.draw(g);
        }
    }
}
