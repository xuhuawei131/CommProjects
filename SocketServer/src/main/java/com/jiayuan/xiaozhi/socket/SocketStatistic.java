package com.jiayuan.xiaozhi.socket;

/**
 * 统计实体
 * Created by xiaoxian on 16/1/11.
 */
public class SocketStatistic {
    private long clientNums;

    private long inTraffic;

    private long outTraffic;


    public synchronized void addClient(){
        this.clientNums++;
    }

    public synchronized void reduceClient(){
        this.clientNums--;
    }

    public long getClientNums() {
        return clientNums;
    }

    public void setClientNums(long clientNums) {
        this.clientNums = clientNums;
    }

    public synchronized void addInTraffic(){
        this.inTraffic++;
    }

    public synchronized void addOutTraffic(){
        this.outTraffic++;
    }

    public long getInTraffic() {
        return inTraffic;
    }

    public void setInTraffic(long inTraffic) {
        this.inTraffic = inTraffic;
    }

    public long getOutTraffic() {
        return outTraffic;
    }

    public void setOutTraffic(long outTraffic) {
        this.outTraffic = outTraffic;
    }
}
