import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import javax.swing.JLabel;

public class RouletteLogic {
    private int upperbound = 13;
    Random rand = new Random();
    private int int_random;
    private Balance balance;
    private BoardRectangles boardRectangles;
    private JLabel winningNumberLabel;
    private RouletteTriangles rouletteTriangles;
    private Ball ball;
    private Renderer renderer;

    public RouletteLogic(Balance balance, BoardRectangles boardRectangles, JLabel winningNumberLabel, RouletteTriangles rouletteTriangles, Renderer renderer) {
        this.balance = balance;
        this.boardRectangles = boardRectangles;
        this.winningNumberLabel = winningNumberLabel;
        this.rouletteTriangles = rouletteTriangles;
        this.renderer = renderer;
    }

    public void spin() {
        int_random = rand.nextInt(upperbound);
        updateWinningNumberDisplay();
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

    public void updateWinningNumberDisplay() {
        winningNumberLabel.setText("Winning Number: "+int_random);
    }

    public void placeChipOnTriangle(int triangleIndex) {
        Point renderPoint = rouletteTriangles.renderPoints[triangleIndex];
        ball = new Ball(0, 0, Color.WHITE);
        ball.setPosition(renderPoint.x, renderPoint.y);
        renderer.addBall(ball);
    }
}