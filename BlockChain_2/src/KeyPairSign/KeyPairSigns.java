package KeyPairSign;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;


public class KeyPairSigns {
 
    
     public static void main(String args[]) {
            try{/*
                SignerUser signer = new SignerUser ();			 						// Пользователь который будет подписывать сообщение
                String message = "Today was a good day"; 								// Сообщение которое нужно подписать
                                 
                byte[] sign = signData(message.getBytes(), signer.getPrivateKey()); 	// подписываем сообщение
               
                PublicKey pubKey = signer.getPubKey(); 									// получаем публичный ключ для проверки подписи

                System.out.println("----------Example with a valid signature----------"); 
                verifySignature(pubKey, message.getBytes(), sign); 

                System.out.println("----------Example with a invalid signature: the message was changed----------");
                String anotherMessage = "Today I didn't even have to use my AK";
                verifySignature(pubKey, anotherMessage.getBytes(), sign); 						// Проверка подписи с измененным сообщением

                String message2 = "Plus nobody I know got killed in South Central LA";
                SignerUser signerB=new SignerUser ();
                PublicKey pubKey2 = signerB.getPubKey(); 										 
                byte[] sign2 = signData(message2.getBytes(), signerB.getPrivateKey());			// Создаем второго пользователя и пытаемся подписать транзакцию им и его подписью
                
                System.out.println("----------Example with a invalid signature: using signature that does not match with the current message----------");
                verifySignature(pubKey, message.getBytes(), sign2);
                System.out.println("----------Example with a invalid signature: using public key from another user----------");
                verifySignature(pubKey2, message.getBytes(), sign);*/

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static boolean verifySignature(PublicKey publicKey, byte[] message, byte[] signature) throws 		// проверка подписи
        NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature clientSig = Signature.getInstance("DSA");
        clientSig.initVerify(publicKey);
        clientSig.update(message);
        if (clientSig.verify(signature))
        {
           return true;
        } else 
        {
           return false;
        }
    }
    
     public static byte[] signData(byte[] message, PrivateKey privateKey) throws NoSuchAlgorithmException, 	// генерация подписи
        	  InvalidKeyException, SignatureException
     	{
              Signature sig = Signature.getInstance("DSA");
              sig.initSign(privateKey);
              sig.update(message);
              byte[] sign= sig.sign();
              return sign;
        }
    
    
     	public static class SignerUser 											// Создание пользователя подписывающего сообщения
     	{
     		private static int balance;

	        private PublicKey publicKey;
	        private PrivateKey privateKey;
	        public PublicKey getPubKey() {
	              return publicKey;
        }
        
        public SignerUser() throws NoSuchAlgorithmException{ 					// Генерируем ключи для подписующего пользователя
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
            SecureRandom secRan = new SecureRandom();
            kpg.initialize(512, secRan);
            KeyPair keyPair = kpg.generateKeyPair();
            this.publicKey= keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();

        }

        public PublicKey getPublicKey() { 
            return publicKey;
        }

        public void setPublicKey(PublicKey publicKey) {
            this.publicKey = publicKey;
        }

        public PrivateKey getPrivateKey() {
            return privateKey;
        }
	
        public void setPrivateKey(PrivateKey privateKey) {
            this.privateKey = privateKey;
        }
		public static int setBalance(int balance) {
			return SignerUser.balance = balance;
		}
    	public int getBalance() {
 			return this.balance;
 		};
 		
 
    }
}
