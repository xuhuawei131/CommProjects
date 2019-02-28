package com.jiayuan.xiaozhi.socket.server.mina.websocket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MinaWebPrefixedByteArrayCodecFactory implements ProtocolCodecFactory{
	private final MinaWebPrefixedByteArrayDecoder decoder;

    private final MinaWebPrefixedByteArrayEecoder encoder;

    public MinaWebPrefixedByteArrayCodecFactory() {
        encoder = new MinaWebPrefixedByteArrayEecoder();
        decoder = new MinaWebPrefixedByteArrayDecoder();
    }

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}

	public int getEncoderMaxDataLength() {
        return encoder.getMaxDataLength();
    }
    public void setEncoderMaxDataLength(int maxDataLength) {
        encoder.setMaxDataLength(maxDataLength);
    }
    
    
    public int getDecoderMaxDataLength() {
        return decoder.getMaxDataLength();
    }  
    public void setDecoderMaxDataLength(int maxDataLength) {
        decoder.setMaxDataLength(maxDataLength);
    }
	
}
