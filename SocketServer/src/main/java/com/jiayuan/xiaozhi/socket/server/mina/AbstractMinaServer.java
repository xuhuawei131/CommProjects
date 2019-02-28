package com.jiayuan.xiaozhi.socket.server.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * socket 服务器
 * Created by xiaoxian on 15/11/16.
 */
public abstract class AbstractMinaServer{
    /**
     * 数据缓冲区大小
     */
    public static final int BUFFER_MAX_LENGTH = 1024 * 1000;
    /**
     * 最大空闲时间（分钟）
     */
    public static final int MAX_IdleTime = 15;
    /**
     * 日志
     */
    public static final Logger logger = LoggerFactory.getLogger("minaSocketServer");

    //服务器
    protected NioSocketAcceptor acceptor;

    protected AbstractMinaHandler handler;

    private String hostName;

    //过滤器
    private IoFilter ioFilter;


    /**
     * 启动服务器
     */
    public void start(int port){
        acceptor = new NioSocketAcceptor();
        acceptor.setReuseAddress(true);
        acceptor.getSessionConfig().setReuseAddress(true);
        acceptor.getSessionConfig().setReadBufferSize(BUFFER_MAX_LENGTH);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60 * MAX_IdleTime);
        acceptor.setCloseOnDeactivation(true);

        setFilter();

        setHandler();

        acceptor.setHandler(handler);

        try {
            acceptor.bind(new InetSocketAddress(port));
            logger.info("{} server start,port:{}", this.getClass().getSimpleName(), port);
            //启动成功,注册到manager
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("bind socket error");
        }
    }


    /**
     * 设置服务器的处理器与过滤器
     */
    public abstract void setFilter();

    public abstract void setHandler();

    public IoFilter getIoFilter() {
        return ioFilter;
    }

    public void setIoFilter(IoFilter ioFilter) {
        this.ioFilter = ioFilter;
    }

    public AbstractMinaHandler getHandler() {
        return handler;
    }

    public void setHandler(AbstractMinaHandler handler) {
        this.handler = handler;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
