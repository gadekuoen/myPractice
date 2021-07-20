package dataStructure.stack_and_queue;

public interface CircularQueue {

    //向循环队列插入一个元素。如果成功插入则返回真
    public boolean enQueue(int value);

    //从循环队列中删除一个元素。如果成功删除则返回真
    public boolean deQueue();

    //从队首获取元素。如果队列为空，返回 -1
    public int Front();

    //获取队尾元素。如果队列为空，返回 -1
    public int Rear();

    //检查循环队列是否为空
    public boolean isEmpty();

    //检查循环队列是否已满
    public boolean isFull();
}
