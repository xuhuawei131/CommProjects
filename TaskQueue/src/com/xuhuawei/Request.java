package com.xuhuawei;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
public class Request {
    public String request;
    public Request(String request){
        this.request=request;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request='" + request + '\'' +
                '}';
    }
}
