public class Node<T implements Comparable> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T newData){
        data = newData;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }
}
