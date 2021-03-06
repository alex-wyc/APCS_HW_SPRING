import java.util.*;
import java.io.*;

public class Maze {
    private char[][] board;
    private int myX;
    private int myY;
    
    private final char road = '#';
    private final char wall = ' ';
    private final char me = '@';
    private final char exit = '$';
    private final char visited = '.';
    private final char start = '+';
    private final char path = '=';

    private final int maxX;
    private final int maxY;

    private MyStack<Position> frontier = new MyStack<Position>();

    public Maze(String filename, int newMaxX, int newMaxY) {
        maxX = newMaxX;
        maxY = newMaxY;
        board = new char[maxX][maxY];

        try {
            Scanner sc = new Scanner(new File(filename));
            int j = 0;

            while (sc.hasNext()) {
                String line = sc.nextLine();

                for (int i = 0 ; i < maxX ; i++) {
                    board[i][j] = line.charAt(i);
                    if (board[i][j] == start) {
                        myX = i;
                        myY = j;
                    }
                }

                j++;
            }
        }
        catch(Exception e) {}
        frontier.push(new Position(myX, myY, board[myX][myY]));
    }

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

    public void solve() {
        while(!frontier.empty()) {
            System.out.println(this);
            try {
                Thread.sleep(20);
            }
            catch (Exception e) {}

            //System.out.println(frontier);
            //System.out.println(frontier.empty());

            Position current = frontier.pop();
            //System.out.println(current);
            myX = current.getX();
            myY = current.getY();
            char currentChar = current.getStuff();
            //System.out.println(currentChar);
            if (currentChar == exit) {
                for (Position newPos = current.getPrevious() ; newPos != null ; newPos = newPos.getPrevious()) {
                    board[newPos.getX()][newPos.getY()] = path;
                    System.out.println(this);
                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {}
                }

                System.out.println(this);
                System.exit(0);
            }

            if (currentChar == road || currentChar == start) {
                board[myX][myY] = visited;
                try {
                    if (board[myX + 1][myY] != visited && board[myX + 1][myY] != wall){
                        frontier.push(new Position(myX + 1, myY, board[myX + 1][myY], current));
                    }
                } catch (Exception e) {}

                try {
                    if (board[myX - 1][myY] != visited && board[myX - 1][myY] != wall){
                        frontier.push(new Position(myX - 1, myY, board[myX - 1][myY], current));
                    }
                } catch (Exception e) {}

                try {
                    if (board[myX][myY + 1] != visited && board[myX][myY + 1] != wall){
                        frontier.push(new Position(myX, myY + 1, board[myX][myY + 1], current));
                    }
                } catch (Exception e) {}
                
                try {
                    if (board[myX][myY - 1] != visited && board[myX][myY - 1] != wall){
                        frontier.push(new Position(myX, myY - 1, board[myX][myY - 1], current));
                    }
                } catch (Exception e) {}
            }
        }
    }

    public static void main(String[] args) {
        Maze maze1 = new Maze(args[0], 101, 101);
        System.out.println(maze1);
        maze1.solve();
    }
}
