public class Llist {
    private Node l = null;

    public void add(String s) {
        Node tmp = new Node(s);
        tmp.setNext(l);
        l = tmp;
    }

    public String toString() {
        String s = "";
        Node tmp;
        for (tmp = l ; tmp != null ; tmp = tmp.getNext()) {
            s = s + tmp + " --> ";
        }
        s = s + "null";
        return s;
    }

    public String find(int n) {
        Node tmp = l;
        for (int i = 0 ; i < n && tmp != null ; i++) {
            tmp = tmp.getNext();
        }

        if (tmp == null)
            return "null";

        return tmp.getData();
    }

    public void insert(int n, String s) {
        Node newEl = new Node(s);
        Node currentNode = l;
        if (n == 0) {
            add(s);
            return;
        }

        for (int i = 0 ; i < n - 1 && currentNode != null ; i++) {
            currentNode = currentNode.getNext();
        }

        if (currentNode == null) {
            throw new IndexOutOfBoundsException();
        }

        newEl.setNext(currentNode.getNext());
        currentNode.setNext(newEl);
        //currentNode = newEl;
    }
}
