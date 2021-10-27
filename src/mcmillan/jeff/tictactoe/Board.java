package mcmillan.jeff.tictactoe;
public class Board {
	
	private static final int size = 3;
	
	private State[][] field;
	private static State turn;
	private int xWins = 0, oWins = 0, ties = 0;
	
	public Board() {
		newGame();
	}
	
	public boolean attemptMove(State player, int x, int y) {
		if (player != State.X && player != State.O) throw new IllegalArgumentException("Invalid Player!");
		if (x >= size || y >= size || x < 0 || y < 0) throw new IllegalArgumentException(x + ", " + y + " out of field bounds!");
		if (field[x][y] != State.EMPTY) {
			return false;
		} else {
			field[x][y] = player;
			moveExecuted(); // TODO: Make sure move is visible on GUI even if it wins the game!
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
			turn = State.X;
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
			System.out.println("Win totals:\n X: " + xWins + "\n O: " + oWins + "\n Ties:" + ties); // TODO: Add GUI for win totals.
			System.out.println("\nNew game!\n");
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
				field[x][y] = State.EMPTY;
			}
		}
		// TODO: Solution to refresh GUI on board clear?
	}
	
	enum XCoord {
		A("A",0),B("B",1),C("C",2);
		
		XCoord(String n, int i) {
			name = n;
			idx = i;
		}
		private int idx;
		private String name;
		public int getIndex() {
			return idx;
		}
		public String getName() {
			return name;
		}
		public static XCoord getFromChar(char c) {
			switch (c) {
			case 'a':
				return A;
			case 'b':
				return B;
			case 'c':
				return C;
			default:
				throw new IllegalArgumentException("'" + c + "' is not a valid XCoord!");
			}
		}
	}
	enum YCoord {
		One("1",0),Two("2",1),Three("3",2);
		
		YCoord(String n, int i) {
			name = n;
			idx = i;
		}
		private int idx;
		private String name;
		public int getIndex() {
			return idx;
		}
		public String getName() {
			return name;
		}
		public static YCoord getFromChar(char c) {
			switch (c) {
			case '1':
				return One;
			case '2':
				return Two;
			case '3':
				return Three;
			default:
				throw new IllegalArgumentException("'" + c + "' is not a valid YCoord!");
			}
		}
	}
	public State getState(int x, int y) {
		return field[x][y];
	}
	public static void newTurn() {
		if (turn == State.X) {
			turn = State.O;
		} else {
			turn = State.X;
		}
	}
}