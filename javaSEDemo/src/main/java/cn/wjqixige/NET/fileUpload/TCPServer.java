package cn.wjqixige.NET.fileUpload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * 文件上传的服务器端：读取客户端上传的文件，保存到服务器的硬盘上，给客户端回写“上传成功”
 */
public class TCPServer {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8888);

        while (true){
            Socket socket = server.accept();

            new Thread(() -> {
                try{
                    InputStream is = socket.getInputStream();
                    File file = new File("javaSEDemo\\upload");
                    if (!file.exists()){
                        file.mkdirs();
                    }

                    //自定义一个文件命名规则：防止同名的文件被覆盖。规则：域名+毫秒值+随机数
                    String fileName = "wjqixige" + System.currentTimeMillis() + new Random().nextInt(999999) + ".jpg";

                    BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file + "\\" + fileName));
                    byte[] bytes = new byte[1024 * 15];
                    int len;
                    while((len = is.read(bytes)) != -1){
                        fos.write(bytes, 0, len);
                    }

                    socket.getOutputStream().write("上传成功".getBytes());

                    fos.close();
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
