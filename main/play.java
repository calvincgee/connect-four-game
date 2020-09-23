import java.util.Scanner;
import GamePackage.CalvinAI;
import GamePackage.CFGame;
import GamePackage.CFPlayer;
import GamePackage.ConsoleCF;
import GamePackage.GUICF;
import GamePackage.RandomAI;

public class play{
	public static void main ( String [] args ) {
		CFPlayer ai1 = new CalvinAI ();
		CFPlayer ai2 = new RandomAI();
	
		GUICF g1 = new GUICF(ai1);
		//GUICF g2 = new GUICF(ai1, ai1);
		
		//ConsoleCF g = new ConsoleCF(ai2);
		//g.playOut();
		
		/*
		int n = 10000;
		int winCount = 0;
		for (int i=0; i<n; i++) {
			ConsoleCF game = new ConsoleCF (ai1 , ai2 );
			game.playOut();
			if (game.getWinner()== ai1.getName())
				winCount ++;
		}
		System.out.print(ai1.getName() + " wins ");
		System.out.print((( double ) winCount )/(( double ) n )*100 + "%");
		System.out.print(" of the time .");
		*/	
	}
}