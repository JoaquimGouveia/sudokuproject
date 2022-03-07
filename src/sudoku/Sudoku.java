package sudoku;

import java.util.Arrays;

public class Sudoku implements SudokuSolver{
	private int[][] grid;
	
	
	public Sudoku() {
		grid = new int[9][9];
	}
	
	@Override
	public boolean solve() {
		if (isValid()) {
			return solve(0, 0);
		}
		else return false;
	}
	
	private boolean solve(int row, int col) {
		int newRow = row;
		int newCol = col;
		
		if (newRow == 9) {
			return true;}
		
		if (newCol != 8) {
			newCol = col + 1;
		} else {
			newCol = 0;					//Sätter nya värden på Row och Col inför nästa anrop av solve
			newRow += 1;
		}
		if (grid[row][col] == 0) {
			for (int i = 1; i < 10; i++) {    //checkar för alla möjliga värden som en digit kan anta
				if (checkRules(row, col, i)) {	//ifall den följer reglerna för sudokun 
					grid[row][col] = i; //ifall den gör det så sätts den på den platsen
					if (solve(newRow, newCol)) {	 //Sedan så anropar man solve ytterligare en gång med nästa värden på row och col
						return true;				//Kommer orsaka en lång "stack"? av metoder tills man kommer till nån position där reglerna inte följs
					} else {
						grid[row][col] = 0; //Isåfall så sätter man platsen till 0 igen och for-loopen fortsätter
					}									//håll koll på identeringen
				}
			}
			return false;
		}
		return solve(newRow, newCol);
		// Om det inte står en 0 på plats row, col så anropar man bara en en ny solve med de nya värdena
	}

	
	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	@Override
	public void add(int row, int col, int digit) {
		if (checkRange(row, col, digit)) {
			grid[row][col] = digit;
		} else {
			throw new IllegalArgumentException("Row, col or digit is outside the range");
		}
	}

	/**
	 * Removes digit in box row, col.
	 * 
	 * @param row  The row
	 * @param col  The col
	 */
	@Override
	public void remove(int row, int col) {
		grid[row][col] = 0;
	}

	
	/**
	 * Gets the digit in box row, col.
	 * 
	 * @param row  The row
	 * @param col  The col
	 * @return Get the digit in box row, col
	 */
	@Override
	public int get(int row, int col) {
		return grid[row][col];
	}

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * 
	 * @return false if grid contains any value that is different from 0 and does not follow the sudoku rules.
	 */
	@Override
	public boolean isValid() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] != 0) {
					if (!checkRules(i, j, grid[i][j]) ) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Removes all digits from the grid.
	 */
	@Override
	public void clear() {
		grid = new int[9][9];	
	}

	/**
	 * 
	 */
	@Override
	public void setMatrix(int[][] m) {
		if (m.length != grid.length || m[0].length != grid[0].length) {
			throw new IllegalArgumentException("Wrong dimensions for the matrix");
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; i < 9; i++) {
				if (!checkDigit(m[i][j])) {
					throw new IllegalArgumentException("Digit at row: " + i + ", col: " + j + " is outside the range");
				}
			}
		}
		grid = m;
	}

	@Override
	public int[][] getMatrix() {
		int[][] copy = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				copy[i][j] = grid[i][j];
			}
		}
		return copy;
	}
	
	private Boolean checkRules(int row, int col, int digit) {
		return checkRow(row, col, digit) && checkCol(row, col, digit) && checkBox(row, col, digit);
	}
	
	private Boolean checkRow(int row, int col, int digit) {
		for (int i = 0; i < 9; i++) {
			if (col != i && digit == grid[row][i]) {
				return false;
			}
		}
		return true;
	}
	
	private Boolean checkCol(int row, int col, int digit) {
		for (int i = 0; i < 9; i++) {
			if (row != i && digit == grid[i][col]) {
				return false;
			}
		}
		return true;
	}
	
	private Boolean checkBox(int row, int col, int digit) {
		int byRow = row / 3;
		int byCol = col / 3;
		
		for (int i = byRow * 3; i < byRow * 3 + 3; i++) {
			for (int j = byCol * 3; j < byCol * 3 + 3; j++) {
				if ((row != i || col != j) && grid[i][j] == digit) {
					return false;
				}
			}
		}
		return true;
	}
	
	private Boolean checkRange(int row, int col, int digit) {
		return row >= 0 && row < 9 && col >= 0 && col < 9 && checkDigit(digit);
	}
	
	public Boolean checkDigit(int digit) {
		return digit > 0 && digit < 10;
	}
	
    public void print2D()
    {
        // Loop through all rows
        for (int[] row : grid)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
}
