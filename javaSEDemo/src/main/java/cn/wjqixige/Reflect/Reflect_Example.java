package cn.wjqixige.Reflect;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 写一个“框架”，可以创建任意类的对象，可以执行任意方法
 */
public class Reflect_Example {

    public static void main(String[] args) throws Exception {
        //1. 加载配置文件
        //1.1 创建Properties对象
        Properties pro = new Properties();
        //1.2 加载配置文件，转换为一个集合
        ClassLoader classLoader = Reflect_Example.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        pro.load(is);

        //2. 获取配置文件定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //3. 加载该类进内存
        Class cls = Class.forName(className);

        //4. 创建对象
        Object obj = cls.newInstance();
        //5. 获取方法对象
        Method method = cls.getMethod(methodName);
        //6. 执行方法
        method.invoke(obj);
    }
}
