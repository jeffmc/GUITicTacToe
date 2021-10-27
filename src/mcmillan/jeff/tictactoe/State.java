package mcmillan.jeff.tictactoe;

public enum State {
	EMPTY("empty"),X("X"),O("O");
    private String name;
	State(String s) {
		name = s;
	}
	@Override
	public String toString() {
		return name;
	}
	
	public String getWinner() {
		switch (this) {
		case EMPTY:
			return "Nobody";
		case X:
		case O:
			return this.toString();
		default:
			throw new IllegalStateException("State should be something!");
		}
			
	}
}