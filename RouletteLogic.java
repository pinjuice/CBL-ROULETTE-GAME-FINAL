import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class RouletteLogic {
    private int upperbound = 13;
    Random rand = new Random();
    private int int_random;
    private Balance balance;
    private BoardRectangles boardRectangles;
    private RouletteTriangles rouletteTriangles;
    private Ball ball;
    private Renderer renderer;

    public RouletteLogic(Balance balance, BoardRectangles boardRectangles, RouletteTriangles rouletteTriangles, Renderer renderer) {
        this.balance = balance;
        this.boardRectangles = boardRectangles;
        this.rouletteTriangles = rouletteTriangles;
        this.renderer = renderer;
    }

    public void spin() {
        int_random = rand.nextInt(upperbound);
    }

    public void checkWinningSquareAndUpdateBalance() {
        int winningSquare = getWinningNumber();
        int[] sumSquares = boardRectangles.getSumSquares();
        if (sumSquares[winningSquare] > 0) {
            int winnings = sumSquares[winningSquare] * 13;
            balance.add(winnings);
        }
    }

    public int getWinningNumber() {
        return int_random;
    }


    public void placeChipOnTriangle(int triangleIndex) {
        Point renderPoint = rouletteTriangles.renderPoints[triangleIndex];
        ball = new Ball(0, 0, Color.WHITE);
        ball.setPosition(renderPoint.x, renderPoint.y);
        renderer.addBall(ball);
    }
}