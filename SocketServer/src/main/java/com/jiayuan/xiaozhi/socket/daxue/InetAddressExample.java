package com.jiayuan.xiaozhi.socket.daxue;

import java.net.*;
import java.util.Enumeration;

/**
 * 获知目前本机所有网卡连接地址
 * Created by xiaoxian on 16/10/20.
 */
public class InetAddressExample {
    public static void main(String[] args){
        try {
            //获取主机每个接口的网络
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
            if(interfaceList == null){
                System.out.println("no interface found");
            }else{
                while (interfaceList.hasMoreElements()){
                    NetworkInterface iface = interfaceList.nextElement();
                    //接口名,也可以通常说网卡名
                    System.out.println("Interface:"+iface.getName());

                    Enumeration<InetAddress> addrList = iface.getInetAddresses();
                    while (addrList.hasMoreElements()){
                        InetAddress address = addrList.nextElement();
                        System.out.print(address instanceof Inet4Address?"v4--":
                                (address instanceof Inet6Address?"v6--":"unknow--"));
                        System.out.println(address.getHostAddress()+"--"+ address.getHostName());

                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------");
        try {
            InetAddress[] addressList = InetAddress.getAllByName("localhost");
            for(InetAddress addr:addressList){
                System.out.println(addr.getHostName()+":"+addr.getHostAddress());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
