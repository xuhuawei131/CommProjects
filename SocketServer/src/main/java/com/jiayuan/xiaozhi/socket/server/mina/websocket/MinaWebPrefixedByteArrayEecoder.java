package com.jiayuan.xiaozhi.socket.server.mina.websocket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;


public class MinaWebPrefixedByteArrayEecoder extends ProtocolEncoderAdapter{
	public final static int DEFAULT_MAX_DATA_LENGTH = 1024*512;

    private int maxDataLength = DEFAULT_MAX_DATA_LENGTH;
    
    private String FirstHttpPackageKey = "http_response_first_package";


	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		if(message != null && message instanceof byte[]){
    		byte[] bhead = null;
    		boolean firstPackage =true;
    		if(firstPackage){
                session.setAttribute(FirstHttpPackageKey, false);
                String acceptCode = (String) session.getAttribute("Sec-WebSocket-Accept");
                StringBuffer sb = new StringBuffer();
                sb.append("HTTP/1.1 101 Switching Protocols").append("\r\n");
				sb.append("Connection:Upgrade").append("\r\n");
				sb.append("Server: jyChat websocket server").append("\r\n");
				sb.append("Upgrade:WebSocket").append("\r\n");
				sb.append("Access-Control-Allow-Credentials:true").append("\r\n");
				sb.append("Access-Control-Allow-Headers:content-type").append("\r\n");
				sb.append("Sec-WebSocket-Accept:").append(acceptCode).append("\r\n").append("\r\n");
                bhead = sb.toString().getBytes("Utf-8");
                session.removeAttribute("Sec-WebSocket-Accept");
    		}
    		
    		
    		byte[] b = (byte[])message;
    		int masking_key_startIndex = 2; 
			if (b.length <= 125) {
				masking_key_startIndex = 2;
			} else if (b.length > 65536) {
				masking_key_startIndex = 10;
			} else if (b.length > 125) {
				masking_key_startIndex = 4;
			}
    		
			// 创建返回数据  即数据包加掩码长度
			byte[] result = new byte[b.length + masking_key_startIndex]; 
    		
			// 开始计算ws-frame 
			// frame-fin + frame-rsv1 + frame-rsv2 + frame-rsv3 + frame-opcode 
			result[0] = (byte) 0x81; // -127 
			// frame-masked+frame-payload-length 
			// 从第9个字节开始是 1111101=125,掩码是第3-第6个数据 
			// 从第9个字节开始是 1111110>=126,掩码是第5-第8个数据 
			if (b.length <= 125) { 
				result[1] = (byte) (b.length); 
			} else if (b.length > 65536) { 
				result[1] = 0x7F; // 127 
//				byte[] longArrayToByteArray = JavaTools.longArrayToByteArray(new long[]{(long)b.length});
				byte[] longArrayToByteArray =null;
				for (int i = 0; i < longArrayToByteArray.length; i++) {
					result[i + 2] = longArrayToByteArray[i];
				}
			} else if (b.length > 125) { 
				result[1] = 0x7E; // 126 
				result[2] = (byte) (b.length >> 8); 
				result[3] = (byte) (b.length % 256); 
			} 
    		
			System.arraycopy(b, 0, result, masking_key_startIndex, b.length);
			
			
    		IoBuffer buffer = IoBuffer.allocate((bhead == null ? 0 : bhead.length) + result.length).setAutoExpand(true);
    		if(null != bhead){
    			buffer.put(bhead);
    		}
           buffer.put(result);
            if (buffer.position() > maxDataLength) {
                throw new IllegalArgumentException("Data length: " + buffer.position());
            }
            buffer.flip();
            out.write(buffer);
    	}
		
	}

	public int getMaxDataLength() {
		return this.maxDataLength;
	}

	public void setMaxDataLength(int maxDataLength) {
		this.maxDataLength = maxDataLength;
	}


}
