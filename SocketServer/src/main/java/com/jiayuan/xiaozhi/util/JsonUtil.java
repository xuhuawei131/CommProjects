package com.jiayuan.xiaozhi.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaoxian on 15/4/6.
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 对象转换成json串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        String resultJson = StringUtils.EMPTY;
        if (obj == null) {
            return resultJson;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SerializationConfig sc = objectMapper.getSerializationConfig().withDateFormat(new SimpleDateFormat("YYYY-MM-DD hh:mm:ss"));
            objectMapper.setSerializationConfig(sc);
            resultJson = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("convert Object to json error!", e);
        }

        return resultJson;
    }

    /*
     * json字符串到HashMap<String,Object>
     * @aidi
     */
    public static HashMap<String, Object> toHashMap(String json) {
        if (json != null)
            try {
                return new ObjectMapper().readValue(json, HashMap.class);

            } catch (IOException e) {
                logger.error("convert json string to HashMap error!");
            }
        return new HashMap<String, Object>();

    }


    public static String toJsonp(final String callbackName, final Object object)
            throws IOException {
        String jsonString = mapper.writeValueAsString(object);
        String result = new StringBuilder().append(callbackName).append("(")
                .append(jsonString).append(");").toString();
        return result;
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws IOException {
        return (T) mapper.readValue(json, clazz);
    }

}
