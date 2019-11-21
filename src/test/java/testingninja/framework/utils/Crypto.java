package testingninja.framework.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class Crypto {

    //Key has to be 128 bit
    //https://stackoverflow.com/questions/23561104/how-to-encrypt-and-decrypt-string-with-my-passphrase-in-java-pc-not-mobile-plat
    //https://stackoverflow.com/questions/2418485/how-do-i-convert-a-byte-array-to-base64-in-java
    public static String encryptPassword(String password, String key) {
        String encryptedString = null;
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());

            encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public static String decryptPassword(String encryptedPassword, String key) {
        String decryptedString = null;
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPassword)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    }
}
