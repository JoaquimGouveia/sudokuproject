package sudoku;

public class SudokuApplication {
	public static void main(String[] args) {
		SudokuSolver sudoku = new Sudoku();
		new SudokuSolverGUI(sudoku);
	}
}
