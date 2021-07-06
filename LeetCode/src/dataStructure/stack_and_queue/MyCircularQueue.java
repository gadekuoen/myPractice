package dataStructure.stack_and_queue;

public class MyCircularQueue {
    private Integer[] data;
    private int head;
    private int tail;

    //构造器，设置队列长度为 k
    public MyCircularQueue(int k) {
        data = new Integer[k];
        head = 0;
        tail = 0;
    }

    //向循环队列插入一个元素。如果成功插入则返回真
    public boolean enQueue(int value) {
        data[tail] = value;
        tail++;
        return true;
    }

    //从循环队列中删除一个元素。如果成功删除则返回真
    public boolean deQueue() {
        if (isEmpty()){
            return false;
        }
        head++;
        return true;
    }

    //从队首获取元素。如果队列为空，返回 -1
    public int Front() {
        if (isEmpty()){
            return -1;
        }

        return data[head];
    }

    //获取队尾元素。如果队列为空，返回 -1
    public int Rear() {
        if (isEmpty()){
            return -1;
        }

        return data[tail];
    }

    //检查循环队列是否为空
    public boolean isEmpty() {
        return data == null || data.length == 0;
    }

    //检查循环队列是否已满
    public boolean isFull() {
        return tail == head + 1;
    }

    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */
    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(5);
        boolean res_1 = queue.enQueue(2);
        boolean res_2 = queue.enQueue(3);
        boolean res_3 = queue.enQueue(9);
        boolean res_4 = queue.enQueue(7);
        boolean res_5 = queue.enQueue(6);

        System.out.print("[");
        for (int i = 0; i < queue.data.length; i++) {
            if (i == queue.data.length - 1){
                System.out.print(queue.data[i]);
            } else {
                System.out.print(queue.data[i] + ", ");
            }
        }
        System.out.println("]");

        System.out.println(queue.head + ", " + queue.tail);

        boolean res_01 = queue.deQueue();

        System.out.println(queue.head + ", " + queue.tail);



    }
}


