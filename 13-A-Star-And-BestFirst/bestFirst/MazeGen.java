import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.StringBuilder;
import java.util.Stack;

public class MazeGen {
    private Random randgen = new Random();

    private char[][] board;
    private boolean[][] visitedCells;
    private int myX;
    private int myY;
    private int lastX;
    private int lastY;

    private final char road = '#';
    private final char wall = ' ';
    private final char exit = '$';
    private final char start = '+';

    private final int maxX;
    private final int maxY;
    private final int size;

    private int exitX;
    private int exitY;
    private int visited;

    private Stack<Position> frontier = new Stack<Position>();

    public MazeGen(int NMaxX, int NMaxY) {
        maxX = NMaxX;
        maxY = NMaxY;
        board = new char[maxY][maxX];
        for (int j = 0 ; j < maxY ; j++) {
            for (int i = 0 ; i < maxX ; i++) {
                board[j][i] = wall;
            }
        }

        size = (maxX * maxY) / 4;

        exitX = randgen.nextInt(maxX);
        while (exitX % 2 == 0)
            exitX = randgen.nextInt(maxX);

        exitY = randgen.nextInt(maxY);
        while (exitY % 2 == 0)
            exitY = randgen.nextInt(maxY);

        board[exitY][exitX] = exit;

        frontier.push(new Position(exitX, exitY, null));
        lastX = exitX;
        lastY = exitY;
        
        visited = 1;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int y = 0 ; y < maxY ; y++) {
            for (int x = 0 ; x < maxX ; x++) {
                s.append(board[y][x]);
            }
            s.append("\n");
        }
        return s.toString();
    }

    public char[][] getBoard() {
        return board;
    }

    public void generate() {
        while (visited < size) {
            //System.out.println(this);
            //System.out.println(visited);
            //try {
            //    Thread.sleep(20);
            //} catch (Exception e) {}
            Position current = frontier.pop();
            myX = current.getX();
            myY = current.getY();
            if (current.getPrevious() != null) {
                lastX = current.getPrevious().getX();
                lastY = current.getPrevious().getY();
            }

            board[myY][myX] = board[lastY + ((myY - lastY) / 2)][lastX + ((myX - lastX) / 2)] = road;
            pushAllNeighbor(myX, myY, current);

            visited++;
        }
        board[myY][myX] = start;
        board[exitY][exitX] = exit;
        //System.out.println("\033\143");
        //System.out.println(this);
    }

    private boolean inBounds(int x, int y) {
        return (x >= 0 && x < maxX && y > 0 && y < maxY);
    }

    private void pushAllNeighbor(int X, int Y, Position current) {
        Position[] neighborList = new Position[4];
        if (inBounds(X, Y+2) && board[Y + 2][X] == wall) {
            neighborList[0] = new Position(X, Y + 2, current);
        }
        
        if (inBounds(X, Y-2) && board[Y - 2][X] == wall) {
            neighborList[1] = new Position(X, Y - 2, current);
        }
        if (inBounds(X+2, Y) && board[Y][X + 2] == wall) {
            neighborList[2] = new Position(X + 2, Y, current);
        }
        if (inBounds(X-2, Y) && board[Y][X - 2] == wall) {
            neighborList[3] = new Position(X - 2, Y, current);
        }

        shuffleArray(neighborList);

        int a = 2;
        for (int i = 0 ; i < a && a < 4 ; i++) {
            if (neighborList[i] != null) {
                frontier.push(neighborList[i]);
            }
            else {
                a++;
            }
        }
    }

    private void shuffleArray(Position[] arr) {
        for (int i = arr.length - 1 ; i > 0 ; i--) {
            int index = randgen.nextInt(i + 1);
            Position tmp = arr[index];
            arr[index] = arr[i];

            arr[i] = tmp;
        }
    }

    public static void main(String[] args) {
        MazeGen mg = new MazeGen(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        mg.generate();
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(args[2]));
            w.write(mg.toString().substring(mg.getBoard().length + 1));
            w.close();
        } catch (Exception e) {}
    }
}
