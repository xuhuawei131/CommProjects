package com.jiayuan.xiaozhi.socket.server.mina.objectMima;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.jiayuan.xiaozhi.socket.client.TimeClientHandler;

/**
 * Created by xiaoxian on 16/12/15.
 */
public class MinaObjectClient {
    public static void main(String[] args){
        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast( "logger", new LoggingFilter() );
        connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new ObjectSerializationCodecFactory()));
        connector.setHandler(new TimeClientHandler());
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", 9543));
        //等待建立连接
        connectFuture.awaitUninterruptibly();

        IoSession session = connectFuture.getSession();

        session.write("你好");
    }
}
