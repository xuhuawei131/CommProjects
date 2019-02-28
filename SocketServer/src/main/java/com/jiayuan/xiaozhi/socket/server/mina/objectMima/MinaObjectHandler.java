package com.jiayuan.xiaozhi.socket.server.mina.objectMima;

import org.apache.mina.core.session.IoSession;

import com.jiayuan.xiaozhi.socket.server.mina.AbstractMinaHandler;

/**
 * Created by xiaoxian on 16/12/15.
 */
public class MinaObjectHandler extends AbstractMinaHandler{
    @Override
    public void processMessageReceive(IoSession session, Object message) {
//        logger.info("objserver|"+session.getRemoteAddress()+"|receive message|"+message);
        session.write("I'm obj server,我收到了:"+message);
    }

    @Override
    public void processMessageSent(IoSession session, Object message) {
//        logger.info("objserver|"+session.getRemoteAddress()+"|receive send|"+message);
    }

    @Override
    public void processOpen(IoSession session) {

    }

    @Override
    public void processClose(IoSession session) {

    }

    @Override
    public void processIdel(IoSession session) {

    }
}
