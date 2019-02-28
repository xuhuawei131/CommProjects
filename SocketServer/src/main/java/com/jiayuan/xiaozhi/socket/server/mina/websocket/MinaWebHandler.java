package com.jiayuan.xiaozhi.socket.server.mina.websocket;

import org.apache.mina.core.session.IoSession;

import com.jiayuan.xiaozhi.socket.server.mina.AbstractMinaHandler;
import com.jiayuan.xiaozhi.socket.user.SocketUser;

/**
 * Created by xiaoxian on 16/5/13.
 */
public class MinaWebHandler extends AbstractMinaHandler {
    @Override
    public void processMessageReceive(IoSession session, Object message) {

        synchronized (session) {
            if (null == session || session.isClosing() || !session.isConnected()) {
                return;
            }
            SocketUser user = (SocketUser) session.getAttribute(SocketUser.class.getName());
            if (user == null) {
                user = new SocketUser();
                user.setUserKey(session.getRemoteAddress().toString());
                user.setSession(session);
                session.setAttribute(SocketUser.class.getName(), user);
                this.addUser(user.getUserKey(),user);
            }
        }

        writeOut(session,"shoudao".getBytes(),20000);
    }

    @Override
    public void processMessageSent(IoSession session, Object message) {

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
