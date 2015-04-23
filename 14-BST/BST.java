public class BST<T implements Comparable> {
    private Node<T> root;
    
    public BST(T data) {
        root = new Node<T>(data);
    }

    public Node search(T i) {
        Node<T> tmp = root;
        while (T != null) {
            int c = T.getData().comparedTo(i);
            if (c == 0) {
                return tmp;
            }

            tmp = (c > 0) ? T.getRight() : T.getLeft();
        }
        return null;
    }

    public void insert(T i) {
        Node<T> tmp1 = root;
        Node<T> tmp2 = null;

        while (tmp1 != null) {
            int c = T.compareTo(tmp1.getData());
            tmp2 = tmp1;

            tmp1 = (c > 0) ? tmp1.getRight() : tmp1.getLeft();
        }

        int c = T.compareTo(tmp2.getData());
        Node<T> newNode = new Node<T>(i);
        if (c > 0) {
            tmp2.setRight(newNode);
        }
        else {
            tmp2.getLeft(newNode);
        }
    }

    public static void main(String[] args) {
        BST<Integer> tmp = new BST<Integer>(10);
    }
}
