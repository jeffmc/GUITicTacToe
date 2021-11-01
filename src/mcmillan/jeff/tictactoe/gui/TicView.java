package mcmillan.jeff.tictactoe.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import layout.SpringUtilities;
import mcmillan.jeff.tictactoe.State;

// TicView manages all elements of the GUI, it's methods are meant to be called by TicController.
public class TicView {

	private TicController controller;

	private JFrame frame;
	
	private JPanel boardPanel;
	private TicCell[][] cells;
	
	private JPanel winPanel;
	private TicResultLabel xLabel, oLabel, tieLabel; 
	
	private JPanel miscPanel;
	private JLabel turnLabel;
	private JTextField xNickTextField; 
	private JTextField oNickTextField; 
	private JButton changeNameBtn;
	
	public TicView(TicController ctrl) {
		controller = ctrl;
		cells = new TicCell[3][3];
		
		frame = new JFrame("TicTacToe");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		boardPanel = new JPanel(new GridLayout(3, 3));
		boardPanel.setPreferredSize(new Dimension(500, 500));
		
		for (int y=0;y<3;y++) {
			for (int x=0;x<3;x++) {
				TicCell cell = new TicCell(State.EMPTY,x,y,this);
				cells[x][y] = cell;
				boardPanel.add(cell, y*3+x);
			}
		}
		
		frame.getContentPane().add(boardPanel);
		
		winPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		xLabel = new TicResultLabel(State.X, this);
		oLabel = new TicResultLabel("Ties", this);
		tieLabel = new TicResultLabel(State.O, this);
		winPanel.add(xLabel);
		winPanel.add(oLabel);
		winPanel.add(tieLabel);
		
		frame.getContentPane().add(winPanel);
		
		miscPanel = new JPanel(new SpringLayout());
		turnLabel = new JLabel();
		changeNameBtn = new JButton("Change name");
		
		changeNameBtn.addActionListener(new ChangeNameListener(this));
		
		JLabel xNickLabel = new JLabel("X Nickname:");
		xNickTextField = new JTextField("X", 10);
		xNickLabel.setLabelFor(xNickTextField);
		JLabel oNickLabel = new JLabel("O Nickname:");
		oNickTextField = new JTextField("O", 10);
		oNickLabel.setLabelFor(oNickTextField);
		refreshTurn();
		miscPanel.add(turnLabel);
		miscPanel.add(changeNameBtn);
		miscPanel.add(xNickLabel);
		miscPanel.add(xNickTextField);
		miscPanel.add(oNickLabel);
		miscPanel.add(oNickTextField);
		
		SpringUtilities.makeCompactGrid(miscPanel, 3, 2, 5, 5, 5, 5);
		
		frame.getContentPane().add(miscPanel);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public boolean cellClicked(TicCell cell) {
		return controller.attemptMove(cell.getCol(), cell.getRow());
	}
	
	public State getState(int x, int y) {
		return controller.getState(x, y);
	}

	public void refreshField() {
		for (int y=0;y<3;y++) {
			for (int x=0;x<3;x++) {
				cells[x][y].setState(controller.getState(x, y));
			}
		}
	}
	
	public void refreshCell(int x, int y) {
		cells[x][y].setState(controller.getState(x, y));
	}

	public String getPlayerName(State s) {
		switch (s) {
		case X:
			return controller.getXNick();
		case O:
			return controller.getONick();
		default:
			return s.getWinner();
		}
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
		xLabel.refresh();
		oLabel.refresh();
		tieLabel.refresh();
	}
	
	public void refreshTurn() {
		State p = controller.getTurn();
		turnLabel.setText(getPlayerName(p) + "'s turn");
	}
	
	class ChangeNameListener implements ActionListener {
		private TicView view;
		public ChangeNameListener(TicView v) {
			view = v;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			view.refreshNames();
		}
		
	}

	public void refreshNames() {
		controller.setXNick(xNickTextField.getText());
		controller.setONick(oNickTextField.getText());
		refreshTurn();
		refreshTotals();
		refreshField();
	}

	public String getNickname(State s) {
		switch (s) {
		case X:
			return controller.getXNick();
		case O:
			return controller.getONick();
		case EMPTY:
		default:
			return "";
				
		}
	}
	
	public void showAlert(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}
}
