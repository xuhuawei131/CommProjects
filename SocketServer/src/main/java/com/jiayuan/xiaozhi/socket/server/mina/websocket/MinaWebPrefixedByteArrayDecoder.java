package com.jiayuan.xiaozhi.socket.server.mina.websocket;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.jiayuan.xiaozhi.socket.ByteArrayDataPackage;

public class MinaWebPrefixedByteArrayDecoder extends CumulativeProtocolDecoder{
	
	public final static int DEFAULT_PREFIX_LENGTH = 4;

    public final static int DEFAULT_MAX_DATA_LENGTH = 1024*100;

    private int prefixLength = DEFAULT_PREFIX_LENGTH;

	private int maxDataLength = DEFAULT_MAX_DATA_LENGTH;
	
	private String FirstHttpPackageKey = "http_request_first_package";
	public static final String GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

	public static final String HEADER_CODE = "utf-8";
	
//	public static final Log log = Log.newInstance().init(null, "webSocketProtLog", null);


	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
//		long m1 = System.currentTimeMillis();
		//将数据报文解析为list<byte[]>
		List<byte[]> byteList = null;
		
		boolean closeSession = false;
		boolean firstPackage =true;
		//首包，需要握手
		while (in.limit() > in.position()) {
			if(firstPackage){
				boolean lastIsReturn = false;
				byte[] b = null;
				int len = in.limit() - in.position();
				b = new byte[10240];
				int i = 0;
				for (; i < len; i++) {
					b[i] = in.get();
					if (b[i] == '\n') {
						if (lastIsReturn) {
							firstPackage = false;
							session.setAttribute(FirstHttpPackageKey, false);
							i++;
							break;
						}
						lastIsReturn = true;
					} else if (b[i] != '\r')
						lastIsReturn = false;
				}
	
				ByteArrayDataPackage dp = new ByteArrayDataPackage(DEFAULT_MAX_DATA_LENGTH, session);
				dp.write(b, 0, i);
				
				if (!firstPackage) {
					b = dp.getArray();
					if (b != null) {
						if (byteList == null)
							byteList = new LinkedList<byte[]>();
						byteList.add(b);
	
						// 处理头信息
						String allH = new String(b, "utf-8");
						try {
							String info = "";

//							log.debug(session.getId() + "|" + session.getRemoteAddress() +"|" + info);
						} catch (Exception e) {
						}
						/*if (allH.indexOf("Sec-WebSocket-Key1") > 0 && allH.indexOf("Sec-WebSocket-Key2") > 0) {
							System.out.println("webSocket请求包含两个key,目前不支持此握手.....");
						}*/
						String[] sl = null;
						Map<Object, Object> dealFisrtHttpheader = dealFisrtHttpheader(sl[0]);
						session.setAttribute("webSocket_req_header_param", dealFisrtHttpheader);
						for (String eveL : sl) {
							// 带一个安全key的请求，结果是先以“SHA-1”进行加密，再以base64的加密，结果放在Sec-WebSocket-Accept请求头中返回
							if (eveL.startsWith("Sec-WebSocket-Key")) {
								String sec_WebSocket_Key = eveL.substring(eveL.indexOf(":") + 1, eveL.length()).trim();
								byte[] bts = MessageDigest.getInstance("SHA1").digest((sec_WebSocket_Key + GUID).getBytes(HEADER_CODE));
								String acceptCode = new sun.misc.BASE64Encoder().encode(bts);
								session.setAttribute("Sec-WebSocket-Accept", acceptCode);
								break;
							}
						}
	
					}
					dp.close(session);
				}
			
				
			}else{
				//建立连接之后的包
				ByteArrayDataPackage dp = ByteArrayDataPackage.getDataPackage(session);
				
				if(dp==null){
					byte b = in.get();// 查看第一帧的值 代表是否结束
					int isend = b >> 7 & 0x1;
					if (1 == isend) {// 消息尾部
						byte maskBt = in.get();
						boolean mask = ((maskBt >> 7 & 0x1) == 1) ? true : false;
						if (mask) {
							//使用了掩码 websocket默认必须为1
							int dataLength = maskBt & 0x7F;
							byte[] frameMaskingAry = new byte[4];// 掩码数组
							long datalen = 0L;
							if (dataLength < 126) {
								in.get(frameMaskingAry);
								datalen = dataLength;
							} else if (dataLength == 126) {
								short short1 = in.getShort();
								in.get(frameMaskingAry);
								datalen = short1;
							} else if (dataLength == 127) {
								long long1 = in.getLong();
								in.get(frameMaskingAry);
								datalen = long1;
							}
							
							session.setAttribute("frameMaskingAry", frameMaskingAry);
							dp = new ByteArrayDataPackage(datalen, session);
						}else{
	//						System.out.println("----use not mask----");
							closeSession = true;
						}
					}
					
				}else{
					int left = (int) dp.getLeftLength();
					if (left < 0) {
	//					System.out.println("<----lest is 《 0---->");
						closeSession = true;
					}
					int len = in.limit() - in.position();
					if (left > 0 && len > 0) {
						if (len > left)
							len = left;
						byte[] bytes = new byte[len];
						in.get(bytes);
						dp.write(bytes);
					}
					left = (int) dp.getLeftLength();
					if (left == 0) {
						byte[] bytes = dp.getArray();
	
						if (bytes != null) {
							byte[] resdata = new byte[bytes.length];
							byte[] frameMaskingAry = (byte[]) session.getAttribute("frameMaskingAry");
							session.removeAttribute("frameMaskingAry");
							int frame_masking_key = 0;
							for (int i = 0; i < bytes.length; i++) {
								resdata[i] = (byte) (bytes[i] ^ frameMaskingAry[frame_masking_key % 4]);// 把数据进行异或运算
								frame_masking_key++;
							}
							if (byteList == null)
								byteList = new LinkedList<byte[]>();
							byteList.add(resdata);
						}
						dp.close(session);
					}
				}
					
						
			}
			
		}
		
		if (closeSession) {
			ByteArrayDataPackage.closeFromSessoin(session);
//			System.out.println("beacuse - ------>>>>");
			session.close(true);
		}

		if (byteList != null)
			out.write(byteList);
		
//		long m2=System.currentTimeMillis();
//		System.out.println("decode use time :"+(m2-m1));
		return byteList != null;
	}

	public int getMaxDataLength() {
		return this.maxDataLength;
	}

	public void setMaxDataLength(int maxDataLength) {
		this.maxDataLength = maxDataLength;
	}
	
	public static Map<Object, Object> dealFisrtHttpheader(String jdata) {
		Map<Object, Object> hashMap = new HashMap<Object, Object>();
		int npos = jdata.indexOf('?');
		if (npos > 0) {
			int npos2 = jdata.lastIndexOf(' ');
			if (npos2 > npos) {
				jdata = jdata.substring(npos + 1, npos2);
			}
			String[] pars = jdata.split("\\&");
			for (String ps : pars) {
//				if (Tools.stringIsNotNull(ps)) {
//					npos = ps.indexOf('=');
//					if (npos > 0) {
//						hashMap.put(ps.substring(0, npos), Tools.decodePar(ps.substring(npos + 1), "utf-8"));
//					}
//				}
			}
		}
		return hashMap;
	}
}
