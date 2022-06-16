package Task_4;

import Task_4.HelpFunction;
import Task_4.Task4;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
ШИФРОВАНИЕ И РАСШИФРОВАНИЕ AES НА ОСНОВЕ ПАРОЛЯ
 */
public class Task4_V2 {

    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

    private static final int TAG_LENGTH_BIT = 128; 
    private static final int IV_LENGTH_BYTE = 12;
    private static final int SALT_LENGTH_BYTE = 16;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static void main(String[] args) throws Exception 
    {

        String OUTPUT_FORMAT = "%-30s:%s";
        String PASSWORD = "I cant belive, today was a good day";
        String text = "Today was like one of those fly dreams";

        String encryptedTextBase64 = encrypt(text.getBytes(UTF_8), PASSWORD);

        System.out.println("\n----------AES Encryption with password----------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input text", text));
        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted ", encryptedTextBase64));

        System.out.println("\n----------AES Decryption with password----------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input text", encryptedTextBase64));

        String decryptedText = decrypt(encryptedTextBase64, PASSWORD);
        System.out.println(String.format(OUTPUT_FORMAT, "Decrypted text", decryptedText));

    }
	public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException // получение секретного ключа из пароля
    {
        SecretKeyFactory sfc = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKey secret = new SecretKeySpec(sfc.generateSecret(spec).getEncoded(), "AES");
        return secret;

    }
    public static String encrypt(byte[] text, String password) throws Exception {

        byte[] salt = HelpFunction.getRandomValue(SALT_LENGTH_BYTE);

        byte[] iv = HelpFunction.getRandomValue(IV_LENGTH_BYTE);

        SecretKey aesKeyFromPassword = getAESKeyFromPassword(password.toCharArray(), salt);

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

        byte[] cipherText = cipher.doFinal(text);

        byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
                .put(iv)
                .put(salt)
                .put(cipherText)
                .array();

        return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);

    }

    private static String decrypt(String CipherText, String password) throws Exception {

        byte[] decode = Base64.getDecoder().decode(CipherText.getBytes(UTF_8));

        ByteBuffer byteBuff = ByteBuffer.wrap(decode);

        byte[] iv = new byte[IV_LENGTH_BYTE];
        byteBuff.get(iv);

        byte[] salt = new byte[SALT_LENGTH_BYTE];
        byteBuff.get(salt);

        byte[] cipherText = new byte[byteBuff.remaining()];
        byteBuff.get(cipherText);

        SecretKey aesKeyFromPassword = getAESKeyFromPassword(password.toCharArray(), salt);

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

        byte[] plainText = cipher.doFinal(cipherText);

        return new String(plainText, UTF_8);

    }
}