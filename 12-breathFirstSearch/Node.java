import java.util.*;
import java.io.*;

public class Node<E> {
    private E data;
    private Node<E> next;
    private Node<E> previous;

    public Node() {
        data = null;
        next = null;
        previous = null;
    }

    public Node(E s) {
        data = s;
        next = null;
        previous = null;
    }

    public void setData(E s) {
        data = s;
    }

    public E getData() {
        return data;
    }

    public void setNext(Node<E> s) {
        next = s;
    }

    public Node<E> getNext() {
        return next;
    }

    public String toString() {
        return ""+data;
    }

    public void setPrevious(Node<E> s) {
        previous = s;
    }

    public Node<E> getPrevious() {
        return previous;
    }
}
