import java.util.*;
import java.io.*;

public class Maze {

	private char[][] board;
	private final int maxX = 40;
	private final int maxY = 20;
	private int myX = 0;
	private int myY = 0;

	private final char road = '#';
	private final char wall = ' ';
	private final char me = '@';
	private final char exit = '$';
	private final char visited = '.';

	public Maze(String filename) {
		board = new char[maxX][maxY];

		try {
			Scanner sc = new Scanner(new File(filename));
			int j = 0;

			while (sc.hasNext()) {
				String line = sc.nextLine();

				for (int i = 0 ; i < maxX ; i++) {
					board[i][j] = line.charAt(i);
				}

				j++;
			}
		}
		catch (Exception e) {}
	}

	public String toString() {
		String s = "\033\143";
		for (int y = 0 ; y < maxY ; y++) {
			for (int x = 0 ; x < maxX ; x++) {
				s = s + board[x][y];
			}

			s = s + "\n";
		}

		// s = s + "\n" + myX + myY;

		return s;
	}

	public void solve(int x, int y) {

		try {
			Thread.sleep(20);
		}
		catch (Exception e) {}
		if (board[x][y] == exit) {
			System.out.println(this);
			System.exit(0);
		}
		if (board[x][y] != road) {
			return;
		}

		board[x][y] = me;

		System.out.println(this);

		System.out.println(x + ", " + y);

		solve(x + 1, y);
		solve(x - 1, y);
		solve(x, y + 1);
		solve(x, y - 1);

		board[x][y] = visited;
	}

	public static void main(String[] args) {
		Maze maze1 = new Maze(args[0]);
		System.out.println(maze1);
		maze1.solve(1,1);
	}
}
