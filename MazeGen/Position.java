public class Position {
    private int x;
    private int y;
    private Position previous;

    public Position(int myX, int myY, Position myPrev){
        x = myX;
        y = myY;
        previous = myPrev;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public Position getPrevious() {
        return previous;
    }
}
