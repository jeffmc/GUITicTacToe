package mcmillan.jeff.tictactoe.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import mcmillan.jeff.tictactoe.State;

@SuppressWarnings("serial")
public class TicCell extends JButton {
	
	public static final Font defaultFont = new JLabel().getFont().deriveFont(48f).deriveFont(Font.BOLD);
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
	
	private State state;
	private int col, row;
	private TicView view;
	public TicCell(State s, int c, int r, TicView v) {
		super(getCharFromState(s)+"");
		this.setFont(defaultFont);
		this.addActionListener(onClick);
		state = s;
		col = c;
		row = r;
		view = v;
	}
	
	public void clicked() {
		view.cellClicked(this);
	}
	
	public State getState() {
		return state;
	}

	public void setState(State s) {
		state = s;
		switch (s) {
		case X:
		case O:
			this.setEnabled(false);
		case EMPTY:
		default:
			this.setEnabled(true);
		}
		this.setText(getCharFromState(s)+"");
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
