import java.awt.Point;
import java.awt.Rectangle;

/**
 * Represents the rectangular areas of a game board where bets can be placed.
 * <p>
 * This class encapsulates the logic required to identify
 * and interact with the betting rectangles on a game board.
 * </p>
 */
public class BoardRectangles {

    /** Array to store the sum of bets placed on each rectangle. */
    public int[] sumBoardRectangles;

    /** Array of rectangles representing the betting areas on the game board. */
    public Rectangle[] boardRectangles;

    /**
     * Initializes a new instance of the BoardRectangles class.
     * This constructor defines the positions of the betting rectangles.
     */
    public BoardRectangles() {
        boardRectangles = new Rectangle[] {
            new Rectangle(871, 80, 1321 - 871, 176 - 80),     //0
            new Rectangle(871, 182, 1018 - 871, 276 - 182),   //1
            new Rectangle(1022, 182, 1171 - 1022, 276 - 182), //2
            new Rectangle(1175, 182, 1321 - 1175, 276 - 182), //3
            new Rectangle(871, 283, 1017 - 871, 378 - 283),   //4
            new Rectangle(1022, 283, 1171 - 1022, 378 - 283), //5
            new Rectangle(1175, 283, 1321 - 1175, 378 - 283), //6
            new Rectangle(871, 384, 1017 - 871, 480 - 384),   //7
            new Rectangle(1022, 384, 1171 - 1022, 480 - 384), //8
            new Rectangle(1175, 384, 1321 - 1175, 480 - 384), //9
            new Rectangle(871, 485, 1017 - 871, 580 - 485),   //10
            new Rectangle(1022, 485, 1171 - 1022, 580 - 485), //11
            new Rectangle(1175, 485, 1321 - 1175, 580 - 485)  //12
        };
        sumBoardRectangles = new int[boardRectangles.length];
    }

    /**
     * Retrieves the index of the rectangle containing the specified point.
     *
     * @param p The point to check for containment within the board rectangles.
     * @return The index of the rectangle containing the point, or -1 if the point 
     *         is not contained within any rectangle.
     */
    public int getBoardRectangleIndex(Point p) {
        for (int i = 0; i < boardRectangles.length; i++) {
            if (boardRectangles[i].contains(p)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the total number of betting rectangles on the board.
     *
     * @return The number of board rectangles.
     */
    public int getNumberOfBoardRectangles() {
        return boardRectangles.length;
    }

    /**
     * Returns the array representing the sum of bets placed on each rectangle.
     *
     * @return An integer array where each entry corresponds to the total bet 
     *         amount for a specific rectangle.
     */
    public int[] getSumBoardRectangles() {
        return sumBoardRectangles;
    }
}
