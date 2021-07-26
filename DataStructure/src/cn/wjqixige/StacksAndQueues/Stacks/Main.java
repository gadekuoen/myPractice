package cn.wjqixige.StacksAndQueues.Stacks;

import java.util.Random;

public class Main {

//    public static void main(String[] args) {
//        ArrayStack<Integer> stack = new ArrayStack<>();
//        for (int i = 0; i < 5; i++) {
//            stack.push(i);
//            System.out.println("Stack: " + stack);
//        }
//
//        stack.pop();
//        System.out.println("Stack: " + stack);

        //比较数组栈和链表栈的性能
//        int opCount = 1000000;
//        ArrayStack<Integer> arrayStack = new ArrayStack<>();
//        double time1 = testStack(arrayStack,opCount);
//        System.out.println("ArrayStack, time: " + time1 + " s");
//
//        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
//        double time2 = testStack(linkedListStack,opCount);
//        System.out.println("LinkedListStack, time: " + time2 + " s");

        // 其实这个时间比较复杂，因为LinkListStack中包含更多的new操作
//    }

    //测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
    private static double testStack(Stack<Integer> stack, int opCount){

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
