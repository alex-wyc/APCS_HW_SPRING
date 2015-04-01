public class Position {
    private int x;
    private int y;
    private char stuff;

    public Position(int myX, int myY, char myStuff) {
        x = myX;
        y = myY;
        stuff = myStuff;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public char getStuff() {
        return stuff;
    }
}
