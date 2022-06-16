	package Task_4;

	//import Task_4.HelpFunction;

	import javax.crypto.Cipher;
	import javax.crypto.KeyGenerator;
	import javax.crypto.SecretKey;
	import javax.crypto.spec.IvParameterSpec;

	import java.nio.ByteBuffer;
	import java.nio.charset.Charset;
	import java.nio.charset.StandardCharsets;
	import java.security.NoSuchAlgorithmException;
	import java.security.SecureRandom;

		
	public class Task4 
		{
		public class EncryptorAesCbc {
	    private static final String ENCRYPT_ALGO = "AES/CBC/PKCS5Padding"; 
	    private static final int BYTES_LENGTH = 16;
	    private static final int AES_KEY_BIT = 256;

	    private static final Charset UTF_8 = StandardCharsets.UTF_8;


	    public static void main(String[] args) throws Exception
	    {
	        String OUTPUT_FORMAT = "%-30s:%s";

	        String text = "Today was a good day";
	        SecretKey secretKey = getAESKey(AES_KEY_BIT); 					// генерируем случайный ключ
	        byte[] bytes = HelpFunction.getRandomValue(BYTES_LENGTH);			 		// генерируем случайные байты
	        byte[] encryptedText = EncryptorAesCbc.encryptWithIV(text.getBytes(UTF_8), secretKey, bytes);

	        System.out.println("----------AES CBC Encryption----------");
	        System.out.println(String.format(OUTPUT_FORMAT, "Text", text));
	        System.out.println(String.format(OUTPUT_FORMAT, "Key in hex", HelpFunction.hex(secretKey.getEncoded())));
	        System.out.println(String.format(OUTPUT_FORMAT, "IV in hex", HelpFunction.hex(bytes)));
	        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted(hex) ", HelpFunction.hex(encryptedText)));
	        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) in blocks", HelpFunction.hexOnBlocks(encryptedText, 16)));

	        System.out.println("----------AES CBC Decryption----------");
	        System.out.println(String.format(OUTPUT_FORMAT, "Input hex", HelpFunction.hex(encryptedText)));
	        System.out.println(String.format(OUTPUT_FORMAT, "Hex in blocks", HelpFunction.hexOnBlocks(encryptedText, 16)));
	        System.out.println(String.format(OUTPUT_FORMAT, "Key in hex", HelpFunction.hex(secretKey.getEncoded())));

	        String decryptedText = EncryptorAesCbc.decryptWithIV(encryptedText, secretKey);
	        System.out.println(String.format(OUTPUT_FORMAT, "Decrypted text", decryptedText));
	    }
	    public static SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException { // генерация секретного ключа
	        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
	        keyGen.init(keysize, SecureRandom.getInstanceStrong());
	        return keyGen.generateKey();
	    }


	    public static byte[] encryptWithIV(byte[] text, SecretKey secret, byte[] iv) throws Exception
	    {
	        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
	        cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv));
	        byte[] encryptedText = cipher.doFinal(text);
	        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + encryptedText.length)
	                .put(iv)
	                .put(encryptedText)
	                .array();
	        return cipherTextWithIv;

	    }
	    public static String decryptWithIV(byte[] CipherText, SecretKey secret) throws Exception
	    {
	        ByteBuffer ByteBuf = ByteBuffer.wrap(CipherText);

	        byte[] iv = new byte[BYTES_LENGTH];
	        ByteBuf.get(iv);

	        byte[] cipherText = new byte[ByteBuf.remaining()];
	        ByteBuf.get(cipherText);

	        String text = decrypt(cipherText, secret, iv);
	        return text;
	    }
	    public static String decrypt(byte[] CipherText, SecretKey secret, byte[] iv) throws Exception
	    {
	        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
	        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
	        byte[] text = cipher.doFinal(CipherText);
	        return new String(text, UTF_8);
	    }
	}
};