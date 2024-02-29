public class SlinkedList<E extends Comparable<E>> {
    // private class Node
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

    private Node<E> head;
    private int size;

    public SlinkedList() {
        this.head = null;
        size = 0;
    }

    private void addFirst(E item) {
        Node<E> newNode = new Node<E>(item, head);
        this.head = newNode;
        size++;
    }

    public int Size() {
        return size;
    }

    private void addAfter(Node<E> node, E item) {
        Node<E> newNode = new Node<E>(item, node.next);
        node.next = newNode;
        size++;
    }

    private E removeAfter(Node<E> node) {
        E oldVal = node.next.data;
        node.next = node.next.next;
        size--;
        return oldVal;
    }

    private E removeFirst() {
        if (this.head == null) {
            return null;
        }
        E ol = this.head.data;
        this.head = this.head.next;
        size--;
        return ol;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }
        Node<E> cur = head;
        for (int i = 0; i < index && cur != null; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // public

    public String toString() {
        String str = "";
        Node<E> nodeRef = head;
        for (int i = 0; i < size; i++) {
            str = str + nodeRef.data + "\n";
            nodeRef = nodeRef.next;
        }
        return str;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        return getNode(index).data;
    }

    public E set(int index, E nItem) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        E oldVal = get(index);
        getNode(index).data = nItem;
        return oldVal;
    }

    public void add(int index, E entry) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(entry);
        } else {
            addAfter(getNode(index - 1), entry);
        }
    }

    public boolean add(E item) {
        add(size, item);
        return true;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            E val = get(0);
            removeFirst();
            return val;
        } else {
            E val = get(index);
            removeAfter(getNode(index - 1));
            return val;
        }
    }

    public int indexOf(E item) {
        for (int i = 0; i < size; i++) {
            if (getNode(i).data.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean remove(E item) {
        if (indexOf(item) == -1) {
            return false;
        }
        remove(indexOf(item));
        return true;
    }

    public boolean contains(E item) {
        return indexOf(item) != -1;
    }

    public void clear() {
        while (head != null) {
            head.data = null;
            head = head.next;
        }
        size = 0;
    }

    public boolean deleteAlt(SlinkedList<E> l2) {
        if (this.head == null) {
            return false;
        }
        int i = 0;
        Node<E> iterNode1 = head.next.next;
        Node<E> iterNode2 = head.next;
        l2.head = new Node<E>(head.data);
        head = head.next;
        while (iterNode1 != null) {
            if (i % 2 == 0) {
                E nData = iterNode1.data;
                iterNode2.next = iterNode1.next;
                iterNode1 = iterNode1.next;
                Node<E> otherN = new Node<E>(nData);
                Node<E> t = l2.head;
                while (t.next != null) {
                    t = t.next;
                }
                t.next = otherN;
            } else {
                iterNode2 = iterNode1;
                iterNode1 = iterNode1.next;
            }
            if (iterNode1 == null) {
                break;
            }
            i++;
        }
        return true;
    }

    public boolean reverseTwoMiddle() {
        int index = (this.size / 2) - 1;
        Node<E> temp = head.next;
        Node<E> temp1 = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
            temp1 = temp1.next;
        }
        // ! IN THIS CLASS, E EXTENDS COMPARABLE, NO NEED FOR CASTING
        if (temp1.data.compareTo(temp.data) > 0) {
            E t1 = temp1.data;
            E t2 = temp.data;

            temp1.data = t2;
            temp.data = t1;
        } else {
            return false;
        }
        return true;
    }

    public boolean insertSorted(E item) {
        if (this.head == null) {
            this.head = new Node<E>(item);
        } else if (this.size == 1) {
            if (this.head.data.compareTo(item) > 0) {
                Node<E> newNode = new Node<E>(item);
                head.next = newNode;
            } else {
                this.head = new Node<E>(item, this.head);
            }
        } else {
            // compare with head
            if (this.head.data.compareTo(item) > 0) {
                this.head = new Node<E>(item, this.head);
            } else {
                Node<E> temp = this.head.next;
                Node<E> prev = this.head;
                Node<E> newNode = new Node<E>(item);
                while (temp != null) {
                    if (temp.data.compareTo(item) > 0 && prev.data.compareTo(item) < 0) {
                        newNode.next = temp;
                        prev.next = newNode;
                        break;
                    } else {
                        temp = temp.next;
                        prev = prev.next;
                    }
                }
                // consider last element if its following sorting
                if (temp == null) {
                    prev.next = newNode;
                }
            }
        }
        return true;
    }

    public void printList() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.print(temp.data.toString() + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
