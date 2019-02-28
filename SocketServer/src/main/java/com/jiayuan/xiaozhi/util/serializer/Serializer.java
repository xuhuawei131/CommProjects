/**
 * @(#)ISerializer.java, 2012-6-11. Copyright (c) 2011, Wandou Labs and/or its
 *                       affiliates. All rights reserved. WANDOU LABS
 *                       PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *                       terms.
 */
package com.jiayuan.xiaozhi.util.serializer;

import java.io.IOException;

/**
 * @author Jerry
 */
public interface Serializer {

    String serialize(Object obj) throws IOException;

    <T> T deserialize(String json, Class<T> clazz) throws IOException;

}
