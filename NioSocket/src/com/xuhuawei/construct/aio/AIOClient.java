package com.xuhuawei.construct.aio;

import com.xuhuawei.construct.MyConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/10/15 0015.
 */
public class AIOClient {
    private AsynchronousSocketChannel client;
    private InetSocketAddress serverAddress=new InetSocketAddress("localhost", MyConstants.port2);
    private ByteBuffer sendBuff=ByteBuffer.allocate(1024);
    public AIOClient() {
        try {
            client= AsynchronousSocketChannel.open();
            Future<?> f=client.connect(serverAddress);
            System.out.println("客户端已经启动");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(String content){
        sendBuff.clear();
        sendBuff.put(content.getBytes());
        sendBuff.flip();
        client.write(sendBuff);
    }
    public static void main(String[] args) {
        AIOClient aioClient=new AIOClient();
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextLine()){
            String content=scanner.nextLine();
            aioClient.send(content);
        }
    }
}
