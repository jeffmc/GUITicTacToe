package mcmillan.jeff.tictactoe.gui;

import mcmillan.jeff.tictactoe.*;

public class Main {

	private static Board board;
	@SuppressWarnings("unused")
	private static GUIController gui;
	
	public static void main(String[] args) {

		board = new Board();
		gui = new GUIController(board);
		return;
	}
}
