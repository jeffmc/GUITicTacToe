package mcmillan.jeff.tictactoe.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mcmillan.jeff.tictactoe.Board;
import mcmillan.jeff.tictactoe.State;

public class GUIController {
	
	private JPanel boardPanel;
	private JFrame frame;
	
	private Board board;
	
	public GUIController(Board b) {
		board = b;
		
		frame = new JFrame("TicTacToe");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		boardPanel = new JPanel(new GridLayout(3, 3));
		boardPanel.setPreferredSize(new Dimension(500, 500));
		
		for (int y=0;y<3;y++) {
			for (int x=0;x<3;x++) {
				boardPanel.add(new TicCell(State.EMPTY,x,y,this), y*3+x);
			}
		}
		
		frame.getContentPane().add(boardPanel, BorderLayout.PAGE_START);

		//4. Size the frame.
		frame.pack();
		frame.setVisible(true);
	}
	
	public State getState(int x, int y) {
		return board.getState(x,y);
	}
	
	public boolean cellClicked(TicCell cell) {
		return board.attemptMove(board.getTurnPlayer(), cell.getCol(), cell.getRow()); // TODO: Fix player argument to reflect possessor of current move.
	}
	
}
