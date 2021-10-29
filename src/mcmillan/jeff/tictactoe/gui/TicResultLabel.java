package mcmillan.jeff.tictactoe.gui;

import javax.swing.JLabel;

import mcmillan.jeff.tictactoe.State;

@SuppressWarnings("serial")
public class TicResultLabel extends JLabel {
	
	private TicView view;
	private String prefix;
	private State player;
	
	public TicResultLabel(String custom, TicView v) {
		super();
		view = v;
		player = State.EMPTY;
		prefix = TicResultLabel.makePrefix(custom);
		refreshLabel();
	}
	public TicResultLabel(State p, TicView v) {
		this(v.getPlayerName(p), v);
		player = p;
	}
	
	public void refreshLabel() {
		int total;
		switch(player) {
		case X:
			total = view.getXWins();	
			break;
		case O:
			total = view.getOWins();	
			break;
		case EMPTY:
			total = view.getTies();	
			break;
		default:
			total = 0;	
			break;
		}
		this.setText(prefix + total);
	}
	
	public void refreshName() {
		prefix = makePrefix(view.getPlayerName(player));
		refreshLabel();
	}
	

	private static String makePrefix(String custom) {
		return custom + ": ";
	}
}
