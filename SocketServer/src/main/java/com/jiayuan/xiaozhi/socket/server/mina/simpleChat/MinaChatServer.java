package com.jiayuan.xiaozhi.socket.server.mina.simpleChat;

import java.nio.charset.Charset;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.jiayuan.xiaozhi.socket.SocketStatistic;
import com.jiayuan.xiaozhi.socket.server.mina.AbstractMinaServer;


/**
 * Created by xiaoxian on 15/11/16.
 */
@Component("minaChatServer")
@Lazy
public class MinaChatServer extends AbstractMinaServer {

    @Value("${socket.chat.server.name}")
    private String serverName;

    @Value("${socket.chat.server.port}")
    private int port;

    @Override
    public void setFilter() {
        this.acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
        this.acceptor.getFilterChain().addLast( "codec",
                new ProtocolCodecFilter( new TextLineCodecFactory(Charset.forName("UTF-8"))));
    }

    @Override
    public void setHandler() {
        MinaChatHandler adapter = new MinaChatHandler();
        setHandler(adapter);

    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public SocketStatistic getStatistic(){
        return getHandler().getStatistic();
    }
}
