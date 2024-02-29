public interface QueueOps<E> {
    public boolean offer(E item);
    public E peek();
    public E poll();
    public boolean isEmpty();
}
