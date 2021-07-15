package cn.wjqixige.IO;

import cn.wjqixige.IO.domain.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * ObjectInputStream反序列化流，将之前使用ObjectOutputStream序列化的原始数据恢复为对象。
 * 构造方法：
 *      public ObjectInputStream(InputStream in)： 创建一个指定InputStream的ObjectInputStream。
 *
 * 注意：
 *    对于JVM可以反序列化对象，它必须是能够找到class文件的类。如果找不到该类的class文件，则抛出一个ClassNotFoundException异常。
 */
public class ObjectInputStreamDemo {

    public static void main(String[] args) {
        Employee e = null;
        try {
            // 创建反序列化流
            FileInputStream fileIn = new FileInputStream("javaSEDemo\\employee.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // 读取一个对象
            e = (Employee) in.readObject();
            // 释放资源
            in.close();
            fileIn.close();
        }catch(IOException i) {
            // 捕获其他异常
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)  {
            // 捕获类找不到异常
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        // 无异常,直接打印输出
        System.out.println("Name: " + e.getName());
        System.out.println("Address: " + e.getAddress());
        System.out.println("age: " + e.getAge());
    }

}
