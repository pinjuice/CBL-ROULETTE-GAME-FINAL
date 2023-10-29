import javax.swing.*;

/**
 * Represents a UI component for placing bets using a slider and a button.
 * <p>
 * The BetSlider contains a JSlider that allows users to select a percentage value of the bet
 * and a JButton to confirm or place the bet. The slider and the button can be shown or hidden.
 * </p>
 */
public class BetSlider {

    /** Maximum value for the slider, representing 100%. */
    private static final int MAX_PERCENTAGE = 100;
    
    /** The JSlider used for selecting the percentage of the bet. */
    private JSlider slider;
    
    /** The JButton used to confirm or place the bet. */
    private JButton button;
    
    /** The parent frame containing the BetSlider. */
    public JFrame parentFrame;

    /**
     * Constructs a new BetSlider attached to a specified JFrame.
     *
     * @param frame  the parent JFrame for this BetSlider.
     */
    public BetSlider(JFrame frame) {
        this.parentFrame = frame;

        // Configuration of the slider
        slider = new JSlider(0, MAX_PERCENTAGE, 50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        updateSliderLabels();
        slider.setVisible(false);  // Initially hidden

        // Configuration of the button
        button = new JButton("Place Bet");
        button.setFocusPainted(false);
        button.setVisible(false);  // Initially hidden
    }

    /**
     * Updates the labels displayed on the slider.
     * <p>
     * The labels show the percentage values at regular intervals.
     * </p>
     */
    private void updateSliderLabels() {
        java.util.Hashtable<Integer, JLabel> labelTable = new java.util.Hashtable<>();
        for (int i = 0; i <= MAX_PERCENTAGE; i += 20) {
            labelTable.put(i, new JLabel(i + "%"));
        }
        slider.setLabelTable(labelTable);
    }

    /**
     * Shows the BetSlider components (slider and button) on the parent frame.
     */
    public void show() {
        slider.setMaximum(MAX_PERCENTAGE);  // Ensures it's always up to 100%
        slider.setVisible(true);
        button.setVisible(true);
        parentFrame.repaint();
    }

    /**
     * Hides the BetSlider components (slider and button) from the parent frame.
     */
    public void hide() {
        slider.setVisible(false);
        button.setVisible(false);
        parentFrame.repaint();
    }

    /**
     * Returns the JSlider component of the BetSlider.
     *
     * @return the JSlider used for selecting the percentageof the bet.
     */
    public JSlider getSlider() {
        return slider;
    }

    /**
     * Returns the JButton component of the BetSlider.
     *
     * @return the JButton used to confirm or place the bet.
     */
    public JButton getButton() {
        return button;
    }
}
