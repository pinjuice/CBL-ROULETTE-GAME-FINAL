import javax.swing.*;

public class BetSlider {

    private static final int MAX_PERCENTAGE = 100;
    private JSlider slider;
    private JButton button;
    JFrame parentFrame;

    public BetSlider(JFrame frame) {
        this.parentFrame = frame;

        // Create the slider
        slider = new JSlider(0, MAX_PERCENTAGE, 50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        updateSliderLabels();
        slider.setVisible(false);  // Initially hidden

        // Create the button
        button = new JButton("Place Bet");
        button.setVisible(false);  // Initially hidden
    }

    private void updateSliderLabels() {
        java.util.Hashtable<Integer, JLabel> labelTable = new java.util.Hashtable<>();
        for (int i = 0; i <= MAX_PERCENTAGE; i += 20) {
            labelTable.put(i, new JLabel(i + "%"));
        }
        slider.setLabelTable(labelTable);
    }

    public void show() {
        slider.setMaximum(MAX_PERCENTAGE);  // Ensure it's always up to 100%
        slider.setVisible(true);
        button.setVisible(true);
        parentFrame.repaint();
    }

    public void hide() {
        slider.setVisible(false);
        button.setVisible(false);
        parentFrame.repaint();
    }

    public JSlider getSlider() {
        return slider;
    }

    public JButton getButton() {
        return button;
    }
}
