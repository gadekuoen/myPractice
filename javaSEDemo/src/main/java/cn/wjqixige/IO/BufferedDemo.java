package cn.wjqixige.IO;

import java.io.*;

/**
 * 缓冲流,也叫高效流，是对4个基本的`FileXxx` 流的增强，所以也是4个流，按照数据类型分类：
 *      字节缓冲流：BufferedInputStream，BufferedOutputStream
 *      字符缓冲流：BufferedReader，BufferedWriter
 * 缓冲流的基本原理，是在创建流对象时，会创建一个内置的默认大小的缓冲区数组，通过缓冲区读写，减少系统IO次数，从而提高读写的效率。
 *
 * 构造方法：
 *      public BufferedInputStream(InputStream in) ：创建一个 新的缓冲输入流。
 *      public BufferedOutputStream(OutputStream out)： 创建一个新的缓冲输出流。
 *
 *      public BufferedReader(Reader in)：创建一个 新的缓冲输入流。
 *      public BufferedWriter(Writer out)： 创建一个新的缓冲输出流。
 *
 */
public class BufferedDemo {

    /**
     * 基本流
     */
    public static void baseStream(){

        long start = System.currentTimeMillis();
        try(
            FileInputStream fis = new FileInputStream("H:\\GitRepository\\javaSEDemo\\java.exe");
            FileOutputStream fos = new FileOutputStream("copy.exe");
        ){
            int b;
            while ((b = fis.read()) != -1){
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("普通流花费的时间：" + (end - start) + " 毫秒");
    }

    /**
     * 缓冲流
     */
    public static void bufferedStream(){
        long start = System.currentTimeMillis();

        try(
                BufferedInputStream fis = new BufferedInputStream(new FileInputStream("H:\\GitRepository\\javaSEDemo\\java.exe"));
                BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("copy.exe"));
        ){
            int b;
            while ((b = fis.read()) != -1){
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("缓冲流花费的时间：" + (end - start) + " 毫秒");
    }

    /**
     * 数组方式
     */
    public static void arrayStream(){
        long start = System.currentTimeMillis();

        try(
                BufferedInputStream fis = new BufferedInputStream(new FileInputStream("H:\\GitRepository\\javaSEDemo\\java.exe"));
                BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("copy.exe"));
        ){
            int b;
            byte[] bytes = new byte[8*1024];
            while ((b = fis.read(bytes)) != -1){
                fos.write(bytes,0,b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("缓冲流花费的时间：" + (end - start) + " 毫秒");
    }

    public static void main(String[] args){
        baseStream();
        bufferedStream();
        arrayStream();
    }


}
