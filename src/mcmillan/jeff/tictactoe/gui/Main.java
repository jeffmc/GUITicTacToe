package mcmillan.jeff.tictactoe.gui;

// Jeff McMillan
// October 29th, 2021
// An implementation of the TicTacToe game with a graphical user interface.

public class Main {
	
	public static TicController ctrl;
	
	public static void main(String[] args) {
		ctrl = new TicController();
		ctrl.start();
	}
}
