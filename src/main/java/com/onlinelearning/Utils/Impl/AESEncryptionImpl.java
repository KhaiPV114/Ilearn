package com.onlinelearning.Utils.Impl;

import com.onlinelearning.Utils.DotEnv;
import com.onlinelearning.Utils.Encryption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author duy20
 */
public class AESEncryptionImpl implements Encryption {

    @Override
    public String encrypt(String strToEncrypt) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = DotEnv.get("AES_PRIVATE_KEY").getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception ex) {
            Logger.getLogger(AESEncryptionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String decrypt(String strToDecrypt) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = DotEnv.get("AES_PRIVATE_KEY").getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
