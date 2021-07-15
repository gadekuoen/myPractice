package cn.wjqixige.StacksAndQueues.Queues;

public interface Queue<E> {

    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
