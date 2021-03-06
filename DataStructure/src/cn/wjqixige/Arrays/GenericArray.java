package cn.wjqixige.Arrays;

/**
 * 使用泛型
 */
public class GenericArray<E> {

    private E[] data;
    private int size;

    public GenericArray(){
        this(10);
    }

    //构造函数，传入数组的容量capacity构造Array
    public GenericArray(int capacity){
        data = (E[]) new Object[capacity];
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
    public void addLast(E e){
        add(size, e);
    }

    //在所有元素前添加一个新元素
    public void addFirst(E e){
        add(0, e);
    }

    //在第index个位置插入一个元素
    public void add(int index, E e){
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
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    //修改index索引位置的元素为e
    void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    //查找数组中是否有元素e
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){  //应用类型比较 == 改为 equals
                return true;
            }
        }
        return false;
    }

    //查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){

        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    // 从数组中删除index位置的元素，返回删除的元素
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        E ret = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        data[size] = null; // loitering objects != memory leak
        return ret;
    }

    // 从数组中删除第一个元素，返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素，返回删除的元素
    public E removeLast(){
        return remove(size-1);
    }

    //从数组中删除元素e
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("GenericArray: size = %d, capacity = %d\n", size, data.length));
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
//
//        GenericArray<Student> arr = new GenericArray<Student>();
//        arr.addLast(new Student("Alice", 100));
//        arr.addLast(new Student("Bob", 66));
//        arr.addLast(new Student("Charlie", 88));
//
//        System.out.println(arr);
//    }
}
