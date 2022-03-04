package sudoku;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SudokuSolverGUI {
	
	public SudokuSolverGUI(SudokuSolver s) {
		SwingUtilities.invokeLater(() -> createWindow(s, "Joaquim och Olles SudokuSolver", 800, 800));
	}
	
	public void createWindow(SudokuSolver sudoku, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = frame.getContentPane();
		JPanel sudokuPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton solveButton = new JButton("Solve Sudoku");
		JButton clearButton = new JButton("Clear Sudoku");
		GridLayout sudokuGrid = new GridLayout(9, 9);
		
	}
}