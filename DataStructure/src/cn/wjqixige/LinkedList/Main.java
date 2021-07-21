package cn.wjqixige.LinkedList;

public class Main {

    public static void main(String[] args) {

        LinkedList2<Object> linkedList2 = new LinkedList2<>();
        for (int i = 0; i < 5; i++) {
            linkedList2.addFirst(i);
            System.out.println(linkedList2);
        }

        linkedList2.add(2,666);
        System.out.println(linkedList2);
    }
}
