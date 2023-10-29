import javax.swing.*;

public class Gui {
    
    JFrame frame = new JFrame("CBLRoulette");
    JLabel labelBoard = new JLabel();
    JLabel timerLabel = new JLabel();
    JLabel balanceDisplay = new JLabel();
    JLabel winningNumberLabel = new JLabel();
    JLabel rouletteLabel = new JLabel();

    Renderer renderer;
    BettingTimer bettingTimer;
    BettingMechanic bettingMechanic;
    BoardRectangles boardRectangles;
    Balance balance = new Balance(1000, balanceDisplay);
    RouletteLogic rouletteLogic;
    BetSlider betSlider;
    RouletteAnimation rouletteAnimation;
    RouletteTriangles rouletteTriangles;
    PostAnimationTimer postAnimationTimer;

    private int roundsPlayed = 0;
    private static final int MAX_ROUNDS = 5;

    public Gui() {
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        timerLabel.setBounds(870, 10, 300, 50);
        frame.add(timerLabel);

        balanceDisplay.setBounds(700, 10, 300, 50);
        frame.add(balanceDisplay);
        
        boardRectangles = new BoardRectangles();

        postAnimationTimer = new PostAnimationTimer(this);
        
        winningNumberLabel.setBounds(870, 40, 300, 50);
        frame.add(winningNumberLabel);
        
        renderer = new Renderer();
        // NOTE: You'll need to initialize rouletteTriangles here as well before using it
        // rouletteTriangles = new RouletteTriangles(...);
        rouletteTriangles = new RouletteTriangles();
        rouletteLogic = new RouletteLogic(balance, boardRectangles, winningNumberLabel, rouletteTriangles, renderer);
        rouletteAnimation = new RouletteAnimation(rouletteLabel, rouletteLogic, postAnimationTimer);
        bettingTimer = new BettingTimer(timerLabel, rouletteLabel, rouletteLogic, postAnimationTimer);
        
        renderer.setBounds(0, 0, 1920, 1080);
        renderer.setOpaque(false);

        // Initialize and position BetSlider before BettingMechanic
        betSlider = new BetSlider(frame);
        betSlider.getSlider().setBounds(940, 620, 300,  50);
        betSlider.getButton().setBounds(940, 680, 120, 30);
        frame.add(betSlider.getSlider());
        frame.add(betSlider.getButton());
        
        // Initialize BettingMechanic after BetSlider
        bettingMechanic = new BettingMechanic(boardRectangles, renderer, betSlider, bettingTimer, balance);

        frame.add(renderer);
     
        ImageIcon board = new ImageIcon("bettingboardCBL.jpg");
        labelBoard.setIcon(board);
        labelBoard.setBounds(870, 80, 454, 503);
        frame.add(labelBoard);

        ImageIcon roulette = new ImageIcon("roulette_animation/roulette1.png");
        rouletteLabel.setIcon(roulette);
        rouletteLabel.setBounds(20, 40, 738, 671);
        frame.add(rouletteLabel);
        
        frame.setVisible(true);

        setupNewRound();
    }
    public void endOfRound() {
        roundsPlayed++;
        if (roundsPlayed < MAX_ROUNDS && balance.getBalance() > 0) {
            setupNewRound();
        } else {
            JOptionPane.showMessageDialog(frame, "Game Over CHRISTOPHER");
        }
    }
    public void setupNewRound() {
        renderer.reset();
        bettingMechanic.resetBets();
        bettingTimer.resetTimer();
        rouletteAnimation.resetAnimation();
    }
    public static void main(String[] args) {
        new Gui();
    }
}