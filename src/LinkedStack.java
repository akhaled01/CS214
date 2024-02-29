import java.util.NoSuchElementException;

/**
 * 
 * E extends comparable to support allowed comparing
 * 
 */

@SuppressWarnings("unused")
public class LinkedStack<E extends Comparable<E>> implements StackOps<E> {
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

    private Node<E> topOfStack;

    public LinkedStack() {
        topOfStack = null;
    }

    public LinkedStack(LinkedStack<E> other) {
        if (other.isEmpty()) {
            this.topOfStack = null;
        } else {
            Node<E> newNode = new Node<E>(other.topOfStack.data);
            this.topOfStack = newNode;
            Node<E> ptr = other.topOfStack.next;
            Node<E> ptr1 = this.topOfStack;
            while (ptr != null) {
                Node<E> tempo = new Node<E>(ptr.data);
                ptr1.next = newNode;
                ptr = ptr.next;
                ptr1 = ptr1.next;
            }
        }
    }

    public boolean isEmpty() {
        return topOfStack == null;
    }

    @Override
    public E pop() {
        E val = this.topOfStack.data;
        this.topOfStack = this.topOfStack.next;
        return val;
    }

    @Override
    public E push(E item) {
        this.topOfStack = new Node<E>(item, this.topOfStack);
        return item;
    }

    @Override
    public E peek() {
        if (topOfStack == null) {
            throw new NoSuchElementException();
        }
        return topOfStack.data;
    }

    
}
