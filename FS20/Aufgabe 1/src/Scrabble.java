import java.util.*;

public class Scrabble {

    public static void drawNameSquare(String name) {
    	// must be upper case
    	name = name.toUpperCase();
		char[][] board = new char[name.length() * 2 + 1][name.length() * 4 + 1];
		// initial fill w / " "
		for (char[] arr : board) {
			Arrays.fill(arr, ' ');
		}
		
		// fill board
		int x = 1;
		int y = 2;
		for (int i = 0; i < name.length(); ++i) {
			addField(board, x, 2, name.charAt(i));
			addField(board, board.length - x - 1, board[0].length - 3, name.charAt(i));
			
			addField(board, 1, y, name.charAt(i));
			addField(board, board.length - 2, board[0].length - y - 1, name.charAt(i));
			
			x += 2;
			y += 4;
		}
		
		printBoard(board);
    }
    
    public static void addField(char[][] board, int x, int y, char s) {
    	// middle
    	board[x][y] = s;
    	
    	// top / bottom
    	board[x - 1][y - 1] = '-';
    	board[x - 1][y] = '-';
    	board[x - 1][y + 1] = '-';
    	board[x + 1][y - 1] = '-';
    	board[x + 1][y] = '-';
    	board[x + 1][y + 1] = '-';
    	
    	// left / right
    	board[x][y - 2] = '|';
    	board[x][y + 2] = '|';
    	
    	// edge
    	board[x - 1][y - 2] = '+';
    	board[x - 1][y + 2] = '+';
    	board[x + 1][y - 2] = '+';
    	board[x + 1][y + 2] = '+';
    }
    
    
    public static void printBoard(char[][] board) {
    	// print as required in tests
    	for (int i = 0; i < board.length; ++i) {
    		for (int j = 0; j < board[i].length; ++j) {
    			System.out.print(board[i][j]);
    		}
    		if (i != board.length - 1) {
    			System.out.print("\n");
    		}
    	}
    }
}
