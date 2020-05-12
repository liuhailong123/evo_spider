package org.evoAdmin.upload.video;

import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * 腾讯云 点播js上传，服务端签名生成方法
 * 
 * @author luxin 2017年07月06日10:04:25
 */
public class Signature {
	public String m_strSecId;// secretId
	public String m_strSecKey;// secretKey
	public Integer classId = 0;// 视频分类id，默认为0，非必填
	public Integer isTranscode = 0;// 视频是否转码，默认为0，不发起转码，非必填
	public Integer isScreenshot = 0;// 视频是否截图，默认为0，不截图，非必填
	public Integer isWatermark = 0;// 视频是否打水印，默认为0，不打水印，非必填
	public long m_qwNowTime;
	public int m_iRandom;
	public int m_iSignValidDuration;

	private static final String HMAC_ALGORITHM = "HmacSHA1";
	private static final String CONTENT_CHARSET = "UTF-8";

	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public String GetUploadSignature() {
		String strSign = "";
		String contextStr = "";
		long endTime = (m_qwNowTime + m_iSignValidDuration);
		try {
			contextStr += "secretId=" + java.net.URLEncoder.encode(this.m_strSecId, "utf8");
			contextStr += "&currentTimeStamp=" + this.m_qwNowTime;
			contextStr += "&expireTime=" + endTime;
			contextStr += "&random=" + this.m_iRandom;
			contextStr += "&classId=" + this.classId;
			contextStr += "&isTranscode=" + this.isTranscode;
			contextStr += "&isScreenshot=" + this.isScreenshot;
			contextStr += "&isWatermark=" + this.isWatermark;

			Mac mac = Mac.getInstance(HMAC_ALGORITHM);
			SecretKeySpec secretKey = new SecretKeySpec(m_strSecKey.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
			mac.init(secretKey);
			byte[] hash = mac.doFinal(contextStr.getBytes(CONTENT_CHARSET));
			byte[] sigBuf = byteMerger(hash, contextStr.getBytes("utf8"));
			strSign = new String(new BASE64Encoder().encode(sigBuf).getBytes());
			strSign = strSign.replace(" ", "").replace("\n", "").replace("\r", "");
		} catch (Exception e) {
			System.out.print(e.toString());
			return "";
		}
		return strSign;
	}

	public static void main(String[] args) {
		Signature sign = new Signature();
		sign.m_strSecId = "AKIDvjLl04wc6PsCKGaWr3Up7IHBwWv9m5lF";
		sign.m_strSecKey = "jLlz4cw9D6mJf0DE3g6KNDsKaeWBfx3x";
		sign.m_qwNowTime = System.currentTimeMillis() / 1000;
		sign.m_iRandom = new Random().nextInt(java.lang.Integer.MAX_VALUE);
		sign.m_iSignValidDuration = 3600 * 24 * 2;

		System.out.print(sign.GetUploadSignature());
	}
}
