public class Position {
    private int x;
    private int y;
    private char stuff;
    private Position previous;

    public Position(int myX, int myY, char myStuff, Position myPrevious) {
        x = myX;
        y = myY;
        stuff = myStuff;
        previous = myPrevious;
    }

    public Position(int myX, int myY, Position myPrevious) {
        x = myX;
        y = myY;
        previous = myPrevious;
    }

    public Position(int myX, int myY, char myStuff){
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

    public Position getPrevious() {
        return previous;
    }
}
