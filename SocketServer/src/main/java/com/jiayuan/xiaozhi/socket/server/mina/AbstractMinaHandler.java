package com.jiayuan.xiaozhi.socket.server.mina;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jiayuan.xiaozhi.socket.ByteArrayDataPackage;
import com.jiayuan.xiaozhi.socket.SocketStatistic;
import com.jiayuan.xiaozhi.socket.user.SocketUser;

/**
 * 处理连接的各种事件
 * Created by xiaoxian on 16/1/12.
 */
public abstract class AbstractMinaHandler extends IoHandlerAdapter {
    /**
     * 统计对象
     */
    private SocketStatistic ss = new SocketStatistic();

    public Logger logger = LoggerFactory.getLogger("minaHandler");

    //本地用户缓存 sessionid-socketUser  每个客户端实例一个
    public Map<String,SocketUser> socketUsers = new HashMap<String, SocketUser>();

    public abstract void processMessageReceive(IoSession session, Object message);

    public abstract void processMessageSent(IoSession session, Object message);

    public abstract void processOpen(IoSession session);

    public abstract void processClose(IoSession session);

    public abstract void processIdel(IoSession session);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info(this.getClass().getSimpleName()+"|receive msg|sessionId:{}|address:{}|msg:{}.",session.getId(),
                session.getRemoteAddress(),message.toString());
        ss.addInTraffic();
        if (message instanceof List) {
            try {
                this.doWithPackage(session, (List<byte[]>) message);
            } catch (Exception e) {

            }
        }else{
            processMessageReceive(session,message);
        }
    }

    protected void doWithPackage(IoSession session, List<byte[]> message) {
        final IoSession f_session = session;
        final List<byte[]> f_message = message;

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (f_session != null && f_message != null && f_message.size() > 0) {
                    for (byte[] body : (List<byte[]>) f_message) {
                        try {
                            processMessageReceive(f_session, body);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info(this.getClass().getSimpleName()+"|sent msg|sessionId:{}|address:{}|msg:{}.",session.getId(),
                session.getRemoteAddress(),message.toString());
        processMessageSent(session, message);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info(this.getClass().getSimpleName()+"|close|sessionId:{}|address:{}.",session.getId(),
                session.getRemoteAddress());
        ss.reduceClient();
        if(session!=null){
            synchronized (session) {
                ByteArrayDataPackage dp = ByteArrayDataPackage.getDataPackage(session);
                if (dp != null) {
                    dp.close(session);
                }

                ByteArrayDataPackage.closeFromSessoin(session);
                Object sessionUser = session.getAttribute(SocketUser.class.getName());
                SocketUser user = (SocketUser) sessionUser;
                if (user != null) {
                    this.getSocketUsers().remove(user.getUserKey());
                    session.removeAttribute(SocketUser.class.getName());
                }
            }
        }
        processClose(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info(this.getClass().getSimpleName()+"|open|sessionId:{}|address:{}.",session.getId(),
                session.getRemoteAddress());
        ss.addClient();
        processOpen(session);
    }

    public static boolean writeOut(IoSession session, Object message, long timeout) {
        if (session.isConnected()) {
            synchronized (session) {
                WriteFuture wf = session.write(message);
                if (timeout == 0)
                    return true;
                if (wf != null) {
                    if (timeout > 0)
                        wf.awaitUninterruptibly(timeout);
                    else
                        wf.awaitUninterruptibly();
                    return wf.isWritten();
                }
            }
        }
        return false;
    }

    public SocketStatistic getStatistic(){
        return ss;
    }

    public Map<String,SocketUser> getSocketUsers(){
        return this.socketUsers;
    }

    public synchronized void addUser(String key,SocketUser user){
        this.socketUsers.put(key,user);
    }
}
