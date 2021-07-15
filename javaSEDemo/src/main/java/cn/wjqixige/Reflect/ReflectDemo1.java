package cn.wjqixige.Reflect;

import cn.wjqixige.Reflect.domain.Person;

import java.lang.reflect.Method;

public class ReflectDemo1 {

    /**
     * 获取class对象的方式：
     *      1. Class.forName("全类名")：将字节码文件加载进内存，返回Class对象 --多用于配置文件，读取文件，加载类
     *      2. 类名.class：通过类名的属性class获取  -- 多用于参数的传递
     *      3. 对象.getClass()：getClass()方法在Obejct类中定义着  -- 多用于对象获取字节码的方式
     *
     *  同一个字节码文件（*.class）在一次程序运行过程中，只会被加载一次，
     *  不论通过哪一种方式获取的Class对象都是同一个。
     *
     *  Class对象功能：
     *      * 获取功能：
     *          1. 获取成员变量
     *              Field[] getField()  获取所有public修饰的成员变量
     *              Field getField(String name)  获取指定名称的public修饰的成员变量
     *
     *              Field[] getDeclaredFields()  获取所有的成员变量，不考虑修饰符
     *              Field getDeclaredField(String name)
     *          2. 获取构造方法
     *              Constructor<?>[] getConstructors()
     *              Constructor<T> getConstructors(类<?>... parameterTypes)
     *
     *              Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
     *              Constructor<?>[] getDeclaredConstructors()
     *          3. 获取成员方法
     *              Method[] getMethods()
     *              Method getMethod(String name, 类<?>... parameterTypes)
     *
     *              Method[] getDeclaredMethods()
     *              Method getDeclaredMethod(String name, 类<?>... parameterTypes)
     *          4. 获取类名
     *              String getName()
     */
    public static void main(String[] args) throws Exception {

        //获取对象的三种方式
//        Class cls = Class.forName("cn.wjqixige.Reflect.domain.Person");
//        System.out.println(cls);
//
//        Class cls2 = Person.class;
//        System.out.println(cls2);
//
//        Person p = new Person();
//        Class cls3 = p.getClass();
//        System.out.println(cls3);
//
//        System.out.println(cls == cls2);
//        System.out.println(cls == cls3);

        //获取成员变量
//        Class personClass = Person.class;
//        Field[] field = personClass.getFields();
//        for (Field field1 : field) {
//            System.out.println(field1);
//        }
//
//        System.out.println("------------");
//        Field a = personClass.getField("a");
//        Person p = new Person();
//        Object value = a.get(p);
//        System.out.println(value);
//        a.set(p,"张三");
//        System.out.println(p);
//
//        System.out.println("------------");
//        Field[] fields = personClass.getDeclaredFields();
//        for (Field field1 : fields) {
//            System.out.println(field1);
//        }
//
//        Field d = personClass.getDeclaredField("d");
//        d.setAccessible(true);//暴力反射。忽略访问权限修饰符的安全检查
//        Object value2 = d.get(p);
//        System.out.println(value2);

        //获取构造方法
//        Class personClass1 = Person.class;
//        Constructor constructor = personClass1.getConstructor(String.class, int.class);
//        System.out.println(constructor);
//        Object person1 = constructor.newInstance("张三", 23);
//        System.out.println(person1);
//
//        System.out.println("----------------");
//        Class personClass2 = Person.class;
//        Constructor constructor1 = personClass2.getConstructor();
//        Object person2 = constructor1.newInstance();
//        System.out.println(person2);

        //获取成员方法
        Class personClass3 = Person.class;
        Method eat_method = personClass3.getMethod("eat");
        Person p = new Person();
        eat_method.invoke(p);

        Method eat_method2 = personClass3.getMethod("eat", String.class);
        eat_method2.invoke(p,"饭");

        Method[] methods = personClass3.getMethods();
        for (Method method : methods) {
            System.out.println(method);
            String name = method.getName();
            System.out.println(name);
        }

        //获取类名
        String className = personClass3.getName();
        System.out.println(className);

    }
}
