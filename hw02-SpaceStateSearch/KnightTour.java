import java.util.*;
import java.io.*;

public class KnightTour {
	private String[][] board;
	private String knight = "&";
	private int moves;
	private int sidelength;

	public KnightTour(int N){
		sidelength = N;
		moves = sidelength * sidelength;
		board = new String[N+4][N+4];
		for (int i = 0 ; i < 2 ; i++) {
			for (int j = 0 ; j < N + 4 ; j++) {
				board[i][j] = "-1";
			}
		}

		for (int i = 2 ; i < N + 2 ; i++) {
			for (int j = 0 ; j < 2 ; j++) {
				board[i][j] = "-1";
			}

			for (int j = 2 ; j < N + 2 ; j++) {
				board[i][j] = "0";
			}

			for (int j = N + 2 ; j < N + 4 ; j++) {
				board[i][j] = "-1";
			}
		}

		for (int i = N + 2 ; i < N + 4 ; i++) {
			for (int j = 0 ; j < N + 4 ; j++) {
				board[i][j] = "-1";
			}
		}
	}

	public String toString() {
		String s = "\033\143|";
		for (int y = 2 ; y < board.length - 2 ; y++) {
			for (int i = 0 ; i < (board.length - 4) * 5 - 1; i++) {
				s = s + "-";
			}

			s = s + "|\n";

			s = s + "|";

			for (int x = 2 ; x < board[0].length - 2 ; x++) {
				s = s + String.format("%4s", board[x][y]) + "|";
			}
			s = s + "\n|";
		}

		for (int i = 0 ; i < (board.length - 4) * 5 - 1; i++) {
			s = s + "-";
		}

		s = s + "|\n";

		return s;
	}

/*	// For heuresic purposes...
	private int legalMoveCounter(int x, int y) {
		counter = 0;
		if (board[x + 4][y + 3].equals("0"))
			counter++;

		if (board[x + 4][y + 1].equals("0"))
			counter++;

		if (board[x + 3][y + 4].equals("0"))
			counter++;

		if (board[x + 3][y + 0].equals("0"))
			counter++;

		if (board[x + 1][y + 4].equals("0"))
			counter++;

		if (board[x + 1][y + 0].equals("0"))
			counter++;

		if (board[x + 0][y + 3].equals("0"))
			counter++;

		if (board[x + 0][y + 1].equals("0"))
			counter++;

		return counter;
	}
*/

	private void solveH(int x, int y, int counter) {
/*
		try {
			Thread.sleep(0);
		}
		catch (Exception e) {}
*/
		if (counter == moves) {
			System.out.println(this);
			System.exit(0);
			return;
			// YAY we're done!
		}

		if (!(board[x + 2][y + 2].equals("0"))) {
			// Visited already or off the board...
			return;
		}

		board[x + 2][y + 2] = knight + Integer.toString(counter);

//		System.out.println(this);

/*
		// Warnsdorff's rule!!

		int candidate = 0;
		int legalMoves = legalMoveCounter(x + 2, y + 1);

		// #FIXME There must be an easier way
		if (legalMoveCounter(x + 2, y - 1) < legalMoveCounter)
			candidate = 1;
		if (legalMoveCounter(x + 1, y + 2) < legalMoveCounter) 
			candidate = 2;
		if (legalMoveCounter(x + 1, y - 2) < legalMoveCounter)
			candidate = 3;
		if (legalMoveCounter(x - 1, y + 2) < legalMoveCounter)
			candidate = 4;
		if (legalMoveCounter(x - 1, y - 2) < legalMoveCounter)
			candidate = 5;
		if (legalMoveCounter(x - 2, y + 1) < legalMoveCounter)
			candidate = 6;
		if (legalMoveCounter(x - 2, y - 1) < legalMoveCounter)
			candidate = 7;
*/
		
		solveH(x + 2, y + 1, counter + 1);
		solveH(x + 2, y - 1, counter + 1);
		solveH(x + 1, y + 2, counter + 1);
		solveH(x + 1, y - 2, counter + 1);
		solveH(x - 1, y + 2, counter + 1);
		solveH(x - 1, y - 2, counter + 1);
		solveH(x - 2, y + 1, counter + 1);
		solveH(x - 2, y - 1, counter + 1);

		board[x + 2][y + 2] = "0";
	}

	public void solve() {
		solveH(0, 0, 0);
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("USAGE: java KnightTour <sideLength>");
			System.exit(1);
		}
		KnightTour KT1 = new KnightTour(Integer.parseInt(args[0]));
		KT1.solve();
	}
}
