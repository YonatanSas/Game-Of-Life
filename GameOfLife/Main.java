import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int worldSize = 25; // Set the desired world size
        GOLPanel golPanel = new GOLPanel(worldSize);

        // Set the preferred size of the GOLPanel
        Dimension preferredSize = new Dimension(600, 400);
        golPanel.setPreferredSize(preferredSize);

        frame.getContentPane().add(golPanel);
        frame.pack();

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
