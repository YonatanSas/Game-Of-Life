package HomeExercises.Ex5;// Name: Yonatan Sasson
// ID: 207600495

public class GOLMatrix {

    boolean[][] world; // The matrix representing the world
    int generations; // The number of generations


    /**
     * Constructs a HomeExercises.Ex5.GOLMatrix with the specified size.
     * @param n The size of the matrix.
     */
    public GOLMatrix(int n) {
        n = Math.max(n, 3); // Ensure minimum size of 3
        this.world = new boolean[n][n]; // Create a new world matrix. All cells contain false as default
        this.generations = 0; // Initialize generations count
    }

    /**
     * Flips the state of a cell at the specified position.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */
    public void flipCell(int row, int col) {
        this.world[row][col] = !this.world[row][col];
    }

    public boolean[][] getWorld() {
        return this.world;
    }

    public int getGenerations() {
        return this.generations;
    }

    /**
     * Clears the world matrix and resets the generations count to zero.
     */
    public void clearWorld() {
        int n = this.world.length;
        this.world = new boolean[n][n]; // Override 'this' world by empty new world
        this.generations = 0;
    }

    /** Aid method
     * Counts the number of live cells around a specified cell position.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The number of live cells around the specified cell.
     */
    private int howManyLives(int row, int col) {
        int counter = 0; // Counter for live cells

        // Iterate over the neighboring cells
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                try {
                    if (this.world[i][j] && (i != row || j != col))
                        counter++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                    // Ignore out-of-bounds exception
                }
            }
        }
        return counter;
    }

    /** Aid method
     * Creates a copy of the world matrix.
     * @param world The world matrix to be copied.
     * @return A copy of the world matrix.
     */
    private boolean[][] copyWorld(boolean[][] world) {
        int length = this.world.length;
        boolean[][] copyWorld = new boolean[length][length];

        // Copy each cell from the original matrix to the copy
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                copyWorld[row][col] = world[row][col];
            }
        }
        return copyWorld;
    }

    /**
     * Computes the next generation of the world matrix based on the rules of the game:
     * 1. If a certain living cell has less than two living neighbors, in the next generation the cell will die.
     * 2. If a certain living cell has two or three living neighbors, in the next generation the cell will continue to live.
     * 3. If a certain living cell has more than three living neighbors, in the next generation the cell will die.
     * 4. If a certain dead cell has exactly three living neighbors, in the next generation the cell will live.
     */
    public void nextGeneration() {
        boolean[][] tempWorld = copyWorld(this.world); // Create a copy of the current world matrix

        // Iterate over each cell in the copy
        for (int row = 0; row < tempWorld.length; row++) {
            for (int col = 0; col < tempWorld.length; col++) {
                int lives = howManyLives(row, col); // Count the number of live cells around the current cell
                if (tempWorld[row][col]) {
                    if (lives > 3 || lives < 2)
                        tempWorld[row][col] = false; // Cell dies due to overpopulation or underpopulation
                } else if (lives == 3)
                    tempWorld[row][col] = true; // Cell comes to life due to reproduction
            }
        }
        this.generations++; // Increment generations count
        this.world = tempWorld; // Update the world matrix with the new generation
    }
}
