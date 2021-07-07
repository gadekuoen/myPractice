package cn.wjqixige.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopyDemo {
    public static void main(String[] args) throws Exception {
        //1 创建流对象
        //1.1 指定数据源
        FileInputStream fis = new FileInputStream("javaSEDemo\\timg.jpg");
        //1.2 指定目的地
        FileOutputStream fos = new FileOutputStream("javaSEDemo\\timg_copy.jpg");
        //2 读写数据
        byte[] bytes = new byte[1024];
        int len;
        while((len = fis.read(bytes)) != -1){
            fos.write(bytes,0,len);
        }

        //3 关闭资源
        fos.close();
        fis.close();
    }
}
