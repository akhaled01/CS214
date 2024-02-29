import java.util.*;

public class LinkedQueue<E extends Comparable<E>> implements QueueOps<E> {
    private static class Node<T extends Comparable<T>> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private class Iter implements Iterator<E> {
        Node<E> itr = front;

        @Override
        public boolean hasNext() {
            return itr.next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E rval = itr.data;
            itr = itr.next;
            return rval;
        }
        
    }

    private Node<E> front, rear;
    private int size;


    @Override
    public boolean offer(E item) {
        Node<E> newNode = new Node<E>(item);
        if (front == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = rear.next;
        }
        size++;
        return true;
    }

    @Override
    public E peek() {
        if (front == null) {
            return null;
        }
        return front.data;
    }

    @Override
    public E poll() {
        if (front == null) {
            throw new NoSuchElementException();
        }
        E ol = front.data;
        front = front.next;
        size--;
        return ol;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    public Iterator<E> Iterator () {
        return new Iter();
    }

    public int Size() {
        return size;
    }
}
