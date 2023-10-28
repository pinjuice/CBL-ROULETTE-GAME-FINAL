import java.awt.*;

public class ChipWatcher {
    private Squares squares;
    private ChipRenderer chipRenderer;
    private Balance balance;
    private RouletteLogic rouletteLogic;

    public ChipWatcher(Squares squares, ChipRenderer chipRenderer, Balance balance, RouletteLogic rouletteLogic) {
        this.squares = squares;
        this.chipRenderer = chipRenderer;
        this.balance = balance;
        this.rouletteLogic = rouletteLogic;
    }

    public void checkChipsPosition() {
        for (Chip chip : chipRenderer.getChips()) {
            int index = squares.getSquareIndex(new Point(chip.getX(), chip.getY()));
            if (index == rouletteLogic.getWinningNumber()) {
                rouletteLogic.checkWinningSquareAndUpdateBalance();
                balance.updateDisplay();
            }
        }
    }
}
