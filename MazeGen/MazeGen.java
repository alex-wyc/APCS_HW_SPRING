import java.util.Random;

public class MazeGen {
    private Random randgen = new Random();

    private char[][] board;
    private boolean[][] visitedCells;
    private int myX;
    private int myY;

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

    private MyStack<Position> frontier = new MyStack<Position>();

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
        
        visited = 1;
    }

    public String toString() {
        String s = "\033\143";
        for (int y = 0 ; y < maxY ; y++) {
            for (int x = 0 ; x < maxX ; x++) {
                s = s + board[y][x];
            }

            s = s + "\n";
        }
        return s;
    }

    public char[][] getBoard() {
        return board;
    }

    public void generate() {
        while (visited < size) {
            System.out.println(this);
            try {
                Thread.sleep(20);
            } catch (Exception e) {}
            Position current;
            try {
                current = frontier.pop();
            } catch (Exception e) {break;}
            myX = current.getX();
            myY = current.getY();
            while (getRandNeighbor(myX, myY, current) == null) {
                current = current.getPrevious();
                myX = current.getX();
                myY = current.getY();
            }

            frontier.push(getRandNeighbor(myX, myY, current));
            if (board[myY][myX] == wall)
                board[myY][myX] = road;
            visited++;
        }
        board[myY][myX] = start;
        System.out.println(this);
        try {
            Thread.sleep(200);
        } catch (Exception e) {}
    }

    private Position getRandNeighbor(int X, int Y, Position current) {
        boolean[] neighborList = new boolean[4];
        boolean isEmpty = true;
        
        try {
            if (board[Y + 2][X] == wall) {
                neighborList[0] = true;
                isEmpty = false;
            }
        } catch (Exception e) {}          
        try {
            if (board[Y - 2][X] == wall) {
                neighborList[1] = true;
                isEmpty = false;
            }
        } catch (Exception e) {}       
        try {
            if (board[Y][X + 2] == wall) {
                neighborList[2] = true;
                isEmpty = false;
            }
        } catch (Exception e) {}       
        try {
            if (board[Y][X - 2] == wall) {
                neighborList[3] = true;
                isEmpty = false;
            }
        } catch (Exception e) {}

        if (isEmpty) {
            return null;
        }

        int direction = randgen.nextInt(4);
        while (!neighborList[direction]) {
            direction = randgen.nextInt(4);
        }

        switch (direction) {
            case 0:
                board[Y + 1][X] = road;
                return new Position(X, Y + 2, current);
                
            case 1:
                board[Y - 1][X] = road;
                return new Position(X, Y - 2, current);
                
            case 2:
                board[Y][X + 1] = road;
                return new Position(X + 2, Y, current);
                
            case 3:
                board[Y][X - 1] = road;
                return new Position(X - 2, Y, current);
                
        }
        return null;
    }

    public static void main(String[] args) {
        MazeGen mg = new MazeGen(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        mg.generate();
    }
}
