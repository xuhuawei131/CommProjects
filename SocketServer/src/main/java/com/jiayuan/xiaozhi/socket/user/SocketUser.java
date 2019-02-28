package com.jiayuan.xiaozhi.socket.user;

import org.apache.mina.core.session.IoSession;

import com.jiayuan.xiaozhi.socket.SocketConstant;


/**
 * 代表一个用户
 * Created by xiaoxian on 15/11/16.
 */
public class SocketUser {
    //用户会话
    private IoSession session;

    private Long uid;

    private SocketConstant.UserDevice userDevice;

    private String userversion;

    private String userKey;

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public SocketConstant.UserDevice getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(SocketConstant.UserDevice userDevice) {
        this.userDevice = userDevice;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void write(Object message) {

    }

    public void close() {

    }
}
