public class Llist2 {
    private Node2 l = new Node2(0);
    private int len = 0;

    public void add(int n) {
        Node2 tmp = new Node2(n);
        tmp.setNext(l.getNext());
        l.setNext(tmp);
        len++;
    }

    public String toString() {
        String s = "";
        Node2 tmp;
        for (tmp = l.getNext() ; tmp != null ; tmp = tmp.getNext()) {
            s = s + tmp.getData() + " --> ";
        }
        s = s + "null";
        return s;
    }

    public int get(int n) {
        Node2 tmp = l;
        if (n >= len) {
            return -1;
        }
        for (int i = 0 ; i <= n ; i++) {
            tmp = tmp.getNext();
        }

        return tmp.getData();
    }

    public void add(int n, int s) {
        Node2 newEl = new Node2(s);
        Node2 currentNode = l.getNext();

        if (n > len) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0 ; i < n - 1 ; i++) {
            currentNode = currentNode.getNext();
        }

        newEl.setNext(currentNode.getNext());
        currentNode.setNext(newEl);
        
        len++;
    }

    public int length() {
        return len;
    }

    public int remove(int n) {
        if (n >= len || n < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node2 currentNode = l;

        for (int i = 0 ; i < n - 1 ; i++) {
            currentNode = currentNode.getNext();
        }

        int removed = currentNode.getNext().getData();
        currentNode.setNext(currentNode.getNext().getNext());

        return removed;
    }
}
