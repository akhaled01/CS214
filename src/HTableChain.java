@SuppressWarnings("unchecked")
public class HTableChain<E extends Comparable<E>> {
    private class Node<T extends Comparable<T>> {
        private T data;
        private Node<T> next;

        private Node(T item) {
            this.data = item;
            this.next = null;
        }

        private Node(T item, Node<T> nexNode) {
            this.data = item;
            this.next = nexNode;
        }
    }

    private int cap;
    private static final int INIT_CAP = 10;
    private Node<E>[] Data;

    public HTableChain() {
        cap = INIT_CAP;
        Data = new Node[cap];
    }

    public HTableChain(int s) {
        if (s <= 0)
            this.cap = INIT_CAP;
        else
            this.cap = s;
        Data = new Node[this.cap];
    }


    public int Capacity() {
        return cap;
    }

    public void insertKey(E key) {
        int loc = (Integer) key % cap;
        Node<E> newNode = new Node<E>(key);
        if (this.Data[loc] == null) {
            this.Data[loc] = newNode;
        } else {
            newNode.next = this.Data[loc];
            this.Data[loc] = newNode;
        }
    }

    public int search(E target) {
        int loc = (Integer) target % cap;
        Node<E> temp = this.Data[loc];
        while (temp != null) {
            if (temp.data.equals(target)) {
                return loc;
            }
            temp = temp.next;
        }
        return -1;
    }


    public boolean removeChaining(E item) {
        Node<E> iterNode = this.Data[(int)item % this.cap];
        Node<E> aheadNode = iterNode.next;
        while (aheadNode != null) {
            if (aheadNode.data.equals(item)) {
                iterNode.next = aheadNode.next;
                break;
            }
            aheadNode = iterNode;
            iterNode = iterNode.next;
        }
        return true;
    }
}
