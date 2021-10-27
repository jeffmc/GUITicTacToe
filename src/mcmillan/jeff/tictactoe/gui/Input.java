package mcmillan.jeff.tictactoe.gui;

import java.util.Scanner;

public class Input {
	
	private Scanner console;
	
	public Input() {

		console = new Scanner(System.in);
	}
	
	public static char validateChar(final char[] o, char c) throws IllegalArgumentException {
		String txt = "";
		if (o.length <= 0) throw new NullPointerException("Char array is length <= 0!");
		else if (o.length == 1) txt += "'" + o[0] + "'";
		else if (o.length == 2) txt += "'" + o[0] + "' or '" + o[1] + "'";
		else {
			for (int i = 0; i < o.length-1; i++) {
				txt += "'" + o[i] + "', ";
			}
			txt += "or '" + o[o.length-1] + "'";
		};
		for (char t : o) {
			if (t == c) return t;
		}
		throw new IllegalArgumentException(txt);
	}
	
	public char[] readTwoChars(String prompt, final char[] a, final char[] b) {
		while (true) {
			try {
				System.out.print(prompt);
				String raw = this.nextLine().trim().toLowerCase();
				if (raw.length() != 2) throw new IllegalArgumentException("Not 2 characters!");
				char x = raw.charAt(0);
				try {
					validateChar(a,x);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException("1st character is not " + e.getMessage() + ".");
				}
				char y = raw.charAt(1);
				try {
					validateChar(b,y);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException("2st character is not " + e.getMessage() + ".");
				}
				char[] res = {x,y};
				return res;
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public String nextLine() {
		return console.nextLine();
	}
	
}