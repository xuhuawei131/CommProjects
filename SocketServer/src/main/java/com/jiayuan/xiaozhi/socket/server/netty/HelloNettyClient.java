package com.jiayuan.xiaozhi.socket.server.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * Created by xiaoxian on 15/11/12.
 */
public class HelloNettyClient {
    public static void main(String args[]) {

        // Client服务启动器

        ClientBootstrap bootstrap = new ClientBootstrap(

                new NioClientSocketChannelFactory(

                        Executors.newCachedThreadPool(),

                        Executors.newCachedThreadPool()));

        // 设置一个处理服务端消息和各种消息事件的类(Handler)

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override

            public ChannelPipeline getPipeline() throws Exception {

                return Channels.pipeline(new HelloNettyClientHandler());

            }

        });

        // 连接到本地的8000端口的服务端

        bootstrap.connect(new InetSocketAddress(

                "127.0.0.1", 8000));

    }
}
