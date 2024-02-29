import java.util.*;

public class KWLinkedList<E> {
    private static class Node<T> {
        public T data;
        public Node<T> next, prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private class KWListIter implements ListIterator<E> {
        private Node<E> nextItem; // A reference to the next item
        private Node<E> lastItemReturned; // A reference to the last item returned.
        private int index = 0; // The index of the current item.

        public KWListIter() {
            lastItemReturned = null; // No item returned yet.
            index = 0;
            nextItem = head; // Start at the beginning
        }

        public KWListIter(int i) {
            // Validate parameter i.
            if (i < 0 || i > size)
                throw new IndexOutOfBoundsException("Invalid index " + i);
            lastItemReturned = null; // No item returned yet.
            if (i == size) // Special case of last item
            {
                index = size;
                nextItem = null;
            } else {
                // Start at the beginning
                nextItem = head;
                for (index = 0; index < i; index++)
                    nextItem = nextItem.next;
            } // end else
        }

        /**
         * Construct a KWListIter that is a copy of another KWListIter
         * 
         * @param other The other KWListIter
         */
        public KWListIter(KWListIter other) {
            this.index = other.index;
            this.lastItemReturned = other.lastItemReturned;
            this.nextItem = other.nextItem;
        }

        public boolean hasNext() {
            return nextItem != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        public boolean hasPrevious() {
            return ((nextItem == null && size != 0) ||
                    (nextItem != null && nextItem.prev != null));
        }

        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            if (nextItem == null) {
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            index--;
            return lastItemReturned.data;
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index - 1;
        }

        public void add(E item) {
            Node<E> newNode = new Node<E>(item);
            if (head == null) {
                head = tail = newNode;
            } else if (nextItem == head) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (nextItem == null) {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            } else {
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            index++;
            size++;
            lastItemReturned = null;
        }

        public void remove() {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }

            if (head == tail) {
                head = tail = null;
            } else if (lastItemReturned == head) {
                head = head.next;
                head.prev = null;
            } else if (lastItemReturned == tail) {
                tail = tail.prev;
                tail.next = null;
            } else {
                lastItemReturned.prev.next = lastItemReturned.next;
                lastItemReturned.next.prev = lastItemReturned.prev;
            }

            // check if next item was the item that was removed
            if (lastItemReturned == nextItem) {
                nextItem = nextItem.next;
            } else {
                index--;
            }

            size--;
            lastItemReturned = null;
        }

        public void set(E nItem) {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }
            lastItemReturned.data = nItem;
            lastItemReturned = null;
        }
    } // end of kwlistiter

    private Node<E> head, tail;
    private int size;

    public KWLinkedList() {
        head = tail = null;
        size = 0;
    }

    public void addFirst(E item) {
        Node<E> newNode = new Node<E>(item);
        if (this.head == null) {
            this.head = this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.size++;
    }

    public void addLast(E item) {
        Node<E> newNode = new Node<E>(item);
        if (this.head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        this.size++;
    }

    public boolean add(E item) {
        if (head == null) {
            head = tail = new Node<E>(item);
        } else {
            addLast(item);
        }
        return true;
    }

    public E getFirst() {
        return head != null ? head.data : null;
    }

    public Iterator<E> Iterator() {
        KWListIter iter = new KWListIter();
        return iter;
    }

    public ListIterator<E> ListIterator() {
        KWListIter iter = new KWListIter();
        return iter;
    }

    public ListIterator<E> ListIterator(int i) {
        KWListIter iter = new KWListIter(i);
        return iter;
    }

    public ListIterator<E> ListIterator(ListIterator<E> other) {
        KWListIter iter = new KWListIter((KWListIter) other);
        return iter;
    }

    public void add(int index, E item) {
        KWListIter iter = new KWListIter(index);
        iter.add(item);
    }

    public E get(int index) {
        KWListIter iter = new KWListIter(index);
        return iter.next();
    }

    public int Size() {
        return size;
    }

    // public boolean findLargest(E item, KWLinkedList<E> list2) {
    // ListIterator<E> thisiter = this.ListIterator();
    // ListIterator<E> otheriter = list2.ListIterator();

    // if (this.head == null) {
    // return false;
    // }

    // while (thisiter.hasNext()) {
    // // ! REMEMBER TO USE COMPARABLE
    // E val = thisiter.next();
    // if ((Comparable<E>)val.compareTo((Comparable<E>)item) > 0) {
    // otheriter.add(val);
    // }
    // }

    // return list2.head != null;
    // }

    public void revNodes() {
        // 0 or 1 nodes
        if (this.head == null || this.head.next == null) {
            System.out.println("Only 0 or 1 nodes in the list");
            return;
        } else {
            Node<E> thead = this.head;
            Node<E> tTail = this.tail;

            // Check if thead and tTail have crossed each other
            while (thead != null && thead != tTail && thead.prev != tTail) {
                E hdata = thead.data;
                E tdata = tTail.data;
                thead.data = tdata;
                tTail.data = hdata;
                thead = thead.next;
                tTail = tTail.prev;
            }
        }
    }

    public void insertFirstLast(E item) {
        Node<E> newNode = new Node<E>(item);
        if (this.head == null) {
            this.head = this.tail = newNode;
        } else {
            Node<E> newNode2 = new Node<E>(item);
            newNode.next = head;
            head.prev = newNode;
            this.head = newNode;

            tail.next = newNode2;
            newNode2.prev = tail;
            tail = newNode2;
        }
        this.size++;
    }

    public boolean deleteMultipleNodes(int count, char direction) {
        if (this.size < count) {
            return false;
        } else {
            ListIterator<E> iter = this.ListIterator();
            int curIndex = 0;
            switch (direction) {
                case 'f':
                    while (iter.hasNext() && curIndex != count) {
                        iter.next();
                        iter.remove();
                        curIndex++;
                    }
                    break;
                case 'b':
                    while (curIndex != count) {
                        this.tail = this.tail.prev;
                        this.tail.next = null;
                        curIndex++;
                    }
                    break;
            }
        }
        return true;
    }

    public static <E> void display2Directions(KWLinkedList<E> list, int n) {
        if (list.head == null || n + 1 > list.Size()) {
            return;
        }
        ListIterator<E> nIter = list.ListIterator(n + 1);
        ListIterator<E> pIter = list.ListIterator(n);
        while (nIter.hasNext() && pIter.hasPrevious()) {
            E nitem = nIter.next();
            E pitem = pIter.previous();
            System.out.print(nitem + " " + pitem + " ");
        }
        System.out.println();
    }

    public void printList() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.print(temp.data.toString() + " ");
            temp = temp.next;
        }
        System.out.println();
    }

} // ==end of KWLinkedList==/
