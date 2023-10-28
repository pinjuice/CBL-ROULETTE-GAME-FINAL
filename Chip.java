// Chip.java
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Chip {
    private int x, y;
    private final int radius = 30;
    private Color color;
    private int value;

    public Chip(int x, int y, Color color, int value) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.value = value;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPositionRect(Rectangle rect) {
        this.x = rect.x + rect.width / 2;
        this.y = rect.y + rect.height / 2;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        g2d.setColor(color);
        g2d.fill(circle);

        // Draw the value of the chip in the center of the chip
        g2d.setColor(Color.BLACK);  // Set text color
        g2d.setFont(new Font("Arial", Font.BOLD, 20));  // Set font size and style
        String valueStr = String.valueOf(value);
        int textWidth = g2d.getFontMetrics().stringWidth(valueStr);
        int textHeight = g2d.getFontMetrics().getAscent();
        g2d.drawString(valueStr, x - textWidth / 2, y + textHeight / 4);  // Adjust position to center the text
    }

    public boolean contains(Point p) {
        Ellipse2D circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        return circle.contains(p);
    }
}
