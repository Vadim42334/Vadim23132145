package KeyPairSignTest;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class GenKeyPair {
	private static final Charset UTF_8 = StandardCharsets.UTF_8;
	
	public static String getSecretKey() 			 			 // Генерация секретного ключа
    {       
		int keylength_8 = 128; 								

		long keys_28 = pow(2, keylength_8); 				
		String SecretKey = test(keylength_8);
        return SecretKey;
    }

    public static String getPublicKey(String text) 			 // Вывод результатов хеширования
    {       
    	String SHA3 = "SHA3-512"; 
        String SHA1 = "SHA-1"; 		
        byte[] hashResult = Hash.hash(text.getBytes(UTF_8), SHA3);
        String PublicKey = Hash.bytesToHex(hashResult);
        return PublicKey;
    }

	public static long pow(int value, int powValue) 						// ФУНКЦИЯ ВОЗВОДЯЩАЯ В СТЕПЕНЬ
	{
		BigInteger a = new BigInteger(String.valueOf(value));
		return a.pow(powValue).longValue();
	}

	public static String test(int value)									// ФУНКЦИЯ СОЗДАЮЩАЯ КЛЮЧИ
	{
		String otherString = ""; 											// начало
		for (int a = 0; a < 999999999; a++) { 								// Прогоняем цикл максимальное количество раз пока не получим нужный результат
												
			String s = String.format("%x", (int) (Math.random() * 15)); 	// 15 - максимальное число 16 ричной системы

			if (otherString.length() < value) 								// Добавляем рандомные символы в строку пока ее длинна не станет равна  заданной
												
			{
				otherString = otherString + s;
			} else {
				break;
			}
		}
		return otherString;

	}
}
