package KeyPairSignTest;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash {
    

    public static byte[] hash(byte[] text, String algorithm) { 			// Хеширование
        MessageDigest alg;
        try {
           alg = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] hashResult = alg.digest(text);
        return hashResult;
    }

    public static String bytesToHex(byte[] bytes) { 			 // Перевод хеша из байтов в HEX
        StringBuilder str = new StringBuilder();
        for (byte b : bytes) {
            str.append(String.format("%02x", b));
        }
        return str.toString();
    }
}