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

    private int exitX;
    private int exitY;

    private directedQueue<Position> frontier = new directedQueue<Position>();

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

                    if (board[i][j] == exit) {
                        exitX = i;
                        exitY = j;
                    }
                }

                j++;
            }
        }
        catch(Exception e) {}
        frontier.enqueue(new Position(myX, myY, board[myX][myY]), manhattanDist(myX, myY));
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

    public int manhattanDist(int thisX, int thisY) {
        return Math.abs(thisX - exitX) + Math.abs(thisY - exitY);
    }

    public void solve() {
        while (!frontier.empty()) {
            System.out.println(this);
            try {
                Thread.sleep(20);
            }
            catch (Exception e) {}

            int currentCost = frontier.headCost();
            Position current = frontier.dequeue();
            myX = current.getX();
            myY = current.getY();
            char currentChar = current.getStuff();
            if (currentChar == exit) {
                int stepCount = 1;
                for (Position newPos = current.getPrevious() ; newPos != null ; newPos = newPos.getPrevious()) {
                    board[newPos.getX()][newPos.getY()] = path;
                    stepCount++;
                    System.out.println(this);
                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {}
                }

                System.out.println(this);
                System.out.println(stepCount);
                System.exit(0);
            }

            if (currentChar == road || currentChar == start) {
                board[myX][myY] = visited;
                try {
                    if (board[myX + 1][myY] != visited && board[myX + 1][myY] != wall){
                        frontier.enqueue(new Position(myX + 1, myY, board[myX + 1][myY], current), manhattanDist(myX + 1, myY) + currentCost);
                    }
                } catch (Exception e) {}

                try {
                    if (board[myX - 1][myY] != visited && board[myX - 1][myY] != wall){
                        frontier.enqueue(new Position(myX - 1, myY, board[myX - 1][myY], current), manhattanDist(myX - 1, myY) + currentCost);
                    }
                } catch (Exception e) {}

                try {
                    if (board[myX][myY + 1] != visited && board[myX][myY + 1] != wall){
                        frontier.enqueue(new Position(myX, myY + 1, board[myX][myY + 1], current), manhattanDist(myX, myY + 1) + currentCost);
                    }
                } catch (Exception e) {}
                
                try {
                    if (board[myX][myY - 1] != visited && board[myX][myY - 1] != wall){
                        frontier.enqueue(new Position(myX, myY - 1, board[myX][myY - 1], current), manhattanDist(myX, myY - 1) + currentCost);
                    }
                } catch (Exception e) {}
            }
        }
    }

    public static void main(String[] args) {
        Maze maze1 = new Maze(args[0], 40, 20);
        System.out.println(maze1);
        maze1.solve();
    }
}
