package mcmillan.jeff.tictactoe.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mcmillan.jeff.tictactoe.State;

public class TicView {

	private TicController controller;
	
	private JPanel boardPanel;
	private JFrame frame;
	private TicCell[][] cells;
	
	
	public TicView() {
		cells = new TicCell[3][3];
		
		frame = new JFrame("TicTacToe");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO: Add GUI for win totals.
		// TODO: Add GUI for changing player names.
		
		boardPanel = new JPanel(new GridLayout(3, 3));
		boardPanel.setPreferredSize(new Dimension(500, 500));
		
		for (int y=0;y<3;y++) {
			for (int x=0;x<3;x++) {
				TicCell cell = new TicCell(State.EMPTY,x,y,this);
				cells[x][y] = cell;
				boardPanel.add(cell, y*3+x);
			}
		}
		
		frame.getContentPane().add(boardPanel, BorderLayout.PAGE_START);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setController(TicController ctrl) {
		controller = ctrl;
	}
	
	public boolean cellClicked(TicCell cell) {
		return controller.attemptMove(cell.getCol(), cell.getRow());
	}
	
	public State getState(int x, int y) {
		return controller.getState(x, y);
	}

	public void refreshCell(int x, int y) {
		cells[x][y].setState(controller.getState(x, y));
	}
	
}
