@SuppressWarnings("unchecked")
public class HTableOpenAddr<E> {
    private int size, cap;
    private E[] Data;
    private static final int INIT_CAP = 10;

    public HTableOpenAddr() {
        this.size = 0;
        this.cap = INIT_CAP;
        this.Data = (E[]) new Object[cap];
    }

    public HTableOpenAddr(int ncap) {
        this.size = 0;
        this.cap = ncap;
        this.Data = (E[]) new Object[cap];
    }

    public int Size() {
        return size;
    }

    public void insertKeyLinear(E key) {
        if (this.size == this.cap) {
            System.out.println("HashTable is full :(");
            return;
        } else {
            int loc = (Integer) key % cap;
            int nloc = loc;
            int i = 1;
            while (this.Data[nloc] != null) {
                loc = (nloc + i) % this.cap;
                i++;
            }
            this.size++;
            this.Data[nloc] = key;
        }
    }

    public void insertKeyQuadratic(E key) {
        if (this.size == this.cap) {
            System.out.println("HashTable is full :(");
            return;
        } else {
            int loc = (Integer) key % cap;
            int nloc = loc;
            int i = 1;
            while (this.Data[nloc] != null) {
                loc = (nloc + (int) Math.pow(i, 2)) % this.cap;
                i++;
            }
            this.size++;
            this.Data[loc] = key;
        }
    }

    public int linearSearch(E key) {
        int loc = (Integer) key % cap;
        int nloc = loc;
        boolean quit = false;
        int i = 1;
        while (!quit && this.Data[nloc] != key) {
            if (this.Data[nloc].equals(key)) {
                quit = true;
            } else {
                nloc = (loc + i) % cap;
                if (nloc == loc) {
                    break;
                }
                i++;
            }
        }

        return quit ? nloc : cap;
    }

    public int QuadraticSearch(E key) {
        int loc = (Integer) key % cap;
        int nloc = loc;
        boolean quit = false;
        int i = 1;
        while (!quit && this.Data[nloc] != key) {
            if (this.Data[nloc].equals(key)) {
                quit = true;
            } else {
                nloc = (loc + (int) Math.pow(i, 2)) % cap;
                if (nloc == loc) {
                    break;
                }
                i++;
            }
        }

        return quit ? nloc : cap;
    }

    public E get(int i) {
        return this.Data[i];
    }

    public boolean linearRemove(E item) {
        int loc = (int) item % this.cap;
        int nloc = loc;
        int counter = 1;
        if (!this.Data[loc].equals(item)) {
            while (counter != this.cap) {
                nloc = (loc + counter) % this.cap;
                if (this.Data[nloc].equals(item)) {
                    this.Data[nloc] = null;
                    this.size--;
                    return true;
                } else {
                    counter++;
                }
            }
        }
        return false;
    }
}
