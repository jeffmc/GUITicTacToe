package mcmillan.jeff.tictactoe.gui;

import mcmillan.jeff.tictactoe.State;

public class TicController {
	
	private TicModel model;
	private TicView view;

	public TicController() {
		model = new TicModel(this);
		view = new TicView(this);
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

	public int getXWins() {
		return model.getXWins();
	}

	public int getOWins() {
		return model.getOWins();
	}

	public int getTies() {
		return model.getTies();
	}

	public void refreshTotals() {
		view.refreshTotals();
	}
	
	public void refreshTurn() {
		view.refreshTurn();
	}

	public State getTurn() {
		return model.getTurnPlayer();
	}
}
