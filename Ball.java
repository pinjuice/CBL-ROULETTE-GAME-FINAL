import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Represents a ball with a specified position, radius, and color.
 * <p>
 * The ball can be moved to a new position and drawn on a graphical context.
 * </p>
 */
public class Ball {
    
    /** X-coordinate of the ball's center. */
    private int x;
    
    /** Y-coordinate of the ball's center. */
    private int y;
    
    /** The radius of the ball. */
    private final int radius = 20;
    
    /** The color of the ball. */
    private Color color;

    /**
     * Constructs a new Ball with specified x and y coordinates.
     * The default color of the ball is white.
     *
     * @param x  The x-coordinate of the ball's center.
     * @param y  The y-coordinate of the ball's center.
     */
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.WHITE;
    }

    /**
     * Sets the position of the ball to the specified x and y coordinates.
     *
     * @param x  The new x-coordinate of the ball's center.
     * @param y  The new y-coordinate of the ball's center.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the ball on the given graphical context.
     * <p>
     * The ball is represented as a filled circle with the specified radius and color.
     * </p>
     *
     * @param g  The graphics context to be used for drawing.
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        g2d.setColor(color);
        g2d.fill(circle);
    }
}
