import java.awt.Color;
import javax.swing.*;

/**
 * This class is the main graphical user interface for the roulette game.
 * <p>
 * The Gui class manages the game's visual representation and interaction. It sets up 
 * the game components and handles the game progression through various rounds. The game 
 * supports a maximum number of rounds and will end when the maximum rounds are reached 
 * or when the player has an insufficient balance which means the balance will be equal to 0.
 * </p>
 */
public class Gui {
    
    // Frame components
    private JFrame frame = new JFrame("CBLRouletteGame");
    private JLabel boardLabel = new JLabel();
    private JLabel timerLabel = new JLabel();
    private JLabel balanceDisplay = new JLabel();
    private JLabel rouletteLabel = new JLabel();
    
    // Game mechanics and visuals
    private RoundDisplay roundDisplay;
    private Balance balance;
    private BoardRectangles boardRectangles;
    private PostAnimationTimer postAnimationTimer;
    private Renderer renderer;
    private RouletteTriangles rouletteTriangles;
    private RouletteLogic rouletteLogic;
    private RouletteAnimation rouletteAnimation;
    private BetSlider betSlider;
    private BettingTimer bettingTimer;
    private BettingMechanic bettingMechanic;

    // Game settings
    private int roundsPlayed = 0;
    private static final int MAX_ROUNDS = 5;

    /**
     * Constructs the game GUI, initializing game components and setting up the main frame.
     */
    public Gui() {
        // Basic frame settings
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Timer label setup
        timerLabel.setBounds(870, 20, 400, 50);
        frame.add(timerLabel);

        // Round display setup
        roundDisplay = new RoundDisplay();
        roundDisplay.setBounds(20, 20, 300, 50);
        frame.add(roundDisplay);

        // Balance display setup
        balanceDisplay.setBounds(20, 45, 400, 50);
        frame.add(balanceDisplay);

        // Balance initialization
        balance = new Balance(1000, balanceDisplay);
        
        // Board rectangles for betting
        boardRectangles = new BoardRectangles();

        // Post-animation timer initialization
        postAnimationTimer = new PostAnimationTimer(this);
        
        // Renderer for the game visuals
        renderer = new Renderer();

        // Renderer bounds and settings
        renderer.setBounds(0, 0, 1920, 1080);
        renderer.setOpaque(false);
        
        // Roulette triangles for the wheel
        rouletteTriangles = new RouletteTriangles();
        
        // Core game logic initialization
        rouletteLogic = new RouletteLogic(balance, boardRectangles, rouletteTriangles, renderer);
        
        // Roulette animation setup
        rouletteAnimation = new RouletteAnimation(rouletteLabel, rouletteLogic, postAnimationTimer);

        // Betting slider setup
        betSlider = new BetSlider(frame);
        betSlider.getSlider().setBounds(940, 620, 300,  50);
        betSlider.getButton().setBounds(940, 680, 120, 30);
        bettingTimer = new BettingTimer(timerLabel, rouletteLabel, rouletteLogic,
         postAnimationTimer, betSlider);
        frame.add(betSlider.getSlider());
        frame.add(betSlider.getButton());

        // Add renderer to frame
        frame.add(renderer);
        
        // Betting mechanic setup
        bettingMechanic = new BettingMechanic(boardRectangles, renderer,
         betSlider, bettingTimer, balance);
     
        // Betting board image
        ImageIcon board = new ImageIcon("bettingboardCBL.jpg");
        boardLabel.setIcon(board);
        boardLabel.setBounds(870, 80, 454, 503);
        frame.add(boardLabel);

        // Roulette wheel image
        ImageIcon roulette = new ImageIcon("roulette_animation/roulette1.png");
        rouletteLabel.setIcon(roulette);
        rouletteLabel.setBounds(20, 40, 738, 671);
        frame.add(rouletteLabel);
        
        // Make the frame visible
        frame.setVisible(true);

        // Setup initial round
        setupNewRound();
    }

    /**
     * Handles the end-of-round actions, such as determining if the game continues 
     * to the next round or if it's over based on played rounds and user's balance.
     */
    public void endOfRound() {
        roundsPlayed++;
        if (roundsPlayed < MAX_ROUNDS && balance.getBalance() > 0) {
            roundDisplay.incrementRound();
            setupNewRound();
        } else if (balance.getBalance() > 0) {
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            JOptionPane.showMessageDialog(
                null, "Game over, your balance after 5 rounds is: " 
                + balance.getBalance() + "$", "Game Over", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
        } else if (roundsPlayed < MAX_ROUNDS) {
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            JOptionPane.showMessageDialog(
                    null, "Insufficient balance, you were kicked out of the Casino",
                     "Game Over", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Resets the game state to prepare for a new round. This includes clearing any 
     * bets, reseting rendering panel. resetting timers, 
     * and setting the roulette animation to its initial state.
     */
    public void setupNewRound() {
        renderer.reset();
        bettingMechanic.resetBets();
        bettingTimer.resetTimer();
        rouletteAnimation.resetAnimation();
    }

    /**
     * The main entry point for the application. Initializes and starts the roulette game GUI.
     */
    public static void main(String[] args) {
        new Gui();
    }
}
