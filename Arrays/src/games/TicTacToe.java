package games;

/** This is a two-player TicTacToe game that handles none
 *  of the strategy elements.
 *  
 *  TODO: Add artificial intelligence and GUI
 *  
 *  @author HumanD1ffM4chin3
 *  @version 1.0 */
public class TicTacToe {
    public static final int X = 1, O = -1;  // Players
    public static final int EMPTY = 0;      // Empty Square
    private int board[][] = new int [3][3]; // game board
    private int player;                     // current player
    
    /** Constructor */
    public TicTacToe() {
    	clearBoard();
    }
    
    /** Clears the board for a new game */
    public void clearBoard() {
    	for (int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			board[i][j] = EMPTY;        // Make all cells writable
    		}
    	}
    	player = X;                         // X is first player
    }
    
    /** Puts an X or O mark at position i,j */
    public void putMark(int i, int j) throws IllegalArgumentException {
    	if ((i < 0) || (i > 2) || (j < 0) || (j > 2)) {
    		throw new IllegalArgumentException("Invalid board position");
    	}
    	if (board[i][j] != EMPTY) {
    		throw new IllegalArgumentException("Board position occupied");
    	}
    	board[i][j] = player;               // Mark value of current player
    	player = -player;                   // This is why they are opposite nums
    }
    
    /** Checks whether the board configuration is a win for the given player */
    public boolean isWin(int mark) {
    	return ((board[0][0] + board[0][1] + board[0][2] == mark * 3)
    		    || (board[1][0] + board[1][1] + board[1][2] == mark * 3)
    			|| (board[2][0] + board[2][1] + board[2][2] == mark * 3)
                || (board[0][0] + board[1][0] + board[2][0] == mark * 3)
                || (board[0][1] + board[1][1] + board[2][1] == mark * 3)
                || (board[0][2] + board[1][2] + board[2][2] == mark * 3)
                || (board[0][0] + board[1][1] + board[2][2] == mark * 3)
                || (board[2][0] + board[1][1] + board[0][2] == mark * 3));
    }
    
    /** Returns the winning player's code or a 0 to indicate a tie/unfinished game */
    public int winner() {
    	if (isWin(X)) {
    		return X;
    	} else if (isWin(O)) {
    		return O;
    	} else {
    		return 0;
    	}
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			switch(board[i][j]) {
    			case X:        sb.append("X"); break;
    			case O:        sb.append("O"); break;
    			case EMPTY:    sb.append(" "); break;
    			}
    			if (j < 2) {
    				sb.append(" | ");    // column boundary
    			}
    		}
    		if (i < 2) {
    			sb.append("\n----------\n");  // row boundary
    		}
    	}
    	return sb.toString();
    }
    
    /** Test driver for the TicTacToe class */
    public static void main(String args[]) {
    	TicTacToe game = new TicTacToe();
    	
    	/* X moves: */                /* O moves: */
    	game.putMark(1, 1);           game.putMark(0, 2);
    	game.putMark(2, 2);           game.putMark(0, 0);
    	game.putMark(0, 1);           game.putMark(2, 1);
    	game.putMark(1, 2);           game.putMark(1, 0);
    	game.putMark(2, 0);
    	
    	System.out.println(game);  // Relies on toString() method
    	int winningPlayer = game.winner();
    	String[] outcome = {"O wins", "Tie", "X wins"}; // rely on array ordering
    	System.out.println(outcome[1 + winningPlayer]);
    }
}
