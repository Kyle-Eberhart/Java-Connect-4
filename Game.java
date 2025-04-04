package connect4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Game {
	/*
	 * This main can be used to play a game of Connect 4.  
	 */
	public static void main(String[] args) {
		Board b = new Board();
		while (b.gameStatus() == 'U') {
			boolean legalMove = false;
			while (!legalMove) {
				StdOut.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
				StdOut.println(b);
				StdOut.println("Current player: " + b.currentPlayer());
				StdOut.println("Enter column number for next move: ");
				int col = StdIn.readInt();
				legalMove = b.play(col);
			}
		}
		StdOut.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		StdOut.println(b);
		StdOut.println("GAME OVER");
		char winner = b.gameStatus();
		if (winner == 'D')
			StdOut.println("It's a draw");
		else
			StdOut.println(winner + " WINS!!!");
	}
}
