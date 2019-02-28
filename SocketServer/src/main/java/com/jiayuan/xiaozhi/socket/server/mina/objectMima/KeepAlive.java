package com.jiayuan.xiaozhi.socket.server.mina.objectMima;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 心跳 服务器方 被动类型
 * Created by xiaoxian on 16/12/15.
 */
public class KeepAlive implements KeepAliveMessageFactory {
    Logger logger = Logger.getLogger("heartbeat");
    public static final String heartbeatReq="h";
    /**
     * 默认响应心跳内容
     */
    public static final String heartbeatRes="c";
    @Override
    public boolean isRequest(IoSession ioSession, Object o) {

        if(o.toString().equals(heartbeatReq)){
            logger.info(ioSession.getRemoteAddress()+"|heart|"+o);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object o) {
        return false;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        return null;
    }

    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        return heartbeatRes;
    }
}
