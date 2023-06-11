/**
 * nomakchteinskym
 * Code author - Micaela Nomakchteinsky
 *
 */
import java.util.*;

public class Minesweeper {
    private final int myRows;
    private final int myCols;
    private final String[][] myField;
    private int[][] myEmptyField;
    private final int[][] myHintField;

    /**
     * Constructs a Minesweeper game
     * @param theRows - an integer representing the number of user specified rows for the game
     * @param theCols - an integer representing the number of user specified columns for the game
     * @param theField - a 2D String Array representing the user specified minefield
     */
    public Minesweeper(final int theRows, final int theCols, final String[][] theField) {
        this.myRows = theRows;
        this.myCols = theCols;
        this.myField = theField;
        myEmptyField = emptyField();
        myHintField = createHintField(myEmptyField);
    }

    /**
     * Returns a hint field for testing purposes
     * @param hintField - an empty 2D int array representing the minefield initializes with 0's
     * @return - return a 2D int array representing the hint minefield
     */
    int[][] getHintField(final int[][] hintField) {
        return createHintField(hintField);
    }

    /**
     * Returns an empty field for testing purposes
     * @return - return a 2D int array representing the minefield initialized with 0's
     */
    int[][] getEmptyField() {
        return emptyField();
    }

    /**
     * Creates the hint field based on the placement of mines in the minefield
     * @param theHintField - a 2D integer array representing the minefield initialized with 0's
     * @return theHintField - a 2D integer array representing the minefield with hints.
     */
    private int[][] createHintField(final int[][] theHintField) {
        for (int fieldRow = 0; fieldRow < myRows; fieldRow++) {
            for (int fieldCol = 0; fieldCol < myCols; fieldCol++) {
                String mineCheck = myField[fieldRow][fieldCol];
                if (mineCheck.equals("*")) {
                    theHintField[fieldRow + 1][fieldCol + 1] = -1;
                    for (int surroundRow = fieldRow; surroundRow <= fieldRow + 2; surroundRow++) {
                        for (int surroundCol = fieldCol; surroundCol <= fieldCol + 2; surroundCol++) {
                            if (theHintField[surroundRow][surroundCol] != -1) {
                                theHintField[surroundRow][surroundCol]++;
                            }
                        }
                    }
                }

            }
        }
        printHintField(theHintField);

        return theHintField;
    }

    /**
     * Prints the hint minefield replacing numbers with *'s if there is a mine
     * @param theHintField - a 2D integer array representing the minefield with hints.
     */
    private void printHintField(final int[][] theHintField) {
        for(int row = 1; row < theHintField.length - 1; row++) {
            for(int col = 1; col < theHintField[row].length - 1; col++) {
                if (theHintField[row][col] == -1) {
                    System.out.print("*");
                } else {
                    System.out.print(theHintField[row][col]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Initialized a minefield with 0's
     * @return emptyHintField - a 2D integer array initialized with 0's
     */
    private int[][] emptyField() {
        int[][] emptyHintField = new int[myRows + 2][myCols + 2];
        for (int row = 0; row < myRows + 2; row++) {
            for (int col = 0; col < myCols + 2; col++) {
                emptyHintField[row][col] = 0;
            }
        }

        return emptyHintField;
    }

    public static void main(String[] args) {
        Minesweeper minesweeper;
        int fieldNum = 1;
        Scanner input = new Scanner(System.in);
        int rows = input.nextInt();
        while (rows != 0) {
            int cols = input.nextInt();
            String[][] field = new String[rows][cols];
            for (int row = 0; row < rows; row++) {
                String rowLine = input.next();
                for (int col = 0; col < cols; col++) {
                    char mineCheck = rowLine.charAt(col);
                    if (mineCheck == '*') {
                        field[row][col] = "*";
                    } else {
                        field[row][col] = ".";
                    }
                }
            }
            System.out.println("Field #" + fieldNum + ":");
            minesweeper = new Minesweeper(rows, cols, field);
            System.out.println();
            rows = input.nextInt();
            fieldNum++;
        }
    }
}
