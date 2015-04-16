public class Position implements Comparable {
    private int x;
    private int y;
    private Position previous;
    private char stuff;
    private int cost;

    public Position(int myX, int myY, Position myPrev){
        x = myX;
        y = myY;
        previous = myPrev;
    }

    public Position(int myX, int myY, char myStuff) {
        x = myX;
        y = myY;
        stuff = myStuff;
    }

    public Position(int myX, int myY, char myStuff, Position myPrev) {
        x = myX;
        y = myY;
        stuff = myStuff;
        previous = myPrev;
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
