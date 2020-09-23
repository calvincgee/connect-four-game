package GamePackage;

import java.util.Random;
import java.util.Scanner;

public class ConsoleCF extends CFGame {
	CFPlayer player1;
	CFPlayer player2;
	
	//creates a console game for a human and ai
	public ConsoleCF (CFPlayer ai){
		Random rand = new Random();
		//randomly assign who to go first
		int assign = rand.nextInt(2);
		if (assign == 0){
			player1 = new HumanPlayer();
			player2 = ai;
		} else {
			player1 = ai;
			player2 = new HumanPlayer();
		}
	}
	
	//creates a console game for two ai's
	public ConsoleCF(CFPlayer ai1, CFPlayer ai2){
		Random rand = new Random();
		//randomly assign who to go first
		int assign = rand.nextInt(2);
		if (assign == 0){
			player1 = ai1;
			player2 = ai2;
		} else {
			player1 = ai2;
			player2 = ai1;
		}
	}
	
	
	//plays out a game until it is over
	public void playOut(){
		while(!this.isGameOver()){
			if (this.isRedTurn()){
				play(player1.nextMove(this));
				if (this.isGameOver()){
					break;
				}
			} else {
				play(player2.nextMove(this));
			}
		}
		/*
		if (this.winner() == 0){
			System.out.println("The game ended in a " + this.getWinner() + ".");
		} else {
			System.out.println("The game ended and " + this.getWinner() + " was the winner.");
		}
		*/
	}
	
	//returns the name of the winner of the game
	public String getWinner(){
		if (this.winner() == 1){
			return player1.getName();
		} else if (this.winner() == -1){
			return player2.getName();
		} else {
			return "draw";
		}
	}
	
	//Human Player class to allow human to play the game
	private class HumanPlayer implements CFPlayer{
		//next move is scanned in
		public int nextMove(CFGame g){
			Scanner scan = new Scanner(System.in);
			g.printBoard();
			System.out.println("Please enter your next move (int)");
			int move = scan.nextInt();
			//checks if move can be played
			while(!g.canBePlayed(move)){
				System.out.println("Not a valid move");
				move = scan.nextInt();
			}
			return move;
		}
		//returns the human player's name
		public String getName(){
			return "Human Player";
		}
	}
	
	
}