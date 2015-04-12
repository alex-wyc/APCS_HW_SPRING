public class directedQueue<E> {
    private Node<E> top;

    public directedQueue() {
        top = new Node<E>();
    }

    public boolean empty() {
        return top.getNext() == null;
    }

    public void enqueue(E data, int cost) {
        Node<E> newDat = new Node<E>(data, cost);
        Node<E> tmp;
        boolean notAdded = true;
        for (tmp = top ; tmp.getNext() != null; tmp = tmp.getNext()) {
            if (cost < tmp.getNext().getCost()) {
                newDat.setNext(tmp.getNext());
                tmp.setNext(newDat);
                notAdded = false;
                break;
            }
        }
        if (notAdded) {
            newDat.setNext(tmp.getNext());
            tmp.setNext(newDat);
        }
    }

    public E dequeue() {
        if (empty()) {
            return null;
        }
        E retVal = top.getNext().getData();
        top.setNext(top.getNext().getNext());
        return retVal;
    }

    public E head() {
        if (empty()) {
            return null;
        }
        return top.getNext().getData();
    }

    public String toString() {
        String s = "TOP\n";
        for (Node<E> tmp = top.getNext() ; tmp != null ; tmp = tmp.getNext()) {
            s = s + tmp.getData() + "  Cost: " + tmp.getCost() + "\n";
        }
        return s;
    }
}
