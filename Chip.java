// Chip.java
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Represents a betting chip in the game.
 * <p>
 * This class encapsulates the visual and data aspects of a chip used
 * for placing bets on the game board. It provides methods for drawing the 
 * chip and adjusting its properties.
 * </p>
 */
public class Chip {

    private int x;
    private int y;
    private int value;
    private final int radius = 30;
    private Color color;

    /**
     * Constructs a new chip with specified position and value.
     *
     * @param x     The x-coordinate of the chip's center.
     * @param y     The y-coordinate of the chip's center.
     * @param value The "value" that the chip stores.
     */
    public Chip(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.color = Color.RED;
        this.value = value;
    }

    /**
     * Returns the "value" of the chip.
     *
     * @return The chip's value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Positions the chip at the center of the specified rectangle.
     *
     * @param rect The rectangle within which the chip should be centered.
     */
    public void setPositionRect(Rectangle rect) {
        this.x = rect.x + rect.width / 2;
        this.y = rect.y + rect.height / 2;
    }

    /**
     * Sets the "value" of the chip.
     *
     * @param value The new "value" of the chip.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Draws the chip on the specified graphics object.
     * The chip's shape, color are drawn, as well as the chip's "value" on it.
     *
     * @param g The graphics context on which to draw the chip.
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        g2d.setColor(color);
        g2d.fill(circle);
        g2d.setColor(Color.BLACK);  // Sets text color
        g2d.setFont(new Font("Arial", Font.BOLD, 20));  // Set font size and style
        String valueStr = String.valueOf(value); // Converts the chip's "value" to a string
        // Calculate the width and height of the value string to position it centrally
        int textWidth = g2d.getFontMetrics().stringWidth(valueStr);
        int textHeight = g2d.getFontMetrics().getAscent();
        // Draw the value string such that it's centered on the chip
        g2d.drawString(valueStr, x - textWidth / 2, y + textHeight / 4);
    }
}
