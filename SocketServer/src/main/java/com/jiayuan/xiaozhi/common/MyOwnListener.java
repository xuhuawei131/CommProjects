package com.jiayuan.xiaozhi.common;

import java.util.Map;
import java.util.Properties;


import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.jiayuan.xiaozhi.socket.server.mina.objectMima.MinaObjectClient;
import com.jiayuan.xiaozhi.socket.server.mina.objectMima.MinaObjectServer;
import com.jiayuan.xiaozhi.socket.server.mina.simpleChat.MinaChatServer;
import com.jiayuan.xiaozhi.socket.server.mina.websocket.MinaWebsocketServer;


/**
 * Created by xiaoxian on 15/11/16.
 */
public class MyOwnListener extends ContextLoaderListener{
    @Override
    public void contextInitialized(ServletContextEvent event) {
        //检查环境参数
//        Properties properties = System.getProperties();
//        for(Map.Entry<Object,Object> entry:properties.entrySet()){
//            System.out.println(entry.getKey()+"*****:******"+entry.getValue());
//        }


        //启动spring容器
        super.contextInitialized(event);
        System.out.println("-----spring init success------");

        //自定义加载的服务 可以抽象出统一服务加载

        //socket mina 服务器
//        MinaChatServer minaChatServer = SpringContextHolder.getBean("minaChatServer");
//        minaChatServer.start(minaChatServer.getPort());

        //websocket mina 服务器
//        MinaWebsocketServer minaWebsocketServer = SpringContextHolder.getBean("minaWebServer");
//        minaWebsocketServer.start(minaWebsocketServer.getPort());

        //
        //websocket mina 服务器
        MinaObjectServer minaObjectServer = SpringContextHolder.getBean("minaObjectServer");
        minaObjectServer.start(minaObjectServer.getPort());
    }
}
