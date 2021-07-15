package cn.wjqixige.StacksAndQueues.Stacks;

public class Main {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println("Stack: " + stack);
        }

        stack.pop();
        System.out.println("Stack: " + stack);
    }
}
