# Game of Life

This is an implementation of John Conway's Game of Life cellular automaton simulation in Java. The Game of Life is a zero-player game where the evolution of the game is determined by its initial state, requiring no further input from the user.

## Game Preview
![GameOfLifeGif](https://github.com/YonatanSas/Game-Of-Life/assets/146161426/73149a1e-6bcf-4218-af3a-8c46014595f1)

## Features
- Graphical user interface (GUI) for visualizing the Game of Life simulation
- Buttons to control the simulation: Go, Stop, Next, Clear, Faster, Slower
- Clickable grid of cells to manually toggle cell states
- Display of the current generation count
- Adjustable speed of the simulation
- Resizable window to accommodate different grid sizes

## Rules of the Game

The Game of Life follows these rules:

1. Any live cell with fewer than two live neighbors dies (underpopulation).
2. Any live cell with two or three live neighbors lives on to the next generation.
3. Any live cell with more than three live neighbors dies (overpopulation).
4. Any dead cell with exactly three live neighbors becomes a live cell (reproduction).

## Classes

- `GOLMatrix`: Represents the matrix of cells in the Game of Life simulation. It handles the logic for updating the cell states based on the rules of the game.
- `GOLPanel`: Represents the graphical user interface panel for the Game of Life simulation. It contains the buttons, labels, and the grid of cells. It interacts with the `GOLMatrix` to update the display based on the current state of the simulation.
- `Main`: The entry point of the program. It creates an instance of the `GOLPanel` and displays it in a `JFrame`. It also sets the preferred size of the window and centers it on the screen.

## Customization

- You can adjust the size of the game grid by modifying the `worldSize` variable in the `Main` class. The default value is set to 50.
- The preferred size of the window can be changed by modifying the `preferredSize` variable in the `Main` class. The default size is set to 800 pixels wide and 600 pixels high.

## Dependencies

This implementation relies on the Java Swing library for creating the graphical user interface.

## Contributing

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.


## Acknowledgments

- This implementation is based on John Conway's Game of Life cellular automaton simulation.
- The code structure and GUI design were inspired by various Game of Life implementations available online.

Feel free to customize the README file further based on your specific implementation details and preferences.
