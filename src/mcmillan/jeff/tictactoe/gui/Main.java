package mcmillan.jeff.tictactoe.gui;

import mcmillan.jeff.tictactoe.*;

public class Main {

	private static Input input;
	private static Board board;
	private static GUI gui;
	
	private static final char[] cols = {'a', 'b', 'c'};
	private static final char[] rows = {'1', '2', '3'};
	private static State turn;
	
	public static void main(String[] args) {
		
		gui = new GUI();
		input = new Input();
		board = new Board();
		
		return;
//		
//		
//		int xWins = 0, oWins = 0, ties = 0;
//		
//		System.out.println("When prompted with \"Coordinates:\", make a move by specifying column and row you would like to fill.\n"
//				+ "Examples: \"a1\", \"c3\", \"b2\"\n");
//		
//		while (true) {
//			turn = State.X;
//			board.clear();
//			boolean gameComplete = false;
//			State winner = State.EMPTY;
//			while (!gameComplete) {
//				board.print();
//				boolean validMove = false;
//				while (!validMove) {
//					System.out.println(turn.toString() + "'s turn!\n");
//					char[] coord = input.readTwoChars("Coordinates: ", cols, rows);
//					validMove = board.attemptMove(turn, coord[0], coord[1]);
//					System.out.println(validMove ? "\n" : coord[0] +""+ coord[1] + " not empty!");
//				}
//				Pair<State, Boolean> status = board.gameStatus();
//				gameComplete = status.snd;
//				winner = status.fst;
//				newTurn();
//			}
//			board.print();
//			System.out.println("GAME OVER! " + winner.getWinner() + " wins!");
//			switch (winner) {
//			case X:
//				xWins++;
//				break;
//			case O:
//				oWins++;
//				break;
//			case EMPTY:
//			default:
//				ties++;
//				break;
//			}
//			System.out.println("Win totals:\n X: " + xWins + "\n O: " + oWins + "\n Ties:" + ties);
//			System.out.println("\nNew game!\n");
//		}
	}
	
	// TODO: move all turn logic into board class
	public static void newTurn() {
		if (turn == State.X) {
			turn = State.O;
		} else {
			turn = State.X;
		}
	}

}
