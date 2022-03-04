package sudoku;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SudokuSolverGUI {

	public SudokuSolverGUI(String title, int width, int height) {
		SwingUtilities.invokeLater(() -> createWindow(title, width, height));
	}
	
	private void createWindow(String title, int width, int height) {
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container grid = frame.getContentPane();
		grid.setLayout(new GridLayout(9, 9));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; i < 10; j++) {
				grid.add(new TextField(), i, j);
			}
		}
		frame.add(grid);
		frame.pack();
		frame.setVisible(true);
	}
}
