package sudoku;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuSolverGUI {
	
	public SudokuSolverGUI(SudokuSolver s) {
		SwingUtilities.invokeLater(() -> createWindow(s, "Joaquim och Olles SudokuSolver", 800, 800));
	}
	
	public void createWindow(SudokuSolver sudoku, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		JButton button = new JButton("hej");
		JTextField[][] textFields = new JTextField[9][9];
		
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(9, 9));
		createTextFields(textFields);
		setGrid(sudoku, textFields);
		createTextMatrix(gridPanel, textFields);
		frame.add(gridPanel);
		
		
		
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setGrid(SudokuSolver s, JTextField[][] textfields) {
		int[][] sudokuGrid = s.getMatrix();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//if (sudokuGrid[i][j] != 0) {
					
					textfields[i][j].setText("hello");
			//	} else {
			//		textfields[i][j].setText(" ");
				//}
			}
		}
	}
	
	public void createTextFields(JTextField[][] textfields) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField s = new JTextField();
				textfields[i][j] = s;
			}
		}
	}
	
	//Integer.toString(sudokuGrid[i][j])
	
	public void createTextMatrix(JPanel gridPanel, JTextField[][] textfields) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				gridPanel.add(textfields[i][j]);
			}
		}
	}
	
}