import java.util.*;

public class Fourinarow {

	public boolean kannGewinnen(boolean roterSpieler, Spielzustand brett, int n) {
		if (brett.hatGewonnen(roterSpieler) || brett.hatGewonnen(!roterSpieler)) {
			throw new IllegalStateException("Jemand hat schon gewonnen");
		}
		
		// recursive helper
		return recKannGewinnen(roterSpieler, roterSpieler, brett, n, 0);
	}

	public boolean recKannGewinnen(boolean player, boolean cPlayer, Spielzustand board, int n, int depth) {
		// base cases
		// too many tries or other one has won
		if (depth > n || board.hatGewonnen(!player)) {
			return false;
		}

		// has won
		if (board.hatGewonnen(player)) {
			return true;
		}

		// where can we place stones?
		Set<Integer> moves = board.moeglicheSpalten();

		// all in brute force recursion
		for (int m : moves) {
			// copy board
			Spielzustand newBoard = new Spielzustand(board);
			// place
			newBoard.setzeStein(cPlayer, m);
			
			// adjust depth, increase after every move of target
			int nextDepth = depth;
			if (player == cPlayer) {
				++nextDepth;
			}
			// won?
			if (recKannGewinnen(player, !cPlayer, newBoard, n, nextDepth)) {
				return true;
			}
		}
		
		// can't win
		return false;
	}

	public boolean unentschieden(boolean roterSpieler, Spielzustand brett) {
		if (brett.hatGewonnen(roterSpieler) || brett.hatGewonnen(!roterSpieler)) {
			throw new IllegalStateException("Jemand hat schon gewonnen");
		}

		// check if red can win
		if (kannGewinnen(roterSpieler, brett, 100)) {
			return false;
		}

		// all possible moves for red, see if yellow can win
		Set<Integer> moves = brett.moeglicheSpalten();
		for (int m : moves) {
			// copy board
			Spielzustand newBoard = new Spielzustand(brett);
			// place
			newBoard.setzeStein(roterSpieler, m);

			// check
			if (kannGewinnen(!roterSpieler, newBoard, 100)) {
				return false;
			}
		}
		
		// all are winners :)
		return true;
	}

}
