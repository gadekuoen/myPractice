package cn.wjqixige.NET;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP通信的客户端：向服务器发送连接请求，给服务器发送数据，读取服务器回写的数据
 * 表示客户端的类：
 *      java.net.Socket：此类实现客户端套接字。套接字是两台机器间通信的断电。
 *
 * 构造方法：
 *      Socket(String host, int port)创建一个流套接字并将其连接到指定主机上的指定端口号。
 * 成员方法：
 *      OutputStream getOutputStream() 返回此套接字的输出流
 *      InputStream getInputStream() 返回此套接字的输入流
 *      void close() 关闭此套接字
 */
public class SocketDemo {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",8888);
        OutputStream os = socket.getOutputStream();
        os.write("你好服务器".getBytes());

        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes,0,len));

        socket.close();

    }
}
