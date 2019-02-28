package com.jiayuan.xiaozhi.socket.server.mina.simpleChat;

import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import com.jiayuan.xiaozhi.common.SpringContextHolder;
import com.jiayuan.xiaozhi.socket.server.mina.AbstractMinaHandler;
import com.jiayuan.xiaozhi.socket.server.mina.websocket.MinaWebsocketServer;
import com.jiayuan.xiaozhi.socket.user.SocketUser;


/**
 * Created by xiaoxian on 15/11/16.
 */
public class MinaChatHandler extends AbstractMinaHandler {

//    @Override
//    public void messageReceived(final IoSession session, final Object message) throws Exception {
//        System.out.println(message);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                processMessageReceive(session,message);
//            }
//        }).start();
//    }

    @Override
    public void processMessageReceive(IoSession session, Object message) {
        //收到来自服务器的消息  直接转发给websocket
//        MinaWebsocketServer websocketServer = SpringContextHolder.getBean("minaWebServer");
//        if (websocketServer != null){
//            Map<String, SocketUser> socketUsers = websocketServer.getHandler().getSocketUsers();
//            for(Map.Entry<String,SocketUser> entry : socketUsers.entrySet()){
//                SocketUser socketUser = entry.getValue();
//                if(session != null && session.isConnected()){
//                    socketUser.getSession().write(message.toString().getBytes());
//                }
//            }
//
//        }
//        writeOut(session,"已经转发",2000);

//        logger.info("chatserver|"+session.getRemoteAddress()+"|receive message|"+message);
        session.write("I'm textline server,我收到了:"+message);

    }

    @Override
    public void processMessageSent(IoSession session, Object message) {
//        logger.info("chatserver|"+session.getRemoteAddress()+"|receive send|"+message);
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
