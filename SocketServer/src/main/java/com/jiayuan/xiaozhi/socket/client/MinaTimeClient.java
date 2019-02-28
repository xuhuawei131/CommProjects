package com.jiayuan.xiaozhi.socket.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaTimeClient {

    public static void main(String[] args) {

        for(int i = 1;i<=1;i++){


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        IoConnector connector = new NioSocketConnector();
                        connector.getFilterChain().addLast( "logger", new LoggingFilter() );
                        connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory(Charset.forName("UTF-8"))));
                        connector.setHandler(new TimeClientHandler());
                        ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", 9541));
                        //等待建立连接
                        connectFuture.awaitUninterruptibly();

                        IoSession session = connectFuture.getSession();

                        Thread.sleep(2000);
                        session.write("你好");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }


    }

}
