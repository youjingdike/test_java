package com.encrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64; 

public class EncryptUtil {  
	
	public static final String KEY_SHA = "SHA";  
	public static final String KEY_MD5 = "MD5";  
	public static final String KEY_MAC = "HmacMD5";  
  
	private static final char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'}; 
  
	// sun不推荐使用它们自己的base64,用apache的挺好  
	/** 
	* BASE64解密 
	*/  
	public static byte[] decryptBASE64(byte[] dest) {  
		if (dest == null) {
			return null;  
		}  
		return Base64.decodeBase64(dest);  
	}
  
	/** 
	* BASE64加密 
	*/  
	public static byte[] encryptBASE64(byte[] origin) {  
		if (origin == null) {  
			return null;  
		}  
		return Base64.encodeBase64(origin);  
	}  
	
	 /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException  
     */
    public static String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
//        MessageDigest md5=MessageDigest.getInstance("MD5");
        MessageDigest md5=MessageDigest.getInstance(KEY_MD5);
        //加密后的字符串
        String newstr=new String(Base64.encodeBase64(md5.digest(str.getBytes("utf-8"))),"utf-8");
        return newstr;
    }
	
    /**判断用户密码是否正确
     * @param newpasswd  用户输入的密码
     * @param oldpasswd  数据库中存储的密码－－用户密码的摘要
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static boolean checkPassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(encoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
    
	/** 
	* MD5加密 
	*  
	* @throws NoSuchAlgorithmException 
	*/  
	public static byte[] encryptMD5(byte[] data)  throws NoSuchAlgorithmException {  
		if (data == null) {  
			return null;  
		}  
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);  
		md5.update(data);  
		return md5.digest();  
	}  
	  
	/** 
	* SHA加密 
	*  
	* @throws NoSuchAlgorithmException 
	*/  
	public static byte[] encryptSHA(byte[] data)  throws NoSuchAlgorithmException {  
		if (data == null) {  
			return null;  
		}  
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
		sha.update(data);  
		return sha.digest();  
	}  
	  
	/** 
	* 初始化HMAC密钥 
	*  
	* @throws NoSuchAlgorithmException 
	*/  
	public static String initMacKey() throws NoSuchAlgorithmException {  
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);  
		SecretKey secretKey = keyGenerator.generateKey();  
		return new String(encryptBASE64(secretKey.getEncoded()));  
	}  
  
	/** 
	* HMAC加密 
	*  
	* @throws NoSuchAlgorithmException 
	* @throws InvalidKeyException 
	*/  
	public static byte[] encryptHMAC(byte[] data, String key)  throws NoSuchAlgorithmException, InvalidKeyException {  
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key.getBytes()), KEY_MAC);  
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());  
		mac.init(secretKey);  
		return mac.doFinal(data);  
	  
	}  
  
	public static String convert(byte[] md) {
		int j = md.length;  
        char str[] = new char[j * 2];  
        int k = 0;  
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];  
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
            str[k++] = hexDigits[byte0 & 0xf];  
        }
        return new String(str);  
	}
	
	public static String convertByteToHexString(byte[] bytes) {
		String result = "";
		for (int i = 0; i < bytes.length; i++) {  
			int temp = bytes[i] & 0xff;
			String tempHex = Integer.toHexString(temp);
			if (tempHex.length()<2) {
				result += "0"+tempHex;
			} else {
				result += tempHex;
			}
		}  
		return result;  
	}
	
	public static void main(String[] args) throws Exception {  
		// TODO Auto-generated method stub  
//		String data = "简单加密";  
		String data = "0123456789";  
		System.out.println(new BigInteger(encryptBASE64(data.getBytes())).toString(16));  
		System.out.println(new BigInteger(encryptBASE64(data.getBytes())).toString(32));  
		System.out.println(new String(decryptBASE64(encryptBASE64(data.getBytes()))));  
		  
		System.out.println("md5:"+new BigInteger(encryptMD5(data.getBytes())).toString());  
		System.out.println("md5:"+new String(Base64.encodeBase64(encryptMD5(data.getBytes("utf-8"))),"utf-8"));  
		System.out.println(new BigInteger(encryptSHA(data.getBytes())).toString());  
		  
		System.out.println(new BigInteger(encryptHMAC(data.getBytes(), initMacKey())).toString());  
		System.out.println(new BigInteger(encryptHMAC(data.getBytes(), initMacKey())).toString());  
		
		System.out.println(encoderByMd5("123"));
		System.out.println(checkPassword("0123456789","Ed268zhq6h8pdO7phFQhUg=="));
		
		//202cb962ac59075b964b07152d234b70
		System.out.println(convert(encryptMD5("123".getBytes())));
		System.out.println(convertByteToHexString(encryptMD5("123".getBytes())));
	}  
  
}  
