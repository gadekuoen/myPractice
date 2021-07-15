package cn.wjqixige.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * java.io.OutputStream抽象类是表示字节输出流的所有类的超类，将指定的字节信息写出到目的地。
 * 它定义了字节输出流的基本共性功能方法。
 *
 * - public void close() ：关闭此输出流并释放与此流相关联的任何系统资源。
 * - public void flush() ：刷新此输出流并强制任何缓冲的输出字节被写出。
 * - public void write(byte[] b)：将 b.length字节从指定的字节数组写入此输出流。
 * - public void write(byte[] b, int off, int len) ：从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。
 * - public abstract void write(int b) ：将指定的字节输出流。
 *
 * java.io.FileOutputStream extends OutputStream
 * FileOutputStream：文件字节输出流
 * 作用：把内存中的数据写入到硬盘的文件中
 *
 * 构造方法：
 *      FileOutputStream(String name)创建一个向具有指定名称的文件中写入数据的输出文件流
 *      FileOutputStream(File file)创建一个向指定file对象表示的文件中写入数据的文件输出流
 *
 * 写换行：
 *      windows: \r\n
 *      linux: /n
 *      mac: /r
 */
public class OutputStreamDemo {

    public static void main(String[] args) throws Exception {
        //1.创建一个FileOutputStream对象
//        FileOutputStream fos = new FileOutputStream("javaSEDemo\\a.txt");
        FileOutputStream fos = new FileOutputStream("javaSEDemo\\a.txt",true);
//        fos.write(97);
//        fos.close();

        /** 一次写多个字节的方法 */
//        byte[] bytes = {65,66,67,68,69};
//        fos.write(bytes);
//        fos.write(bytes, 1,3);

//        byte[] bytes1 = "你好".getBytes();
//        System.out.println(cn.wjqixige.Arrays.toString(bytes1));
//        fos.write(bytes1);

        /**
         * 追加写/续写
         * - public FileOutputStream(File file, boolean append)： 创建文件输出流以写入由指定的 File对象表示的文件。
         * - public FileOutputStream(String name, boolean append)： 创建文件输出流以指定的名称写入文件。
         * */
        for (int i = 0; i < 10; i++) {
            fos.write("你好".getBytes());
            fos.write("\r\n".getBytes());
        }


        fos.close();
    }
}
