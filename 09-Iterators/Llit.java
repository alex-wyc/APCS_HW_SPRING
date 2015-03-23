import java.util.*;
import java.io.*;

public class Llit<E> implements Iterator<E> {
    private Node<E> t;
    private Node<E> lastRet = null;
    private Node<E> twoRetsAgo = null;
    private boolean nexted = false;

    public Llit(Node<E> n) {
        t = n;
    }

    public boolean hasNext() {
        return t.getNext() != null;
    }

    public E next() {
        E returnVal = t.getData();
        twoRetsAgo = lastRet;
        lastRet = t;
        t = t.getNext();
        nexted = true;
        return returnVal;
    }

    public void remove() {
        // FIXME should work but doesn't...
        // Leaves every other number
        System.out.println("Two Rets ago: " + twoRetsAgo);
        System.out.println("Last ret: " + lastRet);
        if (nexted) {
            if (twoRetsAgo != null)
                twoRetsAgo.setNext(t);
            nexted = false;
        }
        else {
            throw new IllegalStateException();
        }
    }
}
