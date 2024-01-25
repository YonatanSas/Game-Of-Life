package HomeExercises.Ex5;// Name: Yonatan Sasson
// ID: 207600495

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Represents a panel for the Game of Life simulation.
 * Provides buttons and a grid of cells to interact with the simulation.
 */
public class GOLPanel extends JPanel {

    private GOLMatrix golMatrix; // The Game of Life matrix
    private JButton cmdGo, cmdNext, cmdClear, cmdFaster, cmdSlower; // Buttons for controlling the simulation
    private JLabel lblGenerations; // Label displaying the number of generations
    private Timer timer; // Timer for automatic generation progression
    private int interval; // Interval between automatic generations
    private JPanel worldPanel; // Panel containing the grid of cells
    private JButton[][] gameMatrix; // Grid of cells as buttons
    private ButtonClickListener buttonClickListener; // Listener for button clicks
    private TimerListener listener; // Listener for timer events


    /**
     * Constructs a HomeExercises.Ex5.GOLPanel with the specified world size.
     * @param worldSize The size of the Game of Life world.
     */
    public GOLPanel(int worldSize) {
        worldSize = Math.max(worldSize, 3); // Ensure minimum world size of 3
        golMatrix = new GOLMatrix(worldSize);
        cmdGo = new JButton("Go");
        cmdNext = new JButton("Next");
        cmdClear = new JButton("Clear");
        cmdFaster = new JButton("Faster");
        cmdSlower = new JButton("Slower");
        lblGenerations = new JLabel("Number of generations: " + golMatrix.getGenerations());
        JLabel gameOfLife = new JLabel("Game Of Life"); // Label for the game name
        interval = 200; // Default interval for automatic generation progression
        buttonClickListener = new ButtonClickListener();
        listener = new TimerListener();
        timer = new Timer(interval, listener);


        // Enable or disable buttons
        cmdGo.setEnabled(true);
        cmdNext.setEnabled(true);
        cmdClear.setEnabled(true);
        cmdFaster.setEnabled(false);
        cmdSlower.setEnabled(false);

        // Add action listeners to buttons
        cmdGo.addActionListener(buttonClickListener);
        cmdNext.addActionListener(buttonClickListener);
        cmdClear.addActionListener(buttonClickListener);
        cmdFaster.addActionListener(buttonClickListener);
        cmdSlower.addActionListener(buttonClickListener);

        // ******* Set buttons and labels properties ********

        // Get rid of the border of text on the buttons
        cmdGo.setFocusable(false);
        cmdNext.setFocusable(false);
        cmdClear.setFocusable(false);
        cmdFaster.setFocusable(false);
        cmdSlower.setFocusable(false);

        // Set font color for the buttons
        cmdGo.setForeground(Color.BLUE);
        cmdNext.setForeground(Color.BLUE);
        cmdClear.setForeground(Color.BLUE);
        cmdFaster.setForeground(Color.BLUE);
        cmdSlower.setForeground(Color.BLUE);

        // Set background color for the buttons
        cmdGo.setBackground(Color.LIGHT_GRAY);
        cmdNext.setBackground(Color.LIGHT_GRAY);
        cmdClear.setBackground(Color.LIGHT_GRAY);
        cmdFaster.setBackground(Color.LIGHT_GRAY);
        cmdSlower.setBackground(Color.LIGHT_GRAY);

        // Set fonts for text on labels and buttons
        cmdGo.setFont(new Font("Arial Rounded MT Bold",Font.ITALIC,15));
        cmdNext.setFont(new Font("Arial Rounded MT Bold",Font.ITALIC,15));
        cmdClear.setFont(new Font("Arial Rounded MT Bold",Font.ITALIC,15));
        cmdFaster.setFont(new Font("Arial Rounded MT Bold",Font.ITALIC,15));
        cmdSlower.setFont(new Font("Arial Rounded MT Bold",Font.ITALIC,15));
        gameOfLife.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,15));
        lblGenerations.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,15));


        // Create panel for buttons
        JPanel buttons = new JPanel();
        buttons.add(cmdGo);
        buttons.add(cmdNext);
        buttons.add(cmdClear);
        buttons.add(cmdFaster);
        buttons.add(cmdSlower);
        buttons.add(lblGenerations);

        // Create panels for buttons and labels
        JPanel nameLabel = new JPanel((new BorderLayout()));
        nameLabel.add(gameOfLife);

        JPanel buttonsAndLabel = new JPanel(new BorderLayout());
        buttonsAndLabel.add(nameLabel, BorderLayout.NORTH);
        buttonsAndLabel.add(lblGenerations, BorderLayout.SOUTH);
        buttonsAndLabel.add(buttons, BorderLayout.CENTER);
        lblGenerations.setHorizontalAlignment(SwingConstants.CENTER);


        // Create panel for the world grid
        GridLayout worldGrid = new GridLayout(worldSize, worldSize);
        worldPanel = new JPanel(worldGrid);

        // Set panel for whole layout and add components
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        add(buttonsAndLabel, BorderLayout.NORTH);
        add(worldPanel, BorderLayout.CENTER);

        // Create the grid of cell buttons
        gameMatrix = new JButton[worldSize][worldSize];
        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                JButton cellButton = new JButton();
                cellButton.setBorder(new LineBorder(Color.BLACK));
                cellButton.setBorder(BorderFactory.createEtchedBorder()); // Make the buttons look like 3D
                gameMatrix[i][j] = cellButton;
                gameMatrix[i][j].setActionCommand(i + "," + j); // To know which cell was clicked after
                cellButton.addActionListener(buttonClickListener);
                worldPanel.add(cellButton);
            }
        }
    }

    /**
     * Updates the display based on the current state of the Game of Life matrix.
     */
    private void updateDisplay() {
        boolean[][] world = golMatrix.getWorld();
        int n = world.length;

        // Update the colors of the cell buttons
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                JButton cellButton = gameMatrix[row][col];
                boolean isAlive = world[row][col];

                if (isAlive) {
                    cellButton.setBackground(Color.BLUE); // Ture the button from white to blue
                } else {
                    cellButton.setBackground(Color.WHITE); // Ture the button from blue to white
                }
            }
        }
        int generations = golMatrix.getGenerations();
        lblGenerations.setText("Number of generations: " + generations);

        repaint();
    }

    /**
     * Represents a listener for button click events.
     */
    private class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(cmdGo)) {
                // Handle "Go" button click
                if (!timer.isRunning()) {
                    timer.start();
                    cmdGo.setForeground(Color.RED);
                    cmdGo.setText("Stop");
                    cmdNext.setEnabled(false);
                    cmdClear.setEnabled(false);
                    cmdFaster.setEnabled(true);
                    cmdSlower.setEnabled(true);
                } else {
                    timer.stop();
                    cmdGo.setForeground(Color.BLUE);
                    cmdGo.setText("Go");
                    cmdNext.setEnabled(true);
                    cmdClear.setEnabled(true);
                    cmdFaster.setEnabled(false);
                    cmdSlower.setEnabled(false);
                }
            } else if (e.getSource().equals(cmdNext)) {
                // Handle "Next" button click
                golMatrix.nextGeneration();
                updateDisplay();
            } else if (e.getSource().equals(cmdClear)) {
                // Handle "Clear" button click
                golMatrix.clearWorld();
                updateDisplay();
            } else if (e.getSource().equals(cmdFaster)) {
                // Handle "Faster" button click
                if (interval > 100) {
                    interval -= 20;
                    timer.setDelay(interval);
                }
            } else if (e.getSource().equals(cmdSlower)) {
                // Handle "Slower" button click
                if (interval < 1000) {
                    interval += 20;
                    timer.setDelay(interval);
                }
            } else if (e.getSource().getClass().equals(gameMatrix[0][0].getClass())) { // Check if the button that was clicked is a cell button
                // Handle cell button click
                String actionCommand = e.getActionCommand();
                String[] indices = actionCommand.split(",");
                int i = Integer.parseInt(indices[0]);
                int j = Integer.parseInt(indices[1]);
                golMatrix.flipCell(i, j);
                updateDisplay();
            }
            repaint();
        }
    }

    /**
     * Represents a listener for timer events.
     */
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            golMatrix.nextGeneration();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateDisplay();
    }

}

