package mcmillan.jeff.tictactoe.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import mcmillan.jeff.tictactoe.State;

// TicCell represents a single GUI button in the 3x3 TicTacToe grid.
@SuppressWarnings("serial")
public class TicCell extends JButton {
	
	public static final Font defaultFont = new JLabel().getFont().deriveFont(32f).deriveFont(Font.BOLD);
	public static ActionListener onClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().getClass() != TicCell.class) {
					System.err.println("TicCell ActionListener triggered by non-TicCell");
				} else {
					TicCell cell = (TicCell) e.getSource();
					cell.clicked();
				}
			}
		};
	
	private State state; // Which player has clicked this cell
	private int col, row; // Cell location
	private TicView view; // Parent TicView
	public TicCell(State s, int c, int r, TicView v) {
		super(getCharFromState(s)+"");
		this.setFont(defaultFont);
		this.addActionListener(onClick);
		state = s;
		col = c;
		row = r;
		view = v;
	}
	
	public void clicked() { // Called when this cell has been clicked by user.
		view.cellClicked(this);
	}
	
	public State getState() {
		return state;
	}

	public void setState(State s) {
		state = s;
		this.setEnabled(!(state==State.X||state==State.O));
		this.setText(view.getNickname(s)+"");
	}	
	
	public void refresh() {
		setState(state);
	}
	
	private static char getCharFromState(State s) { // Moved from Board class
		switch (s) {
		case EMPTY:
			return ' ';
		case X:
			return 'X';
		case O:
			return 'O';
		default:
			throw new IllegalArgumentException("Invalid state passed to stateChar()! " + s.toString());	
		}
	}
	
	public int getCol() {
		return this.col;
	}
	
	public int getRow() {
		return this.row;
	}
	
}
