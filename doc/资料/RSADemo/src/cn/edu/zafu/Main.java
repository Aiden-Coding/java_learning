package cn.edu.zafu;

import cn.edu.zafu.utils.RSAUtil;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Main {
	public static void main(String[] args) throws Exception {

		RSAPublicKey pubKey = RSAUtil.getRSAPublicKey(Config.modulus, Config.exponent);
		String result =RSAUtil.encryptStringByJs(pubKey, "123456");
		System.out.println(result);
		System.out.println(Config.result);
		System.out.println(result.equals(Config.result));
		RSAPublicKey defaultPublicKey = RSAUtil.getDefaultPublicKey();
		RSAPrivateKey defaultPrivateKey = RSAUtil.getDefaultPrivateKey();
		String encryptStringByJs = RSAUtil.encryptStringByJs("123456");
		String result1=RSAUtil.decryptStringByJs(encryptStringByJs);
		System.out.println(new String(result1));
	}
}
