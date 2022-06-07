package Task_3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Task3 {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static void main(String[] args) {

        String SHA3 = "SHA3-512"; 
        String SHA1 = "SHA-1"; 				// алгоритмы хешировния 
        String a = "Privet Andrei"; 			// строка которую нужно хешировать
        output(a, SHA1);
        output(a, SHA3);


    }
    public static void output(String text, String algorythm) {       // Вывод результатов хеширования
    	System.out.println("---------- "+algorythm+" ----------");
        System.out.println("String: "+ text);
        System.out.println("String length: "+ text.length());

        byte[] hashResult = hash(text.getBytes(UTF_8), algorythm);
        
        System.out.println(algorythm + " in hex: "+ bytesToHex(hashResult));
        System.out.println(algorythm + " number of bytes: "+ hashResult.length);
    }
    
    public static byte[] hash(byte[] text, String algorithm) { 	// Хеширование
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
