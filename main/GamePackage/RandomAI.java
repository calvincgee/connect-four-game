package GamePackage;

import java.util.Random;

public class RandomAI implements CFPlayer{
	//returns the move to be played by the random AI
	public int nextMove(CFGame g){
		Random rand = new Random();
		//create a random move from 1-7
		int move = rand.nextInt(7)+1;
		//check if the move can be played
		while(!g.canBePlayed(move)){
			move = rand.nextInt(7)+1;
		}
		return move;
	}
	
	//returns the name of the random AI
	public String getName(){
		return "Random Player";
	}
}