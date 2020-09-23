package GamePackage;

import java.util.Random;

public class CalvinAI implements CFPlayer{
	
	//return the move to be made by the Calvin AI
	public int nextMove(CFGame g){
		//checks if there is a winnable move to play
		if (g.winnableMove() != 0){
			//if it can be played, play it
			if (g.canBePlayed(g.winnableMove())){
				return g.winnableMove();
			}
		}
		//checks if there is a move that can prevent a win
		if (g.preventableWin() != 0){
			//if it can be played, play it
			if (g.canBePlayed(g.preventableWin())){
				return g.preventableWin();
			}
		}
		//if there is no winnable move or a preventable move, play a random move
		Random rand = new Random();
		//create a random move from 1-7
		int move = rand.nextInt(7)+1;
		//check if the move can be played
		while(!g.canBePlayed(move)){
			move = rand.nextInt(7)+1;
		}
		return move;
	}
	
	//returns the name of Calvin AI
	public String getName(){
		return "Calvin's AI";
	}
}