package mcmillan.jeff.tictactoe.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import mcmillan.jeff.tictactoe.State;

@SuppressWarnings("serial")
public class TicButton extends JButton {
	
	public static final Font defaultFont = new JLabel().getFont().deriveFont(48f).deriveFont(Font.BOLD);
	
	private State state;
	public TicButton(int x, int y, State s) {
		super(x + ", " + y);
		this.setFont(defaultFont);
		state = s;
	}
	
	public State getState() {
		return state;
	}

	public void getState(State s) {
		state = s;
		this.setText(getCharFromState(s)+"");
	}	
	private char getCharFromState(State s) { // Moved from Board class
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
}
