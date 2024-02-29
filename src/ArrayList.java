@SuppressWarnings("unchecked")
public class ArrayList<E extends Comparable<E>> {
    private int size, cap;
    private static final int INITIAL_CAP = 10;
    private E[] Data;

    public ArrayList() {
        this.size = 0;
        this.cap = INITIAL_CAP;
        this.Data = (E[]) new Object[cap];
    }

    public ArrayList(int cap) {
        if (cap <= 0) {
            this.cap = INITIAL_CAP;
        }
        this.size = 0;
        this.cap = cap;
        this.Data = (E[]) new Object[cap];
    }

    private void reallocate() {
        cap *= 2;
        E[] nData = (E[]) new Object[cap];
        for (int i = 0; i < this.Data.length; i++) {
            nData[i] = this.Data[i];
        }
        this.Data = nData;
    }

    public boolean add(E item) {
        if (this.size == this.cap) {
            reallocate();
        }
        this.Data[this.size] = item;
        size++;
        return true;
    }

    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else {
            if (this.size == this.cap) {
                reallocate();
            }
            // gather everything from the index, and shift right
            for (int i = this.size-1; i > index; i--) {
                this.Data[i] = this.Data[i-1];
            }
            this.Data[index] = item;
            size++;
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return this.Data[index];
    }

    public E set(int index, E item) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldVal = get(index);
        this.Data[index] = item;
        return oldVal;
    }


    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldVal = get(index);
        for (int i = index + 1; i < size; i++) {
            this.Data[i-1] = this.Data[i];
        }
        this.Data[size-1] = null;
        size--;
        return oldVal;
    }

    public int indexOf(E target) {
        for (int i = 0; i < this.size; i++) {
            if (get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public boolean remove(E obj) {
        if (indexOf(obj) == -1) {
            return false;
        }
        remove(indexOf(obj));
        return true;
    }

    public boolean contains(E obj) {
        return indexOf(obj) != -1;
    }

    public int Size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < Data.length; i++) {
            this.Data[i] = null;
        }
        this.size = 0;
    }


    public void removeAll(E obj) {
        if (indexOf(obj) == -1) {
            return;
        }
        remove(obj);
        removeAll(obj);
    }
}
