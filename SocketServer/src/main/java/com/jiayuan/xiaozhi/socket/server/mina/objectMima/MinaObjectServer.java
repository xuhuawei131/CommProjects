package com.jiayuan.xiaozhi.socket.server.mina.objectMima;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jiayuan.xiaozhi.socket.SocketStatistic;
import com.jiayuan.xiaozhi.socket.server.mina.AbstractMinaServer;

/**
 * Created by xiaoxian on 16/12/15.
 */
@Component("minaObjectServer")
public class MinaObjectServer extends AbstractMinaServer{

    @Value("${socket.object.server.name}")
    private String serverName;

    @Value("${socket.object.server.port}")
    private int port;

    @Override
    public void setFilter() {
        this.acceptor.getFilterChain().addLast( "codec",
                new ProtocolCodecFilter( new ObjectSerializationCodecFactory()));
        KeepAliveFilter aliveFilter = new KeepAliveFilter(new KeepAlive(), IdleStatus.BOTH_IDLE);
        aliveFilter.setForwardEvent(true); //idle事件回发  当session进入idle状态的时候 依然调用handler中的idled方法
        //说明：尤其 注意该句话，使用了 KeepAliveFilter之后，IoHandlerAdapter中的 sessionIdle方法默认是不会再被调用的！ 所以必须加入这句话 sessionIdle才会被调用

//        aliveFilter.setRequestInterval(10);  //本服务器为被定型心跳  即需要每10秒接受一个心跳请求  否则该连接进入空闲状态 并且发出idled方法回调
        this.acceptor.getFilterChain().addLast("heartbeat",aliveFilter);
    }

    @Override
    public void setHandler() {
        MinaObjectHandler handler = new MinaObjectHandler();
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
