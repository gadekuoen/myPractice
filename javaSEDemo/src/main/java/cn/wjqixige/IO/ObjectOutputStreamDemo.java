package cn.wjqixige.IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * java.io.ObjectOutputStream类，将Java对象的原始数据类型写出到文件,实现对象的持久存储。
 *
 * 构造方法：
 *      public ObjectOutputStream(OutputStream out)： 创建一个指定OutputStream的ObjectOutputStream。
 *
 * 一个对象要想序列化，必须满足两个条件:
 *     - 类必须实现java.io.Serializable接口，Serializable是一个标记接口，
 *     不实现此接口的类将不会使任何状态序列化或反序列化，会抛出NotSerializableException。
 *     - 该类的所有属性必须是可序列化的。如果有一个属性不需要可序列化的，则该属性必须注明是瞬态的，使用transient关键字修饰。
 */
public class ObjectOutputStreamDemo {

    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.name = "zhangsan";
        emp.address = "beiqinglu";
        emp.age = 20;
        try {
            // 创建序列化流对象
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.txt"));
            // 写出对象
            out.writeObject(emp);
            // 释放资源
            out.close();
            System.out.println("Serialized data is saved"); // 姓名，地址被序列化，年龄没有被序列化。
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

class Employee implements Serializable {
    String name;
    String address;
    transient int age; // transient瞬态修饰成员,不会被序列化
    public void addressCheck() {
        System.out.println("Address  check : " + name + " -- " + address);
    }
}
