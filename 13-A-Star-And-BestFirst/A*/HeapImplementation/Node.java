import java.util.*;
import java.io.*;

public class Node<E> {
    private E data;
    private Node<E> next;
    private int cost;

    public Node() {
        data = null;
        next = null;
        cost = 0;
    }

    public Node(E s, int c) {
        data = s;
        next = null;
        cost = c;
    }

    public Node(E s) {
        data = s;
        next = null;
        cost = 0;
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

    public int getCost() {
        return cost;
    }
}
