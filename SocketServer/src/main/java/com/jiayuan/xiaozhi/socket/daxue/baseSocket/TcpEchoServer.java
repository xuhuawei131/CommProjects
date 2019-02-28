package com.jiayuan.xiaozhi.socket.daxue.baseSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiaoxian on 16/10/20.
 */
public class TcpEchoServer {
    private static final int BUFSIZE=32;
    public static void main(String[] args) throws IOException {
        int serverPort=59266;
        ServerSocket serverSocket = new ServerSocket(serverPort);

        int recvMsgSize;
        byte[] receiveBuf = new byte[BUFSIZE];

        while(true){
            Socket accept = serverSocket.accept();

            InputStream in = accept.getInputStream();
            OutputStream out = accept.getOutputStream();

           while((recvMsgSize = in.read(receiveBuf)) != -1 ){
               System.out.println("server++");

               out.write(receiveBuf,0,recvMsgSize);
           }


        }

    }
}
