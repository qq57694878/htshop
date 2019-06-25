package com.kulongtai.mpstore.common.util;

import com.xiaoleilu.hutool.codec.Base64;
import com.xiaoleilu.hutool.util.CharsetUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by aiya1 on 2018/12/29.
 * @author
 */
public class AESUtils {
    private AESUtils(){
        throw new IllegalStateException("Utility class");
    }
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/NOPadding";

    public static String decryptAES(String data, String pass)  {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec keyspec = new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM);
            IvParameterSpec ivspec = new IvParameterSpec(pass.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] result = cipher.doFinal(Base64.decode(data.getBytes(CharsetUtil.UTF_8)));
            return new String(result, CharsetUtil.UTF_8);
        }catch (Exception e){
            return e.toString();
        }
    }

}
