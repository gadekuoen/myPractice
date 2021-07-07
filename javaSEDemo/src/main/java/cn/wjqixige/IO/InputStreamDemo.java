package cn.wjqixige.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * java.io.InputStream:字节输入流
 * 此抽象类是表示字节输入流的所有类的超类
 *
 * - public void close() ：关闭此输入流并释放与此流相关联的任何系统资源。
 * - public abstract int read()： 从输入流读取数据的下一个字节。
 * - public int read(byte[] b)： 从输入流中读取一些字节数，并将它们存储到字节数组 b中 。
 *
 * java.io.FileInputStream extends InputStream
 * FileInputStream:文件字节输入流
 * 作用：把硬盘文件中的数据，读取到内存中使用
 *
 * 构造方法：
 *      FileInputStream(String name)
 *      FileInputStream(File file)
 */

public class InputStreamDemo {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("javaSEDemo\\a.txt");
        /** read方法，每次可以读取一个字节的数据，提升为int类型，读取到文件末尾，返回-1 */
        int len;

//        while((len = fis.read()) != -1){
//            System.out.print((char)len);
//        }

        /**
         * 一次读取多个字节
         * int read(byre[] b) 从输入流中读取一定数量的字节，并将其存储在缓冲区数组b中
         * */
        byte[] bytes = new byte[1024];
//        int read = fis.read(bytes);
//        System.out.println(read);
//        System.out.println(Arrays.toString(bytes));
//        System.out.println(new String(bytes));
        while((len = fis.read(bytes)) != -1){
            System.out.print(new String(bytes,0,len));
        }


        fis.close();
    }

}
