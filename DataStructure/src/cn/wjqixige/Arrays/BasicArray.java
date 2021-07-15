package cn.wjqixige.Arrays;

/**
 * 自定义数组
 */
public class BasicArray {

    private int[] data;
    private int size;

    public BasicArray(){
        this(10);
    }

    //构造函数，传入数组的容量capacity构造Array
    public BasicArray(int capacity){
        data = new int[capacity];
        size = 0;
    }

    //获取数组中的元素个数
    public int getSize(){
        return size;
    }

    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    //判断数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //向所有元素后添加一个新元素
    public void addLast(int e){
        add(size, e);
    }

    //在所有元素前添加一个新元素
    public void addFirst(int e){
        add(0, e);
    }

    //在第index个位置插入一个元素
    public void add(int index, int e){
        if (size == data.length)
            throw new IllegalArgumentException("Add failed. BasicArray is full.");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        for (int i = size - 1; i >= index ; i--) {
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    //获取index索引位置的元素
    int get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    //修改index索引位置的元素为e
    void set(int index, int e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    //查找数组中是否有元素e
    public boolean contains(int e){
        for (int i = 0; i < size; i++) {
            if (data[i] == e){
                return true;
            }
        }
        return false;
    }

    //查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(int e){

        for (int i = 0; i < size; i++) {
            if (data[i] == e){
                return i;
            }
        }
        return -1;
    }

    // 从数组中删除index位置的元素，返回删除的元素
    public int remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        int ret = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        return ret;
    }

    // 从数组中删除第一个元素，返回删除的元素
    public int removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素，返回删除的元素
    public int removeLast(){
        return remove(size-1);
    }

    //从数组中删除元素e
    public void removeElement(int e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("BasicArray: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1){
                res.append(", ");
            }
        }
        res.append(']');

        return res.toString();
    }


//    public static void main(String[] args) {
//        BasicArray arr = new BasicArray(20);
//        for (int i = 0; i < 10; i++) {
//            arr.addLast(i);
//        }
//        System.out.println(arr);
//
//        arr.add(1,100);
//        System.out.println(arr);
//
//        arr.addFirst(-1);
//        System.out.println(arr);
//
//        arr.remove(2);
//        System.out.println(arr);
//
//        arr.removeElement(4);
//        System.out.println(arr);
//
//        arr.removeFirst();
//        System.out.println(arr);
//    }
}
