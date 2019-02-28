package com.jiayuan.xiaozhi.socket.server.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * Created by xiaoxian on 15/11/12.
 */
public class HelloNettyClientHandler extends SimpleChannelHandler{
    @Override

    public void channelConnected(ChannelHandlerContext ctx,

                                 ChannelStateEvent e) {

        System.out.println("Hello world, I'm client.");

    }
}
