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
	}
}
