package com.jiayuan.xiaozhi.socket.server.mina.websocket;


import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jiayuan.xiaozhi.socket.SocketStatistic;
import com.jiayuan.xiaozhi.socket.server.mina.AbstractMinaServer;

/**
 * Created by xiaoxian on 15/11/18.
 */
@Component("minaWebServer")
public class MinaWebsocketServer extends AbstractMinaServer {

    @Value("${socket.web.server.name}")
    private String serverName;

    @Value("${socket.web.server.port}")
    private int port;

    //magic String--socket服务器验证码
    public static final String GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    //第一个http请求，此时用来进行握手
    public static final String FIRST_HTTP_REQUEST = "first_http_request";
    //第一个返回，用于确认握手
    public static final String FIRST_HTTP_RESPONSE = "first_http_response";
    //第一个业务数据包，用于向游戏服务器表明这是第一个包，需要进行身份校验
    public static final String FIRST_GAME_PACKAGE = "first_game_package";

    @Override
    public void setFilter() {
        ProtocolCodecFilter filter = new ProtocolCodecFilter(new MinaWebPrefixedByteArrayCodecFactory());
        this.acceptor.getFilterChain().addLast("codec",filter);
    }

    @Override
    public void setHandler() {
        MinaWebHandler handler = new MinaWebHandler();
        setHandler(handler);
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
