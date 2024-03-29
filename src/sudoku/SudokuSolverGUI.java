package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuSolverGUI {
	
	public SudokuSolverGUI(Sudoku s) {
		SwingUtilities.invokeLater(() -> createWindow(s, "Joaquim och Olles SudokuSolver", 560, 600));
	}
	
	public void createWindow(Sudoku sudoku, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		Container container = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		JPanel gridPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton solveButton = new JButton("Solve");
		JButton clearButton = new JButton("Clear");
		JTextField[][] textFields = new JTextField[9][9];
		
		container.setPreferredSize(new Dimension(540, 580));
		gridPanel.setLayout(new GridLayout(9, 9));
		gridPanel.setPreferredSize(new Dimension(540, 540));
		
		createTextFields(gridPanel, textFields);
		updateTextFields(sudoku, textFields);		
		
        solveButton.addActionListener(event -> {
        	if (updateSudoku(sudoku, textFields)) {
	        	sudoku.print2D();
	            if (sudoku.solve()) {
	                updateTextFields(sudoku, textFields);
	            } else {
	                JOptionPane.showMessageDialog(null, "Unsolvable sudoku", "Could not solve",
	                        JOptionPane.ERROR_MESSAGE);
	            } 
        	}
        	sudoku.print2D();
        });	
        
        clearButton.addActionListener(event -> {
        	sudoku.clear();
        	updateTextFields(sudoku, textFields);
        	sudoku.print2D();
        });

		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(clearButton, BorderLayout.WEST);
		buttonPanel.add(solveButton, BorderLayout.EAST);

		container.add(gridPanel, BorderLayout.NORTH);
		container.add(buttonPanel, BorderLayout.SOUTH);
		
//		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	/** Inserts the values from the sudoku matrix into the textfields (UI)
	 * @param s The sudoku class
	 * @param textfields The textField matrix */
	public void updateTextFields(SudokuSolver s, JTextField[][] textfields) {
		int[][] sudokuGrid = s.getMatrix();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudokuGrid[i][j] != 0) {
					textfields[i][j].setText(Integer.toString(sudokuGrid[i][j]));
				} else {
					textfields[i][j].setText("");
				}
			}
		}
	}
	
	
	/** Inserts the values from the textfields (UI) into the sudoku matrix
	 * @param s The sudoku class
	 * @param textfields The textField matrix 
	 * @return Returns true if no incorrect input is made. */
	public boolean updateSudoku(SudokuSolver s, JTextField[][] textfields) {
		Boolean retVal = true;
		 for (int i = 0; i < 9; i++) {
			 for (int j = 0; j < 9; j++) {
	        	String text = textfields[i][j].getText();
	        	if (isDigit(text)) {
	            	s.add(i, j, Integer.parseInt(text));
	            }
	        	else if (text.equals("")) {
	        		s.remove(i, j);
	        	}
	            else {
	            	s.remove(i, j);
	            	retVal = false;
	                JOptionPane.showMessageDialog(null, "Input at row: " + (i + 1) + ", col: " + (j + 1) + " is invalid");

	            }
	        }
	    }
		 return retVal;
	}
	
	/** Creates the textfields used for input
	 * @param gridPanel The gridPanel to insert textfields into
	 * @param textfields The textField matrix */
	private void createTextFields(JPanel gridPanel, JTextField[][] textfields) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField s = new JTextField();
				textfields[i][j] = s;
				textfields[i][j].setFont(new Font("SansSerif", Font.BOLD, 30));
				textfields[i][j].setHorizontalAlignment(JTextField.CENTER);
				gridPanel.add(textfields[i][j]);
				
				int checkRow = i / 3;
				int checkCol = j / 3;
				if (((checkRow == 0 || checkRow == 2) && (checkCol == 0 || checkCol == 2)) || (checkRow == 1 && checkCol == 1)) {
					textfields[i][j].setBackground(Color.gray);
				}
			}
		}
	}
	
	/** Checks if the input is a digit
	 * @param text The input to check
	 * @return Returns true if text is convertable into single digit, else false. */
    private boolean isDigit(String text) {
        int text1;
        if (text.equals("")){
            return false;
        }
        try {
            text1 = Integer.parseInt(text);
            return (text1 < 10 && text1 > 0);
        } catch (Exception e) {
            return false;
        }
    }
}
	