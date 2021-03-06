import java.util.*;
import java.io.*;

// Still doesn't work... urg...
public class NQueens {
	private char[][] board;
	private char Queen = '@';
	private char Attacked = '*';
	private int[][] queenCoorList;

	public NQueens(int N) {
		board = new char[N][N];
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < N ; j++) {
				board[i][j] = ' ';
			}
		}
		queenCoorList = new int[N][2];
		for (int i = 0 ; i < N ; i++) {
            queenCoorList[i][0] = -1;
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

	private boolean safe(int x, int y) {
	    for (int[] i : queenCoorList) {
	        if (i[0] != -1) {
                if (x == i[0] || y == i[1] || Math.abs(i[1] - y) == Math.abs(i[0] - x)) {
                    return false;
                }
            }
	    }
	    return true;
	}

	public boolean solve(int x, int y) {

		try {
			Thread.sleep(200);
		} catch (Exception e) {}

		if (x == board.length) {
			System.out.println(this);
			System.exit(0);
			return false;
			// YAY we're done! We don't need to change stuff
		}

		if (!(safe(x, y))) { // this square is under attack
		    System.out.println("wut");
			return true; // it is TRUE that we need to change stuff...
		}

		board[x][y] = Queen;
		int[] coorList = new int[2];
		coorList[0] = x;
		coorList[1] = y;
		queenCoorList[x] = coorList;

		System.out.println(this);

		System.out.println(x + ", " + y);

		boolean needChange = true;

		for (int i = 0 ; i < board.length ; i++) {
			needChange &= solve(x + 1, i);
		}

		if (needChange && y == board[0].length - 1) {
		    board[x][y] = ' ';
		    queenCoorList[x] = new int[2];
		    queenCoorList[x][0] = -1;
		    solve(x, y + 1);
		}

		return true; // Either we're done or we ran out of board space, if we're done it doesn't matter. Otherwise we need some changes..
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("USAGE: java NQueens <board length>");
			System.exit(1);
		}

		NQueens NQ1 = new NQueens(Integer.parseInt(args[0]));
		System.out.println(NQ1);
		NQ1.solve(0,0);
	}
}
