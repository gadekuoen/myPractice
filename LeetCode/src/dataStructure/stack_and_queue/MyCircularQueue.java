package dataStructure.stack_and_queue;

public class MyCircularQueue implements CircularQueue{
    private int[] data;
    private int front;
    private int tail;
    private int size;

    //构造器，设置队列长度为 k
    public MyCircularQueue(int k) {
        data = new int[k+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    //向循环队列插入一个元素。如果成功插入则返回真
    @Override
    public boolean enQueue(int value) {
        if (isFull()){
            return false;
        }
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
        return true;
    }

    //从循环队列中删除一个元素。如果成功删除则返回真
    @Override
    public boolean deQueue() {
        if (isEmpty())
            return false;
        data[front] = 0;
        front = (front + 1) % data.length;
        size--;
        return true;
    }

    //从队首获取元素。如果队列为空，返回 -1
    @Override
    public int Front() {
        if (isEmpty()){
            return -1;
        }
        return data[front];
    }

    //获取队尾元素。如果队列为空，返回 -1
    @Override
    public int Rear() {
        if (isEmpty()){
            return -1;
        }
        return data[(tail-1 + data.length) % data.length];
    }

    //检查循环队列是否为空
    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    //检查循环队列是否已满
    @Override
    public boolean isFull() {
        return front == (tail + 1) % data.length;
    }


    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        boolean b = circularQueue.enQueue(1);// 返回 true
        boolean b1 = circularQueue.enQueue(2);// 返回 true
        boolean b2 = circularQueue.enQueue(3);// 返回 true
        boolean b3 = circularQueue.enQueue(4);// 返回 false，队列已满
        int rear = circularQueue.Rear();// 返回 3
        boolean full = circularQueue.isFull();// 返回 true
        boolean b4 = circularQueue.deQueue();// 返回 true
        boolean b5 = circularQueue.enQueue(4);// 返回 true
        int rear1 = circularQueue.Rear();// 返回 4

        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(rear);
        System.out.println(full);
        System.out.println(b4);
        System.out.println(b5);
        System.out.println(rear1);


    }
}


