package cn.edu.zafu;

import java.math.BigInteger;

public class Config {
	//生成公钥的两个参数
    public static final String exponent = "010001";
    public static final String modulus = "00d57982ab09a5400b12663af0bfc153182fb5af3002520d834bcedfafa93bbc9d205c8171e6ef6496ae768010301e3cdef8f2d001aaf09fc860371879b7f302cb13284ccb6bf3581e82555adb3ec138214029c720f16e48d266729c8666e219964d6529d734a6cb2a475dbd6273dd9e1a5cd7c31ffd85244259e1a58cddf246cd";

    //123456在js下加密后的结果
    public static final String result="0445580b6f50e00416d6b492faae2751e7674df4d16665a757d567e6d547b3831088dc39631cd9685ca82dd72a8e46cacaaeaa1924f3163c8b97fa253e9a7d298d26cd13d535fa8783dff252efa466aa5459911cf28a0c0411c056b0d5caf743bd87a74cc694cbeb0b068b8567a17088970ef2b91091705add20fbdffbeaf162";
    
    public static final BigInteger bigModulus=new BigInteger(modulus,16);
    public static final BigInteger bigExponent=new BigInteger(exponent,16);
}
