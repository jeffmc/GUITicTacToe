package mcmillan.jeff.tictactoe.gui;

import mcmillan.jeff.tictactoe.State;
import mcmillan.jeff.tictactoe.TicModel;

public class TicController {
	
	private TicModel model;
	private TicView view;
	
	public TicController(TicModel m, TicView v) {
		model = m;
		model.setController(this);
		view = v;
		view.setController(this);
	}

	public TicController() {
		this(new TicModel(), new TicView());
	}
	
	public State getState(int x, int y) {
		return model.getState(x,y);
	}
	
	public boolean attemptMove(int x, int y) {
		return model.attemptMove(model.getTurnPlayer(), x, y);
	}

	public void start() {
		model.start();
//		view.start();
	}

	public void refreshCell(int x, int y) {
		view.refreshCell(x,y);
	}
}
