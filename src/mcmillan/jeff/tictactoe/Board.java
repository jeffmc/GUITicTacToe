package mcmillan.jeff.tictactoe;
public class Board {
	
	private static final int size = 3;
	
	private State[][] field;
	
	public Board() {
		field = new State[size][size];
		clear();
	}
	
	public boolean attemptMove(State player, char col, char row) {
		if (player != State.X && player != State.O) throw new IllegalArgumentException("Invalid Player!");
		int x = XCoord.getFromChar(col).getIndex();
		int y = YCoord.getFromChar(row).getIndex();
		if (field[x][y] != State.EMPTY) {
			return false;
		} else {
			field[x][y] = player;
			return true;
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
	
	public void clear() { // Fills field with EMPTY state.
		for (int y=0;y<size;y++) {
			for (int x=0;x<size;x++) {
				field[x][y] = State.EMPTY;
			}
		}
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
}