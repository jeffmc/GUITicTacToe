package mcmillan.jeff.tictactoe.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mcmillan.jeff.tictactoe.State;

public class GUI {
	
	private JPanel boardPanel;
	private JFrame frame;
	
	public GUI() {
		frame = new JFrame("TICTACTOE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		boardPanel = new JPanel(new GridLayout(3, 3));
		
		boardPanel.setPreferredSize(new Dimension(500, 500));
		
		for (int y=0;y<3;y++) {
			for (int x=0;x<3;x++) {
				boardPanel.add(new TicCell(x,y, State.EMPTY), y*3+x);
			}
		}
		
		frame.getContentPane().add(boardPanel, BorderLayout.PAGE_START);

		//4. Size the frame.
		frame.pack();
		frame.setVisible(true);
	}
}
