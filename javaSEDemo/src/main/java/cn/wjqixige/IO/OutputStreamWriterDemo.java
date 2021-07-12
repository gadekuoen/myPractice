package cn.wjqixige.IO;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 转换流java.io.OutputStreamWriter，是Writer的子类，是从字符流到字节流的桥梁。
 * 使用指定的字符集将字符编码为字节。它的字符集可以由名称指定，也可以接受平台的默认字符集。
 *
 * 构造方法：
 *  - OutputStreamWriter(OutputStream in): 创建一个使用默认字符集的字符流。
 *  - OutputStreamWriter(OutputStream in, String charsetName): 创建一个指定字符集的字符流。
 */
public class OutputStreamWriterDemo {

    public static void main(String[] args) throws Exception{

        String FileName = "H:\\GitRepository\\javaSEDemo\\out.txt";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(FileName));

        osw.write("你好");
        osw.close();

        String FileName2 = "H:\\GitRepository\\javaSEDemo\\out2.txt";
        OutputStreamWriter osw2 = new OutputStreamWriter(new FileOutputStream(FileName2),"GBK");
        osw2.write("你好");
        osw2.close();
    }
}
