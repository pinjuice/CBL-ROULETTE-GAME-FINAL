import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    private int x, y;
    private final int radius = 20;
    private  Color color;

    public Ball(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPositionRect(Rectangle rect) {
        this.x = rect.x + rect.width / 2;
        this.y = rect.y + rect.height / 2;
    }


    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        g2d.setColor(color);
        g2d.fill(circle);
    }

    public boolean contains(Point p) {
        Ellipse2D circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        return circle.contains(p);
    }
}
