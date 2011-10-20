package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class zhuanma {
	
	
	public static void main(String[] args) {
		

		System.out.println("fushun==="+utf8Encode("测试lwd"));
		System.out.println("fushun==="+utf8Decode("redstone.xmlrpc.XmlRpcException: The response could not be parsed"));
		System.out.println("fushun==="+utf8Decode("gly"));
		
		
	}
	
	public static String utf8Decode(String str) {
		String returnValue = "";
		try {
			returnValue = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return returnValue;

	}
	
	public static String utf8Encode(String str) {
		String returnValue = "";
		try {
			returnValue = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnValue;

	}
}