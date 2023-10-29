import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    private int x, y;
    private final int radius = 20;
    private  Color color;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.WHITE;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        g2d.setColor(color);
        g2d.fill(circle);
    }
}
