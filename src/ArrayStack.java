import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayStack<E> implements StackOps<E> {
    private E[] Data;
    private int topOfStack;
    private static final int INITIAL_CAP = 10;

    public ArrayStack() {
        this.topOfStack = -1;
        this.Data = (E[]) new Object[INITIAL_CAP];
    }

    public ArrayStack(int cap) {
        this.topOfStack = -1;
        if (cap <= 0) {
            this.Data = (E[]) new Object[INITIAL_CAP];
        }
        this.Data = (E[]) new Object[cap];
    }

    public ArrayStack(ArrayStack<E> other) {
        this.topOfStack = other.topOfStack;
        this.Data = (E[]) new Object[other.topOfStack + 1];

        for (int i = 0; i < other.topOfStack + 1; i++) {
            this.Data[i] = other.Data[i];
        }
    }

    private void reallocate() {
        E[] nData = (E[]) new Object[this.Data.length * 2];
        for (int i = 0; i < this.Data.length; i++) {
            nData[i] = this.Data[i];
        }
        this.Data = nData;
    }

    public boolean isEmpty() {
        return topOfStack == -1;
    }

    public E push(E item) {
        if (this.topOfStack == this.Data.length - 1) {
            reallocate();
        }
        topOfStack++;
        this.Data[topOfStack] = item;
        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.Data[topOfStack];
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E val = this.Data[topOfStack];
        this.Data[topOfStack] = null;
        topOfStack--;
        return val;
    }


    public void printStack() {
        ArrayStack<E> cp = new ArrayStack<E>(this);
        while (!cp.isEmpty()) {
            System.out.print(cp.pop() + " ");
        }
        System.out.println();
    }

    public static void rearrangeStack(ArrayStack<Integer> st, int item) {
        ArrayStack<Integer> stcopy2 = new ArrayStack<Integer>(st);
        ArrayStack<Integer> fStack = new ArrayStack<Integer>();

        // only push less than item to fstack
        System.out.println("1");
        while (!st.isEmpty()) {
            int curitem = st.pop();
            if (curitem < item) {
                fStack.push(curitem);
            }
        }

        // push everything else to fstack (greater than item)
        System.out.println("2");
        while (!stcopy2.isEmpty()) {
            int curitem = stcopy2.pop();
            if (curitem > item) {
                fStack.push(curitem);
            }
        }

        // reverse the stack into empty st
        System.out.println("3");
        while (!fStack.isEmpty()) {
            st.push(fStack.pop());
        }
    }
}
