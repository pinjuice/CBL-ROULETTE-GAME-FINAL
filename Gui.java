import javax.swing.*;

public class Gui {
    
    JFrame frame = new JFrame("Cheating Roulette");
    JLabel labelBoard = new JLabel();
    JLabel timerLabel = new JLabel();
    JLabel balanceDisplay = new JLabel();
    JLabel winningNumberLabel = new JLabel();
    JLabel rouletteLabel = new JLabel();

    ChipRenderer chipRenderer;
    BettingTimer bettingTimer;
    BettingMechanic bettingMechanic;
    Squares squares;
    Balance balance = new Balance(1000, balanceDisplay);
    RouletteLogic rouletteLogic;
    BetSlider betSlider;
    RouletteAnimation rouletteAnimation;
    RouletteTriangles rouletteTriangles;

    public Gui() {
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        timerLabel.setBounds(870, 10, 300, 50);
        frame.add(timerLabel);

        balanceDisplay.setBounds(700, 10, 300, 50);
        frame.add(balanceDisplay);
        
        squares = new Squares();
        
        winningNumberLabel.setBounds(870, 40, 300, 50);
        frame.add(winningNumberLabel);

        chipRenderer = new ChipRenderer();
        // NOTE: You'll need to initialize rouletteTriangles here as well before using it
        // rouletteTriangles = new RouletteTriangles(...);
        rouletteTriangles = new RouletteTriangles();
        rouletteLogic = new RouletteLogic(balance, squares, winningNumberLabel, rouletteTriangles, chipRenderer);
        rouletteAnimation = new RouletteAnimation(rouletteLabel, rouletteLogic);
        bettingTimer = new BettingTimer(timerLabel, rouletteLabel, rouletteLogic);
        
        chipRenderer.setBounds(0, 0, 1920, 1080);
        chipRenderer.setOpaque(false);

        // Initialize and position BetSlider before BettingMechanic
        betSlider = new BetSlider(frame);
        betSlider.getSlider().setBounds(940, 620, 300,  50);
        betSlider.getButton().setBounds(940, 680, 120, 30);
        frame.add(betSlider.getSlider());
        frame.add(betSlider.getButton());
        
        // Initialize BettingMechanic after BetSlider
        bettingMechanic = new BettingMechanic(squares, chipRenderer, betSlider, bettingTimer, balance);

        frame.add(chipRenderer);
     
        ImageIcon board = new ImageIcon("bettingboardCBL.jpg");
        labelBoard.setIcon(board);
        labelBoard.setBounds(870, 80, 454, 503);
        frame.add(labelBoard);

        ImageIcon roulette = new ImageIcon("roulette_animation/roulette1.png");
        rouletteLabel.setIcon(roulette);
        rouletteLabel.setBounds(20, 40, 738, 671);
        frame.add(rouletteLabel);
        
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Gui();
    }
}