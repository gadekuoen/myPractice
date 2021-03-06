package cn.wjqixige.NET.fileUpload;

import java.io.*;
import java.net.Socket;

/**
 * 文件上传案例的客户端：读取本地文件，上传到服务器，读取服务器回写的数据
 */
public class TCPClient {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("H:\\favicon.jpg");
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream os = socket.getOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes)) != -1){
            os.write(bytes, 0, len);
        }

        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        while ((len = is.read(bytes)) != -1){
            System.out.println(new String(bytes,0,len));
        }


        fis.close();
        socket.close();
    }

}
