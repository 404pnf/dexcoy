package test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncDec {
	SecretKey desKey;

	public void setKey(String key) {
		byte[] staticKey = key.getBytes();
		try {
			SecretKeyFactory keyfact = SecretKeyFactory.getInstance("DES");
			DESKeySpec dks = new DESKeySpec(staticKey);
			desKey = keyfact.generateSecret(dks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encode(String src) {
		String str = "";
		try {
			Cipher c1 = Cipher.getInstance("DES/ECB/PKCS5Padding");
			c1.init(Cipher.ENCRYPT_MODE, desKey);
			byte[] encoded = c1.doFinal(src.getBytes());
//			 for(int i=0;i>4&0X0F);
//			 ch[i] = Digest[b&0X0F];
			// return new String(ch);
			return "11";
		} catch (Exception e) {
		}
		return str;
	}
}