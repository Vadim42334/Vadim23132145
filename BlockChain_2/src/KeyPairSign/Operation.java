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
import java.sql.Struct;
import java.time.Duration;
import KeyPairSign.KeyPairSigns.SignerUser;
import KeyPairSign.KeyPairSigns;


public class Operation {
 
     public static operationData createOperation( PublicKey sender,  PublicKey recipient,  int amount,  byte[] signature, String message)
     {
    	 	
    	 operationData op = new operationData(); 			// создаем объект состоящий из данных класса
    	            op.sender = sender;
    	            op.recipient = recipient;
    	            op.amount = amount;
    	            op.signature = signature; 				// заполняем объект 
    	            op.message = message;
    	            return op;
     }


     public static void verifyOperation(operationData op,  byte[] signature,  SignerUser signer, byte[] message ) 
     throws InvalidKeyException, NoSuchAlgorithmException, SignatureException
     {
    	 System.out.println("----------Verify Operation----------");
    	 System.out.println("SIGN CHECK: "+ KeyPairSigns.verifySignature(signer.getPubKey(), message, signature)); 	// проверка подписи отрпавлителя
    	  
    	 System.out.println("BALANCE CHECK: "+ balanceCheck(op, signer));		// проверка что сумма перевода не превышает баланс отправителя

     }
	 public static boolean balanceCheck(operationData op, SignerUser signer) 
	 {
     if(op.amount <= signer.getBalance()) 
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