package Connect4;

public class Board {
	
	private char[][] board;
	private char playerOne = 'X';
	private char playerTwo = 'O';
	private char currentPlayer = playerOne;
	
	// Function changes the player currently playing the game when called.
	public void togglePlayer() {
	    // Switch between playerOne (X) and playerTwo (O)
	    if (currentPlayer == playerOne) {
	        currentPlayer = playerTwo;}
	    else {
	        currentPlayer = playerOne;
	    }
	}

	// Ensures player move is valid.
	private boolean checkDirection(int row, int col, int rowDir, int colDir, char player) {
	    for (int i = 0; i < 4; i++) {
	        int newRow = row + i * rowDir;
	        int newCol = col + i * colDir;
	        if (newRow < 0 || newRow >= 6 || newCol < 0 || newCol >= 7 || board[newRow][newCol] != player) {
	            return false;
	        }
	    }
	    return true;
	}
	
	/**
	 * Constructs a new empty connect 4 game board with player X being the first player
	 * and player 'O' being the second player.
	 * Uses 2D array to create board, initialize playerOne (X) , then playerTwo (O). 
	 */
	public Board() {
		board = new char [6][7];
		for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' '; // Empty cell
            }
		}
	}

	/**
	 * Gets the current player (either 'X' or 'O')
	 * 
	 * @return the current player
	 */
	public char currentPlayer() {
		return currentPlayer;
	}

	/**
	 * The current player makes a move in a given column if it is a valid move.
	 * Throws an exception if the game is already over.
	 * 
	 * @param column the column in which to make a move.  For the move to be valid,
	 * the column value must an {@code int} between 1 and 7 inclusive, and
	 * there must have been fewer than 6 moves already made in the given column.
	 * @return {@code true} if the move is valid and false if it is not valid.
	 * @throws RuntimeException if the game is already over.
	 */
	public boolean play(int column) {
		if (gameStatus() != 'U') {
	        throw new RuntimeException("Game is already over");
	    }
		 if (column < 1 || column > 7) {
		        return false; // else move is invalid.
		    }
		    column--; // Adjust for 0-based index

		    // Find the lowest available row in the column
		    for (int row = 5; row >= 0; row--) {
		        if (board[row][column] == ' ') {
		            board[row][column] = currentPlayer;
		            togglePlayer(); // Switch turns
		            return true;
		        }
		    }

		    // else, column is full
		    return false;
	}
	
	/**
	 * Determine the status of the game.
	 * 
	 * @return {@code 'X'} or {@code 'O'} if either player has won, {@code 'D'} if
	 * the game is a draw, and {@code 'U'} if the game is still undecided.
	 */
	public char gameStatus() {
		 // Check for a winner (either 'X' or 'O')
	    for (int row = 0; row < 6; row++) {
	        for (int col = 0; col < 7; col++) {
	            char player = board[row][col];
	            if (player == ' ') continue; // Skip empty cells
	            
	            // Check horizontal, vertical, and two diagonal directions
	            if (checkDirection(row, col, 0, 1, player) || // Horizontal
	                checkDirection(row, col, 1, 0, player) || // Vertical
	                checkDirection(row, col, 1, 1, player) || // Diagonal down-right
	                checkDirection(row, col, 1, -1, player)) { // Diagonal down-left
	                return player;
	            }
	        }
	    }
	    for (int col = 0; col < 7; col++) {  // Check for a draw (if the board is full)
	        if (board[0][col] == ' ') {
	            return 'U'; // Game is still undecided
	        }
	    }
	    return 'D'; // It's a draw
	}

	/**
	 * Constructs a string that depicts the sate of the game.
	 * 
	 * @return a string depicting the game board
	 */
	public String toString() {
		String result = "";

	    // Build the board row by row
	    for (int row = 0; row < 6; row++) {
	        result += "|";
	        for (int col = 0; col < 7; col++) {
	            result += (board[row][col] == ' ' ? ' ' : board[row][col]) + "|";
	        }
	        result += "\n";
	    }

	    // Add the dashed line separator and column numbers
	    result += "---------------\n";  // 15 dashes for the 7 columns
	    result += " 1 2 3 4 5 6 7 \n";  // Column numbers

	    return result;
	}
}
