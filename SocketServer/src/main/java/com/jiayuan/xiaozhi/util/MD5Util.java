package com.jiayuan.xiaozhi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/**
 *  
 * @author xuhongfeng
 *
 */
@Component("md5Util")
public class MD5Util {
    
    /**
     *  计算一个文件的MD5值， 返回结果以16进制字符串表示（32个字符，大写）
     * @param file
     * @return
     * @throws IOException
     */
    public String md5(File file) throws IOException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("can not found algorighm MD5", e);
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new IOException("file"+file.getAbsolutePath()+" for md5 not found!", e);
        }
        byte[] buf = new byte[1024];
        long readLen = 0;
        long totalLen = file.length();
        int tmpLen;
        while(readLen < totalLen) {
            tmpLen = fis.read(buf, 0, 1024);
            md.update(buf, 0, tmpLen);
            readLen += tmpLen;
        }
        byte[] digest = md.digest();
        return getHashtext(digest);
    }
    
    /**
     * 计算一个字符串的MD5值， 返回结果以16进制字符串表示（32个字符，大写）
     * @param s
     * @return
     * @throws IOException
     */
    public String md5(String s) throws IOException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("can not found algorighm MD5", e);
        }
        byte[] digest = md.digest(s.getBytes());
        return getHashtext(digest);
    }

    private String getHashtext(byte[] digest) {
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext.toUpperCase();
    }

    /**
     * sha1 加密
     * @param decript
     * @return
     */
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Constant.ERROR;
    }
}
