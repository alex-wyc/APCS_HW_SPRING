public class Llist {
    private Node l = new Node("start");
    private int len = 0;

    public void add(String s) {
        Node tmp = new Node(s);
        tmp.setNext(l.getNext());
        l.setNext(tmp);
        len++;
    }

    public String toString() {
        String s = "";
        Node tmp;
        for (tmp = l.getNext() ; tmp != null ; tmp = tmp.getNext()) {
            s = s + tmp + " --> ";
        }
        s = s + "null";
        return s;
    }

    public String get(int n) {
        Node tmp = l;
        if (n >= len) {
            return "null";
        }
        for (int i = 0 ; i <= n ; i++) {
            tmp = tmp.getNext();
        }

        return tmp.getData();
    }

    public void insert(int n, String s) {
        Node newEl = new Node(s);
        Node currentNode = l.getNext();

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
}
