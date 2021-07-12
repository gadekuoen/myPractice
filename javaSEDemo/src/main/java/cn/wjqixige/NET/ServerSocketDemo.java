package cn.wjqixige.NET;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP通信的服务器端：接收客户端的请求，读取客户端发送的数据，给客户端回写数据
 * 表示服务器的类：
 *      java.net.ServerSocket:锡类实现服务器套接字
 * 构造方法：
 *      ServerSocket(int port) 创建绑定到特定端口的服务器套接字
 *
 * 服务器必须明确一件事，必须得知道是哪个客户端请求的服务器，所以可以使用accept方法获取到请求的客户端对象Socket
 * 成员方法：
 *      Socket accept() 侦听并接受到此套接字的连接
 */
public class ServerSocketDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes, 0, len));
        OutputStream os = socket.getOutputStream();
        os.write("收到谢谢".getBytes());

        socket.close();
        server.close();
    }
}
