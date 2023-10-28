import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Renderer extends JPanel {
    private List<Chip> chips;
    private List<Ball> balls;

    public Renderer() {
        chips = new ArrayList<>();
        balls = new ArrayList<>();
    }

    public void addChip(Chip chip) {
        chips.add(chip);
    }

    public List<Chip> getChips() {
        return chips;
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void reset() {
        chips.clear();
        balls.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Chip chip : chips) {
            chip.draw(g);
        }

        for (Ball ball : balls) {
            ball.draw(g); // This assumes the Ball class has a similar draw method like Chip.
        }
    }
}
