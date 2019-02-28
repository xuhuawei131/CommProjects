/**
 * @(#)JSONSerializer.java, 2012-6-11. Copyright (c) 2011, Wandou Labs and/or
 *                          its affiliates. All rights reserved. WANDOU LABS
 *                          PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *                          terms.
 */
package com.jiayuan.xiaozhi.util.serializer;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author jerry
 */
public class JSONSerializer implements Serializer {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public String serialize(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) throws IOException {
        return (T) mapper.readValue(json, clazz);
    }

}
