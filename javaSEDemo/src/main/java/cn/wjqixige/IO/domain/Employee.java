package cn.wjqixige.IO.domain;

import java.io.Serializable;

/**
 * 当JVM反序列化对象时，能找到class文件，但是class文件在序列化对象之后发生了修改，
 * 那么反序列化操作也会失败，抛出一个InvalidClassException异常。
 * 发生这个异常的原因如下：
 * - 该类的序列版本号与从流中读取的类描述符的版本号不匹配
 * - 该类包含未知数据类型
 * - 该类没有可访问的无参数构造方法
 *
 * Serializable接口给需要序列化的类，提供了一个序列版本号。
 * serialVersionUID该版本号的目的在于验证序列化的对象和对应类是否版本匹配。
 */
public class Employee implements Serializable {
    // 加入序列版本号
    private static final long serialVersionUID = 1L;
    String name;
    String address;
    transient int age; // transient瞬态修饰成员,不会被序列化

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addressCheck() {
        System.out.println("Address  check : " + name + " -- " + address);
    }

}
