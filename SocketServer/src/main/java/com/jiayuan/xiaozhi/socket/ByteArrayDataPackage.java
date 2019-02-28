package com.jiayuan.xiaozhi.socket;

import org.apache.mina.core.session.IoSession;

import com.jiayuan.wireless.util.cache.stream.ByteCacheOutputStream;


public class ByteArrayDataPackage
{
	public final static long DEFAULT_MAX_DATA_LENGTH = 1024*500;
	
	private long length;
	private ByteCacheOutputStream m_inbuf;
	
	public ByteArrayDataPackage(long length, IoSession session)
	{
		if(session != null && length > 0 && length < DEFAULT_MAX_DATA_LENGTH)
		{
			this.length = length;
			session.setAttribute(ByteArrayDataPackage.class.getName(), this);
		}
	}

    public static void closeFromSessoin(IoSession session)
    {
    	ByteArrayDataPackage dp = getDataPackage(session);
    	if(dp != null)
    		dp.close(session);
    }
	
	public static ByteArrayDataPackage getDataPackage(IoSession session)
	{
		if(session != null)
			return (ByteArrayDataPackage)session.getAttribute(ByteArrayDataPackage.class.getName());
		return null;
	}
	
	public boolean isRight()
	{
		return length > 0;
	}
	
    public long getLength()
    {
    	return length;
    }
	
    public void setLength(long length)
    {
    	this.length = length;
    }
    
    public long getLeftLength()
    {
    	if(length > 0)
    	{
    		if(m_inbuf != null)
    			return length - m_inbuf.size();
    		return length;
    	}
    	return -1;
    }
    
    public void write(byte[] b)
    {
    	if(m_inbuf == null)
    		m_inbuf = new ByteCacheOutputStream();
    	m_inbuf.write(b);
    }
    
    public void write(byte b[], int off, int len)
    {
    	if(m_inbuf == null)
    		m_inbuf = new ByteCacheOutputStream();
    	m_inbuf.write(b, off, len);
    }
    
    public byte[] getArray()
    {
    	if(m_inbuf != null)
    		return m_inbuf.getBytes(-1);
    	return null;
    }

    /**
     * 从指定字节开始读出short，但不清除
     * @param from
     * @return
     */
    public short readShort(int from)
    {
    	return m_inbuf.readShort(from);
    }
    
    /**
     * 读出short，且从缓存中清除
     * @return
     */
    public short getShort()
    {
    	return m_inbuf.getShort();
    }

    /**
     * 从指定字节开始读出int，但不清除
     * @param from
     * @return
     */
    public int readInt(int from)
    {
    	return m_inbuf.readInt(from);
    }
    
    /**
     * 读出int，且从缓存中清除
     * @return
     */
    public int getInt()
    {
    	return m_inbuf.getInt();
    }

    /**
     * 从指定字节开始读出long，但不清除
     * @param from
     * @return
     */
    public long readLong(int from)
    {
    	return this.readLong(from);
    }
    
    /**
     * 读出long，且从缓存中清除
     * @return
     */
    public long getLong()
    {
    	return this.getLong();
    }

    public void close(IoSession session)
    {
		length = 0;
    	if(m_inbuf != null)
    	{
    		m_inbuf.close();
    		m_inbuf = null;
    	}
    	if(session != null)
    		session.removeAttribute(ByteArrayDataPackage.class.getName());
    }
	
	
}
