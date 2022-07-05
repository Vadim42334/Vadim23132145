package BlockChain;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.sql.Struct;
import java.time.Duration;


public class Operation {
	  public static boolean verifySignature(operationData op ) throws 		// проверка подписи
	  NoSuchAlgorithmException, InvalidKeyException, SignatureException 
	  {
		    Signature clientSig = Signature.getInstance("DSA");
		    clientSig.initVerify(op.sender.getPublicKey());
		    clientSig.update(op.message.getBytes());
		    if (clientSig.verify(op.signature))
		    {
		       return true;
		    } else 
		    {
		       return false;
		    }
	 }
	
     public static operationData createOperation( SignerUser sender,  SignerUser recipient,  int amount,  byte[] signature, String message)
     {
    	 	
    	 operationData op = new operationData(); 			// создаем объект состоящий из данных класса
    	            op.sender = sender;
    	            op.recipient = recipient;
    	            op.amount = amount;
    	            op.signature = signature; 				
    	            op.message = message;
    	            return op;
     }

     
     public static boolean verifyOperationB(operationData op)  // функция для проверки операций при добавлении блока в блокчейн, перевод средств в ней не происходит
     throws InvalidKeyException, NoSuchAlgorithmException, SignatureException
     {
    	 if((verifySignature(op) == true) &&( balanceCheck(op) == true)) 
    	 {
    		 return true;
    	 }else {return false;}
     }
     public static boolean verifyOperation(operationData op) 
     throws InvalidKeyException, NoSuchAlgorithmException, SignatureException
     {
    	 if((verifySignature(op) == true) &&( balanceCheck(op) == true)) 
    	 {
 	    	 op.sender.setBalance(op.sender.getBalance() - op.amount); 
 	    	 op.recipient.setBalance(op.recipient.getBalance() + op.amount); // отнимаем монеты у отправителя и добавляем получателю*/
    		 return true;
    	 }else {return false;}
     }
	 public static boolean balanceCheck(operationData op) 
	 {
     if(op.amount <= op.sender.getBalance()) 
     	{
   	  		return true;
     	}
     	return false;
	 }
     public static void printOperation(operationData op )  // вывод всех обьектов операции
     {
    	 System.out.println("Sender: " + op.sender);
    	 System.out.println("Recipier: " + op.recipient);
    	 System.out.println("Amount of coins: " + op.amount);
    	 System.out.println("Signature: " + op.signature);
    	 System.out.println("Message: " + op.message);
     }

}