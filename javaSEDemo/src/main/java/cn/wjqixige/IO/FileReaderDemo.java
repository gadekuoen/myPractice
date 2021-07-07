package cn.wjqixige.IO;

import java.io.FileReader;

/**
 * java.io.Reader抽象类是表示用于读取字符流的所有类的超类，可以读取字符信息到内存中。
 * 
 * - public void close() ：关闭此流并释放与此流相关联的任何系统资源。    
 * - public int read()： 从输入流读取一个字符。 
 * - public int read(char[] cbuf)： 从输入流中读取一些字符，并将它们存储到字符数组 cbuf中 。
 * 
 * java.io.FileReader extends InputStreamReader extends Reader
 * FileReader:文件字符输入流
 * 作用：把硬盘文件中的数据以字符的方式读取到内存中
 * 
 * 构造方法：
 *      FileReader(String filename)
 *      FileReader(File file)
 */
public class FileReaderDemo {

    public static void main(String[] args) throws Exception {

//        File file = new File("javaSEDemo\\a.txt");
//        FileReader fr = new FileReader(file);
        FileReader fr = new FileReader("javaSEDemo\\a.txt");
//        int len = 0;
//        while((len = fr.read()) != -1){
//            System.out.print((char)len);
//        }

        /** int read(char[] cbuf)一次读取多个字符，将字符读入数组。*/
        char[] cs = new char[1024];
        int len = 0;
        while((len = fr.read(cs)) != -1){
            System.out.println(new String(cs, 0, len));
        }


        fr.close();
    }
}
