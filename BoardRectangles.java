import java.awt.Point;
import java.awt.Rectangle;

public class BoardRectangles {
    public int[] sumBoardRectangles;
    public Rectangle[] boardRectangles;

    public BoardRectangles() {
        boardRectangles = new Rectangle[] {
            new Rectangle(871, 80, 1321 - 871, 176 - 80),
            new Rectangle(871, 182, 1018 - 871, 276 - 182),
            new Rectangle(1022, 182, 1171 - 1022, 276 - 182),
            new Rectangle(1175, 182, 1321 - 1175, 276 - 182),
            new Rectangle(871, 283, 1017 - 871, 378 - 283),
            new Rectangle(1022, 283, 1171 - 1022, 378 - 283),
            new Rectangle(1175, 283, 1321 - 1175, 378 - 283),
            new Rectangle(871, 384, 1017 - 871, 480 - 384),
            new Rectangle(1022, 384, 1171 - 1022, 480 - 384),
            new Rectangle(1175, 384, 1321 - 1175, 480 - 384),
            new Rectangle(871, 485, 1017 - 871, 580 - 485),
            new Rectangle(1022, 485, 1171 - 1022, 580 - 485),
            new Rectangle(1175, 485, 1321 - 1175, 580 - 485)
        };
        sumBoardRectangles = new int[boardRectangles.length];
    }

    public int getBoardRectangleIndex(Point p) {
        for (int i = 0; i < boardRectangles.length; i++) {
            if (boardRectangles[i].contains(p)) {
                return i;
            }
        }
        return -1;
    }

    public int getNumberOfBoardRectangles() {
        return boardRectangles.length;
    }

    public int[] getSumBoardRectangles() {
        return sumBoardRectangles;
    }
}
