package sudoku;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuSolverGUI {
	
	public SudokuSolverGUI(SudokuSolver s) {
		SwingUtilities.invokeLater(() -> createWindow(s, "Joaquim och Olles SudokuSolver", 800, 840));
	}
	
	public void createWindow(SudokuSolver sudoku, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		Container container = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		JPanel gridPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton solveButton = new JButton("Solve");
		JButton clearButton = new JButton("Clear");
		JTextField[][] textFields = new JTextField[9][9];
		
		container.setPreferredSize(new Dimension(800, 800));
		gridPanel.setLayout(new GridLayout(9, 9));
		gridPanel.setPreferredSize(new Dimension(720, 720));
		
		createTextFields(gridPanel, textFields);
		updateTextFields(sudoku, textFields);
//		createTextMatrix(gridPanel, textFields);
		
		
        solveButton.addActionListener(event -> {
        	updateSudoku(sudoku, textFields);
            if (sudoku.solve()) {
                updateTextFields(sudoku, textFields);
            } else {
                JOptionPane.showMessageDialog(null, "Unsolvable sudoku", "Could not solve",
                        JOptionPane.ERROR_MESSAGE);
            }
        });	
		
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(clearButton, BorderLayout.WEST);
		buttonPanel.add(solveButton, BorderLayout.EAST);

		container.add(gridPanel, BorderLayout.NORTH);
		container.add(buttonPanel, BorderLayout.SOUTH);
		
//		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	/** inserts the sudoku matrix into the textfields */
	public void updateTextFields(SudokuSolver s, JTextField[][] textfields) {
		int[][] sudokuGrid = s.getMatrix();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudokuGrid[i][j] != 0) {
					textfields[i][j].setText(Integer.toString(sudokuGrid[i][j]));
				} else {
					textfields[i][j].setText(" ");
				}
			}
		}
	}
	
	public void updateSudoku(SudokuSolver s, JTextField[][] textfields) {
		 for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < j; j++) {
	            	String text = textfields[i][j].getText();
	                if (isDigit(text)) {
	                	s.add(i, j, Integer.parseInt(text));
	                } else {
	                    s.remove(i, j);
	                }
	            }
	    }
	}
	
	private void createTextFields(JPanel gridPanel, JTextField[][] textfields) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField s = new JTextField();
				textfields[i][j] = s;
				gridPanel.add(textfields[i][j]);
			}
		}
	}
	
    private boolean isDigit(String text) {
        int text1;
        if (text.equals("")){
            return false;
        }
        try {
            text1 = Integer.parseInt(text);
            if (text1 < 10 && text1 > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Illegal input. Only numbers 1-9 are allowed. Try again");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Illegal input. Only numbers 1-9 are allowed. Try again");
            return false;
        }
    }
}
	
	//Integer.toString(sudokuGrid[i][j])
	