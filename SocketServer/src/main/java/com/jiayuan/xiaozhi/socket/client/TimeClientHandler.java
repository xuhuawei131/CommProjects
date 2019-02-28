package com.jiayuan.xiaozhi.socket.client;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClientHandler implements IoHandler {
    private Logger logger = LoggerFactory.getLogger("minasocketclient");

    @Override
    public void exceptionCaught(IoSession arg0, Throwable arg1)
            throws Exception {
        // TODO Auto-generated method stub
        arg1.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
//        logger.info("sessionId:{} recive, message: {}",session.getId(),message.toString());
//        System.out.println("client接受信息:"+message.toString());
    }

    @Override
    public void messageSent(IoSession arg0, Object message) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("client发送信息"+message.toString());
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
//        System.out.println("client与:"+session.getRemoteAddress().toString()+"断开连接");
//        logger.info("sessionId:{} closed",session.getId());
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
//        System.out.println("client与:"+session.getRemoteAddress().toString()+"建立连接");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // TODO Auto-generated method stub

//        System.out.println( "IDLE " + session.getIdleCount( status ));
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        logger.info("sessionId:{} open,local addr:{},remote addr: {}",
                session.getId(),session.getLocalAddress(),session.getRemoteAddress());
    }

}
