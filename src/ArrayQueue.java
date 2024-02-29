import java.util.*;

@SuppressWarnings("unchecked")
public class ArrayQueue<E> implements QueueOps<E> {
    private int front, rear, size, cap;
    private static final int INIT_CAP = 10;
    private E[] Data;

    private class Iter implements Iterator<E> {
        private int count = 0, index;

        public Iter() {
            index = front;
        }

        public boolean hasNext() {
            return count < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E rval = Data[index];
            index = (index + 1) % cap;
            count++;
            return rval;
        }
    }

    public ArrayQueue() {
        this.Data = (E[]) new Object[INIT_CAP];
        this.cap = INIT_CAP;
        front = rear = size = 0;
    }

    public ArrayQueue(int cap) {
        this.Data = cap <= 0 ? (E[]) new Object[INIT_CAP] : (E[]) new Object[cap];
        this.cap = cap <= 0 ? INIT_CAP : cap;
        front = size = 0;
        rear = cap - 1;
    }

    public ArrayQueue(ArrayQueue<E> other) {
        this.front = other.front;
        this.rear = other.rear;
        this.size = other.size;
        this.cap = other.cap;
        this.Data = (E[]) new Object[other.cap];
        for (int i = 0; i < this.size; i = (i + 1) % cap) {
            this.Data[i] = other.Data[i];
        }
    }

    private void reallocate() {
        cap *= 2;
        E[] nData = (E[]) new Object[cap];
        int j = this.front;
        for (int i = 0; i < size; i++) {
            nData[i] = this.Data[j];
            j = (j + 1) % cap;
        }
        front = 0;
        rear = size - 1;
        this.Data = nData;
    }

    public boolean offer(E item) {
        if (this.size == this.cap) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % cap;
        this.Data[rear] = item;
        return true;
    }

    public E peek() {
        if (this.size == 0) {
            return null;
        }
        return this.Data[front];
    }

    public E poll() {
        if (this.size == 0) {
            return null;
        }
        E val = this.Data[front];
        front = (front + 1) % cap;
        size--;
        return val;
    }

    public int Size() {
        return size;
    }

    public Iterator<E> iterator() {
        return new Iter();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public int countSimilar(ArrayQueue<E> q2) {
        int counter = 0;
        if (this.Data[this.front] == q2.Data[q2.front]) {
            counter++;
        }
        int index = this.front + 1;
        while (this.Data[index] != null) {
            if (this.Data[index].equals(q2.Data[index])) {
                counter++;
            }
            index = (index + 1) % cap;
        }
        return counter;
    }

    public void mergeQueues(ArrayQueue<E> q1, ArrayQueue<E> q2, ArrayQueue<E> q3) {
        ArrayQueue<E> q1c = new ArrayQueue<E>(q1);
        ArrayQueue<E> q2c = new ArrayQueue<E>(q2);

        while (!q1c.isEmpty() || !q2c.isEmpty()) {
            E q1Item = q1c.poll();
            if (q1Item != null) {
                q3.offer(q1Item);
            }
            E q2Item = q2c.poll();
            if (q2Item != null) {
                q3.offer(q2Item);
            }
        }
    }

    public static boolean rearrangeQueue(ArrayQueue<Integer> q1, int item) {
        if (q1.isEmpty()) {
            return false;
        } else {
            ArrayQueue<Integer> q1c = new ArrayQueue<Integer>();
            ArrayQueue<Integer> q1cc = new ArrayQueue<Integer>(q1);

            // poll to q1c
            while (!q1.isEmpty()) {
                int polled = q1.poll();
                if (polled < item) {
                    q1c.offer(polled);
                }
            }

            // add to q1
            while (!q1c.isEmpty()) {
                q1.offer(q1c.poll());
            }

            // add rest from q1cc
            Iterator<Integer> iter = q1cc.iterator();
            while (iter.hasNext()) {
                int cur = iter.next();
                if (cur > item) {
                    q1.offer(cur);
                }
            }
        }
        return true;
    }
}
