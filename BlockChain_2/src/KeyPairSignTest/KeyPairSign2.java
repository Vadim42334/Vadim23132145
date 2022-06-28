package KeyPairSignTest;


public class KeyPairSign2 {

	public static void main(String[] args)
	{
		
		String SecretKey = GenKeyPair.getSecretKey();
		System.out.println("SecretKey: "+ SecretKey);    				// Вывод приватного ключа
		
		String PublicKey = GenKeyPair.getPublicKey(SecretKey);
		System.out.println("PublicKey: " + PublicKey);					// Вывод публичного ключа
		
		String message = "Today I didn't even have to use my AK"; 		// Сообщение которое нужно подписать
		



	}

}
