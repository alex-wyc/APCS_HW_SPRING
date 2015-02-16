import java.util.*;
import java.io.*;

public class NQueens {
	private char[][] board;
	private char Queen = '@';
	private char Attacked = '*';
	private char recentlyAtk = '.';

	public NQueens(int N) {
		board = new char[N][N];
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < N ; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public String toString() {
		String s = "\033\143|";
		for (int y = 0 ; y < board.length ; y ++) {
			for (int i = 0 ; i < board.length * 3 - 1 ; i++) {
				s = s + "-";
			}

			s = s +  "|\n";

			s = s + "|";

			for (int x = 0 ; x < board[0].length ; x++) {
				s = s + String.format("%2c",board[x][y]) + "|";
			}

			s = s + "\n|";
		}

		for (int i = 0 ; i < board.length * 3 - 1 ; i++) {
			s = s + "-";
		}

		s = s + "|\n";

		return s;
	}

	public void clearBoard() {
		for (int i = 0 ; i < board.length ; i++) {
			for (int j = 0 ; j < board.length ; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public void solve(int x, int y) {

		try {
			Thread.sleep(200);
		} catch (Exception e) {}

		if (x == board.length) {
			System.out.println(this);
			System.exit(0);
			return;
			// YAY we're done!
		}

		if (board[x][y] == Attacked || board[x][y] == recentlyAtk) {
			// this square is under attack
			return;
		}

		for (int i = 0 ; i < board.length ; i++) {
			for (int j = 0 ; j < board.length ; j++) {
				if (board[j][i] == recentlyAtk) {
					board[j][i] = Attacked;
				}
			}
		}

		for (int i = 0 ; i < board.length ; i++) {
			for (int j = 0 ; j < board.length ; j++) {
				if (board[j][i] == ' ') {
					if (j == x) {
						board[j][i] = recentlyAtk;
					}

					if (i == y) {
						board[j][i] = recentlyAtk;
					}

					if (Math.abs(i - y) == Math.abs(j - x)) {
						board[j][i] = recentlyAtk;
					}
				}
			}
		}

		board[x][y] = Queen;

		System.out.println(this);

		System.out.println(x + ", " + y);

		for (int i = 0 ; i < board.length ; i++) {
			solve(x + 1, i);
		}

		// urg the ground-zero-step... to re-do the past wrongs...

		for (int i = 0 ; i < board.length ; i++) {
			for (int j = 0 ; j < board.length ; j++) {
				if (board[j][i] == recentlyAtk) {
					board[j][i] = ' ';
				}
			}
		}

		board[x][y] = ' ';

	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("USAGE: java NQueens <board length>");
			System.exit(1);
		}

		NQueens NQ1 = new NQueens(Integer.parseInt(args[0]));
		System.out.println(NQ1);
		for (int i = 0 ; i < Integer.parseInt(args[0]) ; i++) {
			NQ1.solve(0,i);
			NQ1.clearBoard();
		}
	}
}
