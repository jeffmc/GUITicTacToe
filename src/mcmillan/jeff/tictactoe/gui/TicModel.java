package mcmillan.jeff.tictactoe.gui;

import mcmillan.jeff.tictactoe.Pair;
import mcmillan.jeff.tictactoe.State;

// TicModel manages the TicTacToe game state, it will trigger methods in the TicController to refresh TicView.
public class TicModel {
	
	// Board size, this is NOT codebase-wide and should be removed in the future,
	// or the rest of codebase should reference this value.
	private static final int size = 3; // TODO: Remove or better implement this constant.
	
	private TicController controller; // Reference to parent TicController.
	private State[][] field; // The state of 3x3 game grid.
	private State turn; // Current turn, X always start.
	private int xWins = 0, oWins = 0, ties = 0; // Win totals
	
	public TicModel(TicController ctrl) {
		controller = ctrl;
		field = new State[size][size];
		turn = State.X;
	}
	
	public void start() { // Called in TicController after both TicModel and TicView constructor.
		newGame();
	}
	
	public boolean attemptMove(State player, int x, int y) { // Attempt a move, return true if it was possible and made, or false if impossible.
		if (player != State.X && player != State.O) throw new IllegalArgumentException("Invalid Player!");
		if (x >= size || y >= size || x < 0 || y < 0) throw new IllegalArgumentException(x + ", " + y + " out of field bounds!");
		if (field[x][y] != State.EMPTY) {
			return false;
		} else {
			setState(player, x, y);
			moveExecuted();
			return true;
		}
	}
	
	public State getTurnPlayer() { // Returns the player that will make next move!
		return turn;
	}
	
	private void moveExecuted() { // A move has been successfully made in the field.
		Pair<State, Boolean> status = gameStatus();
		boolean gameComplete = status.snd;
		State winner = status.fst;
		if (gameComplete) {
			controller.showFinishedAlert(winner);
			newGame();
			switch (winner) {
			case X:
				xWins++;
				break;
			case O:
				oWins++;
				break;
			case EMPTY:
			default:
				ties++;
				break;
			}
			controller.refreshTotals();
		} else {
			newTurn();
		}
	}
	
	// return type <gameComplete, winner>
	public Pair<State, Boolean> gameStatus() { // Checks if the game is complete, returns winner or tie game.
		final State[] players = {State.X, State.O};
		for (State p : players) {
			if (field[1][1] == p) { // Diagonal check
				if (field[0][0] == p && field[2][2] == p) return new Pair<State, Boolean>(p, true);
				if (field[2][0] == p && field[0][2] == p) return new Pair<State, Boolean>(p, true);
			}
			for (int i=0;i<3;i++) { // Row and col checks
				if (field[i][0] == p && field[i][1] == p && field[i][2] == p) return new Pair<State, Boolean>(p, true);
				if (field[0][i] == p && field[1][i] == p && field[2][i] == p) return new Pair<State, Boolean>(p, true);
			}
		}
		for (int x=0;x<3;x++) { // Check for any empty spaces left
			for (int y=0;y<3;y++) {
				if (field[x][y] == State.EMPTY) return new Pair<State, Boolean>(State.EMPTY, false); // If empty left, game not complete.
			}
		}
		return new Pair<State, Boolean>(State.EMPTY, true); // Winner hasn't been found, board is full, game is complete.
	}
	
	public void newGame() { // Fills field with EMPTY state.
		field = new State[size][size];
		turn = State.X;
		for (int y=0;y<size;y++) {
			for (int x=0;x<size;x++) {
				setState(State.EMPTY, x, y);
			}
		}
	}
	
	public State getState(int x, int y) {
		return field[x][y];
	}

	public void setState(State s, int x, int y) { // Set state of cell and refresh it in the controller.
		field[x][y] = s;
		controller.refreshCell(x,y);
	}
	
	public void newTurn() { // Triggers next turn, refreshes turn in controller
		if (turn == State.X) {
			turn = State.O;
		} else {
			turn = State.X;
		}
		controller.refreshTurn();
	}
	
	public int getXWins() {
		return xWins;
	}

	public int getOWins() {
		return oWins;
	}

	public int getTies() {
		return ties;
	}
}