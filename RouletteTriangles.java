import java.awt.Point;
import java.awt.Polygon;

/**
 * A class representing a collection of "triangles" used to represent roullete's triangles.
 * <p>
 * This class provides methods to manage these "triangles",
 * such as getting the index of a "triangle"
 * containing a specific point and computing render points for each "triangle".
 * </p>
 */
public class RouletteTriangles {
    
    /** An array of Polygons representing the "triangles". */
    public Polygon[] triangles;
    
    /** An array of Points representing render positions for each "triangle". */
    public Point[] renderPoints;
    
    /**
     * Constructor initializes the "triangles" and their respective render points.
     */
    public RouletteTriangles() {
        triangles = new Polygon[] {
            createTriangle(387, 393, 461, 686, 387, 700, 312, 689), //0
            createTriangle(377, 392, 307, 687, 235, 661, 175, 619), //1
            createTriangle(372, 386, 171, 614, 121, 561, 86, 494),  //2
            createTriangle(396, 376, 83, 484, 67, 424, 65, 340),    //3
            createTriangle(370, 368, 66, 332, 85, 257, 119, 195),   //4
            createTriangle(375, 361, 124, 188, 171, 134, 234, 90),  //5
            createTriangle(383, 357, 241, 87, 307, 60, 383, 53),    //6
            createTriangle(391, 356, 391, 52, 462, 60, 534, 87),    //7 
            createTriangle(399, 361, 542, 91, 601, 131, 651, 188),  //8
            createTriangle(405, 368, 655, 196, 690, 260, 707, 331), //9
            createTriangle(406, 377, 709, 340, 709, 416, 691, 486), //10
            createTriangle(403, 385, 687, 492, 654, 560, 603, 613), //11
            createTriangle(396, 392, 598, 619, 537, 662, 468, 687)  //12
        };
        renderPoints = new Point[triangles.length];
        for (int i = 0; i < triangles.length; i++) {
            int[] xpoints = triangles[i].xpoints;
            int[] ypoints = triangles[i].ypoints;
            
            // Creates points that would later serve to determine the position of the ball
            renderPoints[i] = new Point(getRenderX(xpoints[0], xpoints[2]), 
                                        getRenderY(ypoints[0], ypoints[2]));
        }
    }

    /**
     * Creates a "triangle" (actually a polygon) using the specified coordinates.
     * 
     * @param x1 x-coordinate of the first point.
     * @param y1 y-coordinate of the first point.
     * @param x2 x-coordinate of the second point.
     * @param y2 y-coordinate of the second point.
     * @param x3 x-coordinate of the third point.
     * @param y3 y-coordinate of the third point.
     * @param x4 x-coordinate of the fourth point.
     * @param y4 y-coordinate of the fourth point.
     * @return A Polygon object representing the "triangle".
     */
    private Polygon createTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int[] xpoints = {x1, x2, x3, x4};
        int[] ypoints = {y1, y2, y3, y4};
        return new Polygon(xpoints, ypoints, 4);
    }

    /**
     * Computes the x-coordinate of the render point for the ball
     * using the first and third x-coordinates of a "triangle".
     * 
     * @param x1 x-coordinate of the first point.
     * @param x3 x-coordinate of the third point.
     * @return The x-coordinate of the render point for the ball.
     */
    public int getRenderX(int x1, int x3) {
        return ((x1 + x3) / 2);
    }

    /**
     * Computes the y-coordinate of the render point for the ball
     * using the first and third y-coordinates of a "triangle".
     * 
     * @param y1 y-coordinate of the first point.
     * @param y3 y-coordinate of the third point.
     * @return The y-coordinate of the render point for the ball.
     */
    public int getRenderY(int y1, int y3) {
        return ((y1 + y3) / 2);
    }
}
