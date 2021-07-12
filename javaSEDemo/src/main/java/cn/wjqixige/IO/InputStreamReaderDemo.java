package cn.wjqixige.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 转换流java.io.InputStreamReader，是Reader的子类，是从字节流到字符流的桥梁。
 * 它读取字节，并使用指定的字符集将其解码为字符。它的字符集可以由名称指定，也可以接受平台的默认字符集。
 *
 * 构造方法：
 *      InputStreamReader(InputStream in): 创建一个使用默认字符集的字符流。
 *      InputStreamReader(InputStream in, String charsetName): 创建一个指定字符集的字符流。
 */
public class InputStreamReaderDemo {

    public static void main(String[] args) throws Exception {

        String FileName = "H:\\GitRepository\\javaSEDemo\\a.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(FileName));
        InputStreamReader isr2 = new InputStreamReader(new FileInputStream(FileName) , "GBK");

        int read;
        while ((read = isr.read()) != -1) {
            System.out.print((char)read);
        }
        isr.close();

        while ((read = isr2.read()) != -1) {
            System.out.print((char)read);
        }
        isr2.close();
    }
}
