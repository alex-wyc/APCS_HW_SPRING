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
        newDat.setNext(top.getNext());
        top.setNext(newDat);
    }

    public E dequeue() {
        if (empty()) {
            return null;
        }
        Node<E> currentMin = top;
        for (Node<E> tmp = top.getNext() ; tmp.getNext() != null ; tmp = tmp.getNext()) {
            if (tmp.getNext().getCost() < currentMin.getNext().getCost()) {
                currentMin = tmp;
            }
        }
        E retVal = currentMin.getNext().getData();
        currentMin.setNext(currentMin.getNext().getNext());
        return retVal;
    }

    public E head() {
        if (empty()) {
            return null;
        }
        return top.getNext().getData();
    }

    public int headCost() {
        if (empty()) {
            return 0;
        }
        return top.getNext().getCost();
    }

    public String toString() {
        String s = "TOP\n";
        for (Node<E> tmp = top.getNext() ; tmp != null ; tmp = tmp.getNext()) {
            s = s + tmp.getData() + "  Cost: " + tmp.getCost() + "\n";
        }
        return s;
    }
}
