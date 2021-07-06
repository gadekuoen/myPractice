package dataStructure.stack_and_queue;

import java.util.ArrayList;
import java.util.List;

public class MyQueue {

    private List<Integer> data;
    private int p_start;

    public MyQueue(){
        data = new ArrayList<>();
        p_start = 0;
    }

    public boolean enqueue(int num){
        data.add(num);
        return true;
    }

    public boolean dequeue(){
        if (isEmpty()){
            return false;
        }
        p_start++;
        return true;
    }

    private boolean isEmpty(){
        return p_start >= data.size();
    }

    public int front(){
        return data.get(p_start);
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.enqueue(5);
        myQueue.enqueue(7);
        myQueue.enqueue(2);
        myQueue.enqueue(4);
        System.out.println(myQueue.data);

        myQueue.dequeue();
        if (!myQueue.isEmpty()){
            System.out.println(myQueue.p_start + "," + myQueue.front());
        }

        myQueue.dequeue();
        if (!myQueue.isEmpty()){
            System.out.println(myQueue.p_start + "," + myQueue.front());
        }

        myQueue.dequeue();
        if (!myQueue.isEmpty()){
            System.out.println(myQueue.p_start + "," + myQueue.front());
        }

        System.out.println(myQueue.data);

    }
}
