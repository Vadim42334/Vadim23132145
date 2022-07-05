package DEX;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class hash {
	private static final Charset UTF_8 = StandardCharsets.UTF_8;
	
    public static String hashOutput(String text) 			 // Вывод результатов хеширования
    {       
    	String SHA3 = "SHA3-512"; 
       // String SHA1 = "SHA-1"; 		
        byte[] hashResult = hashFunction(text.getBytes(UTF_8));
        String hashText = bytesToHex(hashResult);
        return hashText;
    }
    public static byte[] hashFunction(byte[] text) { 			// Хеширование
        MessageDigest alg;
        try {
           alg = MessageDigest.getInstance("SHA3-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] hashResult = alg.digest(text);
        return hashResult;
    }

    public static String bytesToHex(byte[] bytes) { 							 // Перевод хеша из байтов в HEX
        StringBuilder str = new StringBuilder();
        for (byte b : bytes) {
            str.append(String.format("%02x", b));
        }
        return str.toString();
    }
        

        
    }
