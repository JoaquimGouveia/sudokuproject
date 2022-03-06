package sudoku;

public class SudokuApplication {
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		new SudokuSolverGUI(sudoku);
	}
}
