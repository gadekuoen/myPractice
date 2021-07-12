package cn.wjqixige.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Set;

/**
 * java.util.Properties继承于Hashtable，来表示一个持久的属性集。
 * 它使用键值结构存储数据，每个键及其对应值都是一个字符串。
 * 该类也被许多Java类使用，比如获取系统属性时，System.getProperties方法就是返回一个Properties对象。
 *
 * - public Object setProperty(String key, String value)： 保存一对属性。
 * - public String getProperty(String key)：使用此属性列表中指定的键搜索属性值。
 * - public Set<String> stringPropertyNames()：所有键的名称的集合。
 * - public void load(InputStream inStream)： 从字节输入流中读取键值对。
 */
public class PropertiesDemo {

    public static void main(String[] args) throws Exception {

//        Properties prop = new Properties();
//        prop.setProperty("filename", "a.txt");
//        prop.setProperty("length", "209385038");
//        prop.setProperty("location", "D:\\a.txt");
//        System.out.println(prop);
//
//        System.out.println(prop.getProperty("filename"));
//        System.out.println(prop.getProperty("length"));
//        System.out.println(prop.getProperty("location"));
//
//
//        Set<String> strings = prop.stringPropertyNames();
//        for (String key : strings) {
//            System.out.println(key + "--" + prop.getProperty(key));
//        }


//        Properties obj = System.getProperties();
//        System.out.println(obj.stringPropertyNames());

//        Properties props1 = new Properties();
//        props1.load(new FileInputStream("H:\\GitRepository\\javaSEDemo\\src\\main\\resources\\test.properties"));
//        Set<String> strings = props1.stringPropertyNames();
//        for (String key : strings) {
//            System.out.println(key + "--" + props1.getProperty(key));
//        }

    }
}
