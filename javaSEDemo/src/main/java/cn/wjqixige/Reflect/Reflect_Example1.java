package cn.wjqixige.Reflect;

@Pro(className = "cn.wjqixige.Reflect.domain.Student", methodName = "sleep")
public class Reflect_Example1 {

    public static void main(String[] args) {
        //1. 解析注解
        //1.1 获取该类的字节码文件对象
        Class<Reflect_Example1> reflect_example1Class = Reflect_Example1.class;
        //2. 获取上边的注解对象
        //其实就是在内存中生成了一个该注解接口的子类实现对象
        Pro an = reflect_example1Class.getAnnotation(Pro.class);
        //3. 调用注解对象中定义的抽象方法，获取返回值
        String className = an.className();
        String methodName = an.methodName();

        System.out.println(className);
        System.out.println(methodName);
    }

}
