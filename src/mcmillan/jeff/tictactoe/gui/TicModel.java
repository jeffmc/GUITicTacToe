package mcmillan.jeff.tictactoe.gui;

import mcmillan.jeff.tictactoe.Pair;
import mcmillan.jeff.tictactoe.State;

public class TicModel {
	
	private static final int size = 3;
	
	private TicController controller;
	private State[][] field;
	private State turn;
	private int xWins = 0, oWins = 0, ties = 0;
	
	public TicModel(TicController ctrl) {
		controller = ctrl;
	}
	
	public void start() {
		newGame();
	}
	
	
	public boolean attemptMove(State player, int x, int y) {
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
	
	private void moveExecuted() {
		Pair<State, Boolean> status = gameStatus();
		boolean gameComplete = status.snd;
		State winner = status.fst;
		if (gameComplete) {
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
	

	public Pair<State, Boolean> gameStatus() { // return type <gameComplete, winner>
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

	public void setState(State s, int x, int y) {
		field[x][y] = s;
		controller.refreshCell(x,y);
	}
	public void newTurn() {
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