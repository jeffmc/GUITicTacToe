package mcmillan.jeff.tictactoe.gui;

import mcmillan.jeff.tictactoe.State;

public class TicController {
	
	private TicModel model;
	private TicView view;
	
	private String xNickname, oNickname; // TODO: Possibly move these fields into TicModel

	public TicController() {
		model = new TicModel(this);
		view = new TicView(this);
		xNickname = null;
		oNickname = null;
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
	
	public String getXNick() {
		return xNickname;
	}
	
	public String getONick() {
		return oNickname;
	}
}
