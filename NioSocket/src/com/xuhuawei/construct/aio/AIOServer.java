package com.xuhuawei.construct.aio;

import com.xuhuawei.construct.MyConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2017/10/15 0015.
 */
public class AIOServer {
    private AsynchronousServerSocketChannel server;
    private int port;
    private ByteBuffer receiverBuff=ByteBuffer.allocate(1024);

    public AIOServer(int port)  {
        this.port=port;
        try {
            server= AsynchronousServerSocketChannel.open();
            server.bind(new InetSocketAddress("localhost", MyConstants.port2));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void listener(){

        new Thread(){
            @Override
            public void run() {
                System.out.println("AIO服务已经启动，监听端口"+port);
                server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                    //成功以后的回调
                    @Override
                    public void completed(AsynchronousSocketChannel client, Void attachment) {
                        server.accept(null,this);
                        process(client);
                    }

                    private void process(AsynchronousSocketChannel client) {
                        receiverBuff.clear();
                        try {
                            int length=client.read(receiverBuff).get();
                            receiverBuff.flip();
                            System.out.println("已接收到客户端发来的消息："+new String(receiverBuff.array(),0,length));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failed(Throwable exc, Void attachment) {
                        System.out.println("异步IO失败");
                    }
                });

            }
        }.start();
    }
    public static void main(String[] args) {
            new AIOServer(MyConstants.port2).listener();
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
