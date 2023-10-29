import java.awt.Point;
import java.util.Random;

/**
 * Manages the core logic for the roulette game.
 * <p>
 * This class handles the generation of the winning number, checking if the bet placed 
 * on a board rectangle was successful, and placing the ball on the winning triangle.
 * </p>
 */
public class RouletteLogic {
    private int upperbound = 13;
    private int winningNumber;
    private Random rand = new Random();
    private Balance balance;
    private BoardRectangles boardRectangles;
    private RouletteTriangles rouletteTriangles;
    private Renderer renderer;
    private Ball ball;

    /**
     * Constructs a new roulette logic instance linked to the provided game components.
     *
     * @param balance An instance managing the user's game balance.
     * @param boardRectangles An instance managing the game's board rectangles.
     * @param rouletteTriangles An instance managing the game's roulette triangles.
     * @param renderer An instance rendering the game components.
     */
    public RouletteLogic(Balance balance, BoardRectangles boardRectangles,
         RouletteTriangles rouletteTriangles, Renderer renderer) {
        this.balance = balance;
        this.boardRectangles = boardRectangles;
        this.rouletteTriangles = rouletteTriangles;
        this.renderer = renderer;
    }

    /**
     * Simulates the spinning of the roulette to determine a winning number.
     */
    public void spin() {
        winningNumber = rand.nextInt(upperbound);
    }

    /**
     * Checks if the bet placed on the board rectangle matches the winning number 
     * and updates the user's balance accordingly.
     */
    public void checkWinningBoardRectangleAndUpdateBalance() {
        int winningSquare = getWinningNumber();
        int[] sumSquares = boardRectangles.getSumBoardRectangles();
        if (sumSquares[winningSquare] > 0) {
            int winnings = sumSquares[winningSquare] * 13;
            balance.add(winnings);
        }
    }

    /**
     * Retrieves the winning number of the current round.
     *
     * @return The winning number.
     */
    public int getWinningNumber() {
        return winningNumber;
    }

    /**
     * Places the game ball on the given triangle index, representing the winning number.
     *
     * @param triangleIndex The index of the triangle on which the ball should be placed.
     */
    public void placeChipOnTriangle(int triangleIndex) {
        Point renderPoint = rouletteTriangles.renderPoints[triangleIndex];
        ball = new Ball(0, 0);
        ball.setPosition(renderPoint.x, renderPoint.y);
        renderer.addBall(ball);
    }
}
