package dataStructure.stack_and_queue;

public class MyCircularQueue1 implements CircularQueue{

    private int[] data;
    private int front;
    private int tail;
    private int size;

    public MyCircularQueue1(int k){
        data = new int[k];
        front = -1;
        tail = -1;
        size = k;
    }


    @Override
    public boolean enQueue(int value) {
        if (isFull())
            return false;
        if (isEmpty())
            front = 0;
        tail = (tail + 1) % size;
        data[tail] = value;
        return true;
    }

    @Override
    public boolean deQueue() {
        if (isEmpty())
            return false;
        if (front == tail){
            front = -1;
            tail = -1;
            return true;
        }
        front = (front - 1) % size;
        return true;
    }

    @Override
    public int Front() {
        if (isEmpty())
            return -1;
        return data[front];
    }

    //获取队尾元素。如果队列为空，返回 -1
    @Override
    public int Rear() {
        if (isEmpty()){
            return -1;
        }
        return data[tail];
    }

    @Override
    public boolean isEmpty() {
        return front == -1;
    }

    @Override
    public boolean isFull() {
        return (tail+1) % size == front;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail){
                res.append(", ");
            }
        }
        res.append("] tail");

        return res.toString();
    }
}
