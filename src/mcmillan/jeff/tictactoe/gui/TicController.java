package mcmillan.jeff.tictactoe.gui;

import mcmillan.jeff.tictactoe.State;

// Manages the entire application, game state and GUI elements.
public class TicController {
	
	private TicModel model;
	private TicView view;
	
	// This is bad, no data related to gameplay should be stored in controller.
	private String xNickname = "X", oNickname = "O"; // TODO: Possibly move these fields into TicModel

	public TicController() { // Main class that exchanges information between game and GUI
		model = new TicModel(this); // Model manages game state
		view = new TicView(this); // View manages GUI
	}
	
	public void start() { // Called after constructor in Main class.
		model.start();
	}
	
	public State getState(int x, int y) {
		return model.getState(x,y);
	}
	
	public boolean attemptMove(int x, int y) {
		return model.attemptMove(model.getTurnPlayer(), x, y);
	}
	
	public void refreshCell(int x, int y) { // GUI refresh an individual cell
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

	public void refreshTotals() { // GUI refresh for win totals.
		view.refreshTotals();
	}
	
	public void refreshTurn() { // GUI refresh for turn label.
		view.refreshTurn();
	}

	public State getTurn() { // Returns which player is next to make a turn
		return model.getTurnPlayer();
	}
	
	public String getXNick() {
		return xNickname!=""?xNickname:State.X.getWinner();
	}
	
	public String getONick() {
		return oNickname!=""?oNickname:State.O.getWinner();
	}

	public void setXNick(String nick) { // Trims whitespace and sets nickname (does not refresh GUI)
		xNickname = nick.trim();
	}
	
	public void setONick(String nick) { // Trims whitespace and sets nickname (does not refresh GUI)
		oNickname = nick.trim();
	}
	
	public void showFinishedAlert(State winner) { // Show dialog alerting players of the completed game.
		switch (winner) {
		case X:
			view.showAlert(getXNick() + " won the game!");
			break;
		case O:
			view.showAlert(getONick() + " won the game!");
			break;
		case EMPTY:
		default:
			view.showAlert("Tie game!");
			break;
		}
	}
}
