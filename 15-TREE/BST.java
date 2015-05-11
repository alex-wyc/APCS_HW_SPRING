public class BST<T extends Comparable<T>> {
    private Node<T> root;
    
    public BST(T data) {
        root = new Node<T>(data);
    }

    public Node<T> search(T i) {
        Node<T> tmp = root;
        while (tmp != null) {
            int c = tmp.getData().compareTo(i);
            if (c == 0) {
                return tmp;
            }

            tmp = (c > 0) ? tmp.getRight() : tmp.getLeft();
        }
        return null;
    }

    private Node<T> searchRH(Node current, T i) {
        int c = current.getData().compareTo(i);
        if (c == 0) {
            return current;
        }

        if (c > 0) {
            return searchRH(current.getLeft(), i);
        }

        return searchRH(current.getRight(), i);
    }

    public Node<T> searchR(T i) {
        return searchRH(root, i);
    }

    public void insert(T i) {
        Node<T> tmp1 = root;
        Node<T> tmp2 = null;

        while (tmp1 != null) {
            int c = i.compareTo(tmp1.getData());
            tmp2 = tmp1;

            tmp1 = (c > 0) ? tmp1.getRight() : tmp1.getLeft();
        }

        int c = i.compareTo(tmp2.getData());
        Node<T> newNode = new Node<T>(i);
        if (c > 0) {
            tmp2.setRight(newNode);
        }
        else {
            tmp2.setLeft(newNode);
        }
    }

    private void insertRH(Node current, T i) {
        if (current.getData().compareTo(i) > 0) {
            if (current.getLeft() == null) {
                current.setLeft(new Node<T>(i));
                return;
            }
            insertRH(current.getLeft(), i);
        }
        else {
            if (current.getRight() == null) {
                current.setRight(new Node<T>(i));
                return;
            }
            insertRH(current.getRight(), i);
        }
    }

    public void insertR(T i) {
        insertRH(root, i);
    }

    private Node<T> findParent(Node current, T i) {
        if (current.getData().compareTo(i) > 0) {
            if (current.getLeft().getData().compareTo(i) == 0) {
                return current;
            }
            return findParent(current.getLeft(), i);
        }
        else {
            if (current.getRight().getData().compareTo(i) == 0) {
                return current;
            }
            return findParent(current.getRight(), i);
        }
    }

    private Node<T> findMax(Node<T> current) {
        if (current.getRight() == null)
            return current;

        return findMax(current.getRight());
    }

    public T findMax() {
        return findMax(root).getData();
    }

    public T remove(T i) {
        Node<T> parent = findParent(root, i);
        boolean left = false;
        Node<T> toBeRemoved = parent.getRight();
        if (parent.getLeft() != null && parent.getLeft().getData() == i) {
            toBeRemoved = parent.getLeft();
            left = true;
        }

        // Case 1: if toBeRemoved is a leaf
        if (toBeRemoved.getLeft() == null && toBeRemoved.getRight() == null) {
            if (left) {
                parent.setLeft(null);
            }
            else {
                parent.setRight(null);
            }
        }

        // Case 2: if toBeRemoved has 1 child
        if (toBeRemoved.getLeft() == null || toBeRemoved.getRight() == null) {
            if (toBeRemoved.getLeft() == null) {
                if (left) {
                    parent.setLeft(toBeRemoved.getRight());
                }
                else {
                    parent.setRight(toBeRemoved.getRight());
                }
            }
            else {
                if (left) {
                    parent.setLeft(toBeRemoved.getLeft());
                }
                else {
                    parent.setRight(toBeRemoved.getRight());
                }
            }
        }

        // Case 3: if toBeRemoved has 2 children
        else {
            Node<T> newCenter = findMax(toBeRemoved.getLeft());
            remove(newCenter.getData());
            newCenter.setLeft(toBeRemoved.getLeft());
            newCenter.setRight(toBeRemoved.getRight());
            if (left) {
                parent.setLeft(newCenter);
            }
            else {
                parent.setRight(newCenter);
            }
        }

        return toBeRemoved.getData();
    }

    private String toStringSubTree(Node current, boolean left, String prefix) {
        if (current == null) {
            return "";
        }

        String newPrefix = "";
        
        String newChar = " ";
        if (prefix.length() > 0 && prefix.charAt(prefix.length()-1) == '├') {
            newChar = "│";
        }
        for (int i = 0 ; i < prefix.length() ; i++){
            if (prefix.charAt(i) != ' ') {
                newPrefix = newPrefix + newChar;
            }
            else {
                newPrefix = newPrefix + ' ';
            }
        }
        if (left) {
            newPrefix = newPrefix + "├";
        }
        else {
            newPrefix = newPrefix + "└";
        }
        if (current.getRight() != null) {
            return newPrefix + current.getData() + "\n" + toStringSubTree(current.getLeft(), true, newPrefix) + toStringSubTree(current.getRight(), false, newPrefix);
        }
        else {
            return newPrefix + current.getData() + "\n" + toStringSubTree(current.getLeft(), false, newPrefix);
        }
    }

    public String toString() {
        return root.getData() + "\n" + toStringSubTree(root.getLeft(), true, "") + toStringSubTree(root.getRight(), false, "");
    }

    private String transverseH(Node<T> current) {
        if (current == null) {
            return "";
        }
        else {
            return transverseH(current.getLeft()) + current.getData() + " " + transverseH(current.getRight());
        }
    }

    public String transverse() {
        String s = "";
        return transverseH(root.getLeft()) + root.getData() + " " + transverseH(root.getRight());
    }

    public int nodeCount() {
        return nodeCountH(root);
    }

    private int nodeCountH(Node<E> T) {
        if (T == null) {
            return 0;
        }
        else {
            return 1 + nodeCountH(T.getLeft()) + nodeCountH(T.getRight());
        }
    }

    public E findMax() {
        findMax(root);
    }

    private E findMax(Node<E> T) {
        if (T.isLeaf()) {
            return T.getData();
        }
        else {
            if (T.getData() > findMax(T.getLeft())) {
                if (T.getData() > findMax(T.getRight())) {
                    return findMax(T.getRight());
                }
                else {
                    return findMax(T.getRight());
                }
            }
            else {
                if (findMax(T.getLeft()) > findMax(T.getRight())) {
                    return findMax(T.getLeft());
                }
                else {
                    return findMax(T.getRight());
                }
            }
        }
    }

    public int height(Node<E> T)

    public static void main(String[] args) {
        BST<Integer> tmp = new BST<Integer>(10);
        tmp.insert(5);
        tmp.insert(20);
        tmp.insert(17);
        tmp.insert(8);
        tmp.insert(2);
        tmp.insert(25);
        System.out.println(tmp);

        System.out.println(tmp.searchR(8).getData().toString() + "\n");

        tmp.insertR(100);
        tmp.insertR(4);
        tmp.insertR(15);
        //tmp.insertR(18);
        tmp.insertR(101);

        System.out.println(tmp);

        System.out.println(tmp.remove(100));
        System.out.println(tmp.remove(101));
        System.out.println(tmp.remove(20));
        System.out.println();

        System.out.println(tmp);
        System.out.println(tmp.transverse());
    }
}
