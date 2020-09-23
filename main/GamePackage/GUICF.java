package GamePackage;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICF extends CFGame {
	CFPlayer player1;
	CFPlayer player2;
	private GameBoard this_board;
	JFrame frame;
	Container pane;
	
	private void displayDraw(){
		JLabel draw = new JLabel("The game is a draw");
		pane.add(draw, BorderLayout.SOUTH);
	}
	
	//when the game is over, display a message in the bottom of the frame about who won
	private void displayWinner(CFPlayer player){
		JLabel winner = new JLabel(player.getName() + " has won the game.");
		pane.add(winner, BorderLayout.SOUTH);
	}
	
	//create a GUI game between a human and a computer
	public GUICF(CFPlayer ai){
		
		this_board = new GameBoard();
		JPanel top = new JPanel(new BorderLayout());
		top.setLayout(new GridLayout(1, 7));
		JButton[] arrows = new JButton[7];
		
		class ActionClass implements ActionListener {
			public void actionPerformed(ActionEvent e){
				//play the column corresponding the clicked button
				for (int i = 0; i < 7; i++){
					if (e.getSource() == arrows[i]){
						if (GUICF.this.canBePlayed(i+1)){
							playGUI(i+1);
							//check if the game is over
							if (GUICF.this.isGameOver()){
								if (GUICF.this.isDraw()){
									GUICF.this.displayDraw();
								} else {
									HumanPlayer human = new HumanPlayer();
									GUICF.this.displayWinner(human);
								}
							}
							//ai plays it's next move
							playGUI(ai.nextMove(GUICF.this));
							//check if the game is over
							if (GUICF.this.isGameOver()){
								if (GUICF.this.isDraw()){
									GUICF.this.displayDraw();
								} else {
									GUICF.this.displayWinner(ai);
								}
							}
						}
					}
				}
			}
		}
		
		//add arrows to the top of the pane
		pane.add(top, BorderLayout.NORTH);
		for (int i = 0; i < 7; i++){
			arrows[i] = new JButton("\u2193");
			arrows[i].addActionListener(new ActionClass());
			top.add(arrows[i]);
		}
		Random rand = new Random();
		//randomly decide if the ai goes first
		int assign = rand.nextInt(2);
		if (assign == 0){
			playGUI(ai.nextMove(GUICF.this));
		}
	}
	
	
	//create a GUI game between two ai's
	public GUICF(CFPlayer ai1, CFPlayer ai2){
		class ActionClass implements ActionListener {
			public void actionPerformed(ActionEvent e){
				//if it's red turn, player1 plays
				if (GUICF.this.isRedTurn()){
					playGUI(player1.nextMove(GUICF.this));
					//check if the game is over
					if (GUICF.this.isGameOver()){
						if (GUICF.this.isDraw()){
							GUICF.this.displayDraw();
						} else {
							GUICF.this.displayWinner(player1);
						}
					}
				} else {//otherwise, player2 plays
					playGUI(player2.nextMove(GUICF.this));
					//check if the game is over
					if (GUICF.this.isGameOver()){
						if (GUICF.this.isDraw()){
							GUICF.this.displayDraw();
						} else {
							GUICF.this.displayWinner(player2);
						}
					}
				}
			}
		}
		this_board = new GameBoard();
		JPanel top = new JPanel(new BorderLayout());
		//add play button to the top of the pane
		JButton play = new JButton("Play");
		play.addActionListener(new ActionClass());
		top.add(play);
		pane.add(top, BorderLayout.NORTH);
		Random rand = new Random();
		//randomly assign which ai goes first
		int assign = rand.nextInt(2);
		if (assign == 0){
			player1 = ai1;
			player2 = ai2;
		} else {
			player1 = ai2;
			player2 = ai1;
		}
	}
	
	//change the GUI board for a given move
	private boolean playGUI(int c){
		if (this.canBePlayed(c)){
			play(c);
			this_board.paint(lastPlay[0], lastPlay[1], lastPlay[2]);
			return true;
		} else {
			return false;
		}
	}
	
	//Human Player class
	private class HumanPlayer implements CFPlayer{
		public int nextMove(CFGame g){
			return 0;
		}
		public String getName(){
			return "Human Player";
		}
	}
	
	private class GameBoard extends javax.swing.JPanel {
		JFrame frame;
		JPanel[] boxes = new JPanel[42];
		//GameBoard constructor
		//creates GUI for a 6x7 board
		private GameBoard(){
			frame = new JFrame("CF Game");
			frame.setSize(500,500);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			pane = frame.getContentPane();
			JPanel board = new JPanel(new BorderLayout());
			pane.add(board, BorderLayout.CENTER);
			board.setLayout(new GridLayout(6,7));
			for (int i = 0; i < 42; i++){
				boxes[i] = new JPanel();
				boxes[i].setBorder(BorderFactory.createLineBorder(Color.black));
				board.add(boxes[i]);
			}
			
			frame.setVisible(true);
		}
		
		//set the given grid spot to the given color
		private void paint (int x, int y, int color){
			int num = 7 * (5-y) + x;
			if (color == 1){
				boxes[num].setBackground(Color.RED);
			} else if (color == -1) {
				boxes[num].setBackground(Color.BLACK);
			}
			frame.setVisible(true);
		}
	}
	
}