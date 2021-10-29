package mcmillan.jeff.tictactoe.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mcmillan.jeff.tictactoe.State;

public class TicView {

	private TicController controller;

	private JFrame frame;
	
	private JPanel boardPanel;
	private TicCell[][] cells;
	
	private JPanel winPanel;
	private TicResultLabel xLabel, oLabel, tieLabel; 
	
	public TicView(TicController ctrl) {
		controller = ctrl;
		cells = new TicCell[3][3];
		
		frame = new JFrame("TicTacToe");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO: Add GUI for changing player names and showing current turn.
		
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
		
		winPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		xLabel = new TicResultLabel(State.X, this);
		oLabel = new TicResultLabel("Ties", this);
		tieLabel = new TicResultLabel(State.O, this);
		winPanel.add(xLabel);
		winPanel.add(oLabel);
		winPanel.add(tieLabel);
		
		frame.getContentPane().add(winPanel, BorderLayout.PAGE_END);
		
		frame.pack();
		frame.setVisible(true);
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

	public String getPlayerName(State s) {
		return s.getWinner(); // TODO: Add player naming.
	}

	public int getXWins() {
		return controller.getXWins();
	}

	public int getOWins() {
		return controller.getOWins();
	}

	public int getTies() {
		return controller.getTies();
	}

	public void refreshTotals() {
		xLabel.refreshLabel();
		oLabel.refreshLabel();
		tieLabel.refreshLabel();
	}
	
}
