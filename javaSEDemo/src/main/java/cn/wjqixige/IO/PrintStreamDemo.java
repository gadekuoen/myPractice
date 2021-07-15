package cn.wjqixige.IO;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 构造方法:
 *      public PrintStream(String fileName)： 使用指定的文件名创建一个新的打印流。
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        // 调用系统的打印流,控制台直接输出97
        System.out.println(97);

        // 创建打印流,指定文件的名称
        PrintStream ps = new PrintStream("ps.txt");

        // 设置系统的打印流流向,输出到ps.txt
        System.setOut(ps);
        // 调用系统的打印流,ps.txt中输出97
        System.out.println(97);
    }
}
