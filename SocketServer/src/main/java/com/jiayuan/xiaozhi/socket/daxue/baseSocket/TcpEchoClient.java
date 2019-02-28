package com.jiayuan.xiaozhi.socket.daxue.baseSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xiaoxian on 16/10/20.
 */
public class TcpEchoClient {
    public static void main(String[] args) throws IOException {
        String server = "127.0.0.1";
        byte[] data = "hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello8".getBytes();
        int serverPort = 59266;

        Socket socket = new Socket(server,serverPort);
        System.out.println("conn to server:"+server);

        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        out.write(data);

        //注意的是  并不是说服务器的一个wirte  客户端用一个read即可接受完
        //所以在实际应用中  需要自行添加控制头
        byte[] re = new byte[1024];
        in.read(re);

        System.out.println(new String(re));
        socket.close();

    }
}
