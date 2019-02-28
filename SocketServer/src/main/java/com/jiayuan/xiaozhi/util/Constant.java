package com.jiayuan.xiaozhi.util;

import java.util.ResourceBundle;

import com.jiayuan.xiaozhi.model.EnumDescription;


/**
 * Created by xiaoxian on 15/9/21.
 */
public class Constant {

    protected static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");

    public static final String ERROR = "error";
    public static final String SUCCESS = "success";

    public static final class CacheName {

        public static final String TOKEN_VALUE_CACHE = "tokenCache";
    }

    public static final class Encode {

        public static final String UTF8 = "utf-8";
    }

    /**
     * 消息的类型
     */
    public static enum MsgType implements EnumDescription {
        text,image,voice,video,shortvideo,location,link,event;

        @Override
        public String getDescription() {
            return BUNDLE.getString("weixin.msgtype."+name().toLowerCase());
        }
    }

    /**
     * 当消息为事件时，事件的类型
     */
    public static enum EventType implements EnumDescription{
        SUBSCRIBE,UNSUBSCRIBE,SCAN,LOCATION,CLICK,VIEW;

        @Override
        public String getDescription() {
            return BUNDLE.getString("weixin.event."+name().toLowerCase());
        }
    }
}
