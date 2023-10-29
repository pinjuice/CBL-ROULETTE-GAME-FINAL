import java.awt.Point;
import java.util.Random;

public class RouletteLogic {
    private int upperbound = 13;
    private int winningNumber;
    private Random rand = new Random();
    private Balance balance;
    private BoardRectangles boardRectangles;
    private RouletteTriangles rouletteTriangles;
    private Renderer renderer;
    private Ball ball;

    public RouletteLogic(Balance balance, BoardRectangles boardRectangles, RouletteTriangles rouletteTriangles, Renderer renderer) {
        this.balance = balance;
        this.boardRectangles = boardRectangles;
        this.rouletteTriangles = rouletteTriangles;
        this.renderer = renderer;
    }

    public void spin() {
        winningNumber = rand.nextInt(upperbound);
    }

    public void checkWinningBoardRectangleAndUpdateBalance() {
        int winningSquare = getWinningNumber();
        int[] sumSquares = boardRectangles.getSumBoardRectangles();
        if (sumSquares[winningSquare] > 0) {
            int winnings = sumSquares[winningSquare] * 13;
            balance.add(winnings);
        }
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    public void placeChipOnTriangle(int triangleIndex) {
        Point renderPoint = rouletteTriangles.renderPoints[triangleIndex];
        ball = new Ball(0, 0);
        ball.setPosition(renderPoint.x, renderPoint.y);
        renderer.addBall(ball);
    }
}