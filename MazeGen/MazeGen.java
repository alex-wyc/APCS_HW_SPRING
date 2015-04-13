import java.util.Random;

public class MazeGen {
    private char[][] board;
    private int myX;
    private int myY;

    private final char road = '#';
    private final char wall = ' ';
    private final char exit = '$';
    private final char start = '+';

    private final int maxX;
    private final int maxY;

    private MyStack<Position> frontier = new MyStack<Position>();

    public Maze(int maxX, int maxY)

    public String toString() {
        String s = "\033\143";
        for (int y = 0 ; y < maxY ; y++) {
            for (int x = 0 ; x < maxX ; x++) {
                s = s + board[x][y];
            }

            s = s + "\n";
        }
        return s;
    }

}
